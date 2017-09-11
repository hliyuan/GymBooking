package utils;

import pojo.Order;

import java.util.Comparator;


/**
 * Created by liyuan on 2017/9/10.
 */
public class OrderComparator implements Comparator<Order> {
    public int compare(Order o1, Order o2) {
      String first =o1.getDate()+transfer(o1.getBegin())+transfer(o1.getEnd());
        String second =o2.getDate()+transfer(o2.getBegin())+transfer(o2.getEnd());
        return first.compareTo(second);
    }

    public static String transfer(int time){
        if (time<10){
            return "0"+time;
        }
        return String.valueOf(time);
    }
}
