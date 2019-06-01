package ar.gob.hcdn.ticket;

import ar.gob.hcdn.ticket.config.MyExceptionHandler;
import ar.gob.hcdn.ticket.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;
@SpringBootApplication
public class ServicioTicketRun {

	private static Logger logger = Logger.getLogger(ServicioTicketRun.class.getSimpleName());

	public static void main(String[] args) {

		Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
		SpringApplication.run(new Object[] { ServicioTicketRun.class, SwaggerConfig.class }, args);
	}

}
