CREATE TABLE Employee (
	ID						int(10) NOT NULL AUTO_INCREMENT,
    First_Name				varchar(255) NOT NULL,
	Last_Name				varchar(255) NOT NULL,
	Position_ID				int(10) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE Position (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Type					varchar(255) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE Employee_Availability (
	ID						int(10) NOT NULL AUTO_INCREMENT,
    Date					date,
	Start_Time				time NOT NULL,
	End_Time				time NOT NULL,
	Employee_ID				int(10) NOT NULL,
    PRIMARY KEY (ID));

CREATE TABLE Employee_Appointment (
	Appointment_ID			int(10) NOT NULL,
	Employee_ID				int(10) NOT NULL,
	PRIMARY KEY (Appointment_ID, Employee_ID));

CREATE TABLE Appointment (
  ID                 int(10) NOT NULL AUTO_INCREMENT,
  Title              varchar(255) NOT NULL,
  Description        text,
	Cost               int(10) NOT NULL,
  Date               date NOT NULL,
  Start_Time         time NOT NULL,
  End_Time           time NOT NULL,
  Address_Point_ID   int(10),
  Consulting_Room_ID int(10),
  DrugCost           int(10),
  PRIMARY KEY (ID));

CREATE TABLE Consulting_Room (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Room_Number				varchar(10) NOT NULL,
	Description				text,
	PRIMARY KEY (ID));

CREATE TABLE Consulting_Room_Inaccessibility (
	ID						int(10) NOT NULL AUTO_INCREMENT,
    Date					date,
	Start_Time				time NOT NULL,
	End_Time				time NOT NULL,
	Consulting_Room_ID		int(10) NOT NULL,
    PRIMARY KEY (ID));

CREATE TABLE Drug (
  ID        int(10)      NOT NULL AUTO_INCREMENT,
  Producent varchar(255) NOT NULL,
  Name      varchar(255) NOT NULL,
  Cost      int(10)      NOT NULL,
  PRIMARY KEY (ID));

CREATE TABLE Appointment_Drug (
  Appointment_ID int(10) NOT NULL,
  Drug_ID        int(10) NOT NULL,
  Quantity       int(10) NOT NULL,
  PRIMARY KEY (Appointment_ID, Drug_ID));

CREATE TABLE Patient_Appointment (
	Patient_ID				int(10) NOT NULL,
	Appointment_ID			int(10) NOT NULL,
    PRIMARY KEY (Patient_ID, Appointment_ID));

CREATE TABLE Patient (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Name					varchar(255),
	Sex						enum('M', 'F'),
	Birthdate				varchar(255),
	Coat					varchar(255),
	Special_Characters		text,
	Breed_ID				int(10),
	PRIMARY KEY (ID));

CREATE TABLE Breed (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Name					varchar(255) NOT NULL,
	Species_ID				int(10) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE Species (
	ID 						int(10) NOT NULL AUTO_INCREMENT,
	Name					varchar(255) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE Patron (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	First_Name		varchar(255) NOT NULL,
	Last_Name			varchar(255) NOT NULL,
	Phone					varchar(30),
	Email					varchar(255),
	PRIMARY KEY (ID));

CREATE TABLE Patient_Patron (
	Patron_ID				int(10) NOT NULL,
	Patient_ID				int(10) NOT NULL,
    PRIMARY KEY (Patron_ID, Patient_ID));

CREATE TABLE Address_Point (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	House_Apartment_Number	varchar(20) NOT NULL,
	Street_ID				int(10),
	City_ID					int(10),
	PRIMARY KEY (ID));

CREATE TABLE Street (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Name					varchar(255) NOT NULL,
	City_ID					int(10) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE City (
	ID						int(10) NOT NULL AUTO_INCREMENT,
	Name					varchar(255) NOT NULL,
	PRIMARY KEY (ID));

CREATE TABLE Patron_Address_Point (
	Patron_ID				int(10) NOT NULL,
	Address_Point_ID		int(10) NOT NULL,
    PRIMARY KEY (Patron_ID, Address_Point_ID));

CREATE TABLE Credentials (
	Employee_ID       integer(10) NOT NULL,
	Login             varchar(20) NOT NULL,
  Password_Hash     char(64) NOT NULL,
  PRIMARY KEY (Employee_ID));

ALTER TABLE Patient ADD CONSTRAINT FKBreedInPatient FOREIGN KEY (Breed_ID) REFERENCES Breed (ID);
ALTER TABLE Patient_Patron ADD CONSTRAINT FKPatientInPatient_Patron FOREIGN KEY (Patient_ID) REFERENCES Patient (ID);
ALTER TABLE Patient_Patron ADD CONSTRAINT FKPatronInPatient_Patron FOREIGN KEY (Patron_ID) REFERENCES Patron (ID);
ALTER TABLE Street ADD CONSTRAINT FKCityInStreet FOREIGN KEY (City_ID) REFERENCES City (ID);
ALTER TABLE Address_Point ADD CONSTRAINT FKStreetInAddress_Point FOREIGN KEY (Street_ID) REFERENCES Street (ID);
ALTER TABLE Address_Point ADD CONSTRAINT ExclusiveStreet CHECK ((Street_ID is null xor City_ID is null));
ALTER TABLE Patient_Appointment ADD CONSTRAINT FKPatientInPatient_Appointment FOREIGN KEY (Patient_ID) REFERENCES Patient (ID);
ALTER TABLE Patient_Appointment ADD CONSTRAINT FKAppointmentInPatient_Appointment FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAddress_PointInAppointment FOREIGN KEY (Address_Point_ID) REFERENCES Address_Point (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKConsulting_RoomInAppointment FOREIGN KEY (Consulting_Room_ID) REFERENCES Consulting_Room (ID);
ALTER TABLE Appointment ADD CONSTRAINT ExclusiveLocation CHECK (Address_Point_ID IS NULL XOR Consulting_Room_ID IS NULL);
ALTER TABLE Appointment_Drug ADD CONSTRAINT FKAppointmentInAppointment_Drug FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Appointment_Drug ADD CONSTRAINT FKDrugInAppointment_Drug FOREIGN KEY (Drug_ID) REFERENCES Drug (ID);
ALTER TABLE Employee_Appointment ADD CONSTRAINT FKAppointmentInEmployee_Appointment FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Employee_Appointment ADD CONSTRAINT FKEmployeeInEmployee_Appointment FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);
ALTER TABLE Consulting_Room_Inaccessibility ADD CONSTRAINT FKConsulting_RoomInCRI FOREIGN KEY (Consulting_Room_ID) REFERENCES Consulting_Room (ID);
ALTER TABLE Employee ADD CONSTRAINT FKPositionInEmployee FOREIGN KEY (Position_ID) REFERENCES Position (ID);
ALTER TABLE Employee_Availability ADD CONSTRAINT FKEmployeeInEmployee_Availability FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);
ALTER TABLE Breed ADD CONSTRAINT FKSpeciesInBreed FOREIGN KEY (Species_ID) REFERENCES Species (ID);
ALTER TABLE Patron_Address_Point ADD CONSTRAINT FKPatronInPAP FOREIGN KEY (Patron_ID) REFERENCES Patron (ID);
ALTER TABLE Patron_Address_Point ADD CONSTRAINT FKAddress_PointInPAP FOREIGN KEY (Address_Point_ID) REFERENCES Address_Point (ID);
ALTER TABLE Address_Point ADD CONSTRAINT FKCityInAddress_Point FOREIGN KEY (City_ID) REFERENCES City (ID);
ALTER TABLE Credentials ADD CONSTRAINT FKEmployeeInCredentials FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);

ALTER TABLE Patron ADD INDEX (last_name);
ALTER TABLE Appointment ADD INDEX (date);
ALTER TABLE Employee_Availability ADD INDEX (date);
ALTER TABLE Consulting_Room_Inaccessibility ADD INDEX (date);

# Patient_View
CREATE VIEW Patient_View AS
SELECT patient.ID, patient.name, patient.sex, patient.birthdate, patient.coat, patient.special_characters,
			breed.name AS breed, species.name AS species
		FROM patient, breed, species
		WHERE patient.breed_ID = breed.id AND breed.species_ID = species.ID;

# Address_View
CREATE VIEW Address_View AS
SELECT address_point.ID, city.name AS city,
			if(address_point.street_ID IS NOT NULL, street.name, NULL) AS street,
			address_point.house_apartment_number
		FROM address_point, street, city
        WHERE (address_point.street_ID = street.ID AND street.city_ID = city.ID)
        OR address_point.city_ID = city.ID;

# Employee_Availability_View
CREATE VIEW Employee_Availability_View AS
SELECT employee.ID, CONCAT(employee.first_name, " ", employee.last_name) AS employee_name,
			employee_availability.date, employee_availability.start_time, employee_availability.end_time
        FROM employee, employee_availability
        WHERE employee_availability.employee_ID = employee.ID;

# Consulting_Room_Inaccessibility_View
CREATE VIEW Consulting_Room_Inaccessibility_View AS
SELECT consulting_room.ID, consulting_room.room_number, consulting_room.description AS room_description,
			consulting_room_inaccessibility.date,
            consulting_room_inaccessibility.start_time,
            consulting_room_inaccessibility.end_time
        FROM consulting_room, consulting_room_inaccessibility
        WHERE consulting_room_inaccessibility.consulting_room_ID = consulting_room.ID;

# Appointment_View ### NOT WORKING!!! UNEXPECTED ROW DUPLICATION ###
CREATE VIEW Appointment_View AS
SELECT patient.ID AS patient_ID, patient.name AS patient_name,
			CONCAT(patron.first_name, " ", patron.last_name) AS patron,
            appointment.ID AS appointment_ID, appointment.title AS appointment_title,
            appointment.date, appointment.start_time, appointment.end_time,
            employee.ID as employee_ID, CONCAT(employee.first_name, " ", employee.last_name) AS employee_name,
            if(appointment.address_point_ID IS NOT NULL, address_view.ID, NULL) AS address_ID,
            if(appointment.address_point_ID IS NOT NULL,
				CONCAT(address_view.city, " ",
					if(address_view.street IS NOT NULL, CONCAT(address_view.street, " "), ""),
                    address_view.house_apartment_number),
                NULL) AS address,
            if(appointment.consulting_room_ID IS NOT NULL, consulting_room.ID, NULL) AS consulting_room_ID,
            if(appointment.consulting_room_ID IS NOT NULL, consulting_room.room_number, NULL) AS consulting_room_number
		FROM patient, patron, appointment, employee, address_view, consulting_room,
			patient_patron, patient_appointment, employee_appointment
        WHERE patient_patron.patient_ID = patient.ID
        AND patient_patron.patron_ID = patron.ID
        AND patient_appointment.patient_ID = patient.ID
        AND patient_appointment.appointment_ID = appointment.ID
        AND employee_appointment.employee_ID = employee.ID
        AND employee_appointment.appointment_ID = appointment.ID
        AND (appointment.address_point_ID = address_view.ID OR appointment.consulting_room_ID = consulting_room.ID);

# part of Patron_View
CREATE VIEW Patron_With_Pets_View AS
SELECT patron.ID AS patron_ID, patron.first_name, patron.last_name,
			patient.ID AS patient_ID, patient.name AS patient_name,
            patron.phone, patron.email,
            address_view.ID AS address_ID,
			CONCAT(address_view.city, " ",
				if(address_view.street IS NOT NULL, CONCAT(address_view.street, " "), ""),
				address_view.house_apartment_number) AS address
		FROM patient, patron, address_view, patient_patron, patron_address_point
        WHERE patient_patron.patient_ID = patient.ID
        AND patient_patron.patron_ID = patron.ID
        AND patron_address_point.patron_ID = patron.ID
        AND patron_address_point.address_point_ID = address_view.ID;

# Patron_View
CREATE VIEW Patron_View AS
(SELECT * FROM patron_with_pets_view)
UNION ALL
(SELECT patron.ID AS patron_ID, patron.first_name, patron.last_name,
			NULL AS patient_ID, NULL AS patient_name,
            patron.phone, patron.email,
            address_view.ID AS address_ID,
			CONCAT(address_view.city, " ",
				if(address_view.street IS NOT NULL, CONCAT(address_view.street, " "), ""),
				address_view.house_apartment_number) AS address
		FROM patron, address_view, patron_address_point
        WHERE patron_address_point.patron_ID = patron.ID
        AND patron_address_point.address_point_ID = address_view.ID
        AND patron.ID NOT IN (SELECT patron_ID FROM patron_with_pets_view));

# Employee_View
CREATE VIEW Employee_View AS
SELECT employee.ID, employee.first_name, employee.last_name, position.ID AS position_ID, position.type AS position
		FROM employee, position
        WHERE employee.position_ID = position.ID;


delimiter //
CREATE TRIGGER calc_drug_cost_insert
  AFTER INSERT
  ON appointment_drug
  FOR EACH ROW
  BEGIN
    update appointment
    set DrugCost = (select sum(ad.Quantity * d.Cost) as Koszt
                    from appointment_drug ad
                           inner join drug d on d.Id = ad.Drug_ID
                    where appointment_id = new.Appointment_ID
                    group by ad.Appointment_Id)
    where ID = new.Appointment_ID;
  END;
//


CREATE TRIGGER calc_drug_cost_update
  AFTER UPDATE
  ON appointment_drug
  FOR EACH ROW
  BEGIN
    update appointment
    set DrugCost = (select sum(ad.Quantity * d.Cost) as Koszt
                    from appointment_drug ad
                           inner join drug d on d.Id = ad.Drug_ID
                    where appointment_id = new.Appointment_ID
                    group by ad.Appointment_Id)
    where ID = new.Appointment_ID;
  END;
//

CREATE TRIGGER calc_drug_cost_delete
  AFTER DELETE
  ON appointment_drug
  FOR EACH ROW
  BEGIN
    update appointment
    set DrugCost = (select sum(ad.Quantity * d.Cost) as Koszt
                    from appointment_drug ad
                           inner join drug d on d.Id = ad.Drug_ID
                    where appointment_id = old.Appointment_ID
                    group by ad.Appointment_Id)
    where ID = old.Appointment_ID;
  END;
//

delimiter ;

CREATE INDEX patron_last_name_idx
	ON patron (last_name);
CREATE INDEX employee_last_name_idx
	ON employee (last_name);
CREATE INDEX appointment_date_idx
	ON appointment (date);
CREATE INDEX employee_availability_date_idx
	ON employee_availability (date);
CREATE INDEX consulting_room_inaccessibility_date_idx
	ON consulting_room_inaccessibility (date);


create user 'not_logged_user'@'localhost'
	identified by 'not_logged_user';
create user 'receptionist_user'@'localhost'
	identified by 'receptionist_user';
create user 'doctor_user'@'localhost'
	identified by 'doctor_user';
create user 'drug_service_user'@'localhost'
	identified by 'drug_service_user';
create user 'admin_user'@'localhost'
	identified by 'admin_user';

grant select on wetwet.credentials to 'not_logged_user'@'localhost';
grant select on wetwet.employee to 'not_logged_user'@'localhost';
grant select on wetwet.position to 'not_logged_user'@'localhost';
grant select on wetwet.credentials to 'receptionist_user'@'localhost';
grant select on wetwet.credentials to 'doctor_user'@'localhost';

grant select, insert, update, delete on wetwet.consulting_room_inaccessibility to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.employee_availability to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.appointment to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.patient to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.species to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.breed to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.patron to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.address_point to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.street to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.city to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.employee_appointment to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.patient_appointment to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.patient_patron to 'receptionist_user'@'localhost';
grant select, insert, update, delete on wetwet.patron_address_point to 'receptionist_user'@'localhost';
grant select on wetwet.employee to 'receptionist_user'@'localhost';
grant select on wetwet.position to 'receptionist_user'@'localhost';
grant select on wetwet.consulting_room to 'receptionist_user'@'localhost';
grant select on wetwet.drug to 'receptionist_user'@'localhost';
grant select on wetwet.appointment_drug to 'receptionist_user'@'localhost';
grant select on wetwet.Patient_View to 'receptionist_user'@'localhost';
grant select on wetwet.Address_View to 'receptionist_user'@'localhost';
grant select on wetwet.Employee_Availability_View to 'receptionist_user'@'localhost';
grant select on wetwet.Consulting_Room_Inaccessibility_View to 'receptionist_user'@'localhost';
grant select on wetwet.Appointment_View to 'receptionist_user'@'localhost';
grant select on wetwet.Patron_With_Pets_View to 'receptionist_user'@'localhost';
grant select on wetwet.Patron_View to 'receptionist_user'@'localhost';
grant select on wetwet.Employee_View to 'receptionist_user'@'localhost';

grant select, insert, update, delete on wetwet.consulting_room_inaccessibility to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.employee_availability to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.appointment to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.patient to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.species to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.breed to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.patron to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.address_point to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.street to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.city to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.employee_appointment to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.patient_appointment to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.patient_patron to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.patron_address_point to 'doctor_user'@'localhost';
grant select on wetwet.employee to 'doctor_user'@'localhost';
grant select on wetwet.position to 'doctor_user'@'localhost';
grant select on wetwet.consulting_room to 'doctor_user'@'localhost';
grant select on wetwet.drug to 'doctor_user'@'localhost';
grant select on wetwet.appointment_drug to 'doctor_user'@'localhost';
grant select on wetwet.Patient_View to 'doctor_user'@'localhost';
grant select on wetwet.Address_View to 'doctor_user'@'localhost';
grant select on wetwet.Employee_Availability_View to 'doctor_user'@'localhost';
grant select on wetwet.Consulting_Room_Inaccessibility_View to 'doctor_user'@'localhost';
grant select on wetwet.Appointment_View to 'doctor_user'@'localhost';
grant select on wetwet.Patron_With_Pets_View to 'doctor_user'@'localhost';
grant select on wetwet.Patron_View to 'doctor_user'@'localhost';
grant select on wetwet.Employee_View to 'doctor_user'@'localhost';


grant insert, update, delete on wetwet.appointment_drug to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.drug to 'drug_service_user'@'localhost';
grant ALL PRIVILEGES on wetwet.* to 'admin_user'@'localhost'
WITH GRANT OPTION;
