/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.nio.client.methods;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.HttpAsyncRequestProducer;

@Deprecated
abstract class BaseHttpAsyncRequestProducer implements HttpAsyncRequestProducer {

    private final URI requestURI;

    public BaseHttpAsyncRequestProducer(final URI requestURI) {
        super();
        if (requestURI == null) {
            throw new IllegalArgumentException("Request URI may not be null");
        }
        this.requestURI = requestURI;
    }

    protected abstract HttpRequest createRequest(final URI requestURI);

    public HttpRequest generateRequest() throws IOException, HttpException {
        return createRequest(this.requestURI);
    }

    public HttpHost getTarget() {
        return URIUtils.extractHost(this.requestURI);
    }

    public void produceContent(
            final ContentEncoder encoder, final IOControl ioctrl) throws IOException {
    }

    public boolean isRepeatable() {
        return true;
    }

    public void resetRequest() {
    }

}
