package models;

public class Freeze {

    private float start;
    private float duration;
    private float end;

    public Freeze(float start, float duration, float end) {
        this.start = start;
        this.duration = duration;
        this.end = end;
    }

    public float getStart() {
        return start;
    }

    public float getDuration() {
        return duration;
    }

    public float getEnd() {
        return end;
    }
}
