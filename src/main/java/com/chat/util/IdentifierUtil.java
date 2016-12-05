package com.chat.util;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/5/16
 */
public class IdentifierUtil {
    private static Long idValue = 0L;

    private IdentifierUtil() {
        /*
         * Utilite
         */
    }

    synchronized public static Long getIdValue() {
        return idValue++;
    }
}
