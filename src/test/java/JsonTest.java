import com.alibaba.fastjson.JSON;
import org.junit.Test;
import pojo.Config;
import pojo.FeeStandard;
import utils.Const;
import utils.JsonUtils;
import utils.OrderKit;

import java.util.List;

/**
 * Created by Daihangtao on 2017/9/10.
 */
public class JsonTest {
    static Config config;
    static List<FeeStandard> feeStandardList;
    static {
        String data= JsonUtils.ReadJsonFile(Const.PATH_TO_REOURCES+"config.json");
        config = JSON.parseObject(data, Config.class);
        feeStandardList=config.getFeeStandard();

    }

    @Test
    public void testOrder() throws Exception {
        OrderKit.buildOrder("U001 2016-06-02 22:00~22:00 A",config);
        OrderKit.buildOrder("U001 2017-09-17 22:00~15:00 A",config);
        OrderKit.buildOrder("U001 2017-09-17 12:00~14:00 A",config);
        OrderKit.buildOrder("U001 2017-09-17 16:00~18:00 A",config);
        OrderKit.buildOrder("U003 2017-09-17 12:00~14:00 A C",config);
        OrderKit.buildOrder("U001 2017-09-18 09:00~13:00 B",config);
        OrderKit.buildOrder("U003 2017-09-18 13:00~22:00 B",config);
        OrderKit.buildOrder("U001 2017-09-17 12:00~14:00 A C",config);
        //OrderKit.print(config.getGym());
    }
}
