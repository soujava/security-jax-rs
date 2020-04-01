# Security

This is a dump test to security with JAX-RS and Jakarta EE security.

## Commands 

* `mvn clean package payara-micro:bundle`
* `java -jar target/microprofile-microbundle.jar`


## How to Test

* `curl --location --request GET 'http://localhost:8080/'` -> it returns 401
* `curl --location --request GET 'http://localhost:8080/?username=username&password=password'` -> it returns 403 (Weird it should be 200)