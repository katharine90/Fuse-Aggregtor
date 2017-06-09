package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * 
 * @author jastk
 *
 */

public class AggregatorRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:firstApi")
		.setHeader(Exchange.HTTP_METHOD, simple("GET"))
		.to("http://maps.googleapis.com/maps/api/geocode/json?address=stockholm,sweden");

		from("direct:secondApi")
		.setHeader(Exchange.HTTP_METHOD, simple("GET"))
		.to("http://data.stockholm.se/set/Befolkning/Arbetslosa?$filter=AREA_CODE%20eq%20%27SDO21%27%20and%20KONK_TEXT%20eq%20%27kvinnor%27%20and%20YEAR%20eq%20%272013%27&apikey=L4529E30G1I67B96C1R140PC88K10528");
		// .to("http://gturnquist-quoters.cfapps.io/api/random");

		from("direct:APIstart").aggregate(header("HEADERID"), new AggregationStrategyClass())
		.completionSize(2)
		.to("file:Output?fileName=Response.txt");

	}

}
