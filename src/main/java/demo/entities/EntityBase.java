package demo.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
public abstract class EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract Serializable getPk();

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EntityBase)) {
			return false;
		}
		EntityBase another = (EntityBase) obj;
		return Objects.equals(this.getPk(), another.getPk());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getPk());
	}
}
