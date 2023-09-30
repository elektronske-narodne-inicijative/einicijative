package rs.gov.mduls.einicijative.niapi.utils;

import java.text.SimpleDateFormat;

public class Consts {
    public static final String API_ULOGA_POTPISNIK = "potpisnik";
    public static final String API_ULOGA_INICIJATOR = "inicijator";
    public static final String API_ULOGA_OVLICE = "ovlice";

    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
    public static final String HTTP_HEADER_AUTHORIZATION_BEARER = "Bearer";
    public static final String JWT_HASH_ALGORITHM = "SHA-256";

    public static final String DB_SCHEMA_NAME = "ni";

    public static final String DB_KEYSTORE_PARAM_NAME = "КључЗаПотписивање";

    public static final SimpleDateFormat FORMAT_DATUMA = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat FORMAT_DATUMA_I_VREMENA = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static final SimpleDateFormat FORMAT_VREMENA = new SimpleDateFormat("HH:mm:ss");
}
