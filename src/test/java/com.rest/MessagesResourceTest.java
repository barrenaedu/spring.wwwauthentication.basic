package com.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class MessagesResourceTest {
    private static final String HOST = "https://localhost";
    private static final String RESOURCE_URL = "/rest/messages/";

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;

    @Before
    public void init() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // Turn off http client certificate verification (Trust self signed)
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        restTemplate = new TestRestTemplate();
        ((HttpComponentsClientHttpRequestFactory) restTemplate.getRestTemplate().getRequestFactory()).setHttpClient(httpClient);
    }

    @Test
    public void testGetMessagesUnauthorized() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(HOST + ":" + port + RESOURCE_URL, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetMessagesAuthorized() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResponseEntity<String> response = restTemplate.exchange(HOST + ":" + port + RESOURCE_URL, HttpMethod.GET, new HttpEntity<Void>(headers), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
