package com.staywell.service.impl;

import com.staywell.enums.Role;
import com.staywell.model.Admin;
import com.staywell.model.Customer;
import com.staywell.model.Hotel;
import com.staywell.repository.AdminDao;
import com.staywell.repository.CustomerDao;
import com.staywell.repository.HotelDao;
import com.staywell.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private HotelDao hotelDao;

    @Override
    public Admin adminLogin(String email, String password) {
        Admin admin = adminDao.findByEmail(email).orElseThrow();
        if (BCrypt.checkpw(password, admin.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(admin.getEmail(), null, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.ROLE_ADMIN.toString()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return admin;
        }
        throw new BadCredentialsException("Invalid Username or password");
    }

    @Override
    public Customer customerLogin(String email, String password) {
        Customer admin = customerDao.findByEmail(email).orElseThrow();
        if (BCrypt.checkpw(password, admin.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(admin.getEmail(), null, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.ROLE_CUSTOMER.toString()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return admin;
        }
        throw new BadCredentialsException("Invalid Username or password");
    }

    @Override
    public Hotel hotelLogin(String email, String password) {
        Hotel admin = hotelDao.findByHotelEmail(email).orElseThrow();
        if (BCrypt.checkpw(password, admin.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(admin.getHotelEmail(), null, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.ROLE_HOTEL.toString()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return admin;
        }
        throw new BadCredentialsException("Invalid Username or password");
    }
}
