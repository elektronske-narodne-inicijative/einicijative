package rs.gov.mduls.einicijative.nipub.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rs.gov.mduls.einicijative.nipub.util.PomocneFunkcijeZaDatoteke;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ObjaviSkoroPromenjeneInicijative {

    private static final Logger logger = LoggerFactory.getLogger(ObjaviSkoroPromenjeneInicijative.class);

    @Autowired
    DataSource niDataSource;

    public void obrada(String semaFunc, String imeFunc, String dirDat, String modelImenaDat) throws IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
        String sql = String.format("SELECT idInicijative, jsonDokument FROM %s.%s()",semaFunc, imeFunc);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        logger.info("Број скоро промењених иницијатива: {}",rows.size());
        for (Map row : rows) {
            PomocneFunkcijeZaDatoteke.upisiDokumentInicijative(
                    ((Integer) row.get("idinicijative")).longValue(),
                    dirDat,
                    modelImenaDat,
                    ((String) row.get("jsondokument"))
            );
        }
    }
}
