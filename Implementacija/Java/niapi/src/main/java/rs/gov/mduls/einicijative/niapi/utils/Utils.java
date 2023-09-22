package rs.gov.mduls.einicijative.niapi.utils;

import rs.gov.mduls.einicijative.niapi.db.NiDatabaseApi;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Utils {

    public static boolean bezbedanBooleanIzMape(Map<String, Object> out, String name, boolean defaultValue) {
        return (out.get(name) == null) ? defaultValue : (boolean) out.get(name);
    }

    public static int bezbedanIntIzMape(Map<String, Object> out, String name, int defaultValue) {
        return (out.get(name) == null) ? defaultValue : (int) out.get(name);
    }

    public static NiDatabaseApi.IdPolaEnum bezbedanPolIzMape(Map<String, Object> out, String name, NiDatabaseApi.IdPolaEnum defaultValue) {
        if ("Ж".equals(out.get(name))) {
            return (out.get(name) == null) ? defaultValue : NiDatabaseApi.IdPolaEnum.Z;
        } else {
            return (out.get(name) == null) ? defaultValue : NiDatabaseApi.IdPolaEnum.M;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String jwtToHash(String jwt) {
        try {
            MessageDigest md = MessageDigest.getInstance(Consts.JWT_HASH_ALGORITHM);
            return bytesToHex(md.digest(jwt.getBytes(Charset.defaultCharset())));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
