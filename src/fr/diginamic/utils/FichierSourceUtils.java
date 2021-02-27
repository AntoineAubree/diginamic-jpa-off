package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class FichierSourceUtils {

	public static void traiterFichierSource(String chemin) throws IOException {
		List<String> lignes = recupererLignes(chemin);
		for (String ligne : lignes) {
			traiterLigne(ligne);
		}
	}

	private static void traiterLigne(String ligne) {

		String[] decoupage = ligne.split("\\|");
		String nomCategorie = decoupage[0].toLowerCase();
		String nomMarque = decoupage[1].toLowerCase();
		String nomProduit = decoupage[2].toLowerCase();
		String nutritionGradeFr = decoupage[3].toUpperCase();
		float energie100g = FloatUtils.parse(decoupage[5]);
		float graisse100g = FloatUtils.parse(decoupage[6]);
		float sucres100g = FloatUtils.parse(decoupage[7]);
		float fibres100g = FloatUtils.parse(decoupage[8]);
		float proteines100g = FloatUtils.parse(decoupage[9]);
		float sel100g = FloatUtils.parse(decoupage[10]);
		String[] decoupageIngredients = decoupage[4].split(",|;");
		String[] decoupageAdditifs = decoupage[29].split(",|;");

		Produit nouveauProduit = associerAttributsPrimairesAuProduit(nomProduit, nutritionGradeFr, energie100g,
				graisse100g, sucres100g, fibres100g, proteines100g, sel100g);
		nouveauProduit.setCategorie(new Categorie(nomCategorie));
		nouveauProduit.setMarque(new Marque(nomMarque));
		associerIngredientsAuProduit(decoupageIngredients, nouveauProduit);
		associerAdditifsAuProduit(decoupageAdditifs, nouveauProduit);
		
		
		
	}

	private static List<String> recupererLignes(String chemin) throws IOException {
		File file = new File(chemin);
		List<String> lignes = new ArrayList<>();
		lignes = FileUtils.readLines(file, "UTF-8");
		lignes.remove(0);
		return lignes;
	}

	private static Produit associerAttributsPrimairesAuProduit(String nomProduit, String nutritionGradeFr,
			float energie100g, float graisse100g, float sucres100g, float fibres100g, float proteines100g,
			float sel100g) {
		Produit nouveauProduit = new Produit(nomProduit);
		nouveauProduit.setNutritionGradeFr(nutritionGradeFr);
		nouveauProduit.setEnergie100g(energie100g);
		nouveauProduit.setGraisse100g(graisse100g);
		nouveauProduit.setSucres100g(sucres100g);
		nouveauProduit.setFibres100g(fibres100g);
		nouveauProduit.setProteines100g(proteines100g);
		nouveauProduit.setSel100g(sel100g);
		return nouveauProduit;
	}

	private static void associerIngredientsAuProduit(String[] decoupageIngredients, Produit nouveauProduit) {
		for (String nomIngredient : decoupageIngredients) {
			nouveauProduit.addIngredient(new Ingredient(StringUtils.nettoyerString(nomIngredient)));
		}
	}

	private static void associerAdditifsAuProduit(String[] decoupageAdditifs, Produit nouveauProduit) {
		for (String nomAdditif : decoupageAdditifs) {
			nouveauProduit.addAdditif(new Additif(StringUtils.nettoyerString(nomAdditif)));
		}
	}

}
