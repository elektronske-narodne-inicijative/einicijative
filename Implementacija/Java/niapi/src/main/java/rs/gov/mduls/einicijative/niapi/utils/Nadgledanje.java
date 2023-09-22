package rs.gov.mduls.einicijative.niapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class Nadgledanje {
    private static final Logger syslogLogger = LoggerFactory.getLogger("rs.gov.mduls.einicijative.niapi.nadgledanje");

    public static void greska(String kod, String poruka)
    {
        syslogLogger.error("NIAPI-GRESKA-[{}] {}",kod,poruka);
    }

    public static void apiPoziv(String imeMetode, long trajanjeMs) {
        syslogLogger.info("NIAPI-USPEH:{} ({}ms)",imeMetode, trajanjeMs);
    }

    public static void poruka(String msg) {
        syslogLogger.info(msg);
    }

}
