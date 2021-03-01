package fr.diginamic.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Additif;

public class AdditifDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public AdditifDao() {
	}

	public void insert(Set<Additif> additifs) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (Additif additif : additifs) {
			TypedQuery<Additif> query = em.createQuery("SELECT a FROM Additif a WHERE a.nom = :nom_additif", Additif.class);
			query.setParameter("nom_additif", additif.getNom());
			List<Additif> ingredientSelect = query.getResultList();
			if (ingredientSelect.isEmpty()) {
				em.persist(additif);
			}
		}

		transaction.commit();
	}
	
	public List<Additif> findAll() {
		TypedQuery<Additif> query = em.createQuery("SELECT a FROM Additif a", Additif.class);
		return query.getResultList();
	}

}
