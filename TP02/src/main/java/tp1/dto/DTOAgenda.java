package tp1.dto;

import java.util.Date;

/**
 * Created by faizaaitamara on 22/09/2016.
 */
public class DTOAgenda {

    private String titre;
    private String description;
    private String debut;
    private String fin;

    public DTOAgenda(String titre, String description, String debut, String fin) {
        this.titre = titre;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
    }

    public DTOAgenda() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
