package fr.diginamic.utils;

import java.util.HashMap;
import java.util.Map;

public class ConfigUtils {

	/**
	 * Récupère le password saisi dans les VM arguments de STS
	 * 
	 * @return une Map avec pour premier élément une clé qui correspond à la
	 *         propriété password du persistance.xml et une valeur qui corrspond au
	 *         password de la BDD
	 */
	public static Map<String, String> getPassword() {
		String password = System.getProperty("password");
		Map<String, String> result = new HashMap<>();
		result.put("javax.persistence.jdbc.password", password);
		return result;
	}

}
