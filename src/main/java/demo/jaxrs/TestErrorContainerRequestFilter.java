package demo.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.appslandia.common.utils.ParseUtils;

import demo.models.Result;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Provider
@Priority(2)
public class TestErrorContainerRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String __error = requestContext.getUriInfo().getQueryParameters().getFirst("__error_status");
		int error = ParseUtils.parseInt(__error, 0);

		if (400 <= error && error < 600) {

			requestContext.abortWith(
					Response.status(error).entity(new Result().asError().message("__error_status=" + error)).type(MediaType.APPLICATION_JSON).build());
		}
	}
}