package com.lxl.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {

    private static ArrayList<String> filelist = new ArrayList<String>();

    static void getFiles(String filePath) {
        System.out.println(filePath);
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
            } else {
                getallMes(file.getAbsolutePath());
            }
        }
    }

    static void getallMes(String filePath) {
        String JsonContext = new Util().ReadFile(filePath);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        getFiles("C:\\Users\\lxl\\Desktop\\东秦信息\\");
    }

}
