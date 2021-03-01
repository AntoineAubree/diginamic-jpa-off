package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class MarqueDao extends AbstractDao {

	private EntityManager em;

	public MarqueDao(EntityManager em) {
		this.em = em;
	}

	void insererDepuisProduit(Produit produit) {

		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom_marque", Marque.class);
		query.setParameter("nom_marque", produit.getMarque().getNom());
		List<Marque> marqueSelect = query.getResultList();

		if (marqueSelect.isEmpty()) {
			em.persist(produit.getMarque());
		} else {
			produit.setMarque(marqueSelect.get(0));
		}

	}

}
