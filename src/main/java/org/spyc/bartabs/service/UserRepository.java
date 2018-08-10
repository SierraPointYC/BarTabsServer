package org.spyc.bartabs.service;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.spyc.bartabs.domain.User;

/**
 * User: mtali
 * Date: 07/02/18
 * Time: 20:33 implements QueryDslPredicateExecutor
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<User, Long>, QueryDslPredicateExecutor<User> {

	User findByName(@Param("name")String name);
}
