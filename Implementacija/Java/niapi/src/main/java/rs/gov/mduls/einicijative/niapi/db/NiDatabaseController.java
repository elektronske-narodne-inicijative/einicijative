package rs.gov.mduls.einicijative.niapi.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import rs.gov.mduls.einicijative.niapi.utils.Consts;
import rs.gov.mduls.einicijative.niapi.utils.NadzorniTrag;
import rs.gov.mduls.einicijative.niapi.utils.Utils;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Component("NiDatabaseApi")
public class NiDatabaseController implements NiDatabaseApi {

    private static final Logger logger = LoggerFactory.getLogger(NiDatabaseController.class);

    @Autowired
    DataSource niDataSource;

    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * Specifikacije API metoda
     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /*-----------------------------------------------------------------------------------------------------------------
     * zajedničko za razne vrste korisnika
     */
    @Override
    public Sesija dajSesijuPoHash(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource().addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxDajSesijuPoHash").execute(in);
            boolean prisutna = Utils.bezbedanBooleanIzMape(out, "prisutna", false);
            boolean isteklaSesija = Utils.bezbedanBooleanIzMape(out, "isteklaSesija", false);
            boolean istekaoJWT = Utils.bezbedanBooleanIzMape(out,  "istekaoJWT", false);
            return new Sesija (
                    prisutna,
                    isteklaSesija,
                    istekaoJWT,
                    (String) out.get("idtipasesije"),
                    (String) out.get("idtipakorisnika")
            );
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za potpisnike
     */
    @Override
    public void ptpNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("jwtVrednost",jwtVrednost)
                    .addValue("trnIstekaJWT",trnIstekaJWT)
                    .addValue("idKorisnika",idKorisnika)
                    .addValue("idPola",idPola.getValue())
                    .addValue("godinaRodjenja",godinaRodjenja)
                    .addValue("idOpstine",idOpstine);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxPtpNovaSesija").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public PtpProfil ptpDajProfil(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource().
                    addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxPtpDajProfil").execute(in);
            IdPolaEnum idPola = Utils.bezbedanPolIzMape(out,"idpola",IdPolaEnum.Z);
            int godinaRodjenja = Utils.bezbedanIntIzMape(out,"godinarodjenja",0);
            return new PtpProfil(idPola,(int) godinaRodjenja, (String) out.get("nazivopstine"));
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public PtpPotpis ptpPotpisiInicijativu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxPtpPotpisiInicijativu").execute(in);
            return new PtpPotpis(
                    (String) out.get("idpotpisa"),
                    (UUID) out.get("idpotpisa"),
                    (Date) out.get("trnzavodjenjapotpisa")
            );
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public PtpPotpis ptpDetaljiPotpisa(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxPtpDetaljiPotpisa").execute(in);
            return new PtpPotpis(
                    (String) out.get("idpotpisa"),
                    (UUID) out.get("idpotpisa"),
                    (Date) out.get("trnzavodjenjapotpisa")
            );
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String ptpListaPotpisa(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxPtpDetaljiPotpisa").execute(in);
            return (String) out.get("listapotpisa");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za inicijatore
     */
    @Override
    public void incNovaSesija(
            String jwtHash,
            String jwtVrednost,
            Date trnIstekaJWT,
            UUID idKorisnika,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine,
            String imePrezime
    )  {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("jwtVrednost",jwtVrednost)
                    .addValue("trnIstekaJWT",trnIstekaJWT)
                    .addValue("idKorisnika",idKorisnika)
                    .addValue("idPola",idPola.getValue())
                    .addValue("godinaRodjenja",godinaRodjenja)
                    .addValue("idOpstine",idOpstine)
                    .addValue("imePrezime",imePrezime);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncNovaSesija").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public IncProfil incDajProfil(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource().
                    addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncDajProfil").execute(in);
            return new IncProfil(
                    NiDatabaseApi.IdPolaEnum.fromValue((String) out.get("idpola")),
                    (int) out.get("godinarodjenja"),
                    (String) out.get("nazivopstine"),
                    (String) out.get("imeprezime"),
                    (String) out.get("emailadresa"),
                    (String) out.get("biografija")
            );
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPodesiBiografiju(
            String jwtHash,
            String bio
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("bio",bio);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPodesiBiografiju").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPodesiEmail(
            String jwtHash,
            String email
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("email",email);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPodesiEmail").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public int incDodajInicijativu(
            String jwtHash,
            String idTipaInicijative,
            String nazivInicijative,
            String tekstInicijative,
            String emailZaKontakt,
            String idNivoaVlasti,
            String idJediniceVlasti
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idTipaInicijative",idTipaInicijative)
                    .addValue("nazivInicijative",nazivInicijative)
                    .addValue("tekstInicijative",tekstInicijative)
                    .addValue("emailZaKontakt",emailZaKontakt)
                    .addValue("idNivoaVlasti",idNivoaVlasti)
                    .addValue("idJediniceVlasti",idJediniceVlasti);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncDodajInicijativu").execute(in);
            return (int) out.get("idinicijative");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incIzmeniInicijativu(
            String jwtHash,
            int idInicijative,
            String idTipaInicijative,
            String nazivInicijative,
            String tekstInicijative,
            String emailZaKontakt,
            String idNivoaVlasti,
            String idJediniceVlasti
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("idTipaInicijative",idTipaInicijative)
                    .addValue("nazivInicijative",nazivInicijative)
                    .addValue("tekstInicijative",tekstInicijative)
                    .addValue("emailZaKontakt",emailZaKontakt)
                    .addValue("idNivoaVlasti",idNivoaVlasti)
                    .addValue("idJediniceVlasti",idJediniceVlasti);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncIzmeniInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public int incDodajPrilogInicijative(
            String jwtHash,
            int idInicijative,
            String urlPriloga,
            String nazivPriloga,
            int sortiranje
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("urlPriloga",urlPriloga)
                    .addValue("nazivPriloga",nazivPriloga)
                    .addValue("sortiranje",sortiranje);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncDodajPrilogInicijative").execute(in);
            return (int) out.get("idprilogainicijative");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incIzmeniPrilogInicijative(
            String jwtHash,
            int idPrilogaInicijative,
            String urlPriloga,
            String nazivPriloga,
            int sortiranje
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idPrilogaInicijative",idPrilogaInicijative)
                    .addValue("urlPriloga",urlPriloga)
                    .addValue("nazivPriloga",nazivPriloga)
                    .addValue("sortiranje",sortiranje);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncIzmeniPrilogInicijative").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incObrisiPrilogInicijative(
            String jwtHash,
            int idPrilogaInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idPrilogaInicijative",idPrilogaInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncObrisiPrilogInicijative").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String incDetaljiInicijative(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncDetaljiInicijative").execute(in);
            return (String) out.get("detaljiinicijative");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String incListaPoFaziObrade(
            String jwtHash,
            String idFazaObrade
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idFazaObrade",idFazaObrade);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncListaPoFaziObrade").execute(in);
            return (String) out.get("listainicijativa");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPodnesiZahtev(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPodnesiZahtev").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPokreniInicijativu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPokreniInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPovuciInicijativu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPovuciInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String incPripremiPozivnicu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPripremiPozivnicu").execute(in);
            return (String) out.get("pozivnica");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incIskoristiPozivnicu(
            String jwtHash,
            int idInicijative,
            String pozivnica
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("pozivnica",pozivnica);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncIskoristiPozivnicu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void incPonistiPozivnicu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncPonistiPozivnicu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za ovlašćena lica skupština
     */
    @Override
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
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("jwtVrednost",jwtVrednost)
                    .addValue("trnIstekaJWT",trnIstekaJWT)
                    .addValue("idKorisnika",idKorisnika)
                    .addValue("idNivoaVlasti",idNivoaVlasti)
                    .addValue("idOpstine",idOpstine)
                    .addValue("idPokrajine",idPokrajine)
                    .addValue("imePrezime",imePrezime)
                    .addValue("emailAdresa",emailAdresa);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlNovaSesija").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public OvlProfil ovlDajProfil(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource().
                    addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlDajProfil").execute(in);
            return new OvlProfil(
                    (String) out.get("imeprezime"),
                    (String) out.get("emailadresa"),
                    (String) out.get("nivouprave"),
                    (String) out.get("opisjediniceuprave")
            );
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String ovlDetaljiInicijative(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlDetaljiInicijative").execute(in);
            return (String) out.get("detaljiinicijative");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String ovlListaZaOdobrenje(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlListaZaOdobrenje").execute(in);
            return (String) out.get("listainicijativa");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public String ovlListaZaIshod(
            String jwtHash
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash);
            Map<String, Object> out = simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlListaZaIshod").execute(in);
            return (String) out.get("listainicijativa");
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void ovlOdbijInicijativu(
            String jwtHash,
            int idInicijative,
            String obrazlozenje
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("obrazlozenje",obrazlozenje);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncOdbijInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void ovlOdobriInicijativu(
            String jwtHash,
            int idInicijative
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxIncOdobriInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void ovlRegistrujOdbacenuInicijativu(
            String jwtHash,
            int idInicijative,
            Date datumSednice,
            String beleskaSaSednice
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("datumSednice",datumSednice)
                    .addValue("beleskaSaSednice",beleskaSaSednice);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlRegistrujOdbacenuInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }
    @Override
    public void ovlRegistrujPrihvacenuInicijativu(
            String jwtHash,
            int idInicijative,
            Date datumSednice,
            String beleskaSaSednice
    ) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(niDataSource);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("jwtHash", jwtHash)
                    .addValue("idInicijative",idInicijative)
                    .addValue("datumSednice",datumSednice)
                    .addValue("beleskaSaSednice",beleskaSaSednice);
            simpleJdbcCall.withSchemaName(Consts.DB_SCHEMA_NAME).withProcedureName("NITxOvlRegistrujPrihvacenuInicijativu").execute(in);
        } catch (DataAccessException e) {
            NadzorniTrag.sqlIzuzetak(e);
            throw e;
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------
     * metodi za šalterski sistem (pošte / poreske uprave)
     */
    @Override
    public SltPotpis sltPotpis(
            UUID idGradjanina,
            IdPolaEnum idPola,
            int godinaRodjenja,
            String idOpstine,
            int idInicijative,
            String podaciSaSalteraPotpisa
    ) {
        // TODO: implementiraj logiku
        return new SltPotpis(null, null, null);
    }
    @Override
    public SltInicijativa sltUpitInicijative(
            int idInicijative
    ) {
        // TODO: implementiraj logiku
        return new SltInicijativa(null,null,null,null);
    }
    @Override
    public String sltUpitListePotpisa(
            UUID idGradjanina
    ) {
        // TODO: implementiraj logiku
        return null;
    }
    @Override
    public SltPotpis sltUpitPotpisa(
            UUID idGradjanina,
            int idInicijative
    ) {
        // TODO: implementiraj logiku
        return new SltPotpis(null, null, null);
    }

}
