package fr.diginamic.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Ingredient;

public class IngredientDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public IngredientDao() {
	}

	public void insert(Set<Ingredient> ingredients) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		int compteur = 1;
		for (Ingredient ingredient : ingredients) {
			System.out.println(compteur++);
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.nom = :nom_ingredient",
					Ingredient.class);
			query.setParameter("nom_ingredient", ingredient.getNom());
			List<Ingredient> ingredientSelect = query.getResultList();
			if (ingredientSelect.isEmpty()) {
				em.persist(ingredient);
			}
		}

		transaction.commit();
	}
	
	public List<Ingredient> findAll() {
		TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i", Ingredient.class);
		return query.getResultList();
	}

}
