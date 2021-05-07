package demo.controllers;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.appslandia.common.utils.StringFormat;

import demo.models.Result;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Path("/test")
@ApplicationScoped
public class TestController {

	// JAX-RS Security Context

	@Context
	protected SecurityContext securityContext;

	@GET
	@Path("/admin")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "admin" })
	public Result admin() throws Exception {
		return new Result().message("admin authorized.");
	}

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "user" })
	public Result user() throws Exception {
		return new Result().message("user authorized.");
	}

	@GET
	@Path("/role")
	@Produces(MediaType.APPLICATION_JSON)
	public Result check(@NotNull @QueryParam("role") String role) throws Exception {
		return new Result().message(StringFormat.fmt("authenticationScheme={}, userPrincipal={}, isUserInRole('{}')={}",
				securityContext.getAuthenticationScheme(), securityContext.getUserPrincipal(), role, securityContext.isUserInRole(role)));
	}
}
