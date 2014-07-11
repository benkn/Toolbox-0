package ben.kn.toolbox.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.log4j.Logger;

import ben.kn.toolbox.hbase.ExampleQualifier;
import ben.kn.toolbox.hbase.HBaseConnector;
import ben.kn.toolbox.lang.DataAccessException;

/**
 * Base DAO functionality for HBase datastore. Extend this class for all HBase
 * DAO implementations to take advantage.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Jan 22, 2013
 */
public abstract class BaseHBaseDAO {
    protected HBaseConnector connector;
    protected Logger log = Logger.getLogger(getClass());
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected final String KEY_DELIMITER = "\1E";

    public BaseHBaseDAO(HBaseConnector connector) {
        this.connector = connector;
    }

    /**
     * @return String of the name of the Table
     */
    protected abstract String getTablename();

    /**
     * Get the Hadoop configuration for this DAO.
     */
    protected Configuration getConfig() throws DataAccessException {
        if ( connector != null )
            return connector.getConfiguration();
        else
            throw new DataAccessException("The configuration has not been set!");
    }

    /**
     * Get the Hadoop HTable represented with this DAO.
     */
    protected HTable getTable() throws DataAccessException {
        HTable table;
        try {
            table = new HTable(getConfig(), getTablename());
            table.setAutoFlush(true);
            return table;
        } catch (IOException e) {
            log.error("Failed to create the configuration! " + e.getLocalizedMessage());
            throw new DataAccessException("Failed to create configuration.", e);
        }
    }

    /**
     * Update the given <code>put</code> to include the given qualifier and
     * String value
     */
    protected Put putAdd(Put put, ExampleQualifier qualifier, String value) {
        put.add(qualifier.getFamily().getFamily().getBytes(), qualifier.getQualifer().getBytes(), value.getBytes());
        return put;
    }

    /**
     * Update the given <code>put</code> to include the given qualifier and Date
     * value
     */
    protected Put putAdd(Put put, ExampleQualifier qualifier, Date value) {
        return putAdd(put, qualifier, dateFormat.format(value));
    }

    /**
     * Write the given <code>put</code> to the HBase data store.
     */
    protected void write(Put put) throws DataAccessException {
        HTable table = getTable();

        // write put
        try {
            table.put(put);
        } catch (IOException e) {
            log.error("Failed to execute write operation! " + e.getLocalizedMessage());
            throw new DataAccessException("Failed to execute write operation.", e);
        }
    }

    /**
     * Read the HBase data store with the given <code>get</code> and return the
     * <code>Result</code>.
     */
    protected Result read(Get get) throws DataAccessException {
        HTable table = getTable();
        try {
            return table.get(get);
        } catch (IOException e) {
            log.error("Failed to execute retrieval! " + e.getLocalizedMessage());
            throw new DataAccessException("Failed to execute retrieval.", e);
        }
    }

    /**
     * Execute the given Delete.
     */
    protected void delete(Delete delete) throws DataAccessException {
        HTable table = getTable();
        try {
            table.delete(delete);
        } catch (IOException e) {
            log.error("Failed to execute deletion! " + e.getLocalizedMessage());
            throw new DataAccessException("Failed to execute deletion.", e);
        }
    }

    /**
     * Convert the given byte array to a String
     */
    protected String toString(byte[] bytes) {
        if ( bytes != null )
            return new String(bytes);
        else
            return null;
    }

    /**
     * Convert the given bytes into a Date.
     */
    protected Date toDate(byte[] bytes) throws DataAccessException {
        if ( bytes != null ) {
            try {
                Date d = dateFormat.parse(toString(bytes));
                return d;
            } catch (ParseException e) {
                throw new DataAccessException("Failed to convert to date: " + e.getLocalizedMessage(), e);
            }
        } else {
            return null;
        }
    }
}
