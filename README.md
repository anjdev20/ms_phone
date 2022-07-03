# ms_phone
example microservice to retrieve phone numbers details
API spec available at: src/main/java/com/example/phone/api/Phone_Details_API.yaml
Run application: ./mvnw spring-boot:run

# H2 console access
http://localhost:8080/h2-console/

# Curl commands to check endpoints
curl -X GET "http://localhost:8080/conatctmgmt/phone-details"
curl -X GET "http://localhost:8080/phone-details?customer-id=cus1"
curl -X PATCH "http://localhost:8080/phone-status/+61411111111/Active"
