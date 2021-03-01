package fr.diginamic.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Produit;

public class AllergeneDao extends AbstractDao {

	private EntityManager em;

	public AllergeneDao(EntityManager em) {
		this.em = em;
	}

	void insererListeDepuisProduit(Produit produit) {
		Set<Allergene> allergenesSelect = new HashSet<>();
		for (Allergene allergene : produit.getAllergenes()) {
			TypedQuery<Allergene> query = em.createQuery("SELECT a FROM Allergene a WHERE a.nom = :nom_allergene",
					Allergene.class);
			query.setParameter("nom_allergene", allergene.getNom());
			List<Allergene> allergeneSelect = query.getResultList();

			if (allergeneSelect.isEmpty()) {
				em.persist(allergene);
				allergenesSelect.add(allergene);
			} else {
				allergenesSelect.add(allergeneSelect.get(0));
			}
		}
		produit.setAllergenes(allergenesSelect);

	}

}
