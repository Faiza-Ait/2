package tp1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp1.client.AgendaUI;
import tp1.serveur.Agenda;

/**
 * Created by faizaaitamara on 22/09/2016.
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(AgendaUI.class);
    private static final String defaultDirectory;

    static {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            defaultDirectory = "/Users/faizaaitamara/Desktop/Cours/Intergiciels/OutputTP2";
        } else {
            defaultDirectory = "/Users/faizaaitamara/Desktop/Cours/Intergiciels/OutputTP2";
        }
    }

    public static void  main (String args[]) {

        Agenda agenda = new Agenda();
        AgendaUI agendaUI = new AgendaUI(agenda);
        agendaUI.affichage(defaultDirectory);
    }


}
