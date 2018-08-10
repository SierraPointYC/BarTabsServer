package org.spyc.bartabs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Transaction;
import org.spyc.bartabs.domain.Transaction.Status;

@RepositoryEventHandler
public class PaymentEventHandler {

	@Autowired
	private TransactionRepository transactionRepository;

	public PaymentEventHandler() {
		int a = 0;
		int b = a+ 5;
		System.out.println(b);
	}
	
	@HandleBeforeCreate(Payment.class)
	public void handlePaymentCreate(Payment p) {
		List<Transaction> list = transactionRepository.findByStatusAndDepartmentAndUserName(
				Transaction.Status.UNPAID, 
				p.getDepartment(), 
				p.getUser().getName());
		// validate payment
		int total = 0;
		for (Transaction t : list) {
			total += t.getAmount();
		}
		if (total != p.getAmount()) {
			throw new Error("Wrong payment amount.");
		}
		// Apply payment
		Date now = new Date();
		for (Transaction t : list) {
			t.setCloseDate(now);
			t.setStatus(Status.PAID);
			transactionRepository.save(t);
		}
		
	}

}
