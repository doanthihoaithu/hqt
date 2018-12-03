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

import oracle.kv.KVStore;

import java.util.concurrent.Callable;


public interface Example extends Callable<Void> {
    /**
     * Initialize connection to a store and any other initialization
     * such as creating tables, indices.
     *
     * @param store reference to a store
     */
    void init(KVStore store);

    /**
     * Return a short description for user.
     */
    String getShortDescription();

    /**
     * End the sample run, preferably clearing up all resources
     * such as deleting tables, closing database connection etc.
     */
    void end();
}
