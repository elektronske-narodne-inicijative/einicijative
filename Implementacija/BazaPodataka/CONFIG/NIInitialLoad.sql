-- liquibase formatted sql

-- changeset liquibase:rseni-configuration-load


-- ---------------
--  Tip korisnika
-- ---------------
insert into NITipKorisnika(IDNITipKorisnika, Opis, Sortiranje) values ('Г','Грађанин',1);
insert into NITipKorisnika(IDNITipKorisnika, Opis, Sortiranje) values ('О','Овлашћено лице',2);

-- ---------------
--  Tip sesije
-- ---------------
insert into NITipSesije(IDNITipSesije, Opis, Sortiranje) values ('П','Потписник',1);
insert into NITipSesije(IDNITipSesije, Opis, Sortiranje) values ('И','Иницијатор',2);
insert into NITipSesije(IDNITipSesije, Opis, Sortiranje) values ('О','Овлашћено лице',3);

-- ----
-- Pol
-- ----
insert into NIPol(IDNIPol, Opis, Sortiranje) values ('М','Мушки',1);
insert into NIPol(IDNIPol, Opis, Sortiranje) values ('Ж','Женски',2);

-- -------------
--  Nivo vlasti
-- -------------
insert into NINivoVlasti(IDNINivoVlasti, Opis, Sortiranje) values ('Р','Република',1);
insert into NINivoVlasti(IDNINivoVlasti, Opis, Sortiranje) values ('О','Општина',2);
insert into NINivoVlasti(IDNINivoVlasti, Opis, Sortiranje) values ('П','Покрајина',3);

-- -----------------------------
--  Tipovi narodnih inicijativa
-- -----------------------------
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('ОД','Општи предлог доношења закона',1);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('ОИ','Општи предлог измене закона',2);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('ОУ','Општи предлог укидања закона',3);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('КД','Конкретан предлог доношења закона',4);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('КИ','Конкретан предлог измене закона',5);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('КУ','Конкретан предлог укидања закона',6);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('РФ','Захтев за расписивања референдума',7);
insert into NITipInicijative(IDNITipInicijative, Opis, Sortiranje) values ('ПУ','Захтев за промену Устава',8);

-- ---------------------------------
--  Faza obrade narodne inicijative
-- --------------------------------
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('У','У припреми',1,'В');
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('В','Поднета на верификацију скупштини',2,'АБ');
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('А','Активна (прикупљање потписа у току)',3,'ПНО');
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('П','Покренута (скупљено довољно потписа)',4,'К');
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('К','Комплетирана (скупштина одлучивала)',5,null);
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('Н','Неуспешна (није скупљено довољно потписа)',6,null);
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('Б','Одбијена на верификацији',7,null);
insert into NIFazaObrade(IDNIFazaObrade, Opis, Sortiranje, DozvoljeneSledeceFaze) values ('О','Повучена (иницијативни одбор одустао)',8,null);

-- -----------
--  Pokrajine
-- -----------
insert into NIPokrajina(IDNIPokrajina, Opis, Sortiranje) values ('В','Војводина',1);

-- ----------------
--  Upravni okruzi
-- ----------------
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('БГД','ГРАД БЕОГРАД',1);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('СБЧ','СЕВЕРНО-БАЧКИ',2);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('СРБ','СРЕДЊЕ-БАНАТСКИ',3);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('СВБ','СЕВЕРНО-БАНАТСКИ',4);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЈБН','ЈУЖНО-БАНАТСКИ',5);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЗБЧ','ЗАПАДНО-БАЧКИ',6);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЈБЧ','ЈУЖНО БАЧКИ',7);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('СРМ','СРЕМСКИ',8);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('МЧВ','МАЧВАНСКИ',9);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('КЛБ','КОЛУБАРСКИ',10);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПДН','ПОДУНАВСКИ',11);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('БРН','БРАНИЧЕВСКИ',12);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ШМД','ШУМАДИЈСКИ',13);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПМР','ПОМОРАВСКИ',14);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('БРС','БОРСКИ',15);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЗЈЧ','ЗАЈЕЧАРСКИ',16);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЗЛТ','ЗЛАТИБОРСКИ',17);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('МРВ','МОРАВИЧКИ',18);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('РШК','РАШКИ',19);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('РСН','РАСИНСКИ',20);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('НШВ','НИШАВСКИ',21);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ТПЛ','ТОПЛИЧКИ',22);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПРТ','ПИРОТСКИ',23);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ЈБЛ','ЈАБЛАНИЧКИ',24);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПЧЊ','ПЧИЊСКИ',25);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('КСВ','КОСОВСКИ',26);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПЋК','ПЕЋКИ',27);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('ПРЗ','ПРИЗРЕНСКИ',28);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('КМТ','КОСОВСКО-МИТРОВАЧКИ',29);
insert into NIUpravniOkrug(IDNIUpravniOkrug, Opis, Sortiranje) values ('КПМ','КОСОВСКО-ПОМОРАВСКИ',30);

