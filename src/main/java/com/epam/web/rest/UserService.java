package com.epam.web.rest;

import com.epam.model.User;
import com.epam.model.Role;

import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface UserService {
    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response getAllUsers();

    @GET
    @Path("/user/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response getRoles(@PathParam("username") String name);

    @GET
    @Path("/role")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response getUserByRole(Role role);

    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response addUser(User user);

    @DELETE
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response removeBook(User user);

}
