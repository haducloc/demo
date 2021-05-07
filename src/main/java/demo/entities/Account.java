package demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Entity
@Table(name = "AccountTbl")
@NamedQuery(name = "Account.findByEmail", query = "SELECT e FROM Account e WHERE e.email=:email")
@NamedQuery(name = "Account.getAll", query = "SELECT e FROM Account e")
public class Account extends EntityBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@NotNull
	@Email
	@Column(length = 255, unique = true, updatable = false)
	private String email;

	@NotNull
	@Column(length = 255)
	private String password;

	@NotNull
	@Column(length = 50)
	private String name;

	@Column(length = 100)
	private String roles;

	@Override
	public Serializable getPk() {
		return this.accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
