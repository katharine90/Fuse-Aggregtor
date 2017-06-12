package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * This is a Aggregation strategy class that aggregates messages
 * @author jastk
 * @version 1.0.0
 * @since 2017.06.09
 */

public class AggregationStrategyClass implements AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange orginal, Exchange resource) {
		if (orginal == null) {
			return resource;
		}
	String oldBody = orginal.getIn().getBody(String.class);
    String newBody =  resource.getIn().getBody(String.class);
    String body = "First respond: " + oldBody + "Second respond: " + newBody;
    orginal.getIn().setBody(body);
    
	return orginal;
	}

}
