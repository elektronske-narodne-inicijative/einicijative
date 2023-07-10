﻿-- liquibase formatted sql

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

-- Grant execute on API-related procedures to the API service

GRANT EXECUTE ON PROCEDURE ni.NITxDajSesijuPoHash(text, boolean, boolean, boolean, char, char) TO niapi;

GRANT EXECUTE ON PROCEDURE ni.NITxPtpNovaSesija(text, json, timestamp, uuid, text, integer, text) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxPtpPotpisiInicijativu(text, integer, uuid, timestamp) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxPtpDetaljiPotpisa(text, integer, text, uuid, timestamp) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxPtpListaPotpisa(text, json) TO niapi;

GRANT EXECUTE ON PROCEDURE ni.NITxOvlNovaSesija(text, json, timestamp, uuid, text, text, text, text, text) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlDajProfil(text, text, text, text, text) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlListaZaOdobrenje(text, json) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlListaZaIshod(text, json) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlDetaljiInicijative(text, integer, json) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlOdbijInicijativu(text, integer, text) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlOdobriInicijativu(text, integer) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlRegistrujOdbacenuInicijativu(text, integer, date, text) TO niapi;
GRANT EXECUTE ON PROCEDURE ni.NITxOvlRegistrujPrihvacenuInicijativu(text, integer, date, text) TO niapi;

GRANT EXECUTE ON PROCEDURE ni.NITxIncNovaSesija(text, json, timestamp, uuid, text, integer, text, text) TO niapi;
