package parsers;

import interfaces.Parser;
import models.Freeze;
import models.Video;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FreezeDetectorParser implements Parser {

    public static final String FREEZE_DETECT_STRING = "[freezedetect @";
    public static final String FREEZE_START_STRING = "freeze_start:";
    public static final String FREEZE_DURATION_STRING = "freeze_duration:";
    public static final String FREEZE_END_STRING = "freeze_end:";

    public Video parse (File outputFile) throws Exception {
        // read the file to parse
        List<String> dataPoints = new LinkedList<>();
        try (Scanner myReader = new Scanner(outputFile)) {
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.startsWith(FREEZE_DETECT_STRING)) {
                    dataPoints.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // do some validation
        if (dataPoints.isEmpty()) {
            throw new Exception("Couldn't parse data.  No lines found.");
        }
        if (!dataPoints.get(0).contains(FREEZE_START_STRING)) {
            throw new Exception("First data point is not a 'Start'");
        }
        if (!dataPoints.get(dataPoints.size()-1).contains(FREEZE_END_STRING)) {
            throw new Exception("Last data point is not an 'End'");
        }

        // create models
        Video video = new Video();
        video.setTotalDuration(Float.parseFloat(dataPoints.get(dataPoints.size()-1).split(FREEZE_END_STRING)[1]));
        for (int i = 0; i < dataPoints.size() ; i += 3) {
             Float start = Float.parseFloat(dataPoints.get(i).split(FREEZE_START_STRING)[1]);
             Float duration = Float.parseFloat(dataPoints.get(i+1).split(FREEZE_DURATION_STRING)[1]);
             Float end = Float.parseFloat(dataPoints.get(i+2).split(FREEZE_END_STRING)[1]);

            Freeze freeze = new Freeze(start,duration,end);
            video.addFreeze(freeze);
        }
        return video;
    }
}
