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
public class RestExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception ex) {
		// ex.printStackTrace();

		int status = 500;

		if (ex instanceof WebApplicationException) {
			status = ((WebApplicationException) ex).getResponse().getStatus();
		}

		return Response.status(status).entity(new Result().asError().message(ExceptionUtils.buildMessage(ex))).type(MediaType.APPLICATION_JSON).build();
	}
}