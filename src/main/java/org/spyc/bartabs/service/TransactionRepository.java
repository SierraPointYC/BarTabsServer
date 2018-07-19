package org.spyc.bartabs.service;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.spyc.bartabs.domain.Department;
import org.spyc.bartabs.domain.Name;
import org.spyc.bartabs.domain.Transaction;
import org.spyc.bartabs.domain.Transaction.Status;
import org.spyc.bartabs.domain.User;

/**
 * User: mtali
 * Date: 10/09/14
 * Time: 20:33 implements QueryDslPredicateExecutor
 */
@RepositoryRestResource(collectionResourceRel = "transaction", path = "transaction")
public interface TransactionRepository extends CrudRepository<Transaction, Long>, QueryDslPredicateExecutor<Transaction> {
	List<Transaction> findByUserName(@Param("name")String name);
	List<Transaction> findByStatusAndDepartmentAndUserName(@Param("status")Status status, @Param("department")Department department, @Param("name")String name);
}
