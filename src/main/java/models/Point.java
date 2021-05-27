package models;

public class Point {
    private Float start;
    private Float end;

    public Float getStart() {
        return start;
    }

    public void setStart(Float start) {
        this.start = start;
    }

    public Float getEnd() {
        return end;
    }

    public void setEnd(Float end) {
        this.end = end;
    }

    public Boolean compare(Point point, Float variance) {
        if (Math.abs(this.getStart() - point.getStart()) > variance ||
                Math.abs(this.getEnd() - point.getEnd()) > variance) {
            return false;
        }
        return true;
    }
}
