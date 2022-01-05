package com.console.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.tmdt.utilities.ProjectPath;

public class FilterTextTest {
        //Hàm đọc file và chuyển từng dòng thành arrayList
        public static ArrayList<String> readFileToArray(String filename) throws FileNotFoundException
        {
                ArrayList<String> result = new ArrayList<>();

                try (Scanner s = new Scanner(new FileReader(filename))) {
                        while (s.hasNext()) {
                                result.add(s.next());
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

                // String LocalPath = ProjectPath.getPath() + "WEB-INF\\badWord.txt";
                 // Đọc file badWord.txt và đưa vào array
                 String tempPath = "D:\\Code\\ThucTapIVS_TMDT\\src\\main\\java\\com\\console\\test\\badWord.txt";
                 ArrayList<String> badWords = readFileToArray(tempPath);
                
                 Gson gson = new Gson();
                 System.out.println(gson.toJson(badWords));
                //System.out.println(LocalPath);
        }

}
