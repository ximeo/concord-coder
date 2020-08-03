# Concord Coder App
This is the test Spring Boot Java application for Concord Bank with using REST API and AES256-algorithm of coding of requests and responses.

### Used endpoints

* /users/names?encrypted  <br/>Used for POST requests in JSON-format that must contain id of the requested user in its body, e.g.: <br />
{ <br />
 &nbsp;&nbsp; "id": 1 <br/> } <br />
 and gives responses contain encrypted full user name, for example: <br/>
{<br />&nbsp;&nbsp;"fio_encr": "sfdjnva9sfv87say9hdfow3"<br/>}

* /users/names?decrypted - for POST requests in JSON-format that must contain the name of the requested user in 
encrypted form, e.g.:<br/> {<br/>&nbsp;&nbsp;"fio_encr": "sfdjnva9sfv87say9hdfow3"<br/>}<br/>and gives responses contain decrypted
 full user name, for example:<br/>
 {<br/>&nbsp;&nbsp;"fio": "John Dou"<br/>}<br/>
 
 ### Exceptional situations
 
if the user with the passed id does not exist, or an invalid name is passed, or an internal server error occurs, you will receive an error message in JSON-format with error-status and describing the problem.
 
 ### Start up
 
 You can start application with several ways:
 1. By running the main method of com.concord.coder.ConcordUserNameApplication class (if you are using one of IDEs or having source code).
 2. By using command java -jar and .war file (if you have installed JDK or JRE on your computer).
 3. By using Apache Tomcat server (placing the application .war file in its webapps folder in advance).