package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Mapper.SecurityMapper;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Controller
public class SecurityController {

    @Autowired
    SecurityMapper securityMapper;

    @RequestMapping("/security")
    public String getSecurityPage(Model model){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        String otp_key = securityMapper.getOtpKey(id);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(id + "@cron.com");
        arrayList.add(otp_key);

        model.addAttribute("otpInfo", arrayList);

        return "security";
    }

    @RequestMapping("/api/createOtp")
    @ResponseBody
    public ArrayList<String> createOtp(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // Allocating the buffer
//      byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
        byte[] buffer = new byte[5 + 5 * 5];

        // Filling the buffer with random numbers.
        // Notice: you want to reuse the same random generator
        // while generating larger random number sequences.
        new Random().nextBytes(buffer);

        // Getting the key and converting it to Base32
        Base32 codec = new Base32();
//      byte[] secretKey = Arrays.copyOf(buffer, secretSize);
        byte[] secretKey = Arrays.copyOf(buffer, 5);
        byte[] bEncodedKey = codec.encode(secretKey);

        // 생성된 Key!
        String encodedKey = new String(bEncodedKey);

        System.out.println("encodedKey : " + encodedKey);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(id + "@cron.com");
        arrayList.add(encodedKey);

        int insertResult = securityMapper.insertOTP(id, encodedKey);

        if(insertResult > 0){
            return arrayList;
        }else{
            return null;
        }
    }

    @RequestMapping("/api/deleteOTP")
    @ResponseBody
    public String deleteOTP(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        int deleteResult = securityMapper.deteteOTP(id);

        if(deleteResult > 0){
            return "deleteSuccess!!";
        }else{
            return "deleteFail!!";
        }
    }
}
