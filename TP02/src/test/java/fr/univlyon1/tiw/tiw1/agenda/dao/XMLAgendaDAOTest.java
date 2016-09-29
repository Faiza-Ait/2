package fr.univlyon1.tiw.tiw1.agenda.dao;

import tp1.serveur.Agenda;
import tp1.serveur.Evenement;
import fr.univlyon1.tiw.tiw1.agenda.modele.TestAgendaBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import tp1.serveur.AgendaNotFoundException;
import tp1.serveur.XMLAgendaDAO;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by ecoquery on 20/09/2016.
 */
public class XMLAgendaDAOTest {

    private final static Logger LOG = LoggerFactory.getLogger(XMLAgendaDAOTest.class);

    private Agenda agenda;
    private XMLAgendaDAO xDao;

    private static Schema schema;

    @BeforeClass
    public static void setupClass() throws SAXException {
        schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new StreamSource(XMLAgendaDAO.class.getResourceAsStream("/ressources/agenda-schema.xsd")));
    }

    @Before
    public void setup() throws JAXBException, IOException {
        agenda = TestAgendaBuilder.agenda1();
        xDao = new XMLAgendaDAO(new File("target/test-data"));
    }

    @Test
    public void testSchema() throws IOException {
        Validator validator = schema.newValidator();
        StringWriter sw = new StringWriter();
        xDao.marshall(agenda, sw);
        StringReader sr = new StringReader(sw.toString());
        StreamSource ss = new StreamSource(sr);
        try {
            validator.validate(ss);
        } catch (SAXException e) {
            fail("La validation du xml produit a échoué: " + e.getMessage());
        }
    }

    @Test
    public void testExportImport() throws AgendaNotFoundException {
        xDao.saveAgenda(agenda);
        Agenda agenda2 = xDao.loadAgenda(agenda.getNom());
        assertEquals(agenda.getNom(), agenda2.getNom());
        for (Evenement evt : agenda.getEvenements()) {
            assertTrue("Evenement " + evt.getId() + " non trouvé dans l'agenda serialisé",
                    agenda2.getEvenements().contains(evt));
        }
        for (Evenement evt : agenda2.getEvenements()) {
            assertTrue("Evenement " + evt.getId() + " non présent dans l'agenda initial",
                    agenda.getEvenements().contains(evt));
        }
    }


    @Test
    public void testAjouteEvenement() throws AgendaNotFoundException {
        xDao.saveAgenda(agenda);
        String id1 = agenda.getEvenements().iterator().next().getId();
        LOG.debug("Evenement dans agenda: {}", id1);
        Evenement evt = TestAgendaBuilder.ajouteTPJava(agenda);
        Iterator<Evenement> it2 = agenda.getEvenements().iterator();
        it2.next();
        String id2 = it2.next().getId();
        LOG.debug("Nouvel evenement dans agenda: {}", id2);
        assertNotEquals(id1, id2);
        xDao.saveEvenement(evt, agenda);
        Agenda agenda2 = xDao.loadAgenda(agenda.getNom());
        assertEquals(2, agenda2.getEvenements().size());
        assertTrue("Nouvel événement manquant", agenda2.getEvenements().contains(evt));
    }

}
