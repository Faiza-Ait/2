package fr.univlyon1.tiw.tiw1.agenda.dao;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by ecoquery on 20/09/2016.
 */
public class JPAAgendaDAOTest {

    @Test @Ignore // Pour ne pas faire échouer les tests si JPA n'est pas bien mis en place
    public void emSetupTest() {
        EntityManager em = Persistence.createEntityManagerFactory("pu-test").createEntityManager();
        em.close();
    }
}
