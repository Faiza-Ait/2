package fr.univlyon1.tiw.tiw1.agenda.dao;

import fr.univlyon1.tiw.tiw1.agenda.modele.TestAgendaBuilder;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import tp1.serveur.Agenda;
import tp1.serveur.JSONAgendaDAO;

import java.io.*;

/**
 * Created by ecoquery on 20/09/2016.
 */
public class JSONAgendaDAOTest {

    private static final Schema schema = null;
    private Agenda agenda1;
    private JSONAgendaDAO jDao;

    @BeforeClass
    public static void setupBeforeClass() throws IOException {
        try (InputStream inputStream = JSONAgendaDAO.class.getResourceAsStream("/ressources/agenda-schema.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
        }
    }

    @Before
    public void setup() {
        agenda1 = TestAgendaBuilder.agenda1();
        jDao = new JSONAgendaDAO(new File("target/test-data")); // FIXME: adapter éventuellement la construction du DAO
    }

    @Test @Ignore // FIXME: Supprimer @Ignore une fois la classe JSONAgendaDAO complétée
    public void testJSONSchema() throws IOException {
        StringWriter sw = new StringWriter();
        jDao.marshall(agenda1,sw);
        String json = sw.toString();
        schema.validate(new JSONObject(json));
    }
}
