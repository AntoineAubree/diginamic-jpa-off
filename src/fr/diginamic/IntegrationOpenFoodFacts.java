package fr.diginamic;

import java.io.IOException;

import fr.diginamic.utils.FichierSourceUtils;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		
		String chemin = "resources/open-food-facts.csv";

		try {
			FichierSourceUtils.traiterFichierSource(chemin);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-debut);
	}

}
