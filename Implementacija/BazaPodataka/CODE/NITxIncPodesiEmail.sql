CREATE OR REPLACE PROCEDURE ni.NITxIncPodesiEmail(
    IN  jwtHash Text,
    IN  email   Text
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
    sesija RECORD;
    gradjanin RECORD;
BEGIN
    call ni.NITxIntDajSesiju(
        jwtHash, 
        sesija,
        'И', -- potreban tip sesije
        'Непознат хеш сесије у подешавању емаил адресе иницијатора!',
        'Сесија неодговарајућег типа за подешавање емаил адресе иницијатора!',
        'Истекао је период важења пријаве - молимо пријавите се поново!'
    );
    call ni.NITxIntDajGradjanina(sesija, gradjanin,'Недостају подаци о грађанину за ИД корисника из сесије!');
    --
    UPDATE ni.NIGradjanin
       SET inicijatorovEmail = email
     WHERE IDNIGradjanin = sesija.IDNIKorisnik;
END;
$$;
