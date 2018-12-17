# WetWet
aplikacja rezerwacyjna dla kliniki weterynaryjnej

uruchamianie backendu:
```ReservationServiceApplication.java```

uruchamianie frontendu:
```
   cd reservationservice-web
   yarn
   yarn start
```



DODANIE PIERWSZEGO UŻYTKOWNIKA

Przez aplikację postman wysłać request POST pod adres ```localhost:8080/authorization/sign-up```, w którego body (format raw, JSON):
```
{
	"credentials":{
		"login": "admin",
		"password": "admin"
	},
	"employee": {
		"firstName": "Admin",
		"lastName": "Admin",
		"positionId": 4
		
	}
}
```

żeby się upewnić, że można się zalogować, można wysłac request POST pod adres 
```localhost:8080/authorization/login``` 

```{
   		"login": "admin",
   		"password": "admin"
}```
