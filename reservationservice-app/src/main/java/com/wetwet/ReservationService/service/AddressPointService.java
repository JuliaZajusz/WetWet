package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.AddressPoint;
import com.wetwet.ReservationService.database.City;
import com.wetwet.ReservationService.database.PatronAddressPoint;
import com.wetwet.ReservationService.database.Street;
import com.wetwet.ReservationService.dto.AddressDTO;
import com.wetwet.ReservationService.repository.AddressPointRepository;
import com.wetwet.ReservationService.repository.CityRepository;
import com.wetwet.ReservationService.repository.PatronAddressPointRepository;
import com.wetwet.ReservationService.repository.StreetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressPointService {
    private CityRepository cityRepository;
    private StreetRepository streetRepository;
    private AddressPointRepository addressPointRepository;
    private PatronAddressPointRepository patronAddressPointRepository;

    public AddressPointService(AddressPointRepository addressPointRepository,
                               PatronAddressPointRepository patronAddressPointRepository,
                               CityRepository cityRepository,
                               StreetRepository streetRepository) {
        this.addressPointRepository = addressPointRepository;
        this.patronAddressPointRepository = patronAddressPointRepository;
        this.cityRepository = cityRepository;
        this.streetRepository = streetRepository;
    }

    public List<AddressDTO> getAddressByPatronId(long id) {
        List<PatronAddressPoint> patronAddressPoints = patronAddressPointRepository.getByPatronId(id);
        List<Long> addressPointIds = patronAddressPoints
                .stream()
                .map(patronAddressPoint -> patronAddressPoint.getAddressPointId()).collect(Collectors.toList());
        return addressPointRepository.findAll().stream()
                .filter(addressPoint -> addressPointIds.contains(addressPoint.getId()))
                .map(addressPoint -> convertAddressPointIntoAddressDTO(addressPoint))
                .collect(Collectors.toList());
    }

    public AddressDTO convertAddressPointIntoAddressDTO(AddressPoint addressPoint) {
        AddressDTO address = new AddressDTO(addressPoint);
        if (addressPoint.getCityId() != null) {
            City city = cityRepository.findById(addressPoint.getCityId()).orElse(null);
            address.setCity(city);
        }
        if (addressPoint.getStreetId() != null) {
            Street street = streetRepository.findById(addressPoint.getStreetId()).orElse(null);
            address.setStreet(street);
        }
        return address;
    }
}
