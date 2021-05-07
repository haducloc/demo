package demo.beans;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@Dependent
public class EntityManagerProducer {

	// Produce EntityManager bean

	@Produces
	@PersistenceContext(unitName = "demoPU")
	private EntityManager em;
}
