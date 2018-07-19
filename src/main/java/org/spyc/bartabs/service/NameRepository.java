package org.spyc.bartabs.service;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.spyc.bartabs.domain.Name;

/**
 * User: wahnonm
 * Date: 10/09/14
 * Time: 20:33 implements QueryDslPredicateExecutor
 */
@RepositoryRestResource(collectionResourceRel = "name", path = "name")
public interface NameRepository extends CrudRepository<Name, Long>, QueryDslPredicateExecutor<Name> {

	Name findByValue(String value);
}
