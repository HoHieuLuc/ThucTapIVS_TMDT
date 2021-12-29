package com.tmdt.utilities;

public class ProjectPath {
    static final String LUC_PATH = "D:\\BaiTap\\java\\ThucTapIVS_TMDT\\src\\main\\webapp\\";
    static final String THIEN_PATH = "D:\\Users\\lammi\\eclipse-workspace\\ThucTapIVS_TMDT\\src\\main\\webapp\\";
    static final String THU_PATH = "";

    private ProjectPath(){
    }

    // có gì chỉnh trong đây, up lên rồi bỏ cái file này vô .gitignore luôn
    public static String getPath() {
        return THIEN_PATH;
    }
}
