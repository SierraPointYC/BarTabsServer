package org.spyc.bartabs.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1825433789L;

    public static final QItem item = new QItem("item");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final EnumPath<Department> department = createEnum("department", Department.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<ItemType> type = createEnum("type", ItemType.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

