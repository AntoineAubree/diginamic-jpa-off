package fr.diginamic.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Produit;

public class ProduitDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public ProduitDao() {
	}

	public void insertSet(Set<Produit> produits) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for (Produit produit : produits) {
			
			TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p Join p.marque m WHERE p.nom = :nom_produit AND m.nom = :nom_marque",
					Produit.class);
			query.setParameter("nom_produit", produit.getNom());
			query.setParameter("nom_marque", produit.getMarque().getNom());
			Produit produitSelect = query.getSingleResult();
			
			em.persist(produit);
		}

		transaction.commit();
	}

}
