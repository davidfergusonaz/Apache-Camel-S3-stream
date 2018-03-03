
package com.sap.datacloud.model;

public class BasicAuthentication extends Authentication{

    private String userid;
    private String password;
   

    @Override
    public String getUserid() {
        return userid;
    }

    @Override
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

  
}
