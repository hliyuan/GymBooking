package factory;

import dao.OrderDao;
import utils.Const;

import java.io.FileInputStream;
import java.util.Properties;


/**
 * Created by liyuan on 2017/9/10.
 */
public class DaoFactory {
    private static Properties p;

    static{
        p = new Properties();
        try {
            p.load(new FileInputStream(Const.PATH_TO_REOURCES+"booking.ini"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OrderDao getOrderDao(){
        try {
            return (OrderDao) Class.forName(p.getProperty("OrderDao")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
