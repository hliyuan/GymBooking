import com.alibaba.fastjson.JSON;
import pojo.Config;
import pojo.FeeStandard;
import utils.Const;
import utils.JsonUtils;
import utils.OrderKit;

import java.util.List;
import java.util.Scanner;


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

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String operation = in.nextLine();
            if (Const.COUNT.equals(operation)) {
               OrderKit.print(config.getGym());
            } else {
                OrderKit.buildOrder(operation,config);
            }
        }
    }

}

