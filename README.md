GoEuro
======

A very simple application that calls the GoEuro API and exports some parts of the json response to a csv file. In case the csv file already exists, the response data is appended.


Usage
-----
Simply use the jar file from the result folder, which is named GoEuroTest.jar and call it in the following way:

```
java -jar GoEuroTest.jar "New York"
```


Building the Project
--------------------

```
mvn clean package
```

