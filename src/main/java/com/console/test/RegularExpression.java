package com.console.test;

import java.util.regex.*;

public class RegularExpression {
  public static void main(String[] args) {
      String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";
      System.out.println("Password Tối thiểu 8 và tối đa 14 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt, không có khoảng trắng");      
      System.out.println(Pattern.matches(password_regex, "1$*Zyhhn"));

      String username_regex ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
      System.out.println("Username Tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số ");
      System.out.println(Pattern.matches(username_regex,"H6bz8mNh"));

  }
}
// Outputs Match found