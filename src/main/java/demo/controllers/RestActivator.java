package demo.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationPath("api")
@LoginConfig(authMethod = "MP-JWT", realmName = "JWT") // JWT AUTH
public class RestActivator extends Application {

	public RestActivator() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();

		// Activate ExceptionMappers
		resources.add(DefaultExceptionMapper.class);
		resources.add(ValidationExceptionMapper.class);

		// CORS
		resources.add(RestCorsProvider.class);

		// Others
		resources.add(DelayContainerRequestFilter.class);

		// Activate RESTful resources
		resources.add(AccountController.class);
		resources.add(AuthController.class);
		resources.add(TestController.class);

		return resources;
	}
}
