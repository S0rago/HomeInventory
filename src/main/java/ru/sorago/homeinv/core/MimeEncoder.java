package ru.sorago.homeinv.core;

import java.util.Base64;

public class MimeEncoder {
    private static StringBuilder getMimeEncoder(String toEncode) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(toEncode);
        return buffer;
    }

    public static String encode(String toEncode) {
        StringBuilder buffer = getMimeEncoder(toEncode);
        byte[] encodedAsBytes = buffer.toString().getBytes();
        return Base64.getMimeEncoder().encodeToString(encodedAsBytes);
    }

    public static String decode(String toDecode) {
        byte[] decodedBytes = Base64.getMimeDecoder().decode(toDecode);
        return new String(decodedBytes);
    }
}
