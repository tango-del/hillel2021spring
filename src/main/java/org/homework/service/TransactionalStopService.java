package org.homework.service;

import org.homework.persistence.entity.StopEntity;
import org.homework.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;

    @Transactional
    public StopEntity createOrUpdateStop(final StopEntity entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be set");

        return stopRepository.createOrUpdate(entity);
    }
}
