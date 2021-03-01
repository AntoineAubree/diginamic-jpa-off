package fr.diginamic.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Marque;

public class MarqueDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public MarqueDao() {
	}

	/**
	 * insert un Set de Marque dans la BDD
	 * @param marques Set de Marque
	 */
	public void insert(Set<Marque> marques) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		for (Marque marque : marques) {
			TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom_marque", Marque.class);
			query.setParameter("nom_marque", marque.getNom());
			List<Marque> marqueSelect = query.getResultList();
			if (marqueSelect.isEmpty()) {
				em.persist(marque);
			}
		}

		transaction.commit();
	}
	
	public List<Marque> findAll() {
		TypedQuery<Marque> query = em.createQuery("SELECT m from Marque m", Marque.class);
		return query.getResultList();
	}

}
