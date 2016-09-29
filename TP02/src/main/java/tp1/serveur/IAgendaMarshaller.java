package tp1.serveur;

import java.io.*;

/**
 * Created by ecoquery on 19/09/2016.
 */
public interface IAgendaMarshaller {

    /**
     * Writes an agenda to an output stream
     *
     * @param agenda the agenda to write
     * @param output the output stream to write the agenda to
     * @throws IOException in case of IO problem
     */
    void marshall(Agenda agenda, Writer output) throws IOException;

    /**
     * Reads an agenda from a stream
     *
     * @param input the stream to read from
     * @return the agenda read
     * @throws IOException if the agenda cannot be read from the stream
     */
    Agenda unmarshall(Reader input) throws IOException;
}
