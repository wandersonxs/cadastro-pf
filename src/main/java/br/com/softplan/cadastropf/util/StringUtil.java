package br.com.softplan.cadastropf.util;

/**
 * Classe Util para formatações de String
 */
public class StringUtil {

    /**
     * Obtém somente os números de uma String.
     * @param value
     * @return
     */
    public static String getNumbers(String value) {

        if(value == null)
            return null;

        value =  value.replaceAll("[^\\d.]", "");
        value = value.replace(".", "");
        return value;
    }
}
