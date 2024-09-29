package io.pn.controller;

import org.springframework.http.MediaType;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import io.pn.dto.UserRest;

@Controller
public class UsersController {
	@Autowired
	private OAuth2AuthorizedClientService oauth2ClientService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/users")
	public String showUsers(Model model,@AuthenticationPrincipal OidcUser principle) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		OAuth2AuthenticationToken aouthToken = (OAuth2AuthenticationToken) authentication;
		
		OAuth2AuthorizedClient authorizedClient = oauth2ClientService.loadAuthorizedClient(aouthToken.getAuthorizedClientRegistrationId(), aouthToken.getName());
		
		String tokenValue = authorizedClient.getAccessToken().getTokenValue();
		
		// send the request to our enter application exchange (auth -server)
		sendRequestToExchangeData("Jacktion",tokenValue);
		
		System.out.println(tokenValue);
		System.out.println(principle);
		OidcIdToken idToken = principle.getIdToken();
		System.out.println("Token ID :"+idToken.getTokenValue());
		return "user";
	}
	
	private void sendRequestToExchangeData(String userId,String tokenValue) {
		String BASEURL = "http://localhost:8881/user/api/get/"+userId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+tokenValue);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		
		
		ResponseEntity<String> exchange = restTemplate.exchange(BASEURL, HttpMethod.GET, httpEntity, String.class);
		System.out.println(exchange.getBody());;
	}
}
