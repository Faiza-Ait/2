package tp1.serveur;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

/**
 * Created by faizaaitamara on 29/09/2016.
 */
public class Serveur {

    private Agenda agenda;
    private Evenement evenement;
    private MutablePicoContainer pico;

    public Serveur(Agenda agenda, Evenement evenement) {
        this.agenda = agenda;
        this.evenement = evenement;
        //Ajout objets dans le container
        pico.addComponent(this.agenda);
        pico.addComponent(this.evenement);


    }
}
