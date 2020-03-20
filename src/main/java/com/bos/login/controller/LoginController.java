package com.bos.login.controller;

import bca.bit.proj.library.base.ResultEntity;
import com.bos.login.model.Admin;
import com.bos.login.model.Seller;
import com.bos.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bos", consumes = "application/json", produces = "application/json")
@CrossOrigin(origins = {"*"})
public class LoginController {
    @Autowired
    LoginService g_loginService;

    @PostMapping("/seller")
    public ResultEntity loginSeller(@RequestBody Seller p_seller){
        return g_loginService.loginSeller(p_seller);
    }

    @PostMapping("/admin")
    public ResultEntity loginAdmin(@RequestBody Admin p_admin){
        return g_loginService.loginAdmin(p_admin);
    }

}
