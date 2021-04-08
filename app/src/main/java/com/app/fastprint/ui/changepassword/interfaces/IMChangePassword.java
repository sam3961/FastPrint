package com.app.fastprint.ui.changepassword.interfaces;

public
interface IMChangePassword {
    void changePasswordRestCalls(String token,String old_password,String new_password);
}
