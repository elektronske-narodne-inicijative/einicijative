package rs.gov.mduls.einicijative.niapi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("dbParametri")
public class DbParametri {

    @Autowired
    private NiDatabaseApi dbApi;

    Map<String,String> parametri = null;

    public String vrednost(String imeParametra) {
        if (parametri == null) {
            parametri = dbApi.dajParametre();
        }
        return parametri.get(imeParametra);
    }
}
