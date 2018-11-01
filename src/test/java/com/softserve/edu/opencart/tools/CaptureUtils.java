package com.softserve.edu.opencart.tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public final class CaptureUtils {

	private final String IMAGE_SUFFIX = "_CaptureScreenImage.png";
	private final String SOURCE_SUFFIX = "_CaptureSourceCode.txt";
	private final String MAVEN_DIRECTORY = "surefire.reports.directory";
	private final String DEFAULT_DIRECTORY = "test-output";
	private final String SERVER_DIRECTORY = "surefire.jenkins.server";
	private final String SERVER_ABSENT = "absent";
	private final String SLASH = "/";
	private final String FAILED_TO_CREATE = "Failed to create screenshot: %s";
	private TimeUtils timeUtils;

	public CaptureUtils() {
		this.timeUtils = new TimeUtils(TimeUtils.TimeTemplates.TIME_FULL);
	}

	private String getOutputDirectory() {
		String outputDirectory = System.getProperty(MAVEN_DIRECTORY);
		if ((outputDirectory == null) || (outputDirectory.isEmpty())) {
			outputDirectory = DEFAULT_DIRECTORY;
		}
		// System.out.println("\t+++++outputDirectory = " + outputDirectory);
		return outputDirectory + SLASH;
	}

	private String getRelativeFileName() {
		return getOutputDirectory() + timeUtils.getTimeText();
	}

	private String getRelativePath() {
		String classPath = CaptureUtils.class.getResource(SLASH).getPath().substring(1);
		System.out.println("\t\t+++++ classPath_base = " + classPath);
		classPath = classPath.substring(0, classPath.length() - 1);
		classPath = classPath.substring(0, classPath.lastIndexOf(SLASH));
		classPath = classPath.substring(0, classPath.lastIndexOf(SLASH) + 1);
		System.out.println("\t\t+++++ classPath_sub = " + classPath);
		return classPath;
	}

	private String getServerPath() {
		String basePath = System.getProperty(SERVER_DIRECTORY);
		if ((basePath == null) || (basePath.isEmpty()) || (basePath.equalsIgnoreCase(SERVER_ABSENT))) {
			basePath = getRelativePath();
		}
		return basePath;
	}

//    private String getAbsolutePathImageFileName() {
//        // System.out.println("\t\t\t+++ getAbsolutePathFileName() = "
//        // + CaptureScreen.class.getResource("/").getPath().substring(1)
//        // + getRelativePathFileName());
//        return getRelativeFileName() + IMAGE_SUFFIX;
//    }
//
//    private String getAbsolutePathSourceFileName() {
//        // System.out.println("\t\t\t+++ getAbsolutePathFileName() = "
//        // + CaptureScreen.class.getResource("/").getPath().substring(1)
//        // + getRelativePathFileName());
//        return getRelativeFileName() + SOURCE_SUFFIX;
//    }

	public File takeScreenFile() {
		return Application.get().getBrowser().getScreenshot();
	}

	/**
	 * @return Absolute path of filename.
	 */
	public String takeScreen(String absolutePathFileName) {
		// TODO Zoom page before take screen or move to element
		// File srcFile = Application.get().browser().getScreenshot();
		File srcFile = takeScreenFile();
		try {
			FileUtils.copyFile(srcFile, new File(absolutePathFileName));
		} catch (IOException e) {
			throw new RuntimeException(String.format(FAILED_TO_CREATE, absolutePathFileName), e);
			//throw new GeneralException(String.format(FAILED_TO_CREATE, absolutePathFileName), e);
		}
		return absolutePathFileName;
	}

	public String takeScreen() {
		String projectPathFileName = getRelativeFileName() + IMAGE_SUFFIX;
		takeScreen(getRelativePath() + projectPathFileName);
		return getServerPath() + projectPathFileName;
	}

	public String takeSourceCode(String absolutePathFileName) {
		// TODO get partial HTML code from srcFile
		String srcFile = Application.get().getBrowser().getSourceCode();
		// System.out.println("\t\t srcFile = " + srcFile);
		try {
			FileUtils.writeStringToFile(new File(absolutePathFileName), srcFile, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(String.format(FAILED_TO_CREATE, absolutePathFileName), e);
			//throw new GeneralException(String.format(FAILED_TO_CREATE, absolutePathFileName), e);
		}
		return absolutePathFileName;
	}

	public String takeSourceCode() {
		String projectPathFileName = getRelativeFileName() + SOURCE_SUFFIX;
		takeSourceCode(getRelativePath() + projectPathFileName);
		return getServerPath() + projectPathFileName;
	}

}