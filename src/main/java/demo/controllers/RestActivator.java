package demo.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

import demo.jaxrs.DefaultExceptionMapper;
import demo.jaxrs.DelayContainerRequestFilter;
import demo.jaxrs.JaxRsExceptionMapper;
import demo.jaxrs.RestCorsProvider;
import demo.jaxrs.TestErrorContainerRequestFilter;
import demo.jaxrs.ValidationExceptionMapper;

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
		resources.add(ValidationExceptionMapper.class);
		resources.add(JaxRsExceptionMapper.class);
		resources.add(DefaultExceptionMapper.class);

		// CORS
		resources.add(RestCorsProvider.class);

		// Others
		resources.add(DelayContainerRequestFilter.class);
		resources.add(TestErrorContainerRequestFilter.class);

		// Activate RESTful resources
		resources.add(AccountController.class);
		resources.add(AuthController.class);
		resources.add(TestController.class);

		return resources;
	}
}
