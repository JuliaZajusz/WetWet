package com.wetwet.ReservationService.service;

import com.wetwet.ReservationService.database.ConsultingRoom;
import com.wetwet.ReservationService.database.ConsultingRoomInaccessibility;
import com.wetwet.ReservationService.database.WetDate;
import com.wetwet.ReservationService.repository.ConsultingRoomInaccessibilityRepository;
import com.wetwet.ReservationService.repository.ConsultingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultingRoomService {
    @Autowired
    private ConsultingRoomRepository consultingRoomRepository;
    @Autowired
    private ConsultingRoomInaccessibilityRepository consultingRoomInaccessibilityRepository;

    public ConsultingRoom createConsultingRoom(ConsultingRoom consultingRoom) {
        return consultingRoomRepository.save(consultingRoom);
    }

    public List<ConsultingRoom> getConsultingRooms() {
        return consultingRoomRepository.findAll();
    }

    public ConsultingRoom getConsultingRoomById(Long id) {
        return consultingRoomRepository.findById(id).orElseGet(null);
    }

    public List<ConsultingRoom> getAllAccessibleConsultingRooms(WetDate date) {
        List<ConsultingRoom> allConsultingRooms = consultingRoomRepository.findAll();
        return allConsultingRooms.stream()
                .filter(consultingRoom -> {
                    List<ConsultingRoomInaccessibility> consultingRoomInaccessibilities = consultingRoomInaccessibilityRepository.findAllByConsultingRoomId(consultingRoom.getId());
                    return !checkIfWetDateContainsAtLEastOneFromList(date, consultingRoomInaccessibilities);
                }).collect(Collectors.toList());

    }

    public boolean checkIfWetDateContainsAtLEastOneFromList(WetDate a, List<ConsultingRoomInaccessibility> inaccessibilities) {
        return inaccessibilities.stream().map(inaccessibility -> new WetDate(inaccessibility))
                .anyMatch(wetDate -> checkIfWetDateContainsAnother(a, wetDate));
    }

    public boolean checkIfWetDateContainsAnother(WetDate a, WetDate b) {
        System.out.println(a.date);
        System.out.println(b.date);
        if (!a.date.toString().equals(b.date.toString())) {
            System.out.println("Inna data");
            return false;
        }
        if (a.startTime.after(b.startTime) && a.startTime.before(b.endTime)) {
            System.out.println("Wizyta zaczyna się później, ale przed końcem niedostępności");
            return false;
        }
        if (a.endTime.before(b.endTime) && b.startTime.before(a.endTime)) {
            System.out.println("Wizyta kończy się wcześniej niż niedostępność, ale niedostępność zaczyna się w trakcie wizyty");
            return false;
        }
        System.out.println("ZAWIERA!!!!!!!!!!!!!!!!");
        return true;
    }
}
