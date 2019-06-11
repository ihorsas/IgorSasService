package com.epam.web.rest;

import com.epam.model.LoginModel;
import com.epam.model.User;

import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public interface UserService {
    @GET
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response getAllUsers();

    @GET
    @Path("/roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response getRoles();

    @GET
    @Path("/role/{role}")
    @Produces("application/json; charset=UTF-8")
    Response getUsersByRole(@PathParam("role") String role);

    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response addUser(User user);

    @DELETE
    @Path("/user/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response removeUser(User user);

    @POST
    @Path("/loginModel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response logIn(LoginModel loginModel);
}
