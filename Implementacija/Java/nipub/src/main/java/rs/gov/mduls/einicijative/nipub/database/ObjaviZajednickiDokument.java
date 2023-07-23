package rs.gov.mduls.einicijative.nipub.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rs.gov.mduls.einicijative.nipub.util.PomocneFunkcijeZaDatoteke;

import javax.sql.DataSource;
import java.io.IOException;

@Component
public class ObjaviZajednickiDokument {

    @Autowired
    DataSource niDataSource;

    private String dajDokument(String semaFunc, String imeFunc) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
        String sql = String.format("SELECT %s.%s()",semaFunc, imeFunc);
        return (String) jdbcTemplate.queryForObject(sql, String.class);
    }

    public void obrada(String semaFunc, String imeFunc, String dirDat, String imeDat) throws IOException {
        String dokument = dajDokument(semaFunc, imeFunc);
        if (dokument == null)
            dokument = "";
        PomocneFunkcijeZaDatoteke.upisiZajednickiDokument(dirDat, imeDat, dokument);
    }
}
