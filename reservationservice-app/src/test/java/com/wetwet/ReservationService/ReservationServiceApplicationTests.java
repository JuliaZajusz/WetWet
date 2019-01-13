package com.wetwet.ReservationService;

import com.wetwet.ReservationService.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceApplicationTests {

    @Autowired
    private PatientService patientService;

	@Test
	public void contextLoads() {
	}


//	@Test
//	public void shouldFindExistingPatient() {
//		// when
//		PatientDTO patient = patientService.getPatientById(1);
//		// then
//		assertThat(patient).isNotNull();
//	}

}
