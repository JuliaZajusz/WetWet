package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.AddressPoint;
import com.wetwet.ReservationService.database.PatronAddressPoint;
import com.wetwet.ReservationService.repository.AddressPointRepository;
import com.wetwet.ReservationService.repository.PatronAddressPointRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressPointService {
    private AddressPointRepository addressPointRepository;
    private PatronAddressPointRepository patronAddressPointRepository;

    public AddressPointService(AddressPointRepository addressPointRepository, PatronAddressPointRepository patronAddressPointRepository) {
        this.addressPointRepository = addressPointRepository;
        this.patronAddressPointRepository = patronAddressPointRepository;
    }

    public List<AddressPoint> getAddressById(long id) {
        List<PatronAddressPoint> patronAddressPoints = patronAddressPointRepository.getByPatronId(id);
        List<Long> addressPointIds = patronAddressPoints
                .stream()
                .map(patronAddressPoint -> patronAddressPoint.getAddressPointId()).collect(Collectors.toList());
        return addressPointRepository.findAll().stream()
                .filter(patient -> addressPointIds.contains(patient.getId()))
                .collect(Collectors.toList());
    }
}
