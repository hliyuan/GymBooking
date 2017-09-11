import com.alibaba.fastjson.JSON;
import org.joda.time.IllegalFieldValueException;
import pojo.Config;
import pojo.FeeStandard;
import utils.Const;
import utils.JsonUtils;
import utils.OrderKit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/**
 * Created by liyuan on 2017/9/10.
 */
public class Starter {
    static Config config;
    static List<FeeStandard> feeStandardList;
    static {
        String data= JsonUtils.ReadJsonFile(Const.PATH_TO_REOURCES+"config.json");
        config =JSON.parseObject(data, Config.class);
        feeStandardList=config.getFeeStandard();

    }
    public static void main(String[] args) throws Exception {
        BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
        String operation=null;
        while ((operation=bReader.readLine())!=null) {

            if (operation.equals("")) {
               OrderKit.print(config.getGym());
            } else {
                try{
                OrderKit.buildOrder(operation,config);
                }catch (IllegalFieldValueException e){
                    System.out.println(Const.ERROR);
                }
            }
        }
    }

}

