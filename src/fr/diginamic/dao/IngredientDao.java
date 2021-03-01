package fr.diginamic.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Produit;

public class IngredientDao extends AbstractDao {

	private EntityManager em;

	public IngredientDao(EntityManager em) {
		this.em = em;
	}

	void insererListeDepuisProduit(Produit produit) {
		Set<Ingredient> ingredientsSelect = new HashSet<>();
		for (Ingredient ingredient : produit.getIngredients()) {
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.nom = :nom_ingredient",
					Ingredient.class);
			query.setParameter("nom_ingredient", ingredient.getNom());
			List<Ingredient> ingredientSelect = query.getResultList();
			
			if (ingredientSelect.isEmpty()) {
				em.persist(ingredient);
				ingredientsSelect.add(ingredient);
			} else {
				ingredientsSelect.add(ingredientSelect.get(0));
			}
		}
		produit.setIngredients(ingredientsSelect);

	}

}
