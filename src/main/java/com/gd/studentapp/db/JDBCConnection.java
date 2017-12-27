package com.gd.studentapp.db;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.jvnet.hk2.annotations.Contract;

import javax.sql.DataSource;
import java.sql.Connection;

@Contract
public interface JDBCConnection {

    DataSource setUp();

    GenericObjectPool getConnectionPool();
}
