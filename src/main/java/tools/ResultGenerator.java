package tools;

import json_objects.ResultRoot;
import json_objects.ResultVideo;
import models.Point;
import models.Video;
import java.util.List;

public class ResultGenerator {

    public static final float VARIANCE = 0.5f;

    public ResultRoot Generate(List<Video> videos) {
        ResultRoot root = new ResultRoot();
        for (Video video : videos) {
            ResultVideo resultVideo = new ResultVideo();
            resultVideo.longest_valid_period = video.getLongestValidPeriod();
            resultVideo.valid_video_percentage = video.getValidVideoPercentage();
            resultVideo.valid_periods = video.getValidPeriods();
            root.videos.add(resultVideo);
        }
        // freeze frame synced
        Boolean freezeFrameSynced = true;
        videoloop:
        for (int i = 0; i < videos.size()-1; i++) {
            // get 2 videos
            List<Point> pointList1 = videos.get(i).getValidPeriods();
            List<Point> pointList2 = videos.get(i+1).getValidPeriods();

            // check length
            if (pointList1.size() != pointList2.size()) {
                freezeFrameSynced = false;
                break;
            }

            // check points
            for (int j = 0; j < pointList1.size(); j++) {
                if (!pointList1.get(j).compare(pointList2.get(j), VARIANCE)) {
                    freezeFrameSynced = false;
                    break videoloop;
                }
            }
        }
        root.all_videos_freeze_frame_synced=freezeFrameSynced;
        return root;
    }
}
