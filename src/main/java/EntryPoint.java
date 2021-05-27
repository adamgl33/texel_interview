import json_objects.ResultRoot;
import models.Video;
import parsers.FreezeDetectorParser;
import tools.CommandRunner;
import tools.FileDownloader;
import tools.JSONSerializer;
import tools.ResultGenerator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class EntryPoint {

    // TODO: put configuration in a file
    public static final String WORKDIR = "/tmp/";
    public static final String TEMP_FILE = "freeze_detect_output.txt";
    public static final String FFMPEG_CMD = "ffmpeg";

    public static void main(String[] args) {
        // TODO: use a logger
        System.out.println("Starting freeze frame detection...");
        List<String> videoUrls = new LinkedList<>() {
            {
                add("https://storage.googleapis.com/hiring_process_data/freeze_frame_input_a.mp4");
                add("https://storage.googleapis.com/hiring_process_data/freeze_frame_input_b.mp4");
                //add("https://storage.googleapis.com/hiring_process_data/freeze_frame_input_c.mp4");
            }
        };

        // download the relevant video files
        FileDownloader downloader = new FileDownloader();
        List<String> videoFiles = downloader.downloadVideos(videoUrls, WORKDIR);

        File ffmpegOutputFile = new File(WORKDIR + TEMP_FILE);
        List<Video> videoList = new LinkedList<>();

        System.out.println("Analysing " + videoFiles.size() + " video file(s)");
        for (String inputFile : videoFiles) {

            String[] ffmpegCmd = new String[]{FFMPEG_CMD, "-i", inputFile, "-vf", "freezedetect=n=0.003:d=2", "-map", "0:v:0", "-f", "null", "-"};

            // run the ffmpeg command
            CommandRunner runner = new CommandRunner();
            runner.Run(ffmpegCmd, ffmpegOutputFile);

            // Parse output
            FreezeDetectorParser freezeDetectorParser = new FreezeDetectorParser();
            try {
                videoList.add(freezeDetectorParser.parse(ffmpegOutputFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // generate result object
        ResultGenerator resultGenerator = new ResultGenerator();
        ResultRoot root = resultGenerator.Generate(videoList);

        // serialize to json
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.toJSON(root);
        System.out.println(json);
    }
}
