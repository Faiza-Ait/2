package tp1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Created by Lionel on 21/09/2016.
 */
public class Config {
    public static final Properties defaultCfg = new Properties();
    public static final String DATE_FORMAT = "date_format";

    static {
        defaultCfg.setProperty(DATE_FORMAT,"EEE MMM dd kk:mm:ss zzz yyyy");
    }

}
