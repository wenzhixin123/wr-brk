package com.sinotrans.framework.security.providers.encoding;

import org.springframework.security.providers.encoding.PlaintextPasswordEncoder;

public class TokenPasswordEncoder extends PlaintextPasswordEncoder{

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return true;
	}
	
}
