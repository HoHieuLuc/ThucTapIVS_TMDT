package com.console.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FilterTextTest {
        //Hàm đọc file và chuyển từng dòng thành arrayList
        public static ArrayList<Integer> readFileToArray(String filename) throws FileNotFoundException
        {
                ArrayList<Integer> result = new ArrayList<>();

                try (Scanner s = new Scanner(new FileReader(filename))) {
                        while (s.hasNext()) {
                                result.add(s.nextInt());
                        }
                        return result;
                }
        }

        public static void main(String[] args) throws FileNotFoundException {
                String noiDung = "H e l l      o Tui là thiện";
                // Trim những khoản trắng ở giữa, chỉ để kiểm tra, chứ không phải
                // lưu vào database
                String noiDung_Trim = noiDung.replace(" ", "");
                // Lowwer toàn bộ kí tự cần kiểm tra xuống chữ thường
                noiDung_Trim.toLowerCase();

                 // Đọc file badWord.txt và đưa vào array
                ArrayList<Integer> badWords = readFileToArray("filename");
                
                
        }

}
