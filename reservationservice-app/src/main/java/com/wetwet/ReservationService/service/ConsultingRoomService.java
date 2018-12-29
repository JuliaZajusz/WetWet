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
                .anyMatch(wetDate -> checkIfWetDateContainsAnother(wetDate, a));
    }

    public boolean checkIfWetDateContainsAnother(WetDate inaccessibility, WetDate appointment) {
        System.out.println(inaccessibility.date + "  " + inaccessibility.startTime + "  " + inaccessibility.endTime);
        System.out.println(appointment.date + "  " + appointment.startTime + "  " + appointment.endTime);
        if (inaccessibility.date.toString().equals(appointment.date.toString())) {

            if (inaccessibility.startTime.before(appointment.startTime) && inaccessibility.endTime.after(appointment.startTime)) {
                System.out.println("Poczatek wizyty wpada w niedostepnosc");
                return true;
            }
            if (inaccessibility.startTime.after(appointment.startTime) && inaccessibility.startTime.before(appointment.endTime)) {
                System.out.println("Koniec wizyty wpada w niedostepnosc");
                return true;
            }

            if (inaccessibility.startTime.before(appointment.startTime) && inaccessibility.endTime.after(appointment.endTime)) {
                System.out.println("Srodek wizyty wpda w niedostepnosc");
                return true;
            }
            System.out.println("ta sama data ale nie zawiera");
            return false;
        }
        System.out.println("Inna data");
        return false;
    }
}
