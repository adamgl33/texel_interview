package models;

import java.util.LinkedList;
import java.util.List;

public class Video {
    private Float totalDuration;
    private List<Freeze> freezes = new LinkedList<>();

    public void addFreeze(Freeze freeze) {
        this.freezes.add(freeze);
    }

    public void setTotalDuration(Float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public List<Point> getValidPeriods() {
        List<Point> pointList = new LinkedList<>();
        for (int i = 0; i < freezes.size(); i++) {
            Point point = new Point();
            if (i == 0) {
                point.setStart(0.00f);
            } else {
                point.setStart(freezes.get(i - 1).getEnd());
            }
            point.setEnd(freezes.get(i).getStart());
            pointList.add(point);
        }
        return pointList;
    }

    public Float getLongestValidPeriod() {
        Float longestPeriod = 0.0f;
        for (Point point : getValidPeriods()) {
            Float duration = point.getEnd() - point.getStart();
            if (duration > longestPeriod) {
                longestPeriod = duration;
            }
        }
        return longestPeriod;
    }

    public Float getValidVideoPercentage() {
        Float totalValid = 0.0f;
        for (Point point : getValidPeriods()) {
            Float duration = point.getEnd() - point.getStart();
            totalValid += duration;
        }
        return (totalValid/this.totalDuration)*100;
    }
}
