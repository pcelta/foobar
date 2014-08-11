package com.pcelta.foobar.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pcelta.foobar.entity.Status;

@Path("status")

public class StatusResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Status show()
	{
    	return Status.createSuccess();
	}
}
