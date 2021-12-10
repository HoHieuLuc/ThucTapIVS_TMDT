package com.console.test;

import java.util.regex.Pattern;

public class BooleanRegularExpression {
    public static boolean isValid() {
        String username ="H6bz8mNh";
        String password ="1$*Zyhhn";
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";
        String username_regex ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";

        return (Pattern.matches(username_regex, username) && Pattern.matches(password_regex, password));
    }

    public static void main(String[] args) {
        if (isValid()){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

}
