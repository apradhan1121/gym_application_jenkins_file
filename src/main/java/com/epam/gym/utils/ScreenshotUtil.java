package com.epam.gym.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    public static String takeScreenshot(WebDriver driver, String methodName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = "screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(filePath);
            Files.copy(scrFile.toPath(), destFile.toPath());

            return filePath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot due to IOException", e);
            return null;
        } catch(Exception e){
            logger.error("Failed to capture screenshot due to unexpected error", e);
            return null;
        }
    }
}
