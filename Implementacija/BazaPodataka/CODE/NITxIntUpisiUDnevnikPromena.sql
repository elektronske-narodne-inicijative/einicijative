CREATE OR REPLACE PROCEDURE ni.NITxIntUpisiUDnevnikPromena(
    in  jwtHash text,
    in  tipAkcije text,
    in  iPre RECORD,
    in  iPosle RECORD,
    in  pPre RECORD DEFAULT NULL,
    in  pPosle RECORD DEFAULT NULL
)
LANGUAGE plpgsql SECURITY DEFINER
AS $$
DECLARE
  detPromene text;
BEGIN
    CASE
        WHEN tipAkcije = 'PI' THEN 
            detPromene = CAST(ЈSON_STRIP_NULLS(JSONB_BUILD_OBJECT(
                'тип', 'Промена иницијативе',
                'типИницијативе',    CASE
                                         WHEN iPre.IDNITipInicijative != iPosle.IDNITipInicijative THEN JSONB_BUILD_OBJECT('пре', iPre.IDNITipInicijative, 'после', iPosle.IDNITipInicijative)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'називИницијативе',  CASE
                                         WHEN iPre.NazivInicijative != iPosle.NazivInicijative     THEN JSONB_BUILD_OBJECT('пре', iPre.NazivInicijative, 'после', iPosle.NazivInicijative)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'текстИницијативе',  CASE
                                         WHEN iPre.TekstInicijative != iPosle.TekstInicijative     THEN JSONB_BUILD_OBJECT('пре', iPre.TekstInicijative, 'после', iPosle.TekstInicijative)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'емаилЗаКонтакт',    CASE
                                         WHEN iPre.EmailZaKontakt != iPosle.EmailZaKontakt         THEN JSONB_BUILD_OBJECT('пре', iPre.EmailZaKontakt, 'после', iPosle.EmailZaKontakt)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'нивоВласти',        CASE
                                         WHEN iPre.IDNINivoVlasti  !=  iPosle.IDNINivoVlasti       THEN JSONB_BUILD_OBJECT('пре', iPre.IDNINivoVlasti, 'после', iPosle.IDNINivoVlasti)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'општина',           CASE
                                         WHEN iPre.IDNIOpstina is distinct from iPosle.IDNIOpstina THEN JSONB_BUILD_OBJECT('пре', iPre.IDNIOpstina, 'после', iPosle.IDNIOpstina)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'покрајина',         CASE
                                         WHEN iPre.IDNIPokrajina is distinct from iPosle.IDNIPokrajina THEN JSONB_BUILD_OBJECT('пре', iPre.IDNIPokrajina, 'после', iPosle.IDNIPokrajina)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END
            )) as text);
        WHEN tipAkcije = 'PP' THEN 
            detPromene = CAST(ЈSON_STRIP_NULLS(JSONB_BUILD_OBJECT(
                'тип', 'Промена прилога',
                'иДприлога', cast(pPosle.IDNIPrilogInicijative as text),
                'сортирање',         CASE
                                         WHEN pPre.Sortiranje != pPosle.Sortiranje THEN JSONB_BUILD_OBJECT('пре', pPre.Sortiranje, 'после', pPosle.Sortiranje)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'називПрилога',      CASE
                                         WHEN pPre.NazivPriloga != pPosle.NazivPriloga THEN JSONB_BUILD_OBJECT('пре', pPre.NazivPriloga, 'после', pPosle.NazivPriloga)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END,
                'урлПрилога',        CASE
                                         WHEN pPre.UrlPriloga != pPosle.UrlPriloga THEN JSONB_BUILD_OBJECT('пре', pPre.UrlPriloga, 'после', pPosle.UrlPriloga)
                                         ELSE JSONB_BUILD_OBJECT('промена', 'без')
                                     END
            )) as text);
        WHEN tipAkcije = 'ZP' THEN
            detPromene = '{"тип":"Припремљена позивница"}';
        WHEN tipAkcije = 'ZI' THEN 
            detPromene = '{"тип":"Искоришћена позивница"}';
        WHEN tipAkcije = 'ZO' THEN 
            detPromene = '{"тип":"Обрисана позивница"}'; 
        WHEN tipAkcije = 'NP' THEN
            detPromene = '{"тип":"Прелаз у нову фазу обраде"}';
        ELSE detPromene = null;
    END CASE;

    INSERT INTO NIDnevnikPromena (IDNIDnevnikPromena, TrnPromene, IDNIInicijativa, IDNIFazaObrade, IDNISesija, DetaljiPromene)
    VALUES (nextval('ni.SIDNIDnevnikPromena'), current_timestamp, iPosle.IDNIInicijativa, iPosle.IDNIFazaObrade, jwtHash, detPromene);
END;
$$;
