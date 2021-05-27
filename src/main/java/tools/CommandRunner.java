package tools;

import java.io.File;

public class CommandRunner {

    public void Run(String[] commands, File outputFile) {
        // Run cmd and wait
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(outputFile);
            Process process = processBuilder.start();
            // TODO: use loop with max retires instead of waitfor
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
