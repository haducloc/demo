package demo.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.appslandia.common.utils.AssertUtils;
import com.appslandia.common.utils.ModelUtils;

import demo.entities.Account;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class AccountService {

	@Inject
	protected EntityManager em;

	public Account findByPk(int accountId) throws Exception {
		return em.find(Account.class, accountId);
	}

	public Account findByEmail(String email) throws Exception {
		try {
			return em.createNamedQuery("Account.findByEmail", Account.class).setParameter("email", email).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<Account> getAll() throws Exception {
		return em.createNamedQuery("Account.getAll", Account.class).getResultList();
	}

	// CMT: Container Managed Transaction
	@Transactional
	public Account insert(Account account) throws Exception {
		AssertUtils.assertNotNull(account);
		AssertUtils.assertNull(account.getAccountId());

		em.persist(account);
		em.flush();

		return account;
	}

	@Transactional
	public boolean update(Account account) throws Exception {
		AssertUtils.assertNotNull(account);
		AssertUtils.assertNotNull(account.getAccountId());

		Account managed = em.find(Account.class, account.getAccountId());
		if (managed == null) {
			return false;
		}

		ModelUtils.copy(managed, account, "email", "password", "name", "roles");
		return true;
	}

	@Transactional
	public boolean remove(int accountId) throws Exception {
		try {
			Account managed = em.getReference(Account.class, accountId);
			em.remove(managed);
			return true;

		} catch (EntityNotFoundException ex) {
			return false;
		}
	}
}
