package org.spyc.bartabs.service;

import java.util.List;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Transaction;

//@Component
@SuppressWarnings("rawtypes")
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
		}    
		if (total != p.getAmount()) {
			throw new Error("Wrong payment amount.");
		}
	}

}