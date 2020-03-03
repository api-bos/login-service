package com.bos.login.service;

import bca.bit.proj.library.base.ResultEntity;
import bca.bit.proj.library.enums.ErrorCode;
import com.bos.login.model.Seller;
import com.bos.login.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    SellerRepository g_sellerRepository;

    public ResultEntity loginSeller(Seller p_seller){
        try{
            //Get seller berdasarkan username
            Seller tmp_seller = g_sellerRepository.getSellerByUsername(p_seller.getUsername());
            String tmp_passwordSeller = tmp_seller.getPassword();
            int tmp_flagSeller = tmp_seller.getFlag();

            if (tmp_flagSeller != 4){
                return new ResultEntity(null, ErrorCode.B555, "Seller dengan username: " + p_seller.getUsername() + " belum verifikasi OTP") ;
            }else if (!tmp_passwordSeller.equals(p_seller.getPassword())){
                return new ResultEntity(null, ErrorCode.B555, "Password salah");
            }else{
                return new ResultEntity("Y", ErrorCode.B000);
            }
        }catch (Exception e){
            return new ResultEntity(null, ErrorCode.B555, "Seller dengan username: " + p_seller.getUsername() + " tidak ditemukan.");
        }
    }
}
