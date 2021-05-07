package demo.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.json.bind.JsonbConfig;

import com.appslandia.common.cdi.CDIFactory;
import com.appslandia.common.json.JsonProcessor;
import com.appslandia.common.json.JsonbProcessor;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class JsonProcessorFactory implements CDIFactory<JsonProcessor> {

	// Produce JsonProcessor bean

	@Produces
	@ApplicationScoped
	@Override
	public JsonProcessor produce() {
		JsonbConfig config = JsonbProcessor.newConfig();
		config.withFormatting(false);
		config.withNullValues(false);
		return new JsonbProcessor().setConfig(config);
	}

	@Override
	public void dispose(@Disposes JsonProcessor impl) {
		impl.destroy();
	}
}
