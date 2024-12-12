package com.staywell.service;

import com.staywell.model.Admin;
import com.staywell.model.Customer;
import com.staywell.model.Hotel;

public interface LoginService {

    Admin adminLogin(String email, String password);

    Customer customerLogin(String email, String password);

    Hotel hotelLogin(String email, String password);
}
