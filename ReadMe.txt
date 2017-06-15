Fuse Aggregator – a Fuse integration Project
This application is written with java DSL and build with Maven.  
It’s created mainly for composite services taken from two different json APIs that are later aggregated together as one message.

This project contains 3 java classes. The Camel Context is located in the AggregatorProgram.java class and it’s used as an interface that configures routes. The routes are located in AggregatorRoute.java class that is extended with a Route Builder. The third class, AggregationStrategyClass.java is responsible for merging messages and transforming them into a single message. 

The main class, AggregatorProgram.java creates a producer template that is connected to the Camel Context. Camel Context routes are first requested as objects. To enable the aggregation of the requests the objects are exchanged as strings.

AggregatorRoute.java contains three routes. The first two routes are requests to API’s. Both API’s are json applications. The first API is a google maps API that sends information about a chosen location. The second API randomly fetches quotes about Spring Boot. The third and last route aggregates both API’s using Aggregation Strategy that is located in the AggregationStrategyClass.java. 



=========================================
Camel Project for Java 
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn exec:java

To deploy the project in OSGi. For example using Apache Karaf.
You can run the following command from its shell:

    osgi:install -s mvn:com.mycompany/camel-java/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/
