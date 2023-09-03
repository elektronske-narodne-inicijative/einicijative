CREATE OR REPLACE FUNCTION ni.NITestMuskoIme() returns text
AS $$
DECLARE
    rndval int;
BEGIN
  rndval = floor(random()*30)+1;
  CASE rndval
    WHEN   1 THEN RETURN 'Александар';
    WHEN   2 THEN RETURN 'Бојан';
    WHEN   3 THEN RETURN 'Војин';
    WHEN   4 THEN RETURN 'Горан';
    WHEN   5 THEN RETURN 'Данијел';
    WHEN   6 THEN RETURN 'Дејан';
    WHEN   7 THEN RETURN 'Драган';
    WHEN   8 THEN RETURN 'Ђурађ';
    WHEN   9 THEN RETURN 'Живорад';
    WHEN  10 THEN RETURN 'Зоран';
    WHEN  11 THEN RETURN 'Иван';
    WHEN  12 THEN RETURN 'Јован';
    WHEN  13 THEN RETURN 'Коста';
    WHEN  14 THEN RETURN 'Лазар';
    WHEN  15 THEN RETURN 'Љубиша';
    WHEN  16 THEN RETURN 'Милан';
    WHEN  17 THEN RETURN 'Никола';
    WHEN  18 THEN RETURN 'Новак';
    WHEN  19 THEN RETURN 'Његош';
    WHEN  20 THEN RETURN 'Остоја';
    WHEN  21 THEN RETURN 'Петар';
    WHEN  22 THEN RETURN 'Предраг';
    WHEN  23 THEN RETURN 'Радослав';
    WHEN  24 THEN RETURN 'Стеван';
    WHEN  25 THEN RETURN 'Томислав';
    WHEN  26 THEN RETURN 'Ћирило';
    WHEN  27 THEN RETURN 'Филип';
    WHEN  28 THEN RETURN 'Часлав';
    WHEN  29 THEN RETURN 'Чедомир';
    ELSE RETURN 'Шабан';
  END CASE;
END;
$$
LANGUAGE plpgsql;
