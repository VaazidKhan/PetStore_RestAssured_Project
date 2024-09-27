package api.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileWriter;
import java.io.IOException;

public class LogHeaderAppender {

    private static final Logger logger = LogManager.getLogger(LogHeaderAppender.class);

    // Method to append log header
    public static void appendLogHeader() {
        try (FileWriter writer = new FileWriter("logs/App-" + java.time.LocalDate.now() + ".log", true)) {
            writer.write("-------------------------------------------------\n");
            writer.write("Name: Vaazid Khan\n");
            writer.write("Log Start: " + java.time.LocalDateTime.now() + "\n");
            writer.write("-------------------------------------------------\n\n");
        } catch (IOException e) {
            logger.error("Failed to write log header", e);
        }
    }

    // Method to append log footer with the end time
    public static void appendLogFooter() {
        try (FileWriter writer = new FileWriter("logs/App-" + java.time.LocalDate.now() + ".log", true)) {
            writer.write("-------------------------------------------------\n");
            writer.write("Log End: " + java.time.LocalDateTime.now() + "\n");
            writer.write("-------------------------------------------------\n\n");
        } catch (IOException e) {
            logger.error("Failed to write log footer", e);
        }
    }
}
