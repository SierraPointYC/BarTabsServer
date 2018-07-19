package org.spyc.bartabs.service;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.spyc.bartabs.domain.Name;
import org.spyc.bartabs.domain.Payment;
import org.spyc.bartabs.domain.Transaction;
import org.spyc.bartabs.domain.User;

/**
 * User: mtali
 * Date: 07/02/18
 * Time: 20:33 implements QueryDslPredicateExecutor
 */
@RepositoryRestResource(collectionResourceRel = "payment", path = "payment")
public interface PaymentRepository extends CrudRepository<Payment, Long>, QueryDslPredicateExecutor<Payment> {
	List<Transaction> findByUserName(@Param("name")String name);
}
