package com.vass.reniec.pe.api;

import com.vass.reniec.pe.model.Credentials;

/**
 * @author jantonio
 */
public interface RestConsumer {

    public String get(String endpoint, Credentials credentials, String body, String filter);

    public String post(String endpoint, Credentials credentials, String body);

    public String patch(String endpoint, Credentials credentials, String body);
}