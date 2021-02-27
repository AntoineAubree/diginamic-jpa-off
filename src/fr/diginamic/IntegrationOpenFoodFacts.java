package fr.diginamic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConfigUtils;
import fr.diginamic.utils.FichierSourceUtils;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {
		String chemin = "resources/open-food-facts.csv";

		Set<Categorie> categories = new HashSet<>();
		Set<Marque> marques = new HashSet<>();
		Set<Ingredient> ingredients = new HashSet<>();
		Set<Additif> additifs = new HashSet<>();
		List<Produit> produits = new ArrayList<>();

		try {
			FichierSourceUtils.traiterFichierSource(chemin, categories, marques, ingredients, additifs, produits);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("off", ConfigUtils.getPassword());
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		for (Categorie categorie : categories) {
			em.persist(categorie);
		}

		for (Marque marque : marques) {
			em.persist(marque);
		}

		for (Ingredient ingredient : ingredients) {
			em.persist(ingredient);
		}

		for (Additif additif : additifs) {
			em.persist(additif);
		}

		for (Produit produit : produits) {
			em.persist(produit);
		}

		transaction.commit();

		em.close();

	}

}
