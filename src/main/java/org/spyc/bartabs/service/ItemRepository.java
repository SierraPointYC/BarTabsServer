package org.spyc.bartabs.service;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.spyc.bartabs.domain.Item;
import org.spyc.bartabs.domain.Name;

/**
 * User: mtali
 * Date: 07/02/18
 * Time: 20:33 implements QueryDslPredicateExecutor
 */
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends CrudRepository<Item, Long>, QueryDslPredicateExecutor<Item> {

	Name findByType(@Param("type")String type);
}
