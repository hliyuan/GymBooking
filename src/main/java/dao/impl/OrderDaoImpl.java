package dao.impl;

import com.alibaba.fastjson.JSON;
import dao.OrderDao;
import pojo.Order;
import utils.Const;
import utils.JsonUtils;
import utils.OrderKit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by liyuan on 2017/9/10.
 */
public class OrderDaoImpl implements OrderDao {
    List<Order> orders =new ArrayList<Order>();
    public void insertOrder(Order o) throws Exception {

        Collection<Order> temp = this.selectAllOrder(o.getAreaName());
        List<Order> os = new ArrayList<Order>();
        if (temp != null) {
            for (Order order : temp) {
                os.add(order);
            }
        }
        os.add(o);

        JsonUtils.WriteOrderJsonToFile(o.getAreaName(),os);
        System.out.println(Const.BOOKING_SUCCESS);

    }

    public void deleteOrder(Order o) throws Exception {
        this.selectAllOrder(o.getAreaName());
        int index= alreadyHasIndex(o);
        if (index == -1){
            System.out.println(Const.NO_SUCH_BOOKING);
        }else{
            Order order=orders.get(index);
           // System.out.println("计算赔偿金,设置赔偿金");
            order.setCancel(true);
            order.setPenalty(OrderKit.computePenalty(order));
            orders.remove(index);
            orders.add(index,order);
            JsonUtils.WriteOrderJsonToFile(o.getAreaName(),orders);
            System.out.println(Const.BOOKING_SUCCESS);
          //  System.out.println("取消预订成功,可以查看Json文件的数据状态");
        }

    }

    public int alreadyHasIndex(Order o) throws Exception {
        this.selectAllOrder(o.getAreaName());
        for (int i = 0,len=orders.size(); i <len; i++) {
             Order order =orders.get(i);
            if(order.isCancel()) continue;
             if(order.getAreaName().equals(o.getAreaName())
                     && order.getBegin() == o.getBegin()
                     && order.getEnd() == o.getEnd()
                     && order.getDate().equals(o.getDate())
                     && order.getUser().getUid().equals(o.getUser().getUid())){
                 return  i;
            }
        }
        return -1;
    }


    public Collection<Order> selectAllOrder(String areaName) throws Exception {
        File file =new File(Const.PATH_TO_DATA+areaName+".json");
        if (!file.exists()) return null;
        String ordersJson = JsonUtils.ReadJsonFile(file.getPath());
        orders= JSON.parseArray(ordersJson,Order.class);
        return orders;
    }



}
