/*
 * Copyright 2009 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.kantega.publishing.test.database;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.InputStream;

public class DerbyDatabaseCreator extends AbstractDatabaseCreator{
    static int dbCounter = 0;

    public DerbyDatabaseCreator(String datebaseName, InputStream sqlCreateScript) {
        super(datebaseName, sqlCreateScript);
    }

    @Override
    protected DriverManagerDataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:memory:" + databaseName + dbCounter++ + ";create=true");

        return dataSource;
    }

}

