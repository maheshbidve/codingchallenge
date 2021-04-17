-------------------SmartHost Coding Challenge--------------
API documnetation can be found at following url
http://localhost:8081/swagger-ui.html

Following command can be used to build and test the project in the base directory
mvn clean install

Following command can be used to run the project in target folder
java -jar codingchallenge-0.0.1-SNAPSHOT.jar

Following are the API details which can be used by client

1. API to get booking details based on available rooms
Url : http://localhost:8081/v1/bookings
Method Type : GET
Input Params : 
	numberOfEconomyRooms - Integer
	numberOfPremiumRooms - Integer
	
Output - Json
	e.g. {"occupiedEconomyRooms":3,"occupiedPremiumRooms":3,"economyRoomsAmount":167.99,"premiumRoomsAmount":738.0}
