package tp1.client;

import tp1.dto.DTOAgenda;
import tp1.serveur.Agenda;

import java.io.BufferedReader;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;

/**
 * Created by faizaaitamara on 22/09/2016.
 */
public class AgendaUI {

    private static final Logger LOG = LoggerFactory.getLogger(Agenda.class);

    Agenda agenda;
    public AgendaUI(Agenda agenda) {
        this.agenda = agenda;
    }

    public void  affichage (String defaultDirectory) {
        agenda = new Agenda("test", new File(defaultDirectory));
        DTOAgenda d = new DTOAgenda();

        for (;;) {
            menu();
            int c = new String (readLine()).charAt(0) - '0';
            switch (c){
                case 1:
                    try {
                        d = read();
                    } catch (Exception e) {
                        LOG.error("Erreur dans le menu Ajouter", e);
                    }

                    agenda.addEvenement(d);
                    break;

                case 2:
                    try {
                        d = read();
                    } catch (Exception e) {
                        LOG.error("Erreur dans le menu Supprimer", e);
                    }

                    agenda.removeEvenement(d);
                    break;

                case 3:
                    System.out.println("Evenements dans l'agenda :\n");
                    System.out.println(agenda.getInfos());
                    break;

                case 4:
                    agenda.synchronizeEvenements();
                    System.out.println("Liste des evenements reinitialisee par rapport au DAO.\n\n");
                    break;

                case 5:
                    return;
            }
        }
    }

    public static void menu() {
        System.out.println("Menu\n\n");
        System.out.println("1)\tAjouter un evenement\n");
        System.out.println("2)\tSupprimer un evenement\n");
        System.out.println("3)\tLister les evenements\n");
        System.out.println("4)\tReinitialiser l'agenda\n");
        System.out.println("5)\tQuitter\n");
    }

    //	 ---------------------------------------------
//   Code trouve a : http://www.wellho.net/resources/ex.php4?item=j703/WellHouseInput.java
    public static String readLine() {
        BufferedReader standard = new BufferedReader(new InputStreamReader(System.in));
        try{
            String inline = standard.readLine();
            return inline;
        } catch (Exception e) {
            return ("data entry error");
        }
    }

    public DTOAgenda read (){

        String titre = null;
        String debut = null;
        String fin = null;
        String desc = null;

        System.out.print("Titre : ");
        titre = readLine();
        System.out.print("Description : ");
        desc = readLine();
        System.out.print("Debut : ");
        debut = readLine();
        System.out.print("Fin : ");
        fin = readLine();

        return new DTOAgenda(titre, desc, debut, fin);
    }
}
