package demo.controllers;

import javax.validation.ConstraintViolationException;
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
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException ex) {

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Result().asError().message(ExceptionUtils.buildMessage(ex)))
				.type(MediaType.APPLICATION_JSON).build();
	}
}