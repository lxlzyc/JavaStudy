package com.lxl.uri;

public class Test {

    /**
     * @param @param args
     * @return void
     * @throws
     * @Description: TODO
     * @author lxl
     * @date 2016-4-22
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String i = "abcdefghijklmnopqrstuvwxyz0123456789+/";
        i = i.toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append("");
        for (int j = 0, l = i.length(); j < l; j++) {
            sb.append("'");
            sb.append(i.charAt(j));
            sb.append("'");
            sb.append(",");
        }
        System.out.println(sb.toString());
    }

}
