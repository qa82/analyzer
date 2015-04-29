/*******************************************************************************
* Copyright (c) 2014 Michael Gebhart (michael.gebhart@qa82.org).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Michael Gebhart - initial idea and concept
* 
*******************************************************************************/

package org.qa82.analyzer.server;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class for tests using grizzly server.
 *
 */
public class Main {

    private static String uri = null;
    
    static Logger logger = Logger.getLogger(Main.class.getName());
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String port) {
        // create a resource config that scans for JAX-RS resources and providers in qa82 package
        final ResourceConfig rc = new ResourceConfig().packages("org.qa82.analyzer.server");
        rc.register(org.qa82.analyzer.server.ResponseContainerCorsFilter.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        uri = "http://localhost:" + port + "/analyzer-test/";
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
		return server;
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String port = "8080";
        if(args.length > 0 ){
            port = args[0];
        }

        final HttpServer server = startServer(port);

        logger.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", uri));
        System.in.read();
        server.shutdownNow();
    }

	
}
