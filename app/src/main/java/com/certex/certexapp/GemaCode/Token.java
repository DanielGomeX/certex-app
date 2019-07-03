package com.certex.certexapp.GemaCode;

import android.util.Log;

public class Token {

    private String code;
    private String userID;
    private String companyID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        Log.i("Script", "User_Id: " + userID);
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
        Log.i("Script", "Company_Id: " + companyID);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        Log.i("Script", "TOKEN: " + code);
    }

    @Override
    public String toString() {
        return code;
    }

}
