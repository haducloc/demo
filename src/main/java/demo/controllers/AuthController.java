package demo.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.appslandia.common.crypto.DsaDigester;
import com.appslandia.common.crypto.KeyFactoryUtil;
import com.appslandia.common.json.JsonProcessor;
import com.appslandia.common.jwt.JwtHeader;
import com.appslandia.common.jwt.JwtPayload;
import com.appslandia.common.jwt.JwtProcessor;
import com.appslandia.common.jwt.JwtSigner;
import com.appslandia.common.jwt.JwtToken;
import com.appslandia.common.utils.IOUtils;
import com.appslandia.common.utils.SplitUtils;

import demo.entities.Account;
import demo.models.Result;
import demo.services.AccountService;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Path("/auth")
@ApplicationScoped
public class AuthController {

	@Inject
	protected AccountService accountService;

	@Inject
	protected JsonProcessor jsonProcessor;

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Result login(@NotNull @QueryParam("email") String email, @NotNull @QueryParam("password") String password) throws Exception {

		Account user = this.accountService.findByEmail(email);

		// NOTES: Demo uses clear passwords
		if (user == null || !user.getPassword().equals(password)) {
			return new Result().asError().message("Invalid credentials.");
		}

		// JWT
		String jwt = generateJwt(user.getEmail(), SplitUtils.split(user.getRoles(), ','), 45, TimeUnit.MINUTES);

		return new Result().data(jwt).message("Login succeeded.");
	}

	// JWT: RS256 & SHA256withRSA
	private String generateJwt(String username, String[] userRoles, int expiresIn, TimeUnit expiresInUnit) throws Exception {
		JwtProcessor jwtProcessor = new JwtProcessor().setJsonProcessor(this.jsonProcessor);
		jwtProcessor.setJwtSigner(new JwtSigner().setAlg("RS256").setSigner(new DsaDigester().setAlgorithm("SHA256withRSA").setPrivateKey(loadPrivateKey())));
		jwtProcessor.setIssuer("demo");

		JwtHeader header = jwtProcessor.newHeader();
		JwtPayload payload = jwtProcessor.newPayload().setExpiresIn(expiresIn, expiresInUnit).setSubject(username).set("groups", userRoles);

		return jwtProcessor.toJwt(new JwtToken(header, payload));
	}

	private PrivateKey loadPrivateKey() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("META-INF/jwt_prikey.pem")))) {

			return KeyFactoryUtil.RSA.toPrivateKey(IOUtils.toString(reader));

		} catch (Exception ex) {
			return null;
		}
	}
}
