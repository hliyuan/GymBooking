package pojo;

import java.util.List;


/**
 * Created by liyuan on 2017/9/10.
 */
public class FeeStandard {
    boolean isWeekend;
    List<SegmentCost> segmentCostList;

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public List<SegmentCost> getSegmentCostList() {
        return segmentCostList;
    }

    public void setSegmentCostList(List<SegmentCost> segmentCostList) {
        this.segmentCostList = segmentCostList;
    }

    @Override
    public String toString() {
        return "FeeStandard{" +
                "isWeekend=" + isWeekend +
                ", segmentCostList=" + segmentCostList +
                '}';
    }
}
