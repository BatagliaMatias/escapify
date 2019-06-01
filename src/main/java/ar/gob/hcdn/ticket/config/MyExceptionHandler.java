package ar.gob.hcdn.ticket.config;

import com.mindscapehq.raygun4java.core.RaygunClient;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        RaygunClient client = new RaygunClient("ascWv3FpqAJeoDwZVlsVQg");
        client.send(e);
    }
}
