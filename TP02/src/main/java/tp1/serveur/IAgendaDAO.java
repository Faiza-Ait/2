package tp1.serveur;


/**
 * Created by ecoquery on 18/09/2016.
 */
public interface IAgendaDAO {

    /**
     * Sauve un agenda dans le support de persitance.
     * @param agenda l'agenda à sauver.
     */
    void saveAgenda(Agenda agenda);

    /**
     * Supprime un agenda dans le support de persitance.
     * @param agenda l'agenda à sauver.
     */
    void deleteAgenda(Agenda agenda);

    /**
     * Charge un agenda depuis le support de persistance.
     * @param nomAgenda le nom de l'agenda à charger.
     * @return l'agenda chargé.
     * @throws AgendaNotFoundException si l'agenda n'a pas été trouvé
     */
    Agenda loadAgenda(String nomAgenda) throws AgendaNotFoundException;

    /**
     * Sauve un evenement d'un agenda dans le support de persitance.
     * @param evenement l'evenement à sauver.
     * @param agenda l'agenda dans lequel se trouve l'evenement.
     */
    void saveEvenement(Evenement evenement, Agenda agenda);

    /**
     * Supprime un evenement d'un agenda dans le support de persitance.
     * @param evenement l'evenement à sauver.
     * @param agenda l'agenda dans lequel se trouve l'evenement.
     */
    void deleteEvenement(Evenement evenement, Agenda agenda);
}
