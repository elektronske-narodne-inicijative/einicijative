-- liquibase formatted sql

-- changeset liquibase:rseni-code-NITestZenskoIme

CREATE OR REPLACE FUNCTION ni.NITestZenskoIme() returns text
AS $$
DECLARE
    rndval int;
BEGIN
  rndval = floor(random()*30)+1;
  CASE rndval
    WHEN   1 THEN RETURN 'Аница';
    WHEN   2 THEN RETURN 'Бојана';
    WHEN   3 THEN RETURN 'Биљана';
    WHEN   4 THEN RETURN 'Весна';
    WHEN   5 THEN RETURN 'Верица';
    WHEN   6 THEN RETURN 'Гордана';
    WHEN   7 THEN RETURN 'Горица';
    WHEN   8 THEN RETURN 'Дејана';
    WHEN   9 THEN RETURN 'Даница';
    WHEN  10 THEN RETURN 'Ђурђа';
    WHEN  11 THEN RETURN 'Ева';
    WHEN  12 THEN RETURN 'Живана';
    WHEN  13 THEN RETURN 'Жељка';
    WHEN  14 THEN RETURN 'Зорица';
    WHEN  15 THEN RETURN 'Ивана';
    WHEN  16 THEN RETURN 'Јелена';
    WHEN  17 THEN RETURN 'Јасмина';
    WHEN  18 THEN RETURN 'Катарина';
    WHEN  19 THEN RETURN 'Лариса';
    WHEN  20 THEN RETURN 'Људмила';
    WHEN  21 THEN RETURN 'Милена';
    WHEN  22 THEN RETURN 'Невена';
    WHEN  23 THEN RETURN 'Оливера';
    WHEN  24 THEN RETURN 'Пелагија';
    WHEN  25 THEN RETURN 'Радмила';
    WHEN  26 THEN RETURN 'Србијанка';
    WHEN  27 THEN RETURN 'Татјана';
    WHEN  28 THEN RETURN 'Тијана';
    WHEN  29 THEN RETURN 'Уна';
    ELSE RETURN 'Хелена';
  END CASE;
END;
$$
LANGUAGE plpgsql;
