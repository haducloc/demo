package demo.jaxrs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
public class DelayContainerRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String __delaySec = requestContext.getUriInfo().getQueryParameters().getFirst("__delaySec");
		int delaySec = ParseUtils.parseInt(__delaySec, 0);

		if (delaySec > 0) {
			ThreadUtils.sleepInMs(delaySec, TimeUnit.SECONDS);
		}
	}
}