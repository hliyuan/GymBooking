package utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import pojo.Order;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by liyuan on 2017/9/10.
 */
public class JsonUtils {
    /**
     * 由地址获取Json文件,并读取出来
     * @param Path
     * @return 返回json格式的数据
     */
    public static String ReadJsonFile(String Path) {
        BufferedReader reader = null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                stringBuilder.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

     public  static void WriteOrderJsonToFile(String areaName, List<Order> orderList){
         FileOutputStream fileInputStream = null;
         try {
             fileInputStream = new FileOutputStream(Const.PATH_TO_DATA+areaName+".json");
             JSONObject.writeJSONString(fileInputStream, Charset.defaultCharset(),orderList,SerializerFeature.PrettyFormat);
             fileInputStream.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }
}