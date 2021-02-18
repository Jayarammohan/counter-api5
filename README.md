Word Count Project
==================

Using Spring MVC and JSON, implement RESTful web services to return the word count within the given input file.

Spring MVC Json Example

This Spring MVC JSON application is for solving the Java programming task assigned to me . We are given a text file and the aim is to 
create RESTful services which
1. return the count of given words (number of occurrences of the word within the input file)
2. provide the top n number of (as path variable) texts which has the highest counts in the input file

Technologies used :

Spring 3.2.2.RELEASE, Jackson 1.9.13, Maven 3, Java 8,  Tomcat 7.0.65, Eclipse
Spring Security 3.2.0, Given input data(given as part of the programming task) contained in text.txt

To build the project
====================
cd workspace/counter-api5-main

mvn clean

mvn tomcat:run 

Now, the application gets started up and we get siimilar output to the below on the screen:

Mar 25, 2017 7:27:45 PM org.springframework.web.servlet.FrameworkServlet initServletBean
INFO: FrameworkServlet 'dispatcher': initialization completed in 144 ms
Mar 25, 2017 7:27:46 PM org.apache.coyote.http11.Http11Protocol init
INFO: Initializing Coyote HTTP/1.1 on http-8080
Mar 25, 2017 7:27:46 PM org.apache.coyote.http11.Http11Protocol start
INFO: Starting Coyote HTTP/1.1 on http-8080

Once the application is up and running, we can commence testing. 


Test Results
==============

$ curl http://localhost:8080/counter-api/top/25 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -H"Accept: text/csv"
 17|vel 17|eget 16|sed 15|in 14|et 13|eu 13|ut 12|sit 12|nulla 12|metus 12|id 12|amet 12|ac 11|ipsum 11|duis 11|at 11|vitae 11|nec 10|nunc 10|non 10|dolor 10|aliquam 9|leo 9|consectetur 9|a

$ curl http://localhost:8080/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d'{"searchText":["Duis","Sed","Augue","Donec","Pellentesque","123"]}' -H"Content-Type: application/json" -X POST {"counts":[{"duis":11},{"sed":16},{"augue":7},{"donec":8},{"pellentesque":6},{"123":0}]}

In the curl command, we might substitute the authorization string as -u optus:candidates instead, if we wish.
I used Postman to verify the POST command.
I also used the browser to verify top so many items. It prompts for credentials and I give optus and candidates. Then
the csv file gets downloaded with the appropriate number of topmost items.
Assumptions :
-------------
1. the count of words is case insensitive, ie., it counts Duis and duis occurrences each occurring in the file as 2 counts.
2. If the file contents are modified, then the new file called text.txt needs to be deposited into the location src/main/resources (where it currently exists) and then the server restart is needed to produce correct output
The file's words cannot contain special characters such as apostrophe etc since the split regex currently does not cater for special chars. In other words, should the file need to contain such special characters, the program needs to be modified to cater for that.


Validations
-----------
3.In /top url, if the number of top items is alphanumeric, it is validated and error is returned
If the number of items exceeds int's capacity, ie., 2**31 -1, then also validation is done and error reported
However, if it is <=0, then an empty string is returned.


For the search command, if the input json string contains error, validation captures it and it is reported in terms of the http status code and the error.
 
