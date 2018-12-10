package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.config.EntityManagerUtils;
import com.wetwet.ReservationService.database.Position;
import com.wetwet.ReservationService.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PositionService {
    private PositionRepository repository;
    @Autowired
    private EntityManagerUtils emUtils;

    public List<Position> getPositions() {
        repository = emUtils.getJpaFactory().getRepository(PositionRepository.class);
        return repository.findAll();
    }

    public Optional<Position> getPositionById(Long id) {
        repository = emUtils.getJpaFactory().getRepository(PositionRepository.class);
        return repository.findById(id);
    }

    public Position createPosition(Position position) {
        repository = emUtils.getJpaFactory().getRepository(PositionRepository.class);
        return repository.save(position);
    }
}
