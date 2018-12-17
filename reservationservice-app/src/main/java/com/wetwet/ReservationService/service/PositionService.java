package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.Position;
import com.wetwet.ReservationService.repository.PositionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PositionService {
    private PositionRepository repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public List<Position> getPositions() {
        return repository.findAll();
    }

    public Optional<Position> getPositionById(Long id) {
        return repository.findById(id);
    }

    public Position createPosition(Position position) {
        return repository.save(position);
    }
}
