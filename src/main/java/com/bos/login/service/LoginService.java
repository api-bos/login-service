package com.bos.login.service;

import bca.bit.proj.library.base.ResultEntity;
import bca.bit.proj.library.enums.ErrorCode;
import com.bos.login.model.Admin;
import com.bos.login.model.Seller;
import com.bos.login.repository.AdminRepository;
import com.bos.login.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    SellerRepository g_sellerRepository;
    @Autowired
    AdminRepository g_adminRepository;

    public ResultEntity loginSeller(Seller p_seller){
        try{
            //Get seller berdasarkan username
            Seller tmp_seller = g_sellerRepository.getSellerByUsername(p_seller.getUsername());
            String tmp_passwordSeller = tmp_seller.getPassword();
            int tmp_flagSeller = tmp_seller.getFlag();
            int tmp_sellerId = tmp_seller.getId_seller();

            if (tmp_flagSeller != 4){
                return new ResultEntity(null, ErrorCode.BOS_202) ;
            }else if (!tmp_passwordSeller.equals(p_seller.getPassword())){
                return new ResultEntity(null, ErrorCode.BOS_200);
            }else{
                tmp_seller = new Seller();
                tmp_seller.setId_seller(tmp_sellerId);
                return new ResultEntity(tmp_seller, ErrorCode.BIT_000);
            }
        }catch (Exception e){
            return new ResultEntity(null, ErrorCode.BOS_201);
        }
    }

    public ResultEntity loginAdmin(Admin p_admin){
        ResultEntity l_output;
        try{
            //Get seller berdasarkan username
            Optional<Admin> tmp_admin = g_adminRepository.getAdminByUsername(p_admin.getUsername());

            if (tmp_admin.equals(Optional.empty())){
                l_output = new ResultEntity(null, ErrorCode.BIT_999, "Admin not found");

            }else if (!tmp_admin.get().getPassword().equals(p_admin.getPassword())){
                l_output = new ResultEntity(null, ErrorCode.BOS_200, "Password salah");

            }else{
                l_output = new ResultEntity("Y", ErrorCode.BIT_000);
            }

        }catch (Exception e){
            e.printStackTrace();
            return l_output = new  ResultEntity(null, ErrorCode.BIT_999);
        }

        return l_output;
    }
}
