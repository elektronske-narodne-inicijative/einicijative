CREATE OR REPLACE PROCEDURE ni.NITxIncPodesiBiografiju(
    IN  jwtHash Text,
    IN  bio   Text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије у подешавању биографије иницијатора!',
        'Сесија неодговарајућег типа за подешавање биографије иницијатора!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    --
    UPDATE ni.NIGradjanin
       SET biografija = bio
     WHERE IDNIGradjanin = sesija.IDNIKorisnik;
END;
$$;
