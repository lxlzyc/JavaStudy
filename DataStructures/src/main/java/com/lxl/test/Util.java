package com.lxl.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
    public String ReadFile(String Path) {
        boolean t = false;
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            String tt = "";
            while ((tempString = reader.readLine()) != null) {


                if ("}".equals(tempString)) {
                    t = false;
                    System.out.println("");
                }
                int l = tempString.length();
                if (!",".equals(String.valueOf(tempString.charAt(l - 1)))) {
                    tt = tempString;
                    continue;
                } else {
                    tempString += tt;
                    tt = null;
                }
                if (tempString.indexOf("\"Uin\":") >= 0 || t) {
                    String[] str = tempString.split("\"");
                    t = true;
                    if (tempString.indexOf("UserName") >= 0 || tempString.indexOf("NickName") >= 0 || tempString.indexOf("Sex") >= 0 || tempString.indexOf("Signature") >= 0 || tempString.indexOf("Province") >= 0 || tempString.indexOf("DisplayName") >= 0) {
                        if (String.valueOf(tempString.charAt(l - 2)).hashCode() == 34) {
                            System.out.print(str[3]);
                        } else {
                            System.out.print(tempString.substring(tempString.indexOf(":") + 1, l - 1));
                        }
                        System.out.print("       ");
                    }

                }

            }
            reader.close();
        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}