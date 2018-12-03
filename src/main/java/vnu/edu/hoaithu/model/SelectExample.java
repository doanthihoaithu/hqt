/*-
 * Copyright (C) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This file was distributed by Oracle as part of a version of Oracle NoSQL
 * Database made available at:
 *
 * http://www.oracle.com/technetwork/database/database-technologies/nosqldb/downloads/index.html
 *
 * Please see the LICENSE file included in the top-level directory of the
 * appropriate version of Oracle NoSQL Database for a copy of the license and
 * additional information.
 */

package vnu.edu.hoaithu.model;

import lombok.extern.apachecommons.CommonsLog;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.kv.StatementResult;
import oracle.kv.query.ExecuteOptions;
import oracle.kv.table.RecordValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vnu.edu.hoaithu.payload.SelectResponse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Example: Execute select queries and read the results.
 *
 * This example showcases the API for select queries: how to execute a query
 * and have access to the results, how to reuse compiled queries, how to
 * use variables in parametrized queries and how to access the execution plans.
 */

@Component
public class SelectExample extends BaseExample {

    @Override
    public String getShortDescription() {
        return "Select queries";
    }

    @Override
    public void setup() {
        executeDDL(
                "CREATE TABLE IF NOT EXISTS countries  " +
                        "(country_name STRING, " +
                        " country_code STRING, " +
                        " country_id INTEGER, " +
                        " PRIMARY KEY (country_id))");

        executeDDL("CREATE TABLE IF NOT EXISTS countries.students  " +
                "(first_name STRING, " +
                " last_name STRING, " +
                " student_id INTEGER, " +
                " age INTEGER, " +
                " PRIMARY KEY (student_id))");


        executeDDL(
                "CREATE INDEX IF NOT EXISTS firstlast ON " +
                        "countries.students(first_name, last_name)");
    }

    @Override
    public void teardown() {
        executeDDL("DROP TABLE IF EXISTS countries");
        executeDDL("DROP TABLE IF EXISTS countries.students");

    }

    @Override
    public Void call() {
        simple();
//        multipleExecutions();
//        bindVariables();
//        queryExecutionPlan();

        return null;
    }

    public SelectResponse call (String query) {
        SelectResponse response = new SelectResponse();

        KVStore store = getKVStore();
        ExecuteOptions options = new ExecuteOptions();
        options.setTimeout(30000, TimeUnit.MILLISECONDS);

        System.out.println("\n  Select query: " + query);
        long begin = System.currentTimeMillis();
        StatementResult result =
                store.executeSync(query, options);
        long end = System.currentTimeMillis();

        int count = 0;
        ArrayList<Object> rows = new ArrayList<>();
        for (RecordValue record : result) {
            /* Print the full record as JSON */
            count++;
//            System.out.println(record.toJsonString(true));
            rows.add(record.toJsonString(true));
        };
        System.out.println("Query takes " + (end - begin) + "  milliseconds");

        response.setTime(end - begin);
        response.setNumberOfRow(count);
        response.setResult(rows);
        return response;
    }

    private void simple() {
        KVStore store = getKVStore();

        System.out.println("\n  A simple select all:");

        /* Preparation and execution in one step. */
        long begin = System.currentTimeMillis();
//        StatementResult result =
//            store.executeSync("SELECT * FROM NESTED TABLES (" +
//                    "countries.students s " +
//                    "ANCESTORS(countries c)) where c.country_id = 1");

        StatementResult result =
                store.executeSync("SELECT first_name, count(first_name) from countries.students where first_name= \"James\" group by first_name");
        long end = System.currentTimeMillis();

        /* Iterate the results of the query */
        for (RecordValue record : result) {
            /* Print the full record as JSON */
            System.out.println(record.toJsonString(true));
        }
        System.out.println("Query take " + (end - begin) + "  milliseconds");

        /* Close the result */
        result.close();
    }


    public static void main(String[] args) {
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        KVStore kvStore = KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName
                + ":" + hostPort));
        SelectExample instance = new SelectExample();
        instance.init(kvStore);
        instance.call();
    }
}
