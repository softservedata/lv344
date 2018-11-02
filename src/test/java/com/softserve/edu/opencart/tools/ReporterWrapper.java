package com.softserve.edu.opencart.tools;

import org.testng.Reporter;

import com.softserve.edu.opencart.data.IApplicationSource;

public class ReporterWrapper {
    
    private enum ReporterTags {
        BR_DISPLAY("<br>[DISPLAY]"),
        BR_ERROR("<br>[ERROR]"),
        BR_WARNING("<br>[WARNING]"),
        BR_INFO("<br>[INFO]"),
        BR_DEBUG("<br>[DEBUG]");
        
        private String field;

        private ReporterTags(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }
    
    private enum ReporterLevels {
        ERROR_LEVEL(2),
        WARNING_LEVEL(5),
        INFO_LEVEL(7),
        DEBUG_LEVEL(9);
        
        private int level;

        private ReporterLevels(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }
    }

    //private static final String IMG_TEMPLATE = "<br><img src='%s' alt='could not take screen shot' width='80%' height='80%'>";
    private static final String IMG_TEMPLATE = "<br><div><image style=\"max-width:90%%\" src=\"%s\"  alt=\"could not take screen shot\" /></div>";
    private static final String SCREENSHOT_FILENAME = "<br><p>Screenshot filename is %s</p>";
    private static final String SOURCECODE_FILENAME = "<br><p><a href='%s'>Source Code</a> filename is %s</p>";
    private final String SPACE_SYMBOL = " ";
    private TimeUtils timeUtils;
    private CaptureUtils captureUtils;
    private boolean consoleOutput;

    public ReporterWrapper(IApplicationSource applicationSource) {
        this.timeUtils = new TimeUtils(TimeUtils.TimeTemplates.TIME_REPORT);
        this.captureUtils = Application.get().captureUtils();
        this.consoleOutput = applicationSource.getConsoleOutput();
        // TODO Set default verbose.
    }

    private String getCurrentTime() {
		return SPACE_SYMBOL + timeUtils.getTimeText() + SPACE_SYMBOL;
	}
    
    public void display(String message){
        Reporter.log(ReporterTags.BR_DISPLAY.toString()
                + getCurrentTime() + message, consoleOutput);
    }

    public void error(String message){
        Reporter.log(ReporterTags.BR_ERROR.toString()
                + getCurrentTime() + message,
                ReporterLevels.ERROR_LEVEL.getLevel(), consoleOutput);
    }

    public void warning(String message){
        Reporter.log(ReporterTags.BR_WARNING.toString()
                + getCurrentTime() + message,
                ReporterLevels.WARNING_LEVEL.getLevel(), consoleOutput);
    }

    public void info(String message){
        Reporter.log(ReporterTags.BR_INFO.toString()
                + getCurrentTime() + message,
                ReporterLevels.INFO_LEVEL.getLevel(), consoleOutput);
    }

    public void debug(String message){
        Reporter.log(ReporterTags.BR_DEBUG.toString()
                + getCurrentTime() + message,
                ReporterLevels.DEBUG_LEVEL.getLevel(), consoleOutput);
    }

    public String addHtmlSourceCode() {
        String sourceCodeFileName = captureUtils.takeSourceCode(); 
        display(String.format(SOURCECODE_FILENAME,
                RegexUtils.extractPathWithoutServer(RegexUtils.DELETE_SERVER, sourceCodeFileName),
                sourceCodeFileName));
        return sourceCodeFileName;
    }

    public String addScreenShot() {
      String screenFileName = captureUtils.takeScreen();
      display(String.format(SCREENSHOT_FILENAME, screenFileName));
      display(String.format(IMG_TEMPLATE,
              RegexUtils.extractPathWithoutServer(RegexUtils.DELETE_SERVER, screenFileName)));
      //saveImageAttachAllure(screenFileName);
      return screenFileName;
    }

    // TODO Add method saveImageAttachAllure
//    @Attachment(value = "{0}", type = "image/png")
//    public static byte[] saveImageAttachAllure(String attachName) {
//        try {
//            return Files.readAllBytes(attachName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new byte[0];
//    }

}