package com.app.fastprint.updateAddress.interfaces;

public
interface IMUpdateAddress {
    void addBillingAddressRestCalls(String user_id,String first_name,String last_name, String address_1,
                                    String address_2, String city_name, String zip_code,String country_name, String state_name);
}
