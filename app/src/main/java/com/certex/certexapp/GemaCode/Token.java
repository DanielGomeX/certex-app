package com.certex.certexapp.GemaCode;

import android.util.Log;

public class Token {

    private String code;

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
