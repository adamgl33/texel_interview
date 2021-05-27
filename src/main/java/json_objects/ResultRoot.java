package json_objects;

import java.util.LinkedList;
import java.util.List;

public class ResultRoot {
    public Boolean all_videos_freeze_frame_synced;
    public List<ResultVideo> videos = new LinkedList<>();

    public ResultRoot() {
    }
}
