
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.Map;

public class CertificateBasedAuthentication extends Authentication{

    private String keystorelocation;
    private String keystorepassword;
    private String truststorelocation;
    private String truststorepassword;
   
    public String getKeystorelocation() {
        return keystorelocation;
    }

    public void setKeystorelocation(String keystorelocation) {
        this.keystorelocation = keystorelocation;
    }

    public String getKeystorepassword() {
        return keystorepassword;
    }

    public void setKeystorepassword(String keystorepassword) {
        this.keystorepassword = keystorepassword;
    }

    public String getTruststorelocation() {
        return truststorelocation;
    }

    public void setTruststorelocation(String truststorelocation) {
        this.truststorelocation = truststorelocation;
    }

    public String getTruststorepassword() {
        return truststorepassword;
    }

    public void setTruststorepassword(String truststorepassword) {
        this.truststorepassword = truststorepassword;
    }


}
