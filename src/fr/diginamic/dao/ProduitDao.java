package fr.diginamic.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class ProduitDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	CategorieDao categorieDao = new CategorieDao();
	MarqueDao marqueDao = new MarqueDao();
	IngredientDao ingredientDao = new IngredientDao();
	AdditifDao additifDao = new AdditifDao();

	public ProduitDao() {
	}

	/**
	 * insert un Set de Produit dans la BDD
	 * @param produits Set de Produit
	 */
	public void insert(Set<Produit> produits) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		List<Categorie> categories = categorieDao.findAll();
		List<Marque> marques = marqueDao.findAll();
		List<Ingredient> ingredients = ingredientDao.findAll();
		List<Additif> additifs = additifDao.findAll();

		int compteur = 1;
		for (Produit produit : produits) {
			System.out.println(compteur++);
			TypedQuery<Produit> query = em.createQuery(
					"SELECT p FROM Produit p JOIN p.marque m WHERE p.nom = :nom_produit AND m.nom = :nom_marque",
					Produit.class);
			query.setParameter("nom_produit", produit.getNom());
			query.setParameter("nom_marque", produit.getMarque().getNom());
			List<Produit> produitSelect = query.getResultList();
			if (produitSelect.isEmpty()) {
				associerMarque(categories, produit);
				associerCategorie(marques, produit);
				associerIngredients(ingredients, produit);
				associerAdditifs(additifs, produit);
				em.persist(produit);
			}
		}

		transaction.commit();
	}

	private void associerAdditifs(List<Additif> additifs, Produit produit) {
		Set<Additif> additifsProduit = new HashSet<>();
		for (Additif additif : additifs) {
			for (Additif additifProduit : produit.getAdditifs()) {
				if (additif.getNom().equals(additifProduit.getNom())) {
					additifsProduit.add(additif);
					break;
				}
			}
		}
		produit.setAdditifs(additifsProduit);
	}

	private void associerIngredients(List<Ingredient> ingredients, Produit produit) {
		Set<Ingredient> ingredientsProduit = new HashSet<>();
		for (Ingredient ingredient : ingredients) {
			for (Ingredient ingredientProduit : produit.getIngredients()) {
				if (ingredient.getNom().equals(ingredientProduit.getNom())) {
					ingredientsProduit.add(ingredient);
					break;
				}
			}
		}
		produit.setIngredients(ingredientsProduit);
	}

	private void associerCategorie(List<Marque> marques, Produit produit) {
		for (Marque marque : marques) {
			if (marque.getNom().equals(produit.getMarque().getNom())) {
				produit.setMarque(marque);
				break;
			}
		}
	}

	private void associerMarque(List<Categorie> categories, Produit produit) {
		for (Categorie categorie : categories) {
			if (categorie.getNom().equals(produit.getCategorie().getNom())) {
				produit.setCategorie(categorie);
				break;
			}
		}
	}

}
