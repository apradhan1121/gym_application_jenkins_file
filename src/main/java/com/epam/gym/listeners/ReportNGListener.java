//package com.epam.travel.listeners;
//
//import com.epam.travel.browsers.DriverSingleton;
//import com.epam.travel.utils.ScreenshotUtil;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class ReportNGListener implements ITestListener {
//
//    private static final Logger logger = LogManager.getLogger(ReportNGListener.class);
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        WebDriver driver = DriverSingleton.getDriver();
//
//        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
//        logger.info("Screenshot saved at: {}", screenshotPath);
//
//    }
//}
//
