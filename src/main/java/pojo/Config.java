package pojo;

import java.util.List;


/**
 * Created by liyuan on 2017/9/10.
 */
public class Config {
    Gyms gym;
    List<FeeStandard> feeStandard;
    int openTimeBegin;
    int openTimeEnd;

    public int getOpenTimeBegin() {
        return openTimeBegin;
    }

    public void setOpenTimeBegin(int openTimeBegin) {
        this.openTimeBegin = openTimeBegin;
    }

    public int getOpenTimeEnd() {
        return openTimeEnd;
    }

    public void setOpenTimeEnd(int openTimeEnd) {
        this.openTimeEnd = openTimeEnd;
    }

    public Gyms getGym() {
        return gym;
    }

    public void setGym(Gyms gym) {
        this.gym = gym;
    }

    public List<FeeStandard> getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(List<FeeStandard> feeStandard) {
        this.feeStandard = feeStandard;
    }

    @Override
    public String toString() {
        return "Config{" +
                "gym=" + gym +
                ", feeStandard=" + feeStandard +
                ", openTimeBegin=" + openTimeBegin +
                ", openTimeEnd=" + openTimeEnd +
                '}';
    }
}
