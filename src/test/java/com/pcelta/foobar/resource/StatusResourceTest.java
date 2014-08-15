package com.pcelta.foobar.resource;

import static org.junit.Assert.assertEquals;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pcelta.foobar.Main;
import com.pcelta.foobar.entity.Status;

public class StatusResourceTest {
	
    private HttpServer server;
    private WebTarget target;

	    @Before
	    public void setUp() throws Exception {
	        server = Main.startServer();
	        Client c = ClientBuilder.newClient();
	        target = c.target(Main.BASE_URI);
	    }

	    @After
	    public void tearDown() throws Exception {
	        server.stop();
	    }

	    @Test
	    public void testShow() {
	        String result = target.path("status").request().get(String.class);
	        
	        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
	        jsonBuilder.add("code", 200)
	        		   .add("description", "running");
	        
	        JsonObject expected = jsonBuilder.build();
	        assertEquals(expected.toString(), result);
	    }
}
