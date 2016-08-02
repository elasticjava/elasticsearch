/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.security.ssl;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.xpack.security.ssl.SSLConfiguration.Global;

public class ServerSSLService extends AbstractSSLService {

    public ServerSSLService(Settings settings, Environment environment, Global globalSSLConfiguration) {
        super(settings, environment, globalSSLConfiguration);
    }

    @Override
    protected void validateSSLConfiguration(SSLConfiguration sslConfiguration) {
        if (sslConfiguration.keyConfig() == KeyConfig.NONE) {
            throw new IllegalArgumentException("a key must be configured to act as a server");
        }
        sslConfiguration.keyConfig().validate();
        sslConfiguration.trustConfig().validate();
    }
}
