package rs.gov.mduls.einicijative.niapi.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import rs.gov.mduls.einicijative.niapi.db.DbParametri;
import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;

@Component("pdfUsluge")
public class PdfUsluge {
    private static final Logger logger = LoggerFactory.getLogger(PdfUsluge.class);

    @Value("${niapi.sertifikat.lozinka}")
    String lozinkaSertifikata;
    @Value("${niapi.sertifikat.ko-overava}")
    String koOverava;
    @Value("${niapi.sertifikat.gde-overava}")
    String gdeOverava;
    @Value("${niapi.sertifikat.zasto-overava}")
    String zastoOverava;

    @Autowired
    DbParametri dbParametri;

    private PdfOvera pdfOvera = null;
    private KeyStore sertifikat = null;

    private KeyStore dajSertifikat() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        if (this.sertifikat == null) {
            logger.info("Inicijalizujem sertifikat za potpisivanje...");
            this.sertifikat = KeyStore.getInstance("PKCS12");
            this.sertifikat.load(
                    new ByteArrayInputStream(
                            Base64.decodeBase64(
                                    dbParametri.vrednost(Consts.DB_KEYSTORE_PARAM_NAME)
                            )
                    ),
                    this.lozinkaSertifikata.toCharArray()
            );
            logger.info("Uspešno inicijalizovan sertifikat za potpisivanje.");
        }
        return this.sertifikat;
    }

    public void overiPdf(PDDocument pdfDokument, OutputStream izlaz) {
        if (pdfOvera == null) {
            try {
                logger.info("Inicijalizujem overivač dokumenata...");
                KeyStore tmp = dajSertifikat();
                pdfOvera = new PdfOvera(tmp,lozinkaSertifikata.toCharArray());
                logger.info("Overivač inicijalizovan.");
            } catch (Exception e) {
                NadzorniTrag.izuzetak(e);
                throw new RuntimeException(e);
            } catch (Throwable t) {
                logger.error("Problem u inicijalizaciji overivača", t);
                throw new RuntimeException(t);
            }
        }
        try {
            logger.info("Overavam dokument...");
            pdfOvera.overiPdf(pdfDokument, izlaz, this.koOverava, this.gdeOverava, this.zastoOverava);
            logger.info("Dokument overen.");
        } catch (Exception e) {
            logger.error("Problem u overi dokumenta", e);
            NadzorniTrag.izuzetak(e);
            throw new RuntimeException(e);
        }
    }

    public String overiPdfZaKorisnika(PDDocument pdfDokument) {
        ByteArrayOutputStream izlazniNiz = new ByteArrayOutputStream();
        overiPdf(pdfDokument, izlazniNiz);
        return Base64.encodeBase64String(izlazniNiz.toByteArray());
    }


    public String pripremiPotvrduOPotpisu(int idInicijative, NiDatabaseApi.PtpPotpis potpis) {
        String tmpName = String.format("%s/%s.tmp",System.getProperty("java.io.tmpdir"), potpis.idPotpisa().toString());
        try {
            SimpleDateFormat format = new SimpleDateFormat("");
            PDDocument potvrda = new PDDocument();
            PDPage strana = new PDPage(new PDRectangle(PDRectangle.A6.getHeight(), PDRectangle.A6.getWidth()));
            potvrda.addPage( strana );
            PDPageContentStream sadrzaj = new PDPageContentStream(potvrda, strana);
            sadrzaj.beginText();
            Resource arialSaCirilicom = new ClassPathResource("arial.ttf");
            PDFont font = PDType0Font.load(potvrda, arialSaCirilicom.getInputStream(), false);
            sadrzaj.setFont(font, 14);
            sadrzaj.setLeading(14.5f);

            sadrzaj.newLineAtOffset(20, strana.getCropBox().getUpperRightY()-20);
            sadrzaj.showText("Електронске Народне Иницијативе");
            sadrzaj.newLine();
            sadrzaj.newLine();
            sadrzaj.setFont(font, 10);
            sadrzaj.showText(String.format("Народна иницијатива %d: %s",idInicijative, potpis.nazivInicijative()));
            sadrzaj.newLine();
            sadrzaj.showText(String.format("Идентификатор потписа: %s",potpis.idPotpisa().toString()));
            sadrzaj.newLine();
            sadrzaj.showText(String.format("Датум и време потписа: %s",Consts.FORMAT_DATUMA_I_VREMENA.format(potpis.trnZavodjenjaPotpisa())));
            sadrzaj.endText();
            sadrzaj.close();
            potvrda.save(tmpName);
            potvrda.close();
            // sad pročitaj iz fajla da bi se povezalo sa fajlom za potrebe potpisivanja
            potvrda = Loader.loadPDF(new File(tmpName));
            // overi i konvertuj u base64 za slanje potpisniku
            String b64potvrda = overiPdfZaKorisnika(potvrda);
            Files.deleteIfExists(Path.of(tmpName));
            return b64potvrda;
        } catch (IOException e) {
            NadzorniTrag.izuzetak(e);
            throw new RuntimeException(e);
        }
    }

}
