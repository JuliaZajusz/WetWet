CREATE TABLE Employee (
  ID          int(10)      NOT NULL AUTO_INCREMENT,
  User_Name   varchar(255) NOT NULL,
  First_Name  varchar(255) NOT NULL,
  Last_Name   varchar(255) NOT NULL,
  Position_ID int(10)      NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Position (
  ID  int(10) NOT NULL AUTO_INCREMENT,
  Type varchar(255) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Employee_Availability (
  Date                  date NOT NULL,
  Start_Time             time(6) NOT NULL,
  End_Time              time(6) NOT NULL,
  Employee_ID           int(10) NOT NULL);
CREATE TABLE Employee_Appointment (
  Appointment_ID    int(10) NOT NULL,
  Employee_ID       int(10) NOT NULL);
CREATE TABLE Appointment (
  ID                 int(10) NOT NULL AUTO_INCREMENT,
  Title              varchar(255) NOT NULL,
  Description        varchar(255),
  Cost               int(10),
  Date               date NOT NULL,
  Start_Time         time(6) NOT NULL,
  End_Time           time(6) NOT NULL,
  Address_Point_ID   int(10),
  Consulting_Room_ID int(10),
  DrugCost           int(10),
  PRIMARY KEY (ID));
CREATE TABLE Consulting_Room (
  ID               int(10) NOT NULL AUTO_INCREMENT,
  Room_Number       varchar(10) NOT NULL,
  Description      varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Consulting_Room_Inaccessibility (
  Date                  date NOT NULL,
  Start_Time             time(6) NOT NULL,
  End_Time               time(6) NOT NULL,
  Consulting_Room_ID      int(10) NOT NULL);
CREATE TABLE Drug (
  ID        int(10)      NOT NULL AUTO_INCREMENT,
  Producent varchar(255) NOT NULL,
  Name      varchar(255) NOT NULL,
  Cost      int(10)      NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Appointment_Drug (
  Appointment_ID int(10) NOT NULL,
  Drug_ID        int(10) NOT NULL,
  Quantity       int(10) NOT NULL
);
CREATE TABLE Patient_Appointment (
  Patient_ID int(10) NOT NULL,
  Appointment_ID  int(10) NOT NULL);
CREATE TABLE Patient (
  ID              int(10) NOT NULL AUTO_INCREMENT,
  Name            varchar(255),
  Sex             char(1),
  Birthdate       char(10),
  Coat            varchar(255),
  Special_Characters varchar(255),
  Breed_ID          int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Breed (
  ID        int(10) NOT NULL AUTO_INCREMENT,
  Name     varchar(255) NOT NULL,
  Species_ID int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Patron (
  ID            int(10) NOT NULL AUTO_INCREMENT,
  First_Name     varchar(255) NOT NULL,
  Last_Name      varchar(255) NOT NULL,
  Phone         varchar(30),
  Email         varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Patient_Patron (
  Patron_ID int(10) NOT NULL,
  Patient_ID int(10) NOT NULL);
CREATE TABLE Address_Point (
  ID               int(10) NOT NULL AUTO_INCREMENT,
  House_Apartment_Number varchar(20) NOT NULL,
  Street_ID         int(10),
  City_ID           int(10),
  PRIMARY KEY (ID));
CREATE TABLE Street (
  ID       int(10) NOT NULL AUTO_INCREMENT,
  Name    varchar(255) NOT NULL,
  City_ID int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE City (
  ID    int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Species (
  ID    int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Patron_Address_Point (
  Patron_ID          int(10) NOT NULL,
  Address_Point_ID int(10) NOT NULL);
CREATE TABLE Credentials (
  Login        varchar(255) NOT NULL,
  Password_Hash char(255) NOT NULL,
  Employee_ID integer(10));
ALTER TABLE Patient ADD CONSTRAINT FKPatient167679 FOREIGN KEY (Breed_ID) REFERENCES Breed (ID);
ALTER TABLE Patient_Patron ADD CONSTRAINT `FKPatient_Pa345357` FOREIGN KEY (Patient_ID) REFERENCES Patient (ID);
ALTER TABLE Patient_Patron ADD CONSTRAINT `FKPatient_Pa833237` FOREIGN KEY (Patron_ID) REFERENCES Patron (ID);
ALTER TABLE Street ADD CONSTRAINT FKStreet87761 FOREIGN KEY (City_ID) REFERENCES City (ID);
ALTER TABLE Address_Point ADD CONSTRAINT `FKAddressPo835387` FOREIGN KEY (Street_ID) REFERENCES Street (ID);
ALTER TABLE Patient_Appointment ADD CONSTRAINT `FKPatient_App90145` FOREIGN KEY (Patient_ID) REFERENCES Patient (ID);
ALTER TABLE Patient_Appointment ADD CONSTRAINT `FKPatient_App973838` FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAppointment43210 FOREIGN KEY (Address_Point_ID) REFERENCES Address_Point (ID);
ALTER TABLE Appointment_Drug ADD CONSTRAINT `FKAppointment_Drug834976` FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Appointment_Drug ADD CONSTRAINT `FKAppointment_Dru716135` FOREIGN KEY (Drug_ID) REFERENCES Drug (ID);
ALTER TABLE Employee_Appointment ADD CONSTRAINT `FKEmployee_App457410` FOREIGN KEY (Appointment_ID) REFERENCES Appointment (ID);
ALTER TABLE Employee_Appointment ADD CONSTRAINT `FKEmployee_App722500` FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAppointment967039 FOREIGN KEY (Consulting_Room_ID) REFERENCES Consulting_Room (ID);
ALTER TABLE Consulting_Room_Inaccessibility ADD CONSTRAINT FKInaccessibility736354 FOREIGN KEY (Consulting_Room_ID) REFERENCES Consulting_Room (ID);
ALTER TABLE Employee ADD CONSTRAINT FKEmployee688435 FOREIGN KEY (Position_ID) REFERENCES Position (ID);
ALTER TABLE Employee_Availability ADD CONSTRAINT FKAvailability441313 FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);
ALTER TABLE Breed ADD CONSTRAINT FKRasa237872 FOREIGN KEY (Species_ID) REFERENCES Species (ID);
ALTER TABLE Patron_Address_Point ADD CONSTRAINT `FKPatron_Addr697410` FOREIGN KEY (Patron_ID) REFERENCES Patron (ID);
ALTER TABLE Patron_Address_Point ADD CONSTRAINT `FKPatron_Addr915388` FOREIGN KEY (Address_Point_ID) REFERENCES Address_Point (ID);
ALTER TABLE Address_Point ADD CONSTRAINT `FKAddressPoint214154` FOREIGN KEY (City_ID) REFERENCES City (ID);
ALTER TABLE Credentials ADD CONSTRAINT FKCredentials691803 FOREIGN KEY (Employee_ID) REFERENCES Employee (ID);


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
create user 'admin_user'@'localhost'
  identified by 'admin_user';

grant select on wetwet.credentials to 'not_logged_user'@'localhost';
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
# ****
grant select on wetwet.Address_View to 'receptionist_user'@'localhost';
grant select on wetwet.Employee_Availability_View to 'receptionist_user'@'localhost';
grant select on wetwet.Consulting_Room_Inaccessibility_View to 'receptionist_user'@'localhost';
grant select on wetwet.Appointment_View to 'receptionist_user'@'localhost';
grant select on wetwet.Patron_With_Pets_View to 'receptionist_user'@'localhost';
# grant select on wetwet.Patron_View  to 'receptionist_user'@'localhost';
grant select on wetwet.Employee_View to 'receptionist_user'@'localhost';
# ****

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
# ****
grant select on wetwet.Address_View to 'doctor_user'@'localhost';
grant select on wetwet.Employee_Availability_View to 'doctor_user'@'localhost';
grant select on wetwet.Consulting_Room_Inaccessibility_View to 'doctor_user'@'localhost';
grant select on wetwet.Appointment_View to 'doctor_user'@'localhost';
grant select on wetwet.Patron_With_Pets_View to 'doctor_user'@'localhost';
# grant select on wetwet.Patron_View  to 'doctor_user'@'localhost';
grant select on wetwet.Employee_View to 'doctor_user'@'localhost';
# ****


grant insert, update, delete on wetwet.appointment_drug to 'doctor_user'@'localhost';
grant select, insert, update, delete on wetwet.* to 'admin_user'@'localhost';
