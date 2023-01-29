package com.zjtec.travel.vo;

public class LoginVo {
  private String username;
  private String password;
  private String captcha;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }


  @Override
  public String toString() {
    return "LoginVo{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", captcha='" + captcha + '\'' +
            '}';
  }
}