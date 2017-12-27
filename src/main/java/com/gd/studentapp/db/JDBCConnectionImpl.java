package com.gd.studentapp.db;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import javax.sql.DataSource;

@Service
public class JDBCConnectionImpl implements JDBCConnection {
    private static final Logger logger = LogManager.getLogger(JDBCConnectionImpl.class);

    private static final Config CONFIG = ConfigFactory.load();

    private GenericObjectPool connectionPool = null;

    public DataSource setUp(){
        try {
            String connectionURL = CONFIG.getString("connectionURL");
            String username = CONFIG.getString("username");
            String password = CONFIG.getString("password");

            Class.forName(CONFIG.getString("dbDriver")).newInstance();

            connectionPool = new GenericObjectPool();
            connectionPool.setMaxActive(10);

            ConnectionFactory cf = new DriverManagerConnectionFactory(connectionURL, username, password);

            PoolableConnectionFactory pcf = new PoolableConnectionFactory(
                    cf,
                    connectionPool,
                    null,
                    null,
                    false,
                    true
            );

        } catch (Exception e) {
            logger.error("Connection error: " + e.getLocalizedMessage());
        }
        return new PoolingDataSource(connectionPool);
    }

    public GenericObjectPool getConnectionPool() {
        return connectionPool;
    }
}
