package com.staywell.controller;

import com.staywell.dto.request.LoginRequest;
import com.staywell.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.staywell.model.Admin;
import com.staywell.model.Customer;
import com.staywell.model.Hotel;
import com.staywell.repository.AdminDao;
import com.staywell.repository.CustomerDao;
import com.staywell.repository.HotelDao;

@RestController
@RequestMapping("/staywell")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(security={})
    @PostMapping("/admins/login")
    public ResponseEntity<Admin> getLoggedInAdminDetailsHandler(@RequestBody LoginRequest loginRequest) {
        Admin admin = loginService.adminLogin(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
    }

    @Operation(security={})
    @PostMapping("/customers/login")
    public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(@RequestBody LoginRequest loginRequest) {
        Customer customer = loginService.customerLogin(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @Operation(security={})
    @PostMapping("/hotels/login")
    public ResponseEntity<Hotel> getLoggedInHotelDetailsHandler(@RequestBody LoginRequest loginRequest) {
        Hotel hotel = loginService.hotelLogin(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(hotel, HttpStatus.ACCEPTED);
    }
    
}    
