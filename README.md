# ms_phone
example microservice to retrieve phone numbers details
API spec available at: src/main/java/com/example/phone/api/Phone_Details_API.yaml
Run application: ./mvnw spring-boot:run

# Curl commands to check endpoints
curl -X GET "http://localhost:8080/conatctmgmt/phone-details"
curl -X GET "http://localhost:8080/phone-details?userId=cus1"
curl -X PATCH "http://localhost:8080/phone-status/+614111111111/Active"
