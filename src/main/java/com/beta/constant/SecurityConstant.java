package com.beta.constant;

public final class SecurityConstant {
	private SecurityConstant() {
		throw new IllegalStateException("SecurityConstant class");
	}

	public static String[] SWAGGER_WHITELIST_URL = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**", };
}
