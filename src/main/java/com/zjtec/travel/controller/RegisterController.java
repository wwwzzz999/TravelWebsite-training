package com.zjtec.travel.controller;

import com.zjtec.travel.Application;
import com.zjtec.travel.constant.Const;
//import com.zjtec.travel.domain.Email;
import com.zjtec.travel.domain.User;
import com.zjtec.travel.service.EmailService;
import com.zjtec.travel.service.UserService;
import com.zjtec.travel.service.impl.EmailServiceImpl;
import com.zjtec.travel.util.MsgDigestUtils;
import com.zjtec.travel.util.UuidUtil;
import com.zjtec.travel.vo.ResMsg;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

  private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

  @Autowired
  private UserService userService;

  @Autowired
  public HttpSession session;

  @Autowired
  public EmailService emailService;

  @RequestMapping(value = "/signup")
  @ResponseBody
  public ResMsg signup(@RequestBody User ue){
    ResMsg resmsg=new ResMsg();
    String captcha=(String)session.getAttribute(Const.SESSION_KEY_CAPTCHA);

    if(captcha==null || !captcha.equalsIgnoreCase(ue.getCode())){
      resmsg.setErrcode("4");
      System.out.println(ue.getCode());
      resmsg.setErrmsg("验证码不正确"+ue.getCode());
      return resmsg;
    }
    //TODO:完成注册功能
    if(StringUtils.isNotBlank(ue.getUsername())&&StringUtils.isNotBlank(ue.getPassword())
    &&StringUtils.isNotBlank(ue.getEmail())&&StringUtils.isNotBlank(ue.getBirthday())&&StringUtils.isNotBlank(ue.getTelephone())) {
     // System.out.println(ue);

      if (( userService.existUserNameOrEmail(ue.getUsername(),ue.getEmail()) )==null) {

        ue.setStatus(Const.USER_STATUS_INACTIVE);
        ue.setCode(RandomStringUtils.random(20, Const.CHARSET_ALPHA));
        ue.setRole(Const.USER_ROLE_MEMBER);//注册默认都是会员角色
        ue.setSalt(UuidUtil.getUuid().substring(0,8));

        ue.setPassword(MsgDigestUtils.enpass(ue.getPassword(),ue.getSalt()));

        if (userService.save(ue)) {
          resmsg.setErrcode("0");
          resmsg.setErrmsg("注册成功");
          String content=String.format("http://localhost:8082/activation?username=%s&code=%s", ue.getUsername(), ue.getCode());

          /////发送邮箱
//          Email email = new Email();
//          email.setSmtpAuth("true");
//          email.setSmtpHost("smtp.139.com");
//          email.setPassword("Wyz358381954");
//          email.setUsername("17827377530@139.com");
          String mail="<a href='"+content+"'>【湛江旅游】</a>";
          emailService.sendEmail(ue.getEmail(),"你好，请激活您的用户",mail);

          //logger.info("激活链接 -> " + String.format("http://localhost:8082/activation?username=%s&code=%s", ue.getUsername(), ue.getCode()));
          logger.info("激活链接 -> " + content);

        } else {
          resmsg.setErrcode("1");
          resmsg.setErrmsg("注册失败");
        }
      } else {
        resmsg.setErrcode("2");
        resmsg.setErrmsg("用户名或Email已存在");
      }
    }else {
      resmsg.setErrcode("3");
      resmsg.setErrmsg("注册表格输入框均不能为空");
    }
    return resmsg;
  }

  @RequestMapping(value = "/activation")
  public String activation(ModelMap map, String username, String code){
    //TODO:完成激活功能
    if(username!=null && code!=null){
      if(userService.activeUser(username,code)){
        map.put("msg", "激活成功，请点击跳转链接登录");
        map.put("redirect","/login.html");
      }else{
        map.put("msg", "激活失败，点击跳转链接返回首页");
        map.put("redirect","/");
      }
    }
    else{
      map.put("msg", "激活失败，点击跳转链接返回首页");
      map.put("redirect","/");
    }


    map.put("title","用户激活");
    return "msg";
  }
}
