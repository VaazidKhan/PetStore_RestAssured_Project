package api.utilities;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class LogHeaderSuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        // Add log header when the suite starts
        LogHeaderAppender.appendLogHeader();
    }

    @Override
    public void onFinish(ISuite suite) {
        // Add log footer when the suite finishes
        LogHeaderAppender.appendLogFooter();
    }

}
