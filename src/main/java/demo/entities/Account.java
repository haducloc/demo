package demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.appslandia.common.validators.MinLength;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Entity
@Table(name = "AccountTbl")
@NamedQuery(name = "Account.findByUsername", query = "SELECT e FROM Account e WHERE e.username=:username")
@NamedQuery(name = "Account.queryUser", query = "SELECT e FROM Account e WHERE ((:name IS NULL) OR (e.name LIKE :name)) ORDER BY e.name")
public class Account extends EntityBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	// Demo purpose
	@NotNull
	@MinLength(5)
	@Column(length = 255, unique = true, updatable = false)
	private String username;

	// Demo purpose
	@NotNull
	@MinLength(5)
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
