package demo.controllers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.appslandia.common.utils.ExceptionUtils;

import demo.models.Result;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Provider
public class JaxRsExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException ex) {

		return Response.status(ex.getResponse().getStatus()).entity(new Result().asError().message(ExceptionUtils.buildMessage(ex)))
				.type(MediaType.APPLICATION_JSON).build();
	}
}