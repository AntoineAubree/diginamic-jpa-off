package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class FichierSourceUtils {

	private String chemin;
	private Set<Categorie> categories = new HashSet<>();
	private Set<Marque> marques = new HashSet<>();
	private Set<Ingredient> ingredients = new HashSet<>();
	private Set<Additif> additifs = new HashSet<>();
	private Set<Produit> produits = new HashSet<>();

	public FichierSourceUtils(String chemin) {
		this.chemin = chemin;
	}

	public void traiterFichierSource() throws IOException {
		List<String> lignes = recupererLignes();
		for (String ligne : lignes) {
			traiterLigne(ligne);
		}
	}

	private List<String> recupererLignes() throws IOException {
		File file = new File(this.chemin);
		List<String> lignes = new ArrayList<>();
		lignes = FileUtils.readLines(file, "UTF-8");
		lignes.remove(0);
		return lignes;
	}

	private void traiterLigne(String ligne) {

		String[] decoupage = ligne.split("\\|");
		String nomCategorie = StringUtils.nettoyerString(decoupage[0].toLowerCase());
		String nomMarque = StringUtils.nettoyerString(decoupage[1].toLowerCase());
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

		addCategorie(nomCategorie);
		addMarque(nomMarque);
		addIngredients(decoupageIngredients);
		addAdditifs(decoupageAdditifs);

		Produit nouveauProduit = addProduit(nomProduit, nutritionGradeFr, energie100g, graisse100g, sucres100g,
				fibres100g, proteines100g, sel100g);

		associerCategorieAuProduit(nomCategorie, nouveauProduit);
		associerMarqueAuProduit(nomMarque, nouveauProduit);
		associerIngredientsAuProduit(decoupageIngredients, nouveauProduit);
		associerAdditifsAuProduit(decoupageAdditifs, nouveauProduit);
	}

	private void associerAdditifsAuProduit(String[] decoupageAdditifs, Produit nouveauProduit) {
		for (String nomAdditif : decoupageAdditifs) {
			nouveauProduit.addAdditif(new Additif(StringUtils.nettoyerString(nomAdditif)));
		}
	}

	private void associerIngredientsAuProduit(String[] decoupageIngredients, Produit nouveauProduit) {
		for (String nomIngredient : decoupageIngredients) {
			nouveauProduit.addIngredient(new Ingredient(StringUtils.nettoyerString(nomIngredient)));
		}
	}

	private void associerMarqueAuProduit(String nomMarque, Produit nouveauProduit) {
		nouveauProduit.setMarque(new Marque(nomMarque));
	}

	private void associerCategorieAuProduit(String nomCategorie, Produit nouveauProduit) {
		nouveauProduit.setCategorie(new Categorie(nomCategorie));
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void addCategorie(String nomCategorie) {
		this.categories.add(new Categorie(nomCategorie));
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	public Set<Marque> getMarques() {
		return marques;
	}

	public void addMarque(String nomMarque) {
		this.marques.add(new Marque(nomMarque));
	}

	public void setMarques(Set<Marque> marques) {
		this.marques = marques;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredients(String[] nomsIngredients) {
		for (String nomIngredient : nomsIngredients) {
			this.ingredients.add(new Ingredient(StringUtils.nettoyerString(nomIngredient)));
		}
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Additif> getAdditifs() {
		return additifs;
	}

	public void addAdditifs(String[] nomsAdditifs) {
		for (String nomAdditif : nomsAdditifs) {
			this.additifs.add(new Additif(StringUtils.nettoyerString(nomAdditif)));
		}
	}

	public void setAdditifs(Set<Additif> additifs) {
		this.additifs = additifs;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	private Produit addProduit(String nomProduit, String nutritionGradeFr, float energie100g, float graisse100g,
			float sucres100g, float fibres100g, float proteines100g, float sel100g) {
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

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

}
