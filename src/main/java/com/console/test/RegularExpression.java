package com.console.test;

import java.util.regex.*;

public class RegularExpression {
    static final String PASSWORD_REGEX = "^[A-Z]{1}[A-Za-z0-9]{7,29}$";

    public static void main(String[] args) {
        // String password_regex =
        // "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";

        // System.out.println("Password Tối thiểu 8 và tối đa 14 ký tự, ít nhất một chữ
        // cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt, không có
        // khoảng trắng");
        // System.out.println(Pattern.matches(password_regex, "1$*Zyhhn"));

        // String username_regex ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
        // System.out.println("Username Tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất
        // một chữ cái và một số ");
        // System.out.println(Pattern.matches(username_regex,"H6bz8mNh"));

        System.out.println(
                "Password kí tự đầu là in hoa, các kí tự sau là chữ hoặc số, không có kí tự đặc biệt, độ dài từ 8 đến 30 kí tự");
        System.out.println(Pattern.matches(PASSWORD_REGEX, "Thien666"));
    }
}
// Outputs Match found