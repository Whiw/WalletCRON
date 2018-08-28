package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Data.CronCustomerDto;
import com.wallet.cronnetwork.Data.SmsDto;
import com.wallet.cronnetwork.Mapper.CronCustomerMapper;
import com.wallet.cronnetwork.Mapper.MemberMapper;
import com.wallet.cronnetwork.Service.SmsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    SmsService smsService;
    @Autowired
    CronCustomerMapper cronCustomerMapper;
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/register")
    public String getSigninPage(){
        AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

        if(trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){
            return "register";
        }else{
            return "redirect:/";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/addCustomer")
    @ResponseBody
    public String addCustomer(@RequestBody CronCustomerDto customerdto) {
        int result = cronCustomerMapper.addcustomer(customerdto);

        if(result > 0){
            System.out.print("success");
            return "addSuccess!!";
        }else{
            return "addFail";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/idDuplicateChk")
    @ResponseBody
    public String idDuplicateChk(@RequestParam("id")String id){

        int result = cronCustomerMapper.idDuplicateChk(id);
        if(result > 0){
            return "idDuplicated";
        }else{
            return "idUseable";
        }
    }

    @RequestMapping("/api/hpDuplicateChk")
    @ResponseBody
    public String chkDupHp(@Param("phone") String phone){

        int hpChkRes = memberMapper.checkHp(phone);

        if(hpChkRes > 0){
            return "HpDuplicated!!";
        }else{
            return "HpNotDuplictaed!!";
        }
    }

    //문자 전송 후 db입력
    @RequestMapping(method = RequestMethod.POST, value = "/api/sendSms")
    @ResponseBody
    public String smsSend(@RequestBody SmsDto smsDto) throws Exception{
        //?id=geopia&pwd=wldhsms&code=$code&snum=027868200&rnum=$mobile&msg=$msg&userid=geopia&ipAddr=$_SERVER[REMOTE_ADDR]
        int code = new Random().nextInt(10000) + 1000;
        if (code > 10000) {
            code = code - 1000;
        }

        smsDto.setCode(code + "");

        int connectionResult = smsService.sendSmsResult(smsDto, "Ncoin 인증번호는 " + smsDto.getCode() + " 입니다 ");

        if(connectionResult == 200){
            int result = cronCustomerMapper.insertAuthNumber(smsDto);
            if(result > 0){
                return "sendSuccess!!";
            }else{
                return "insertFail";
            }

        }else{
            System.out.print(connectionResult + "");
            return "fail";
        }

    }

    //인증하기
    @RequestMapping(value = "/api/chkCode", method = RequestMethod.POST)
    @ResponseBody
    public String chkOtp(@RequestBody SmsDto smsDto){
        int result = cronCustomerMapper.chkCode(smsDto);

        if(result > 0){
            return "codeChkSuccess!!";
        }else{
            return "codeChkFailed!!";
        }
    }


}
