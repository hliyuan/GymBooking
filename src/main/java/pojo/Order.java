package pojo;


/**
 * Created by liyuan on 2017/9/10.
 */
public class Order {
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    String orderId;
    User user;
    String date;
    int begin;
    int end;
    String areaName;
    boolean isCancel=false;
    private int totalPrice; //本次预定总消费额
    private double penalty; // 违约金

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }


    public int getEnd() {
        return end;

    }
    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", areaName='" + areaName + '\'' +
                ", isCancel=" + isCancel +
                ", totalPrice=" + totalPrice +
                ", penalty=" + penalty +
                '}';
    }
}
