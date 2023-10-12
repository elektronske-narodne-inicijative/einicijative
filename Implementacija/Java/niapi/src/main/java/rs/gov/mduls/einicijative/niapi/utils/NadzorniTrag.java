package rs.gov.mduls.einicijative.niapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.UUID;

public class NadzorniTrag {
    private static final Logger syslogLogger = LoggerFactory.getLogger("rs.gov.mduls.einicijative.niapi.nadzornitrag");

    public static void sqlIzuzetak(DataAccessException e)
    {
        if( e.getRootCause() != null && e.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            greska(sqlEx.getSQLState(), sqlEx.getMessage());
        } else if(e.getRootCause() != null) {
            greska(e.getRootCause().getClass().getSimpleName(),e.getMessage());
        } else {
            izuzetak(e);
        }
    }

    public static void izuzetak(Exception e)
    {
        greska(e.getClass().getSimpleName(),e.getMessage());
    }

    public static void greska(String kod, String poruka)
    {
        String porukaDoKrajaLinije = poruka;
        if (poruka != null && poruka.indexOf("\n") != -1) {
            porukaDoKrajaLinije = poruka.substring(0,poruka.indexOf("\n"));
        }
        syslogLogger.error("NIAPI-GRESKA-[{}] {}",kod,porukaDoKrajaLinije);
    }

    public static void prijava(String tip, String korisnik) {
        syslogLogger.info("NIAPI-PRIJAVA({}):{}", tip, korisnik);
    }

    public static void potpis(int idInicijative, UUID potpis) {
        syslogLogger.info("NIAPI-POTPIS({}):{}", idInicijative, potpis);
    }
    public static void poruka(String msg) {
        syslogLogger.info(msg);
    }

}
