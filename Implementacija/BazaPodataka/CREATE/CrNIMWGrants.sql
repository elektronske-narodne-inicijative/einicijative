-- liquibase formatted sql

-- changeset liquibase:rseni-mw-grants


-- Grant execute on publishing procedures to the publisher service

GRANT EXECUTE ON FUNCTION ni.NIDocListaNivoaVlasti() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaTipovaInicijativa() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaFazaObrade() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaOpstina() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaUpravnihOkruga() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaAktivnihInicijativa() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocListaNeaktivnihInicijativa() TO nipub;
GRANT EXECUTE ON FUNCTION ni.NIDocsSkoroPromenjeneInicijative(int) TO nipub;

-- TODO: Grant execute on API-related procedures to the API service

GRANT EXECUTE ON PROCEDURE ni.NITxDajSesijuPoHash(text, boolean, boolean, boolean, char, char) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxPtpNovaSesija(text, json, timestamp, uuid, text, integer, text) TO niapi;
