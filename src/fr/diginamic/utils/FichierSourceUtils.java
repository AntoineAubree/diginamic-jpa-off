package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class FichierSourceUtils {

	public static void traiterFichierSource(String chemin, Set<Categorie> categories, Set<Marque> marques,
			Set<Ingredient> ingredients, Set<Additif> additifs, List<Produit> produits) throws IOException {
		List<String> lignes = recupererLignes(chemin);
		for (String ligne : lignes) {
			traiterLigne(categories, marques, ingredients, additifs, produits, ligne);
		}
	}

	private static void traiterLigne(Set<Categorie> categories, Set<Marque> marques, Set<Ingredient> ingredients,
			Set<Additif> additifs, List<Produit> produits, String ligne) {

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

		ajouterCategorieAListeCategories(categories, nomCategorie);
		ajouterMarqueAListeMarques(marques, nomMarque);
		ajouterIngredientAListeIngredients(ingredients, decoupageIngredients);
		ajouterAdditifAListeAdditifs(additifs, decoupageAdditifs);
		Produit nouveauProduit = ajouterProduitAListeProduits(produits, nomProduit, nutritionGradeFr, energie100g,
				graisse100g, sucres100g, fibres100g, proteines100g, sel100g);

		associerCategorieAuProduit(categories, nomCategorie, nouveauProduit);
		associerMarqueAuProduit(marques, nomMarque, nouveauProduit);
		associerIngredientsAuProduit(ingredients, decoupageIngredients, nouveauProduit);
		associerAdditifsAuProduit(additifs, decoupageAdditifs, nouveauProduit);
	}

	private static void associerAdditifsAuProduit(Set<Additif> additifs, String[] decoupageAdditifs,
			Produit nouveauProduit) {
		for (Additif additif : additifs) {
			for (String nomAdditif : decoupageAdditifs) {
				if (additif.getNom().equals(nomAdditif)) {
					nouveauProduit.addAdditif(additif);
					break;
				}
			}
		}
	}

	private static void associerIngredientsAuProduit(Set<Ingredient> ingredients, String[] decoupageIngredients,
			Produit nouveauProduit) {
		for (Ingredient ingredient : ingredients) {
			for (String nomIngredient : decoupageIngredients) {
				if (ingredient.getNom().equals(nomIngredient)) {
					nouveauProduit.addIngredient(ingredient);
					break;
				}
			}
		}
	}

	private static void associerMarqueAuProduit(Set<Marque> marques, String nomMarque, Produit nouveauProduit) {
		for (Marque marque : marques) {
			if (nomMarque.equals(marque.getNom())) {
				nouveauProduit.setMarque(marque);
				break;
			}
		}
	}

	private static void associerCategorieAuProduit(Set<Categorie> categories, String nomCategorie,
			Produit nouveauProduit) {
		for (Categorie categorie : categories) {
			if (nomCategorie.equals(categorie.getNom())) {
				nouveauProduit.setCategorie(categorie);
				break;
			}
		}
	}

	private static void ajouterAdditifAListeAdditifs(Set<Additif> additifs, String[] decoupageAdditifs) {
		for (String nomAdditif : decoupageAdditifs) {
			Additif nouvelAdditif = new Additif(StringUtils.nettoyerString(nomAdditif));
			additifs.add(nouvelAdditif);
		}
	}

	private static void ajouterIngredientAListeIngredients(Set<Ingredient> ingredients, String[] decoupageIngredients) {
		for (String nomIngredient : decoupageIngredients) {
			Ingredient nouvelIngredient = new Ingredient(StringUtils.nettoyerString(nomIngredient));
			ingredients.add(nouvelIngredient);
		}
	}

	private static Produit ajouterProduitAListeProduits(List<Produit> produits, String nomProduit,
			String nutritionGradeFr, float energie100g, float graisse100g, float sucres100g, float fibres100g,
			float proteines100g, float sel100g) {
		Produit nouveauProduit = new Produit(nomProduit);
		produits.add(nouveauProduit);
		nouveauProduit.setNutritionGradeFr(nutritionGradeFr);
		nouveauProduit.setEnergie100g(energie100g);
		nouveauProduit.setGraisse100g(graisse100g);
		nouveauProduit.setSucres100g(sucres100g);
		nouveauProduit.setFibres100g(fibres100g);
		nouveauProduit.setProteines100g(proteines100g);
		nouveauProduit.setSel100g(sel100g);
		return nouveauProduit;
	}

	private static void ajouterMarqueAListeMarques(Set<Marque> marques, String nomMarque) {
		Marque nouvelleMarque = new Marque(nomMarque);
		marques.add(nouvelleMarque);
	}

	private static void ajouterCategorieAListeCategories(Set<Categorie> categories, String nomCategorie) {
		Categorie nouvelleCategorie = new Categorie(nomCategorie);
		categories.add(nouvelleCategorie);
	}

	private static List<String> recupererLignes(String chemin) throws IOException {
		File file = new File(chemin);
		List<String> lignes = new ArrayList<>();
		lignes = FileUtils.readLines(file, "UTF-8");
		lignes.remove(0);
		return lignes;
	}

}
