package tools;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedList;
import java.util.List;

public class FileDownloader {

    private void fetchUrl(String url, String fileName) throws Exception {
        System.out.print("Downloading: " + url + "...");
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("Done");
    }

    public List<String> downloadVideos(List<String> videoUrls, String workDirectory) {
        List<String> fileNames = new LinkedList<>();
        for (String url : videoUrls) {
            String[] parts = url.split("/");
            String fileName = parts[parts.length - 1];
            try {
                fetchUrl(url, workDirectory + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileNames.add(workDirectory + fileName);
        }
        return fileNames;
    }
}
