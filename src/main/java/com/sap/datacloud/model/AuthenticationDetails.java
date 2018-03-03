
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationDetails {

    private BasicAuthentication basicAuthentication;
    private TokenBasedAuthentication tokenBasedAuthentication;
    private CertificateBasedAuthentication certificateBasedAuthentication;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public BasicAuthentication getBasicAuthentication() {
        return basicAuthentication;
    }

    public void setBasicAuthentication(BasicAuthentication basicAuthentication) {
        this.basicAuthentication = basicAuthentication;
    }

    public TokenBasedAuthentication getTokenBasedAuthentication() {
        return tokenBasedAuthentication;
    }

    public void setTokenBasedAuthentication(TokenBasedAuthentication tokenBasedAuthentication) {
        this.tokenBasedAuthentication = tokenBasedAuthentication;
    }

    public CertificateBasedAuthentication getCertificateBasedAuthentication() {
        return certificateBasedAuthentication;
    }

    public void setCertificateBasedAuthentication(CertificateBasedAuthentication certificateBasedAuthentication) {
        this.certificateBasedAuthentication = certificateBasedAuthentication;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
