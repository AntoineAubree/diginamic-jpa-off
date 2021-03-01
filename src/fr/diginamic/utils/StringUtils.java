package fr.diginamic.utils;

public class StringUtils {

	/**
	 * Récupérer un String sans caractères spéciaux en début ou en fin
	 * 
	 * @param string
	 * @return string sans caractères spéciaux au début et à la fin
	 */
	public static String nettoyerString(String string) {
		while (string.startsWith(".") || string.startsWith("_") || string.startsWith("*") || string.startsWith(" ")
				|| string.startsWith("'") || string.startsWith("(") || string.startsWith(")") || string.startsWith("[")
				|| string.startsWith("]") || string.startsWith("-") || string.startsWith("/") || string.startsWith("\"")
				|| string.startsWith(":")) {
			string = string.substring(1);
		}
		while (string.endsWith(".") || string.endsWith("_") || string.endsWith("*") || string.endsWith(" ")
				|| string.endsWith("'") || string.endsWith("(") || string.endsWith(")") || string.endsWith("[")
				|| string.endsWith("]") || string.endsWith("-") || string.endsWith("/") || string.endsWith("\"")
				|| string.endsWith(":")) {
			string = string.substring(0, string.length() - 1);
		}
		string = string.toLowerCase();
		string = string.replaceAll("[`~!@#$^&*()_+:-]", " ");
		string = string.replaceAll("    ", " ");
		string = string.replaceAll("   ", " ");
		string = string.replaceAll("  ", " ");
		string = string.replaceAll(" %", "%");
		string = string.replaceAll("s ", " ");
		string = string.replaceAll("x ", " ");
		string = string.trim();

		return string;
	}

}
