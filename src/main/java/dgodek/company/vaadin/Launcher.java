package dgodek.company.vaadin;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
    public static void main(String[] args) throws Exception {
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase("src/main/resources");
        context.setClassLoader(new WebAppClassLoader(Launcher.class.getClassLoader(), context));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }
}
