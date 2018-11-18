CREATE TABLE Employee (
  ID           int(10) NOT NULL AUTO_INCREMENT,
  First_Name         varchar(255) NOT NULL,
  Last_Name     varchar(255) NOT NULL,
  Position_ID int(10) NOT NULL,
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
  ID                    int(10) NOT NULL AUTO_INCREMENT,
  Title                 varchar(255) NOT NULL,
  Description           varchar(255),
  Cost                  int(10),
  Date                  date NOT NULL,
  Start_Time             time(6) NOT NULL,
  End_Time               time(6) NOT NULL,
  Address_Point_ID        int(10),
  Consulting_Room_ID      int(10),
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
  ID        int(10) NOT NULL AUTO_INCREMENT,
  Producent varchar(255) NOT NULL,
  Name      varchar(255) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Appointment_Drug (
  Appointment_ID int(10) NOT NULL,
  Drug_ID        int(10) NOT NULL);
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
