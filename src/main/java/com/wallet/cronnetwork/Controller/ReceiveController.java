package com.wallet.cronnetwork.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wallet.cronnetwork.Mapper.CronAddressMapper;

/*import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;*/

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReceiveController {

	
	@Autowired
	CronAddressMapper cronAddressMapper;
	
    @RequestMapping("/receive")
    public String getReceivePage(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ArrayList<String> addressList = cronAddressMapper.getAddressList(id);

        model.addAttribute("addressList", addressList);

        return "receive";
    }
    
    @RequestMapping("/registerAddress")
    public @ResponseBody
    String getRegisterAddress(Model model, HttpServletRequest request) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String id = auth.getName();
    	JSONObject json = new JSONObject();
    	model.addAttribute("id", id);
    	json.put("id",  id);
    	String thisval = request.getParameter("thisval");
    	System.out.println(thisval);
    	System.out.println(json);
    	
    	
    	
    	
    	
    	return json.toString();
    }

    @RequestMapping("/api/insertAddress")
    @ResponseBody
    public String insertAddress(@RequestParam("address") String address){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        int insertResult = cronAddressMapper.insertAddress(id, address);

        if(insertResult > 0){
            return "insertSuccess!!";
        }else{
            return "insertFail!!";
        }
    }
}
