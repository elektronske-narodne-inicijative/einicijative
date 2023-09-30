package rs.gov.mduls.einicijative.niapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class NadzorniTrag {
    private static final Logger syslogLogger = LoggerFactory.getLogger("rs.gov.mduls.einicijative.niapi.nadzornitrag");

    public static void sqlIzuzetak(DataAccessException e)
    {
        if( e.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            greska(sqlEx.getSQLState(), sqlEx.getMessage());
        } else {
            greska(e.getRootCause().getClass().getSimpleName(),e.getMessage());
        }
    }

    public static void izuzetak(Exception e)
    {
        greska(e.getClass().getSimpleName(),e.getMessage());
    }

    public static void greska(String kod, String poruka)
    {
        syslogLogger.error("NIAPI-GRESKA-[{}] {}",kod,poruka);
    }

    public static void prijava(String tip, String korisnik) {
        syslogLogger.info("NIAPI-PRIJAVA({}):{}", tip, korisnik);
    }
    public static void poruka(String msg) {
        syslogLogger.info(msg);
    }

}
