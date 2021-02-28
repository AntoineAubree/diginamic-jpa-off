package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class MarqueDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public MarqueDao() {
	}

	void insererDepuisProduit(Produit produit) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom_marque", Marque.class);
		query.setParameter("nom_marque", produit.getMarque().getNom());
		List<Marque> marqueSelect = query.getResultList();

		if (marqueSelect.isEmpty()) {
			em.persist(produit.getMarque());
		} else {
			produit.setMarque(marqueSelect.get(0));
		}
		transaction.commit();

	}

}
