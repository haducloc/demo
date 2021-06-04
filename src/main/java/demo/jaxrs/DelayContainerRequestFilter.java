package demo.jaxrs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.appslandia.common.utils.ParseUtils;
import com.appslandia.common.utils.ThreadUtils;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Provider
@Priority(1)
public class DelayContainerRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String __delayMs = requestContext.getUriInfo().getQueryParameters().getFirst("__delayMs");
		int delayMs = ParseUtils.parseInt(__delayMs, 0);

		if (delayMs > 0) {
			ThreadUtils.sleepInMs(delayMs, TimeUnit.MILLISECONDS);
		}
	}
}