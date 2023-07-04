-- liquibase formatted sql

-- changeset liquibase:rseni-code-NITestPrezime

CREATE OR REPLACE FUNCTION ni.NITestPrezime () returns text
AS $$
DECLARE
    rndval int;
BEGIN
  rndval = floor(random()*80)+1;
  CASE rndval
    WHEN   1 THEN RETURN 'Аврамовић';
    WHEN   2 THEN RETURN 'Аксентијевић';
    WHEN   3 THEN RETURN 'Алексић';
    WHEN   4 THEN RETURN 'Антонијевић';
    WHEN   5 THEN RETURN 'Арсић';
    WHEN   6 THEN RETURN 'Атанацковић';
    WHEN   7 THEN RETURN 'Аћимовић';
    WHEN   8 THEN RETURN 'Аџић';
    WHEN   9 THEN RETURN 'Бабић';
    WHEN  10 THEN RETURN 'Белић';
    WHEN  11 THEN RETURN 'Билбија';
    WHEN  12 THEN RETURN 'Бошковић';
    WHEN  13 THEN RETURN 'Будимировић';
    WHEN  14 THEN RETURN 'Бранисављевић';
    WHEN  15 THEN RETURN 'Васиљевић';
    WHEN  16 THEN RETURN 'Велисављевић';
    WHEN  17 THEN RETURN 'Висковић';
    WHEN  18 THEN RETURN 'Вучковић';
    WHEN  19 THEN RETURN 'Вранешевић';
    WHEN  20 THEN RETURN 'Гајић';
    WHEN  21 THEN RETURN 'Герасимовић';
    WHEN  22 THEN RETURN 'Гојић';
    WHEN  23 THEN RETURN 'Гудељ';
    WHEN  24 THEN RETURN 'Дачић';
    WHEN  25 THEN RETURN 'Делић';
    WHEN  26 THEN RETURN 'Додик';
    WHEN  27 THEN RETURN 'Дугић';
    WHEN  28 THEN RETURN 'Драгић';
    WHEN  29 THEN RETURN 'Ђаковић';
    WHEN  30 THEN RETURN 'Ђелић';
    WHEN  31 THEN RETURN 'Ђоковић';
    WHEN  32 THEN RETURN 'Ђурић';
    WHEN  33 THEN RETURN 'Елезовић';
    WHEN  34 THEN RETURN 'Ерић';
    WHEN  35 THEN RETURN 'Ешкер';
    WHEN  36 THEN RETURN 'Жарковић';
    WHEN  37 THEN RETURN 'Жежељ';
    WHEN  38 THEN RETURN 'Жикић';
    WHEN  39 THEN RETURN 'Жутић';
    WHEN  40 THEN RETURN 'Зарић';
    WHEN  41 THEN RETURN 'Зечевић';
    WHEN  42 THEN RETURN 'Зимоњић';
    WHEN  43 THEN RETURN 'Зорић';
    WHEN  44 THEN RETURN 'Ивић';
    WHEN  45 THEN RETURN 'Игић';
    WHEN  46 THEN RETURN 'Илић';
    WHEN  47 THEN RETURN 'Инђић';
    WHEN  48 THEN RETURN 'Исаиловић';
    WHEN  49 THEN RETURN 'Ифковић';
    WHEN  50 THEN RETURN 'Јаковљевић';
    WHEN  51 THEN RETURN 'Јелинић';
    WHEN  52 THEN RETURN 'Јовић';
    WHEN  53 THEN RETURN 'Југовић';
    WHEN  54 THEN RETURN 'Катић';
    WHEN  55 THEN RETURN 'Кесић';
    WHEN  56 THEN RETURN 'Кићановић';
    WHEN  57 THEN RETURN 'Костић';
    WHEN  58 THEN RETURN 'Кудић';
    WHEN  59 THEN RETURN 'Красић';
    WHEN  60 THEN RETURN 'Лазић';
    WHEN  61 THEN RETURN 'Лекић';
    WHEN  62 THEN RETURN 'Личина';
    WHEN  63 THEN RETURN 'Лончаревић';
    WHEN  64 THEN RETURN 'Љушић';
    WHEN  65 THEN RETURN 'Марић';
    WHEN  66 THEN RETURN 'Меховић';
    WHEN  67 THEN RETURN 'Милићевић';
    WHEN  68 THEN RETURN 'Модрић';
    WHEN  69 THEN RETURN 'Настић';
    WHEN  70 THEN RETURN 'Нешић';
    WHEN  71 THEN RETURN 'Николић';
    WHEN  72 THEN RETURN 'Новаковић';
    WHEN  73 THEN RETURN 'Обрадовић';
    WHEN  74 THEN RETURN 'Одаловић';
    WHEN  75 THEN RETURN 'Ојданић';
    WHEN  76 THEN RETURN 'Остојић';
    WHEN  77 THEN RETURN 'Оташевић';
    WHEN  78 THEN RETURN 'Папић';
    WHEN  79 THEN RETURN 'Петровић';
    ELSE RETURN 'Пилић';
  END CASE;
END;
$$
LANGUAGE plpgsql;
