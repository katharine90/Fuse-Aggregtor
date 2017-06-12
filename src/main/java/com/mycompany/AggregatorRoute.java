package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * This class is a collection of  3 Camel routes.
 * The routes included in this file have different endpoints. 
 * 
 * @author jastk
 * @version 1.0.0
 * @since 2017.06.09
 *
 */

public class AggregatorRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/**
		 * "direct:firstApi" and "direct:second" routes contain a 
		 * GET request from 2 different api endpoints.
		 */
		from("direct:firstApi")
		.setHeader(Exchange.HTTP_METHOD, simple("GET"))
		.to("http://maps.googleapis.com/maps/api/geocode/json?address=stockholm,sweden");

		from("direct:secondApi")
		.setHeader(Exchange.HTTP_METHOD, simple("GET")).log("${headers}")
		.to("http://gturnquist-quoters.cfapps.io/api/random");	
		
		/**
		 * "direct:APIstart" aggregates messages with headers named "HEADERID" 
		 * and send the respond to a file named Output.
		 */

		from("direct:APIstart").aggregate(header("HEADERID"), new AggregationStrategyClass())
		.completionSize(2)
		.to("file:Output?fileName=Response.txt");

	}

}
