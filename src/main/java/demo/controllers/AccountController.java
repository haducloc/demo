package demo.controllers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.appslandia.common.utils.StringUtils;

import demo.entities.Account;
import demo.models.Result;
import demo.services.AccountService;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Path("/account")
@ApplicationScoped
public class AccountController {

	@Inject
	protected AccountService accountService;

	@GET
	@Path("/get/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account get(@PathParam("accountId") int accountId) throws Exception {
		Account obj = accountService.findByPk(accountId);
		if (obj == null) {
			throw new NotFoundException();
		}
		return obj;
	}

	@GET
	@Path("/queryUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> queryUser(@QueryParam("name") String name) throws Exception {
		name = StringUtils.trimToNull(name);
		return accountService.queryUser(name);
	}

	@DELETE
	@Path("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Result remove(@PathParam("accountId") int accountId) throws Exception {
		boolean result = accountService.remove(accountId);
		if (!result) {
			throw new NotFoundException();
		}
		return new Result().message("account was removed successfully.");
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result insert(@Valid Account account) throws Exception {
		accountService.insert(account);

		return new Result().data(account.getAccountId()).message("account was inserted successfully.");
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result update(@Valid Account account) throws Exception {
		boolean result = accountService.update(account);
		if (!result) {
			return new Result().asError().message("account update failed.");
		}
		return new Result().message("account was updated successfully.");
	}
}
