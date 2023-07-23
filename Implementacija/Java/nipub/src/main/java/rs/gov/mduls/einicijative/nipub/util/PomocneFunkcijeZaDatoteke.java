package rs.gov.mduls.einicijative.nipub.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PomocneFunkcijeZaDatoteke {

    private static final Logger logger = LoggerFactory.getLogger(PomocneFunkcijeZaDatoteke.class);

    public static void upisiZajednickiDokument(String dirDat, String imeDat, String sadrzaj) throws IOException {
        FileWriter pisiUDat = null;
        File dir = new File (dirDat);
        if (dir.isDirectory()) {
            File dat = new File (dir, imeDat);
            dat.createNewFile();
            pisiUDat = new FileWriter(dat);
            pisiUDat.write(sadrzaj);
            pisiUDat.close();
            logger.info("Објављен заједнички документ '{}/{}'.}",dirDat,imeDat);
        }
    }

    public static void upisiDokumentInicijative(long idInicijative, String dirDat, String modelImenaDat, String sadrzaj) throws IOException {
        FileWriter pisiUDat = null;
        String ceoDirDat = String.format("%s/%02d",dirDat, idInicijative % 100);
        String imeFajla = String.format(modelImenaDat, idInicijative);
        File dir = new File (ceoDirDat);
        // ako već nije napravljen
        if (!dir.isDirectory()) {
            dir.mkdirs(); // kreiraj ako već ne postoji
        }
        if (dir.isDirectory()) {
            File dat = new File (dir, imeFajla);
            dat.createNewFile(); // kreiraj ako već ne postoji
            pisiUDat = new FileWriter(dat);
            pisiUDat.write(sadrzaj);
            pisiUDat.close();
            logger.info("Објављен документ иницијативе '{}/{}'.}",ceoDirDat,imeFajla);
        } else {
            throw new IOException("Није могуће направити тражену путању до датотеке " + ceoDirDat);
        }
    }
}
