package interfaces;

import models.Video;

import java.io.File;

public interface Parser {
    public Video parse (File outputFile) throws Exception;
}
