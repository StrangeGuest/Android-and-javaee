package com.example.networkdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtilJava {
    public static String getStringFromInputStream(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader breader = new BufferedReader(inputStreamReader);
        String buffer="";
        StringBuffer resultStr=new StringBuffer();
        while ((buffer=breader.readLine())!=null){
            resultStr.append(buffer);
        }
        return resultStr.toString();
    }
}
