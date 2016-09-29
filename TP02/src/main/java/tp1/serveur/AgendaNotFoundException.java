package tp1.serveur;

/**
 * Created by ecoquery on 18/09/2016.
 */
public class AgendaNotFoundException extends Exception {
    
    public AgendaNotFoundException() {
        super();
    }

    public AgendaNotFoundException(String message) {
        super(message);
    }

    public AgendaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgendaNotFoundException(Throwable cause) {
        super(cause);
    }
}
