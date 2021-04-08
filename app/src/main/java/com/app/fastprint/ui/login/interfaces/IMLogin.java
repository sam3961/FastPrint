package com.app.fastprint.ui.login.interfaces;

public
interface IMLogin {
    void loginRestCalls(String email, String password, String devices_token,
               String devices_type,String login_type, String current_latitude,String current_longitude);

    void socialLoginRestCalls(String email,String first_name,String last_name,
                     String image,String login_type,
                     String device_type,String current_lattitude,
                     String current_longitude,String device_token);
}
