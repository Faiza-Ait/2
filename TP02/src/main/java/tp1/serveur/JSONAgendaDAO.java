package tp1.serveur;

import java.io.*;

/**
 * Created by ecoquery on 19/09/2016.
 */
public class JSONAgendaDAO implements IAgendaDAO, IAgendaMarshaller {

    private File directory;

    public JSONAgendaDAO(File directory) {
        this.directory = directory;
    }

    @Override
    public void marshall(Agenda agenda, Writer output) throws IOException {
        //TODO: à implémenter

    }

    @Override
    public Agenda unmarshall(Reader input) throws IOException {
        //TODO: à implémenter
        return null;
    }

    @Override
    public void saveAgenda(Agenda agenda) {
        //TODO: à implémenter
    }

    @Override
    public void deleteAgenda(Agenda agenda) {
        //TODO: à implémenter
    }

    @Override
    public Agenda loadAgenda(String nomAgenda) throws AgendaNotFoundException {
        //TODO: à implémenter
        return null;
    }

    @Override
    public void saveEvenement(Evenement evenement, Agenda agenda) {
        //TODO: à implémenter
    }

    @Override
    public void deleteEvenement(Evenement evenement, Agenda agenda) {
        //TODO: à implémenter
    }
}
