CREATE OR REPLACE PROCEDURE ni.NITxIntDajSesiju(
    IN  jwtHash text,
    OUT  sesija RECORD,
    IN  potrebanTipSesije text,
    IN  porukaAkoNepostojeca text,
    IN  porukaAkoNeodgovarajuca text,
    IN  porukaAkoIsteklaSesija text,
    IN  istekJWTbitan boolean DEFAULT false,
    IN  porukaAkoIstekaoJWT text DEFAULT null
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
BEGIN
    BEGIN
        SELECT *
          INTO STRICT sesija
          FROM ni.NISesija s
         WHERE s.IDNISesija = jwtHash;
    EXCEPTION
        WHEN no_data_found THEN
            call ni.NITxBaciGresku('NISNH', sesija.IDNIKorisnik, porukaAkoNepostojeca);
    END;
    IF sesija.IDNITipSesije != potrebanTipSesije THEN
        call ni.NITxBaciGresku('NISPT', sesija.IDNIKorisnik, porukaAkoNeodgovarajuca);
    END IF;
    IF sesija.trnIstekaSesije < now() THEN
        call ni.NITxBaciGresku('NISTI', sesija.IDNIKorisnik, porukaAkoIsteklaSesija);
    END IF;
    IF istekJWTbitan AND sesija.trnIstekaJWT < now() THEN
        call ni.NITxBaciGresku('NISTI', sesija.IDNIKorisnik, porukaAkoIstekaoJWT);
    END IF;
END;
$$;
