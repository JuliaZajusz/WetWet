package com.wetwet.ReservationService.rest;

import com.wetwet.ReservationService.database.EmployeeAvailability;
import com.wetwet.ReservationService.dto.EmployeeWithAvailability;
import com.wetwet.ReservationService.service.EmployeeAvailabilityService;
import com.wetwet.ReservationService.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/employeeAvailability")
public class EmployeeAvailabilityController {

        private final EmployeeAvailabilityService employeeAvailabilityService;
        private final EmployeeService employeeService;

        public EmployeeAvailabilityController(EmployeeAvailabilityService employeeAvailabilityService, EmployeeService employeeService) {
            this.employeeAvailabilityService = employeeAvailabilityService;
            this.employeeService = employeeService;
        }

        @GetMapping("/all")
        public List<EmployeeWithAvailability> getAllAvailabilities() {
            return employeeService.getAvailabilities();
        }

        @GetMapping(path = "/{id}")
        @ResponseBody
        public EmployeeWithAvailability getAvailability(@PathVariable Long id) {
            return employeeService.getAvailabilityById(id);
        }

        @PostMapping()
        public EmployeeAvailability createAvailability(@Valid @RequestBody EmployeeAvailability employeeAvailability) {
            EmployeeAvailability inn = employeeAvailabilityService.createAvailability(employeeAvailability);
            return inn;
        }

}
