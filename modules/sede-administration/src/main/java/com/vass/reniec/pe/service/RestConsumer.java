package com.vass.reniec.pe.service;

import com.vass.reniec.pe.service.api.Credentials;

public interface RestConsumer {

	public String get(String endpoint, Credentials credentials, String body);

	public String patch(String endpoint, Credentials credentials, String body);

	public String post(String endpoint, Credentials credentials, String body);

}