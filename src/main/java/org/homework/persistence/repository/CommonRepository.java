package org.homework.persistence.repository;

import org.homework.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericRepository<E, ID> {

    private final Class<E> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    protected CommonRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "entity must be set");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<E> findById(ID id) {
        if (Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public void removeById(ID id) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void remove(E entity) {
        throw new UnsupportedOperationException("not implement");
    }
}
