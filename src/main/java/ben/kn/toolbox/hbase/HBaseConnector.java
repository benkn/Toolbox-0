package ben.kn.toolbox.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class aides in creating connections to HBase through the HBase
 * Configuration.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Nov 6, 2012
 */
@Component
@Qualifier("hbaseConnector")
@Scope("singleton")
public class HBaseConnector {

	private Configuration config;

	@Autowired
	public HBaseConnector(@Qualifier("hbaseConfiguration") Configuration config) {
		if ( config == null ) {
			this.config = HBaseConfiguration.create();
		} else {
			this.config = config;
		}
	}

	public Configuration getConfiguration() {
		return config;
	}

}