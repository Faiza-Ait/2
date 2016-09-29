package tp1.serveur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by ecoquery on 19/09/2016.
 */
public class XMLAgendaDAO implements IAgendaDAO, IAgendaMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(XMLAgendaDAO.class);

    private File directory;
    private JAXBContext jaxbC;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XMLAgendaDAO(File directory) throws JAXBException, IOException {
        this.directory = directory;
        if (directory.exists() && !directory.isDirectory()) {
            throw new IOException(directory + " devrait être une répertoire");
        } else if (!directory.exists()) {
            boolean success = directory.mkdirs();
            if (!success) {
                throw new IOException("Echec lors de la création du répertoire " + directory);
            }
        }
        this.jaxbC = JAXBContext.newInstance(Agenda.class, Evenement.class);
        this.marshaller = this.jaxbC.createMarshaller();
        this.unmarshaller = this.jaxbC.createUnmarshaller();
    }

    private File fileFromNomAgenda(String nomAgenda) {
        return new File(directory, nomAgenda + ".xml");
    }

    @Override
    public void saveAgenda(Agenda agenda) {
        File outputFile = fileFromNomAgenda(agenda.getNom());
        try (FileWriter fw = new FileWriter(outputFile)) {
            marshall(agenda, fw);
            fw.flush();
        } catch (IOException e) {
            LOG.error("Erreur lors de l'enregistrement de l'agenda " + agenda.getNom(), e);
        }
    }

    @Override
    public void deleteAgenda(Agenda agenda) {
        File toDelete = fileFromNomAgenda(agenda.getNom());
        if (toDelete.exists()) {
            boolean deleted = toDelete.delete();
            if (!deleted) {
                LOG.error("Le fichier "+toDelete+" n'a pas pu être supprimé");
            }
        }
    }

    @Override
    public Agenda loadAgenda(String nomAgenda) throws AgendaNotFoundException {
        File inputFile = fileFromNomAgenda(nomAgenda);
        if (inputFile.canRead()) {
            try (FileReader fr = new FileReader(inputFile)) {
                return unmarshall(fr);
            } catch (IOException e) {
                throw new AgendaNotFoundException(e);
            }
        } else {
            throw new AgendaNotFoundException("Le fichier " + inputFile + " ne peut être lu");
        }
    }

    @Override
    public void saveEvenement(Evenement evenement, Agenda agenda) {
        if (! agenda.getEvenements().contains(evenement)) {
            agenda.getEvenements().add(evenement);
        }
        saveAgenda(agenda);
    }

    @Override
    public void deleteEvenement(Evenement evenement, Agenda agenda) {
        agenda.getEvenements().remove(evenement);
        saveAgenda(agenda);
    }

    @Override
    public void marshall(Agenda agenda, Writer output) throws IOException {
        try {
            marshaller.marshal(agenda, output);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Agenda unmarshall(Reader input) throws IOException {
        try {
            return (Agenda) unmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }
}
