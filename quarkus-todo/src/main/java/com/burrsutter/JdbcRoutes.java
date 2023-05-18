/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.burrsutter;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class JdbcRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        Todo todo = new Todo();

        from("timer://insertCamel?period=10000")
                .setBody(constant(todo))
                .process(new TodoGenerator())
                .log("Sending body with content ${body} to JPA")
                .to("jpa:com.burrsutter.Todo")
                .log("Inserted Camel ${body}")
                .setBody().simple("SELECT * FROM TODO")
                .to("jdbc:camel-ds")
                .log("We have ${header[CamelJdbcRowCount]} todos in the database.")
                .log("Todos found: ${body}");
    }
}
