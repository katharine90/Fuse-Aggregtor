package com.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.util.ExchangeHelper;

/**
 * This class creates a CamelContext that connects the AggregatorRoute with a
 * producer template. Object Api1 and Api2 requests the body of first two routes
 * that are located in the AggregatorRoute class and later convert them into
 * strings. The converted strings (response1 and response2) are late send as
 * messages with a HEADERID set to "1".
 * 
 * @author jastk
 * @version 1.0.0
 * @since 2017.06.09
 *
 */
public class AggregatorProgram {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new AggregatorRoute());

		context.start();
		Thread.sleep(5000);
		ProducerTemplate template = context.createProducerTemplate();

		Object Api1 = template.requestBody("direct:firstApi", null, String.class);
		Object Api2 = template.requestBody("direct:secondApi", null, String.class);
		Exchange exchange = new DefaultExchange(context);

		String response1 = ExchangeHelper.convertToType(exchange, String.class, Api1);
		String response2 = ExchangeHelper.convertToType(exchange, String.class, Api2);

		template.sendBodyAndHeader("direct:APIstart", response1, "HEADERID", 1);
		template.sendBodyAndHeader("direct:APIstart", response2, "HEADERID", 1);

		context.stop();

	}

}
