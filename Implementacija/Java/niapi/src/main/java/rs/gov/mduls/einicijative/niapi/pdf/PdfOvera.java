package rs.gov.mduls.einicijative.niapi.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;

import org.apache.pdfbox.examples.signature.CreateSignatureBase;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

public class PdfOvera extends CreateSignatureBase
{
    public PdfOvera(KeyStore keystore, char[] pin)
            throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException
    {
        super(keystore, pin);
    }

    public void overiPdf(PDDocument pdfDokument, OutputStream izlaz, String koOverava, String gdeOverava, String zastoOverava)
            throws IOException
    {
        PDSignature overa = new PDSignature();
        overa.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        overa.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        overa.setName(koOverava);
        overa.setLocation(gdeOverava);
        overa.setReason(zastoOverava);
        overa.setSignDate(Calendar.getInstance());
        pdfDokument.addSignature(overa);
        ExternalSigningSupport spoljnaOvera = pdfDokument.saveIncrementalForExternalSigning(izlaz);
        byte[] cmsOvera = sign(spoljnaOvera.getContent());
        spoljnaOvera.setSignature(cmsOvera);
    }

}
