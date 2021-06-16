package demo.jaxrs;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Provider
public class RestCorsProvider implements Feature {

	@Override
	public boolean configure(FeatureContext context) {

		// RESTEasy CORS Filter
		CorsFilter corsFilter = new CorsFilter();

		corsFilter.getAllowedOrigins().add("*");
		corsFilter.setAllowCredentials(true);

		corsFilter.setCorsMaxAge((int) TimeUnit.SECONDS.convert(90, TimeUnit.DAYS));
		corsFilter.setAllowedMethods("GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");

		// SAFE: Accept, Accept-Language, Content-Language, Content-Type
		corsFilter.setAllowedHeaders(
				"Content-Type, Origin, Authorization, Upgrade-Insecure-Requests, Accept-Encoding, X-Requested-With, If-None-Match, If-Match, If-Modified-Since");

		// SAFE: Cache-Control, Content-Language, Content-Length, Content-Type, Expires,
		// Last-Modified, Pragma
		corsFilter.setExposedHeaders("WWW-Authorization, Content-Encoding, ETag, Vary");

		context.register(corsFilter);
		return true;
	}
}