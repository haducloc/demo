package demo.beans;

import java.sql.Connection;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */

//@formatter:off
@DataSourceDefinition(
		  name = "java:/jdbc/demo",
		  className = "org.h2.jdbcx.JdbcDataSource",
		  url = "jdbc:h2:./test",
		  isolationLevel = Connection.TRANSACTION_READ_COMMITTED, 
		  properties = {}
		)
//@formatter:on

@Dependent
public class EntityManagerProducer {

	// Produce EntityManager bean

	@Produces
	@PersistenceContext(unitName = "demoPU")
	private EntityManager em;
}
