package fr.univlyon1.tiw.tiw1.agenda.modele;

import org.junit.Test;
import tp1.serveur.Agenda;
import tp1.serveur.Evenement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ecoquery on 18/09/2016.
 */
public class AgendaTest {

    @Test
    public void testAddedToCollection() {
        Agenda agenda = TestAgendaBuilder.buildAgenda("tiw");
        Evenement evt = TestAgendaBuilder.ajouteCMJava(agenda);
        assertNotNull(evt);
        assertTrue(agenda.getEvenements().contains(evt));
    }

    @Test
    public void testSynchroDAO() {
        Agenda agenda = TestAgendaBuilder.buildAgenda("test-synchro");
        Evenement evt = TestAgendaBuilder.ajouteCMJava(agenda);
        agenda.getEvenements().remove(evt);
        agenda.synchronizeEvenements();
        assertTrue(agenda.getEvenements().contains(evt));
    }
}
