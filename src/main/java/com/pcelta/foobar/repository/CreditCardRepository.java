package com.pcelta.foobar.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;

import org.hibernate.annotations.NotFound;

import com.pcelta.foobar.entity.CreditCard;

public class CreditCardRepository {
	
	private EntityManager entityManager;
	
	public CreditCardRepository(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public CreditCard findOneByNumber(String creditCardNumber) {
		Query query = this.entityManager.createQuery("from CreditCard cc where cc.number = :number");
		query.setParameter("number", creditCardNumber);
		
		List<CreditCard> cards = query.getResultList();
	
		if (cards.size() > 0) {
			return cards.get(0);
		}
		
		return new CreditCard();
	}
	

}
