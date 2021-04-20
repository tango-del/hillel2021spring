package org.homework.persistence.repository;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.homework.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable>
        implements GenericRepository<E, ID> {

    private final Class<E> entityClass;

    final String entityTableName;

    @PersistenceContext
    protected EntityManager entityManager;

    protected CommonRepository(Class<E> entityClass) {
        if (Objects.isNull(entityClass)) throw new IllegalArgumentException("entityClass must be set");

        this.entityClass = entityClass;
        entityTableName = entityClass.getAnnotation(Table.class).name();
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

    @SneakyThrows
    @Override
    public void removeById(ID id) {
        //entityManager.remove(findById(id).get());
        //final E reference = entityManager.getReference(entityClass, id);
        //entityManager.remove(reference);
        entityManager.remove(entityManager.getReference(entityClass, id));
    }

    @Override
    public void remove(E entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity must best");

        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            removeById(entity.getId());
        }
    }

    @Override
    public Collection<E> findByIds(ID... ids) {
        return entityManager.unwrap(Session.class).byMultipleIds(entityClass).multiLoad(ids);
    }

    @Override
    public Collection<E> findByName(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        return entityManager.createQuery(query.
                select(from).
                where(criteriaBuilder.equal(from.get("name"), criteriaBuilder.literal(name))))
                .getResultList();
    }

    @Override
    public Collection<E> findAll() {
        //return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        //return entityManager.createNativeQuery("select * from " + entityTableName, entityClass).getResultList();
//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
//        final Root<E> from = query.from(entityClass);
//        return entityManager.createQuery(query.select(from)).getResultList();
        System.out.println("call findAll() in CommonRepository");

        return entityManager.createStoredProcedureQuery("find_all", entityClass).
                registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR).
                registerStoredProcedureParameter(2, String.class, ParameterMode.IN).
                setParameter(2, entityTableName).
                getResultList();
    }
}
