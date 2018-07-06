package jp.tsubakicraft.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.tsubakicraft.dto.User;

@RestController
public class MainController {

	@RequestMapping("/user")
	public User user(Principal principal) {

		OAuth2Authentication auth = (OAuth2Authentication) principal;
		if(auth.getUserAuthentication() == null) {
			return null;
		}
		Object p = auth.getPrincipal();
		String id = "";
		String name = "";
		String email = "";
		String tokenType = "";
		String tokenValue = "";
		OAuth2AuthenticationDetails  d = (OAuth2AuthenticationDetails) auth.getDetails();
		
		if(p instanceof Map) {
			Map map = (Map)p;
			id = (String) map.get("id");
			name = (String) map.get("name");
			email = (String) map.get("email");
		} else {
			id = p.toString();
			Map details = (Map) auth.getUserAuthentication().getDetails();
			name = (String) details.get("name");
			email = details.get("email") == null ? "" : (String) details.get("email");
		}
		
		tokenType = d.getTokenType();
		tokenValue = d.getTokenValue();
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setTokenType(tokenType);
		user.setTokenValue(tokenValue);
		return user;
	}
	
	@RequestMapping("/unauthenticated")
	public String unauthenticated() {
	  return "redirect:/?error=true";
	}

}
