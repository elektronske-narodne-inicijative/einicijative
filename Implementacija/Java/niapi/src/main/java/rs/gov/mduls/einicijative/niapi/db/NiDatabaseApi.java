package rs.gov.mduls.einicijative.niapi.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public interface NiDatabaseApi {

    /*
     * "Zajednička" definicija enumeracije za polove
     */
    public enum IdPolaEnum {
        M("М"),

        Z("Ж");

        private String value;

        IdPolaEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static NiDatabaseApi.IdPolaEnum fromValue(String value) {
            for (NiDatabaseApi.IdPolaEnum b : NiDatabaseApi.IdPolaEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("NiDatabaseApi.IdPolaEnum='" + value + "'");
        }
    }
    /*
     * Strukture podataka (kad nije primitivni tip ili JSON mapiran u String)
     */
    public record Sesija (boolean prisutna, boolean isteklaSesija, boolean istekaoJWT, String idTipaSesije, String idTipaKorisnika) {}
    public record PtpProfil(IdPolaEnum idPola, int godinaRodjenja, String nazivOpstine) {}
    public record PtpPotpis(String nazivInicijative, UUID idPotpisa, Date trnZavodjenjaPotpisa) {}

    public record IncProfil(IdPolaEnum idPola, int godinaRodjenja, String nazivOpstine, String imePrezime, String emailAdresa, String biorafija) {}

    public record OvlProfil (String imePrezime, String emailAdresa, String nivoUprave, String opisJediniceUprave) {}

    public record SltPotpis(String nazivInicijative, UUID idPotpisa, Date trnZavodjenjaPotpisa) {}
    public record SltInicijativa(String nazivInicijative, String tipInicijative, String nivoVlasti, Date datumAktiviranja) {}

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Specifikacije API metoda
     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*-----------------------------------------------------------------------------------------------------------------
     * Parametri servera
     */
    public Map<String,String> dajParametre();

    /*-----------------------------------------------------------------------------------------------------------------
     * zajedničko za razne vrste korisnika
     */
    public Sesija dajSesijuPoHash(
            String jwtHash
    );

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za potpisnike
     */
    public void ptpNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine
    );
    public PtpProfil ptpDajProfil(
            String jwtHash
    );
    public PtpPotpis ptpPotpisiInicijativu(
            String jwtHash,
            int idInicijative
    );
    public PtpPotpis ptpDetaljiPotpisa(
            String jwtHash,
            int idInicijative
    );
    public String ptpListaPotpisa(
            String jwtHash
    );

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za inicijatore
     */
    public void incNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine,
            String imePrezime
    );
    public IncProfil incDajProfil(
            String jwtHash
    );
    public void incPodesiBiografiju(
            String jwtHash,
            String bio
    );
    public void incPodesiEmail(
            String jwtHash,
            String email
    );
    public int incDodajInicijativu(
            String jwtHash,
            String idTipaInicijative,
            String nazivInicijative,
            String tekstInicijative,
            String emailZaKontakt,
            String idNivoaVlasti,
            String idJediniceVlasti
    );
    public void incIzmeniInicijativu(
            String jwtHash,
            int idInicijative,
            String idTipaInicijative,
            String nazivInicijative,
            String tekstInicijative,
            String emailZaKontakt,
            String idNivoaVlasti,
            String idJediniceVlasti
    );
    public int incDodajPrilogInicijative(
            String jwtHash,
            int idInicijative,
            String urlPriloga,
            String nazivPriloga,
            int sortiranje
    );
    public void incIzmeniPrilogInicijative(
            String jwtHash,
            int idPrilogaInicijative,
            String urlPriloga,
            String nazivPriloga,
            int sortiranje
    );
    public void incObrisiPrilogInicijative(
            String jwtHash,
            int idPrilogaInicijative
    );
    public String incDetaljiInicijative(
            String jwtHash,
            int idInicijative
    );
    public String incListaPoFaziObrade(
            String jwtHash,
            String idFazaObrade
    );
    public void incPodnesiZahtev(
            String jwtHash,
            int idInicijative
    );
    public void incPokreniInicijativu(
            String jwtHash,
            int idInicijative
    );
    public void incPovuciInicijativu(
            String jwtHash,
            int idInicijative
    );
    public String incPripremiPozivnicu(
            String jwtHash,
            int idInicijative
    );
    public void incIskoristiPozivnicu(
            String jwtHash,
            int idInicijative,
            String pozivnica
    );
    public void incPonistiPozivnicu(
            String jwtHash,
            int idInicijative
    );

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za ovlašćena lica skupština
     */
    public void ovlNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            String idNivoaVlasti,
            String idOpstine,
            String idPokrajine,
            String imePrezime,
            String emailAdresa
    );
    public OvlProfil ovlDajProfil(
            String jwtHash
    );
    public String ovlDetaljiInicijative(
            String jwtHash,
            int idInicijative
    );
    public String ovlListaZaOdobrenje(
            String jwtHash
    );
    public String ovlListaZaIshod(
            String jwtHash
    );
    public void ovlOdbijInicijativu(
            String jwtHash,
            int idInicijative,
            String obrazlozenje
    );
    public void ovlOdobriInicijativu(
            String jwtHash,
            int idInicijative
    );
    public void ovlRegistrujOdbacenuInicijativu(
            String jwtHash,
            int idInicijative,
            Date datumSednice,
            String beleskaSaSednice
    );
    public void ovlRegistrujPrihvacenuInicijativu(
            String jwtHash,
            int idInicijative,
            Date datumSednice,
            String beleskaSaSednice
    );

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za šalterski sistem (pošte / poreske uprave)
     */
    public SltPotpis sltPotpis(
            UUID idGradjanina,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine,
            int idInicijative,
            String podaciSaSalteraPotpisa
    );
    public SltInicijativa sltUpitInicijative(
            int idInicijative
    );
    public String sltUpitListePotpisa(
            UUID idGradjanina
    );
    public SltPotpis sltUpitPotpisa(
            UUID idGradjanina,
            int idInicijative
    );
}
