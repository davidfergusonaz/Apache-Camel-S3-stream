
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.Map;

public class TokenBasedAuthentication  extends Authentication{

    private String tokenserviceurl;
    private String tokenserviceuser;
    private String tokenservicepassword;
    private String clientkey;
    private String clientsecret;
    private String audience;
    private String authurl;

 public String getTokenserviceurl() {
        return tokenserviceurl;
    }

    public void setTokenserviceurl(String tokenserviceurl) {
        this.tokenserviceurl = tokenserviceurl;
    }

    public String getTokenserviceuser() {
        return tokenserviceuser;
    }

    public void setTokenserviceuser(String tokenserviceuser) {
        this.tokenserviceuser = tokenserviceuser;
    }

    public String getTokenservicepassword() {
        return tokenservicepassword;
    }

    public void setTokenservicepassword(String tokenservicepassword) {
        this.tokenservicepassword = tokenservicepassword;
    }

    public String getClientkey() {
        return clientkey;
    }

    public void setClientkey(String clientkey) {
        this.clientkey = clientkey;
    }

    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getAuthurl() {
        return authurl;
    }

    public void setAuthurl(String authurl) {
        this.authurl = authurl;
    }

}
