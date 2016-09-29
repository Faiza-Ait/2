package fr.univlyon1.tiw.tiw1.agenda.modele;

import tp1.dto.DTOAgenda;
import tp1.serveur.Agenda;
import tp1.serveur.Evenement;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by ecoquery on 20/09/2016.
 */
public class TestAgendaBuilder {

    //TODO Mettre à jour cette valeur avant de lancer les tests
    private static final String storageDirectoryName;

    static {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            storageDirectoryName = "/Users/faizaaitamara/Desktop/Cours/Intergiciels/OutputTP2";
            //storageDirectoryName = "/C:/temp/";
        } else {
            storageDirectoryName = "/tmp";
        }
    }


    public static Agenda buildAgenda(String nom) {
        Agenda agenda = new Agenda(nom, new File(storageDirectoryName));
        return agenda;
    }

    public static Agenda agenda1() {
        Agenda agenda = buildAgenda("agenda1");
        ajouteCMJava(agenda);
        return agenda;
    }


    public static Evenement ajouteCMJava(Agenda agenda) {
        Calendar d = GregorianCalendar.getInstance();
        d.set(2016, 9, 21, 8, 0);
        Calendar f = (Calendar) d.clone();
        f.set(Calendar.HOUR, 9);
        f.set(Calendar.MINUTE, 30);
        DTOAgenda dtoAgenda = new DTOAgenda("CM1 TIW1", "Introduction", d.getTime().toString(), f.getTime().toString());
        Evenement evt = agenda.addEvenement(dtoAgenda);
        return evt;
    }

    public static Evenement ajouteTPJava(Agenda agenda) {
        Calendar d = GregorianCalendar.getInstance();
        d.set(2016, 9, 21, 9, 45);
        Calendar f = (Calendar) d.clone();
        f.set(Calendar.HOUR, 11);
        f.set(Calendar.MINUTE, 15);
        DTOAgenda dtoAgenda = new DTOAgenda("TP1 TIW1", "Révision Java", d.getTime().toString(), f.getTime().toString());
        Evenement evt = agenda.addEvenement(dtoAgenda);
        return evt;
    }
}
