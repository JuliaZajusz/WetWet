insert into position (Type) values("receptionist");
insert into employee (First_Name, Last_Name, Position_ID) values ("Jan", "Kowalski", 1);
insert into employee (First_Name, Last_Name, Position_ID) values ("Andrzej", "Wolej", 1);

insert into Species(Name) values ("Pies");
insert into Species(Name) values ("Kot");
insert into Species(Name) values ("Rybka");
insert into Species(Name) values ("koń");
insert into Species(Name) values ("krowa");
insert into Species(Name) values ("Świnka morska");

insert into Breed(Name, Species_ID) values ("Owczarek niemiecki", 1);
insert into Breed(Name, Species_ID) values ("jamnik", 1);
insert into Breed(Name, Species_ID) values ("buldog", 1);
insert into Breed(Name, Species_ID) values ("kot perski", 2);
insert into Breed(Name, Species_ID) values ("mieszaniec", 2);
insert into Breed(Name, Species_ID) values ("kot syberyjski", 2);
insert into Breed(Name, Species_ID) values ("Sum", 3);
insert into Breed(Name, Species_ID) values ("Gupik", 3);
insert into Breed(Name, Species_ID) values ("welonka", 3);
insert into Breed(Name, Species_ID) values ("kucyk", 4);
insert into Breed(Name, Species_ID) values ("mleczna", 5);
insert into Breed(Name, Species_ID) values ("krowa amerykańska", 5);

insert into patient (Name, Sex, Birthdate, Coat, Special_Characters, Breed_ID) values ("Frodo", "F", "2010-10-10", "coat", "special", 1);
insert into patient (Name, Sex, Birthdate, Coat, Special_Characters, Breed_ID) values ("Rex", "M", "2015-01-01", "czarny w białe łaty", "jedno oko zielone, drugie niebieskie", 1);

insert into patron (First_Name, Last_Name, Phone, Email) values ("Anna", "Kowalska", "657101002", "aniaka@gmail.com");
insert into patron (First_Name, Last_Name, Phone, Email) values ("Mariusz", "Wtorek", "3233698976", "mariusz.wtorek@gmail.com");
insert into patron (First_Name, Last_Name, Phone, Email) values ("Karolina Maria", "Kawczyńska-Kluczkowska", "", "kmkkluczkowska@hotmail.com");

insert into patient_patron (Patron_ID, Patient_ID) values (1,1);
insert into patient_patron (Patron_ID, Patient_ID) values (1,2);
insert into patient_patron (Patron_ID, Patient_ID) values (2,2);

insert into appointment(Title, Description, Cost, Date, Start_Time, End_Time) values ("Wizyta kontrolna", "To jest opis wizyty", 50, DATE("2018-11-10"), TIME("17:00:00"), TIME("19:30:10"));
insert into appointment(Title, Description, Cost, Date, Start_Time, End_Time) values ("Szczepienie na wsciekliznę", "Pies byl bardzo niespokojny, ale szczepienie się powiodło, podano 50mg Hydroxycyny", 97.99, DATE("2018-11-11"), TIME("11:00:00"), TIME("11:30:59"));

insert into patient_appointment(Patient_ID, appointment_ID) values( 1, 1);
insert into patient_appointment(Patient_ID, appointment_ID) values( 2, 2);

insert into employee_appointment(appointment_ID, Employee_ID) values( 1, 1);
insert into employee_appointment(appointment_ID, Employee_ID) values( 2, 2);

insert into consulting_room(room_number, description) values ("001", "Gabinet chirurgiczny");
insert into consulting_room(room_number, description) values ("002", "Gabinet lekarski");

insert into address_point(House_Apartment_Number) values ("52");

insert into drug (Producent, Name, Cost)
values ("Bayer", "Wapno", 5);
insert into drug (Producent, Name, Cost)
values ("Polpharma OTC", "Etopiryna", 7);


insert into appointment_drug
values (1, 1, 3);
insert into appointment_drug
values (1, 2, 1);

insert into appointment_drug
values (2, 1, 1);
insert into appointment_drug
values (2, 1, 1);
insert into appointment_drug
values (2, 2, 1);
