package org.spyc.bartabs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Transaction;

//@Component
public class PaymentCalculator extends AbstractRepositoryEventListener{

	//@Autowired
	private TransactionRepository transactionRepository;
	
	public PaymentCalculator() {
		int a = 0;
		int b = a + 5;
		System.out.println(b);
	}	
	
    @Override
    public void onAfterCreate(Object entity) {
    	Payment p = (Payment)entity;
		List<Transaction> list = transactionRepository.findByStatusAndDepartmentAndUserName(
				Transaction.Status.UNPAID, 
				p.getDepartment(), 
				p.getUser().getName());
		// validate payment
		int total = 0;
		for (Transaction t : list) {
			total += t.getAmount();
		}    }

}