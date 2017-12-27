package com.gd.studentapp;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        Config appConfig = ConfigFactory.load();

        ResourceConfig config = new ResourceConfig();

        config.packages("com.gd.studentapp");
        config.register(new DependencyBinder());

        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(appConfig.getInt("port"));
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error("Server error: " + e.getLocalizedMessage());
        } finally {
            server.destroy();
        }
    }
}
