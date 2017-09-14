package utils;

import factory.DaoFactory;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import pojo.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static factory.DaoFactory.getOrderDao;


/**
 * Created by liyuan on 2017/9/10.
 */
public class OrderKit {

    /**
     * 对比当前订单,是否与已有的相互冲突
     * 冲突返回true,否则false
     * @param orders
     * @param o
     * @return
     * @throws Exception
     */
    public static boolean hasConflicted(Collection<Order> orders, Order o) throws Exception {
        if (null ==orders)  return false;

        for (Order order : orders) {

            if (order.isCancel()) continue;
            if (!(order.getDate().equals(o.getDate()))) continue;
            if ((o.getEnd()<=order.getBegin()) || (o.getBegin()>=order.getEnd())){
                continue;

            }else{
                return true;
            }
        }

        return false;
    }

    /**
     * 传入客户端输入的命令,以及收费标准列表,去构建一个order:预定的order,或取消预定order
     *
     *
     *  1.首先判断命令长度,只能是4,5,否则打印错误
     *  2.然后先对每个字段记性解析和格式校验,粗略校验
     *  3.格式没有问题的话,重点查看日期的规范性,是否是将来的时间等等,进行规范校验
     *  4.规范校验通过,可以打造一个order对象,然后去数据库比对
     *   4.1 如果是预定,需要比对是否与现有的订单冲突
     *   4.2 如果是取消,则要比对是否有此订单
     * @param command
     * @param config
     * @throws Exception
     */
    public static void buildOrder(String command,Config config) throws IllegalFieldValueException, Exception {
        String[] items = command.split(" ");
        if (items.length != 4 && items.length != 5) {
            System.out.println(Const.ERROR);
        } else if (items.length == 4) {

            String uid = items[0];
            String date = items[1];
            String hours = items[2];
            String where = items[3];
            //校验日期格式是不是YYYY-mm-dd,时间段是不是HH:00~YY:00,场地是否存在，取消是不是"C"
            if (!PatternKit.isDate(date)
                    || !PatternKit.isHours(hours)
                    || !PatternKit.isWhere(where,config)
                    )
            {
                System.out.println(Const.ERROR);
            }//HH:mm的格式是否严谨,比如14:00~10:00
            else if(PatternKit.isHours(hours)){
                String[] times = hours.split("~");
                int begin=Integer.parseInt(times[0].split(":")[0]);
                int end=(Integer.parseInt(times[1].split(":")[0]));
                if (begin>=end || begin<config.getOpenTimeBegin() || end>config.getOpenTimeEnd()){
                    System.out.println(Const.ERROR);
                }
                else {//校验日期YYYY-mm-dd,是否是将来的时间
                    String[] day =parseDay(date);
                    DateTime dateTime = new DateTime(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), begin, 0);

                    if(!dateTime.isAfterNow()){
                        System.out.println(Const.ERROR);
                    }else {
                        Order order = new Order();
                        User user = new User();
                        user.setUid(uid);
                        order.setUser(user);
                        order.setBegin(Integer.parseInt(times[0].split(":")[0]));
                        order.setEnd(Integer.parseInt(times[1].split(":")[0]));
                        order.setDate(date);
                        order.setOrderId(user.getUid()+"_"+DateKit.getCurrentUnixTime());
                        order.setAreaName(where);
                        order.setTotalPrice(computeOrderFee(order, config.getFeeStandard()));
                        Collection<Order> orders = getOrderDao().selectAllOrder(order.getAreaName());

                        if (OrderKit.hasConflicted(orders, order)) {
                            System.out.println(Const.BOOKING_CONFLICT);
                        } else {
                            getOrderDao().insertOrder(order);
                        }
                    }

             }
            }
        } else  {

            String uid = items[0];
            String date = items[1];
            String hours = items[2];
            String where = items[3];
            String ops = items[4];
            if (!PatternKit.isDate(date)
                    || !PatternKit.isHours(hours)
                    || !PatternKit.isWhere(where,config)
                    || !PatternKit.isCancel(ops)
                    ) {
                System.out.println(Const.ERROR);

            }else if(PatternKit.isHours(hours)) {
                String[] times = hours.split("~");
                int begin = Integer.parseInt(times[0].split(":")[0]);
                int end = (Integer.parseInt(times[1].split(":")[0]));
                if (begin >= end || begin < config.getOpenTimeBegin() || end > config.getOpenTimeEnd()) {
                    System.out.println(Const.ERROR);
                } else {
                    String[] day = parseDay(date);
                    DateTime dateTime = new DateTime(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), begin, end);

                    if (!dateTime.isAfterNow()) {
                        System.out.println(Const.ERROR);
                    } else {

                        if (!dateTime.isAfter(DateTime.now())) {
                            System.out.println(Const.ERROR);
                        } else {
                            Order order = new Order();
                            User user = new User();
                            user.setUid(uid);
                            order.setUser(user);
                            order.setBegin(Integer.parseInt(times[0].split(":")[0]));
                            order.setEnd(Integer.parseInt(times[1].split(":")[0]));
                            order.setDate(date);
                            order.setOrderId(user.getUid() + "_" + DateKit.getCurrentUnixTime());
                            order.setAreaName(where);
                            getOrderDao().deleteOrder(order);
                        }
                    }

                }
            }

        }
    }

    /**
     *   传入当前order,收费标准列表
     *   通过找到是周内还是周末,选择一个收费标准
     *   计算预定的租金,返回最后的租金
     * @param o 当前订单
     * @param feeStandards  收费标准的列表
     * @return
     */
    public static int computeOrderFee(Order o,List<FeeStandard> feeStandards){
        //如果是周内
        int totalPrice = 0;
        int oBegin=o.getBegin();
        int oEnd=o.getEnd();
        FeeStandard feeStandard;
        if(DateKit.isWeekday(o.getDate()))
            feeStandard= feeStandards.get(0);
        else
            feeStandard= feeStandards.get(1);

        List<SegmentCost> segmentCostList= feeStandard.getSegmentCostList();
        for(SegmentCost segemnt : segmentCostList){

            //如果不在此时间区间,直接跳过
            if (segemnt.getEndHour()<=oBegin) continue;

            if(oEnd<=segemnt.getEndHour()){
                totalPrice += (oEnd-oBegin) * segemnt.getUnitCost();
                //System.out.println(oEnd+" "+oBegin+" "+segemnt.getUnitCost());
                // System.out.println("没有跨域"+totalPrice);
                break;
            }else{
                totalPrice += (segemnt.getEndHour()-oBegin)*segemnt.getUnitCost();
                //  System.out.println("跨越了"+totalPrice);
                oBegin=segemnt.getEndHour();
            }

        }

        return totalPrice;
    }


    public static double computePenalty(Order o){
        double penalty=0.0;
        if(DateKit.isWeekday(o.getDate())) penalty=o.getTotalPrice()*0.5;
        else penalty=o.getTotalPrice()*0.25;
        return penalty;
    }


    /**
     * ==============================================================================
     * 下面的函数是为统计打印做准备的
     *
     *
     */
    public static void printBefore(){
        System.out.println("> 收入汇总");
        System.out.println("> ---");
    }

    public static double printInfo(String areName) throws Exception {

        System.out.println("> 场地:"+areName);
        Collection<Order> orders= DaoFactory.getOrderDao().selectAllOrder(areName);


        double all=0;
        if(null != orders) {
            Collections.sort((List<Order>) orders,new OrderComparator());
            for (Order order : orders) {

                if (!order.isCancel()) {
                    all += order.getTotalPrice();
                    System.out.println("> "+order.getDate() + " " + PatternKit.transfer(order.getBegin()) + ":00~" + PatternKit.transfer(order.getEnd()) + ":00 " + order.getTotalPrice() + "元");
                } else {
                    all += order.getPenalty();
                    System.out.println("> "+order.getDate() + " " + PatternKit.transfer(order.getBegin()) + ":00~" + PatternKit.transfer(order.getEnd()) + ":00 " + "违约金" + " " + order.getPenalty() + "元");
                }
            }
        }
        System.out.println("> 小计: "+all+"元");
        System.out.println();
        return all;
    }

    public static void printAfter(double tootalPrice){
        System.out.println("> ---");
        System.out.println("> 总计: "+tootalPrice+"元");
    }

    public static void print(Gyms gyms) throws Exception {
        List<String> areas =gyms.getAreas();
        double all=0;
        printBefore();
        for (String areaName:areas){
            all+= printInfo(areaName);
        }
        printAfter(all);
    }


    public static String[] parseDay(String date){
        return date.split("-");
    }

}


