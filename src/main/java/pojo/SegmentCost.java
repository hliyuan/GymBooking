package pojo;


/**
 * Created by liyuan on 2017/9/10.
 */
public class SegmentCost {
    int beginHour;
    int endHour;
    int unitCost;

    public int getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public String toString() {
        return "SegmentCost{" +
                "beginHour=" + beginHour +
                ", endHour=" + endHour +
                ", unitCost=" + unitCost +
                '}';
    }
}
