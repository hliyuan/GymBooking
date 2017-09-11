package pojo;

import java.util.List;


/**
 * Created by liyuan on 2017/9/10.
 */
public class Gyms {
    List<String> areas;
    int areaCount;

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public int getAreaCount() {
        return areaCount;
    }

    public void setAreaCount(int areaCount) {
        this.areaCount = areaCount;
    }


    @Override
    public String toString() {
        return "Gyms{" +
                "areas=" + areas +
                ", areaCount=" + areaCount +
                '}';
    }

}
