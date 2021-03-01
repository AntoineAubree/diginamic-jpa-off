package fr.diginamic.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Categorie;

public class CategorieDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public CategorieDao() {
	}

	public void insert(Set<Categorie> categories) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (Categorie categorie : categories) {
			TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE c.nom = :nom_categorie", Categorie.class);
			query.setParameter("nom_categorie", categorie.getNom());
			List<Categorie> categorieSelect = query.getResultList();
			if (categorieSelect.isEmpty()) {
				em.persist(categorie);
			}
		}

		transaction.commit();
	}
	
	public List<Categorie> findAll() {
		TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c", Categorie.class);
		return query.getResultList();
	}

}
