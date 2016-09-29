package tp1.serveur;

import org.picocontainer.Startable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp1.dto.DTOAgenda;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by faizaaitamara on 22/09/2016.
 */
@XmlRootElement(name = "agenda")
@XmlType(propOrder = {"nom", "evenements"})
public class Agenda implements Startable {

    private static final Logger LOG = LoggerFactory.getLogger(Agenda.class);

    @XmlElement
    private String nom;
    private IAgendaDAO dao;
    private Collection<Evenement> evenements;

    public Agenda() {
    }

    public Agenda(String nom, File daoDirectory) {
        this.nom = nom;
        try {
            this.dao = new XMLAgendaDAO(daoDirectory);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.evenements = new ArrayList<>();
    }

    @Override
    public void start() {
        this.LOG.info("Info : ","Composant Agenda démarré !"+"Objet d'accès aux données : ");

    }

    @Override
    public void stop() {

    }

    @XmlElement(name="evenement")
    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Evenement addEvenement(DTOAgenda dtoAgenda) {
        UUID uuid = UUID.randomUUID();
        Evenement evt = new Evenement(dtoAgenda, uuid.toString());
        evenements.add(evt);
        // insertion dans le support de persistance
        try {
            dao.saveEvenement(evt, this);
            LOG.debug("DAO appelé pour ajout");
        } catch (Exception e) {
            LOG.error("Erreur lors de l'ajout d'événement", e);
        }
        return evt;
    }

    public void removeEvenement(DTOAgenda dtoAgenda) {
        Evenement evenement = new Evenement(dtoAgenda, null);

        // Suppression dans la liste
        for (Iterator<Evenement> i = evenements.iterator(); i.hasNext(); ) {
            Evenement temp = i.next();
            if (temp.equals(evenement)) {
                evenements.remove(temp);
                // Suppression dans le support de persistance
                try {
                    dao.deleteEvenement(temp, this);
                    LOG.debug("DAO appelé pour suppression");
                } catch (Exception e) {
                    LOG.error("Erreur lors de la suppression d'événement", e);
                }
                removeEvenement(dtoAgenda);
                return;
            }
        }
    }

    public void synchronizeEvenements() {
        try {
            Agenda tmp = dao.loadAgenda(this.nom);
            this.setEvenements(tmp.getEvenements());
        } catch (AgendaNotFoundException e) {
            LOG.error("Erreur lors du chargement d'agenda",e );
        }
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode destinée à l'affichage
     *
     * @return une string formattée contentant toutes les infos des événements de l'agenda
     */
    public String getInfos() {
        String infos = "";
        for (Iterator<Evenement> i = evenements.iterator(); i.hasNext(); ) {
            Evenement temp = i.next();
            infos += temp.getInfos() + "\n";
        }
        return infos;
    }


}


