package com.sap.datacloud.builder;

import com.sap.datacloud.model.ProviderPrincipal;
import static com.sap.datacloud.util.AppConstants.USERNAME;
import static com.sap.datacloud.util.AppConstants.BASIC;
import static com.sap.datacloud.util.AppConstants.FTP;
import static com.sap.datacloud.util.AppConstants.AMPERSAND;
import static com.sap.datacloud.util.AppConstants.PASSWORD;
import static com.sap.datacloud.util.AppConstants.PLACE_HOLDER;
import static com.sap.datacloud.util.AppConstants.EQUALS;

public class CamelSecurityURLBuilder {

	private CamelSecurityURLBuilder() {

	}

	public static String buildURLFromAuth(String protocol, ProviderPrincipal principal) {
		StringBuilder authUri = new StringBuilder();
		if (BASIC.equals(principal.getAuthType()) && FTP.equals(protocol)) {
			authUri.append(PLACE_HOLDER + USERNAME + EQUALS).append(principal.getAuthentication().getUserid())
					.append(AMPERSAND).append(PASSWORD + EQUALS).append(principal.getAuthentication().getPassword());

		}
		return authUri.toString();
	}

}
