package ben.kn.toolbox.hbase;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HBaseConfigurationSpringWrapper extends HBaseConfiguration {
	@SuppressWarnings("deprecation")
	public HBaseConfigurationSpringWrapper(Configuration config, Map<String, String> configParams) {
		super(config);
		for (String key : configParams.keySet()) {
			set(key, configParams.get(key));
		}
	}
}