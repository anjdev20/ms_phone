# ms_phone
example microservice to retrieve phone numbers details

Run application: ./mvnw spring-boot:run

# Curl commands to check endpoints
curl -X GET "http://localhost:8080/conatctmgmt/phone-details"
curl -X GET "http://localhost:8080/phone-details?userId=cus1"
curl -X PATCH "http://localhost:8080/phone-status/+614111111111/Active"
