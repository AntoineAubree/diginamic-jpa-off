package fr.diginamic.utils;

public class FloatUtils {

	/**
	 * convertit le String en float
	 * @param valeur
	 * @return la valeur du String en tant que float<br> renvoi 0 si le String est vide
	 */
	public static float parse(String valeur) {
		float result = 0;
		if (!valeur.isEmpty()) {
			result = Float.parseFloat(valeur);
		}
		return result;
	}

}
