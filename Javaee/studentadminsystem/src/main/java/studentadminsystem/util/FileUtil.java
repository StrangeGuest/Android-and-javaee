package studentadminsystem.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class FileUtil {

        public static String getStringFromInputStream(InputStream inputStream) throws IOException {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader breader = new BufferedReader(inputStreamReader);
            String buffer=" ";
            StringBuffer resultStr=new StringBuffer();
            while((buffer=breader.readLine())!=null){
                resultStr.append(buffer);
            }
            return resultStr.toString();
        }
        public static String getRandomFileName(String fileName){
            int index=fileName.lastIndexOf(".");
            String suffix=fileName.substring(index+1);
            return fileName.substring(0,index)+"-" +UUID.randomUUID()+"."+suffix;
        }


}


