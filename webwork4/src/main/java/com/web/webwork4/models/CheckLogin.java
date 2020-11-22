package com.web.webwork4.models;

import com.web.webwork4.Data.LoginData;

public class CheckLogin {

    public static boolean Check(LoginData infor){
        if ("admin".equals(infor.getUsername()) && "123456".equals(infor.getPassword()))
            return true;
        else
            return false;
    }
}
