package com.vass.reniec.pe;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


import com.liferay.portal.kernel.util.Validator;
import com.vass.reniec.pe.api.RestConsumer;
import com.vass.reniec.pe.model.Credentials;
import org.osgi.service.component.annotations.Component;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jantonio
 */
@Component(immediate = true,
	service = RestConsumer.class
)
public class RestConsumerService implements RestConsumer {


	private static Log _log = LogFactoryUtil.getLog(RestConsumerService.class);

	@Override
	public String patch(String endpoint, Credentials credentials, String body) {
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		HttpEntity<String> entity = getEntity(credentials, body);

		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response = restTemplate.exchange(endpoint, HttpMethod.PATCH, entity, String.class);
		} catch (Exception e) {
			_log.error(e);
		}

		return response.getBody();
	}

	@Override
	public String get(String endpoint, Credentials credentials, String body, String filter) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = getEntity(credentials, body);
		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			_log.info(endpoint +  filter);
			response = restTemplate.exchange (endpoint +  filter, HttpMethod.GET, entity, String.class );
		} catch (Exception e) {
			_log.error(e);
		}

		return response.getBody();
	}

	@Override
	public String post(String endpoint, Credentials credentials, String body) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = getEntity(credentials, body);

		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);

		return response.getBody();

	}


	@Override
	public String getGoogleApi(String endpoint, String filter) {

		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(endpoint + filter, String.class);

		return response;

	}


	@Override
	public String delete(String endpoint, Credentials credentials, String body) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = getEntity(credentials, body);

		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		response = restTemplate.exchange(endpoint, HttpMethod.DELETE, entity, String.class);


		return response.getBody();

	}

	private HttpEntity<String> getEntity(Credentials credentials, String body){
		HttpHeaders httpHeaders = getHttpHeaders(credentials);

		return Validator.isNotNull(body) ? new HttpEntity<>(body, httpHeaders) : new HttpEntity<>(httpHeaders) ;

	}

	private HttpHeaders getHttpHeaders(Credentials credentials) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		if (!credentials.getUser().isEmpty())
			headers.setBasicAuth(credentials.getUser(), credentials.getPassword());
		//headers.set
		return headers;
	}


}