package com.epam.web.rest;

import com.epam.model.LoginModel;
import com.epam.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public interface UserService {
    @GET
    @Path("/users")
    @Produces("application/json; charset=UTF-8")
    Response getAllUsers();

    @GET
    @Path("/roles")
    @Produces("application/json; charset=UTF-8")
    Response getRoles();

    @GET
    @Path("/role/{role}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response getUsersByRole(@PathParam("role") String role);

    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response addUser(@RequestBody User user);

    @DELETE
    @Path("/user/delete")
    @Produces("application/json; charset=UTF-8")
    Response removeUser(User user);

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response logIn(@RequestBody LoginModel loginModel);
}
