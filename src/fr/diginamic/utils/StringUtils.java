package fr.diginamic.utils;

public class StringUtils {

	public static String nettoyerString(String string) {
		while (string.startsWith(".") || string.startsWith("_") || string.startsWith("*") || string.startsWith(" ")
				|| string.startsWith("'") || string.startsWith("(") || string.startsWith(")") || string.startsWith("[")
				|| string.startsWith("]") || string.startsWith("-") || string.startsWith("/") || string.startsWith("\"")) {
			string = string.substring(1);
		}
		while (string.endsWith(".") || string.endsWith("_") || string.endsWith("*") || string.endsWith(" ")
				|| string.endsWith("'") || string.endsWith("(") || string.endsWith(")") || string.endsWith("[")
				|| string.endsWith("]") || string.endsWith("-") || string.endsWith("/") || string.endsWith("\"")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

}
