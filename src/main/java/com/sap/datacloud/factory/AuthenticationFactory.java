package com.sap.datacloud.factory;

import com.sap.datacloud.model.AuthenticationDetails;
import com.sap.datacloud.model.ProviderPrincipal;

public class AuthenticationFactory {
	
	
	public static ProviderPrincipal getPrincipal(String authType, AuthenticationDetails authDetails){
		ProviderPrincipal principal = new ProviderPrincipal();
		
		if("basicAuthentication".equals(authType)){
			principal.setAuthType("basic");
			principal.setAuthentication(authDetails.getBasicAuthentication());
		}
		else if("certificateBasedAuthentication".equals(authType)){
			principal.setAuthType("ssl");
			principal.setAuthentication(authDetails.getCertificateBasedAuthentication());
			
		}
		else{
			principal.setAuthType("token");
			principal.setAuthentication(authDetails.getTokenBasedAuthentication());
		}
		
		return principal;
	}

}
