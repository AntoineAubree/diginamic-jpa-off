package fr.diginamic;

import java.io.IOException;

import fr.diginamic.dao.AdditifDao;
import fr.diginamic.dao.CategorieDao;
import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.MarqueDao;
import fr.diginamic.dao.ProduitDao;
import fr.diginamic.utils.FichierSourceUtils;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		String chemin = "resources/open-food-facts.csv";
		FichierSourceUtils fichierSourceUtils = new FichierSourceUtils(chemin);

		try {
			fichierSourceUtils.traiterFichierSource();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		MarqueDao marqueDao = new MarqueDao();
		marqueDao.insert(fichierSourceUtils.getMarques());
		CategorieDao categorieDao = new CategorieDao();
		categorieDao.insert(fichierSourceUtils.getCategories());
		IngredientDao ingredientDao = new IngredientDao();
		ingredientDao.insert(fichierSourceUtils.getIngredients());
		AdditifDao additifDao = new AdditifDao();
		additifDao.insert(fichierSourceUtils.getAdditifs());

		ProduitDao produitDao = new ProduitDao();
		produitDao.insert(fichierSourceUtils.getProduits());

		System.out.println(System.currentTimeMillis() - debut);

	}

}
