package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.entites.Ingredient;

public class IngredientDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public IngredientDao() {
	}

	public void insert(Ingredient ingredient) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(ingredient);

		transaction.commit();
	}

}
