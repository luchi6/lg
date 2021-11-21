/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.shardingsphere.transaction.base.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC util.
 *
 * @author duhongjun
 */
public class JDBCUtil {
    
    /**
     * Execute query use JDBC.
     *
     * @param connection JDBC connection
     * @param sql sql
     * @param params sql parameters
     * @return result set
     * @throws SQLException failed to execute SQL, throw this exception
     */
    public static List<Map<String, Object>> executeQuery(final Connection connection, final String sql, final Collection<Object> params) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            fillParameters(preparedStatement, params);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowResultMap = new LinkedHashMap<>();
                result.add(rowResultMap);
                for (int i = 1; i <= columnCount; i++) {
                    rowResultMap.put(rsMeta.getColumnName(i), rs.getObject(i));
                }
            }
        }
        return result;
    }
    
    /**
     * Execute query use JDBC.
     *
     * @param connection JDBC connection
     * @param sql sql
     * @param params sql parameters
     * @throws SQLException failed to execute SQL, throw this exception
     */
    public static void executeUpdate(final Connection connection, final String sql, final Collection<Object> params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            fillParameters(preparedStatement, params);
            preparedStatement.executeUpdate();
        }
    }
    
    /**
     * Execute batch use JDBC.
     *
     * @param connection JDBC connection
     * @param sql sql
     * @param paramsCollection sql parameters collection
     * @throws SQLException failed to execute SQL, throw this exception
     */
    public static void executeBatch(final Connection connection, final String sql, final Collection<Collection<Object>> paramsCollection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Collection<Object> each : paramsCollection) {
                fillParameters(preparedStatement, each);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
    
    private static void fillParameters(final PreparedStatement preparedStatement, final Collection<Object> params) throws SQLException {
        Iterator<Object> iterator = params.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            preparedStatement.setObject(++index, iterator.next());
        }
    }
}