-- ---------
--  Opštine
-- ---------
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70092','Београд-Барајево',1,23485,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70106','Београд-Вождовац',2,162263,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70114','Београд-Врачар',3,62831,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70122','Београд-Гроцка',4,73208,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70149','Београд-Звездара',5,162352,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70157','Београд-Земун',6,169190,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70165','Београд-Лазаревац',7,49996,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70173','Београд-Младеновац',8,45203,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70181','Београд-Нови Београд',9,204709,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70190','Београд-Обреновац',10,64271,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70203','Београд-Палилула',11,173058,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70211','Београд-Раковица',12,99497,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70220','Београд-Савски Венац',13,39797,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70238','Београд-Сопот',14,17340,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70246','Београд-Стари Град',15,53894,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70254','Београд-Чукарица',16,164766,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71293','Београд-Сурчин',17,39656,'БГД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80071','Бачка Топола',18,27612,'СБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80241','Мали Иђош',19,10001,'СБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80438','Суботица',20,125986,'СБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80144','Житиште',21,13670,'СРБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80152','Зрењанин',22,100156,'СРБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80250','Нова Црња',23,7992,'СРБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80268','Нови Бечеј',24,18708,'СРБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80373','Сечањ',25,10353,'СРБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80012','Ада',26,14602,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80195','Кањижа',27,21098,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80209','Кикинда',28,47211,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80276','Нови Кнежевац',29,8579,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80365','Сента',30,19690,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80489','Чока',31,8871,'СВБ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80039','Алибунар',32,16980,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80098','Бела Црква',33,15632,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80128','Вршац',34,44390,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80217','Ковачица',35,21089,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80225','Ковин',36,27772,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80292','Опово',37,8061,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80314','Панчево',38,106558,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80349','Пландиште',39,8612,'ЈБН','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80047','Апатин',40,24121,'ЗБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80233','Кула',41,33931,'ЗБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80306','Оџаци',42,24127,'ЗБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80381','Сомбор',43,71505,'ЗБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80055','Бач',44,12149,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80063','Бачка Паланка',45,45265,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80080','Бачки Петровац',46,11735,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80101','Беочин',47,13435,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80110','Бечеј',48,31679,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80136','Жабаљ',49,21284,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80284','Нови Сад',50,337233,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80390','Србобран',51,13382,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80411','Сремски Карловци',52,8076,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80446','Темерин',53,24759,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80454','Тител',54,12714,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80462','Врбас',55,33750,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80519','Петроварадин',56,0,'ЈБЧ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80179','Инђија',57,40540,'СРМ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80187','Ириг',58,9052,'СРМ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80322','Пећинци',59,15594,'СРМ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80357','Рума',60,45692,'СРМ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80403','Сремска Митровица',61,68761,'СРМ','В');
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80420','Стара Пазова',62,55927,'СРМ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('80497','Шид',63,29468,'СРМ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70289','Богатић',64,24078,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70408','Владимирци',65,13946,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70637','Коцељева',66,10695,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70661','Крупањ',67,13077,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70734','Лозница',68,75208,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70777','Љубовија',69,12921,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70793','Мали Зворник',70,15453,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71269','Шабац',71,100003,'МЧВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70360','Ваљево',72,75461,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70700','Лајковац',73,11483,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70769','Љиг',74,8623,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70831','Мионица',75,10822,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70882','Осечина',76,9364,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71218','Уб',77,22607,'КЛБ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70386','Велика Плана',78,35536,'ПДН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71099','Смедерево',79,94880,'ПДН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71102','Смедеревска Паланка',80,39594,'ПДН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70394','Велико Градиште',81,18880,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70475','Голубац',82,8092,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70521','Жабари',83,11284,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70530','Жагубица',84,10521,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70696','Кучево',85,15516,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70807','Мало Црниће',86,11775,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70912','Петровац на Млави',87,31051,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70947','Пожаревац',88,57397,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71340','Костолац',89,11315,'БРН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70033','Аранђеловац',90,36840,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70076','Баточина',91,9701,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70599','Кнић',92,10492,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70645','Крагујевац',93,152044,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71013','Рача',94,9078,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71153','Топола',95,17494,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71277','Лапово',96,6472,'ШМД',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70491','Деспотовац',97,21761,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70904','Параћин',98,46549,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71030','Рековац',99,8217,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71048','Јагодина',100,64071,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71056','Свилајнац',101,23796,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71200','Ћуприја',102,28458,'ПМР',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70327','Бор',103,40100,'БРС',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70572','Кладово',104,21499,'БРС',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70785','Мајданпек',105,15966,'БРС',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70840','Неготин',106,36337,'БРС',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70319','Бољевац',107,9761,'ЗЈЧ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70556','Зајечар',108,48390,'ЗЈЧ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70602','Књажевац',109,23221,'ЗЈЧ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71129','Сокобања',110,13267,'ЗЈЧ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70041','Ариље',111,14966,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70068','Бајина Башта',112,21590,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70629','Косјерић',113,9080,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70866','Нова Варош',114,12627,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70955','Пожега',115,23086,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70971','Прибој',116,27331,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70980','Пријепоље',117,32183,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71072','Сјеница',118,27252,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71145','Ужице',119,58023,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71366','Севојно',120,5645,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71234','Чајетина',121,13415,'ЗЛТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70483','Горњи Милановац',122,34828,'МРВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70564','Ивањица',123,24771,'МРВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70742','Лучани',124,15204,'МРВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71242','Чачак',125,94623,'МРВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70459','Врњачка Бања',126,23440,'РШК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70653','Краљево',127,99016,'РШК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70874','Нови Пазар',128,90307,'РШК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71021','Рашка',129,19413,'РШК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71188','Тутин',130,32450,'РШК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70017','Александровац',131,21431,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70343','Брус',132,12796,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70378','Варварин',133,14900,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70670','Крушевац',134,105203,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71170','Трстеник',135,34263,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71196','Ћићевац',136,7205,'РСН',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70025','Алексинац',137,40201,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70467','Гаџин Хан',138,5696,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70513','Дољевац',139,13883,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70823','Мерошина',140,9951,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71005','Ражањ',141,6052,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71064','Сврљиг',142,10356,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71285','Ниш-Нишка Бања',143,11684,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71307','Ниш-Пантелеј',144,45493,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71315','Ниш-Црвени Крст',145,29350,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71323','Ниш-Палилула',146,63711,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71331','Ниш-Медијана',147,77493,'НШВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70262','Блаце',148,8536,'ТПЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70548','Житорађа',149,11940,'ТПЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70688','Куршумлија',150,13974,'ТПЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70998','Прокупље',151,36047,'ТПЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70050','Бабушница',152,8452,'ПРТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70084','Бела Паланка',153,8644,'ПРТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70505','Димитровград',154,8039,'ПРТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70939','Пирот',155,45636,'ПРТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70297','Бојник',156,8318,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70424','Власотинце',157,22970,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70718','Лебане',158,16885,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70726','Лесковац',159,114536,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70815','Медвеђа',160,6123,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71226','Црна Трава',161,1050,'ЈБЛ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70335','Босилеград',162,7298,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70351','Бујановац',163,42762,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70416','Владичин Хан',164,16129,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70432','Врање',165,60355,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('70963','Прешево',166,42842,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71137','Сурдулица',167,16437,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71161','Трговиште',168,4536,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('71358','Врањска Бања',169,8019,'ПЧЊ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90034','Глоговац',170,0,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90115','Качаник',171,0,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90131','Косово Поље',172,5181,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90166','Липљан',173,6297,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90204','Обилић',174,3882,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90247','Подујево',175,478,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90263','Приштина',176,14921,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90301','Урошевац',177,4029,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90310','Штимље',178,0,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90328','Штрпце',179,5928,'КСВ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90069','Дечани',180,0,'ПЋК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90085','Ђаковица',181,0,'ПЋК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90107','Исток',182,3578,'ПЋК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90123','Клина',183,2256,'ПЋК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90239','Пећ',184,4390,'ПЋК',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90212','Ораховац',185,1710,'ПРЗ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90255','Призрен',186,4758,'ПРЗ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90280','Сува Река',187,0,'ПРЗ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90336','Гора',188,7131,'ПРЗ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90026','Вучитрн',189,3950,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90093','Зубин Поток',190,4543,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90158','Лепосавић',191,9812,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90271','Србица',192,344,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90298','Косовска Митровица',193,15477,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90352','Звечан',194,5949,'КМТ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90018','Витина',195,2826,'КПМ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90042','Гњилане',196,8467,'КПМ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90140','Косовска Каменица',197,6593,'КПМ',null);
insert into NIOpstina(IDNIOpstina, Opis, Sortiranje, BrojRegistrovanihGlasaca, IDNIUPravniOkrug, IDNIPokrajina) values ('90182','Ново Брдо',198,1008,'КПМ',null);

-- ---------------------------------
--  Parametri aplikacija
-- --------------------------------
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('НајмањеЧлановаИницијативногОдбора',false, false, '5');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('БројПотписаЗаЗахтевЗаПроменуУстава',false, false, '150000');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('БројПотписаЗаРепубличкиРеферендум',false, false, '100000');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('БројПотписаЗаПокрајинскиРеферендум',false, false, '30000');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ПроценатГласачаЗаОпштинскиРеферендум',false, false, '10');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('БројПотписаЗаИницијативуЗаПроменуЗакона',false, false, '30000');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ИстекСесијеОвлЛице',false, false, '600');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ИстекСесијеИницијатор',false, false, '600');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ИстекСесијеПотписник',false, false, '60');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ПериодЛистеПотписаЗаПотписника', false, false, '365');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ЦиклусЧитањаПараметара',true, true, '600');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ЦиклусЧитањаКоднихДомена',true, false, '300');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('ПериодИзменаЗаДетаље',false, true, '600');
insert into NIParametar(IDNIParametar, niapi, nipub, VrednostParametra) values ('КључЗаПотписивање',true, false, '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');
