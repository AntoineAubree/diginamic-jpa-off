package fr.diginamic.utils;

public class FloatUtils {

	public static float parse(String valeur) {
		float result = 0;
		if (!valeur.isEmpty()) {
			result = Float.parseFloat(valeur);
		}
		return result;
	}

}
