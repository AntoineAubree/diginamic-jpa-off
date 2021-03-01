package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Produit;

public class CategorieDao extends AbstractDao {

	private EntityManager em;

	public CategorieDao(EntityManager em) {
		this.em = em;
	}

	void insererDepuisProduit(Produit produit) {

		TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE c.nom = :nom_categorie",
				Categorie.class);
		query.setParameter("nom_categorie", produit.getCategorie().getNom());
		List<Categorie> categorieSelect = query.getResultList();

		if (categorieSelect.isEmpty()) {
			em.persist(produit.getCategorie());
		} else {
			produit.setCategorie(categorieSelect.get(0));
		}

	}

}
