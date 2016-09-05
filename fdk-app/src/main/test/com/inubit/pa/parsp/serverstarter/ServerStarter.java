package com.inubit.pa.parsp.serverstarter;

import java.util.logging.Handler;
import java.util.logging.LogManager;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class ServerStarter {

    private static final Logger LOG = LoggerFactory.getLogger(ServerStarter.class);

    private static Tomcat tomcat;

    public static void main(String[] args)throws ServletException, LifecycleException {
        ServerStarter.initSlf4jBridge();
        setupTomcat();

        LOG.info("starting Tomcat");
        tomcat.start();
        tomcat.getServer().await();
    }

    private static void setupTomcat() throws ServletException {
        tomcat = new Tomcat();

        tomcat.setPort(8080);
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        
        //  connector.set
        tomcat.getService().addConnector(connector);
        tomcat.setConnector(connector);

        // connector.setProtocol();
        org.apache.coyote.http11.Http11NioProtocol handler = (Http11NioProtocol) connector.getProtocolHandler();
        handler.setPollerThreadCount(20);

        tomcat.setBaseDir(".");
        tomcat.getHost().setAppBase(".");

        tomcat.enableNaming();

        tomcat.addWebapp("/parsp-app", "../target/parsp-app-0.0.1");
    }

    private static void initSlf4jBridge() {
        Handler[] handlers = LogManager.getLogManager().getLogger("").getHandlers();
        for (Handler handler : handlers) {
            LogManager.getLogManager().getLogger("").removeHandler(handler);
        }

        SLF4JBridgeHandler.install();


        java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger("taskmanager");
        julLogger.warning("hello world, welcome to taskmanager"); // this will get redirected to SLF4J
    }

}