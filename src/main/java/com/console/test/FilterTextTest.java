package com.console.test;

public class FilterTextTest {
    
        public static void main(String[] args) {
          String noiDung = "H e l l      o Tui là thiện";
          //Trim  những khoản trắng ở giữa, chỉ để kiểm tra, chứ không phải
          //lưu vào database
          String noiDung_Trim = noiDung.replace(" ","");
          //Lowwer toàn bộ kí tự cần kiểm tra xuống chữ thường
          noiDung_Trim.toLowerCase();
          

          
         // System.out.println(myStr.toUpperCase().("b"));
        //   System.out.println(myStr.contains("e"));
        //   System.out.println(myStr.contains("Hi"));
        }
      
      
}
