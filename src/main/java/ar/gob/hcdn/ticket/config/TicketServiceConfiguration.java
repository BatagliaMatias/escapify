package ar.gob.hcdn.ticket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketServiceConfiguration {

	@Value("${token.splitter}")
	private String tokenSplitter;
	
	public TicketServiceConfiguration() {
	}

	public String getTokenSplitter() {
		return tokenSplitter;
	}

	public void setTokenSplitter(String tokenSplitter) {
		this.tokenSplitter = tokenSplitter;
	}
	
}
