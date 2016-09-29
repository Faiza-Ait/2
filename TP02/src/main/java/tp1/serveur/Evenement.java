package tp1.serveur;

import tp1.Config;
import tp1.dto.DTOAgenda;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ecoquery on 18/09/2016.
 */

@XmlType(propOrder = {"titre", "debut", "fin", "description"})
public class Evenement {

    private String titre;
    private String description;
    private Date debut;
    private Date fin;
    private String id;

    public Evenement() {
    }

    public Evenement(DTOAgenda dtoAgenda, String id) {
        this.titre = dtoAgenda.getTitre();
        this.description = dtoAgenda.getDescription();
        try {
            this.debut = parseDate(dtoAgenda.getDebut());
            this.fin = parseDate(dtoAgenda.getFin());
        }catch (ParseException e){
            this.debut = new Date();
            this.fin = this.debut;
        }
        this.id = id;
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String formatDate(Date d) {
        return new SimpleDateFormat(Config.defaultCfg.getProperty(Config.DATE_FORMAT)).format(d);
    }

    private static Date parseDate(String s) throws ParseException {
        return new SimpleDateFormat(Config.defaultCfg.getProperty(Config.DATE_FORMAT)).parse(s);
    }

    public String getInfos() {
        String infos = "Titre : " + getTitre();
        infos += "\nDescription : " + getDescription();
        infos += "\nDebut : " + formatDate(getDebut());
        infos += "\nFin : " + formatDate(getFin());
        return infos;
    }

    // Pour la comparaison dans la suppression
    //On approxime les dates à la précision du format près, ça suffit bien...
    @Override
    public boolean equals(Object o) {
        Evenement e = (Evenement) o;
        return this.titre.equals(e.titre) && this.description.equals(e.description)
                && formatDate(this.debut).equals(formatDate(e.debut)) && formatDate(this.fin).equals(formatDate(e.fin));
    }


}
