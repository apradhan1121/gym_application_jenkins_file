package com.epam.gym.pages;

import com.epam.gym.utils.DateUtils;
import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BookWorkOutPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='react-calendar__navigation']/button[2]")
    WebElement monthAndYearPickerButton;

    @FindBy(xpath = "//button[text()='Book Workout']")
    WebElement bookWorkOutButton;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private WebElement toastElement;



    public BookWorkOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectYear(int year) {
        Actions actions = new Actions(driver);
        actions.doubleClick(monthAndYearPickerButton).perform();
        WebElement yearToSelect =driver.findElement(By.xpath("//button[text()=" + year + "]"));
       // WaitHelper.waitForElementToBeInvisible(driver,yearToSelect,10);
        yearToSelect.click();
    }

    public void selectMonth(String month) {
        driver.findElement(By.xpath("//button/abbr[text()='" + month + "']")).click();
    }

    public void selectDate(int date) {
        driver.findElement(By.xpath("//button/abbr[text()=" + date + "]")).click();
    }

    public void selectTimeSlot(String timeSlot) {
        driver.findElement(By.xpath("//p[text()='" + timeSlot + "']")).click();
    }

    public boolean isTimeSlotButtonDisabled(String timeSlot) {
        return driver.findElement(By.xpath("//p[text()='" + timeSlot + "']")).isEnabled();
    }

    public void validateTimeSlot(String timeSlot) {
        // Extract start time from the time slot, e.g., "08:00 - 09:00 AM" -> "08:00 AM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        String[] timeRange = timeSlot.split(" - ");
        //String startTimeString = timeRange[0];// "08:00 AM"
        String startTimeString = timeRange[0] + " " + timeRange[1].split(" ")[1];

        // Convert the start time to LocalTime
        LocalTime selectedTime = LocalTime.parse(startTimeString, formatter);

        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Validate if the selected time is in the past
        if (selectedTime.isBefore(currentTime)) {
            System.out.println("Cannot select a past time slot.");
        } else {
            System.out.println("Time slot is valid.");
            driver.findElement(By.xpath("//p[text()='" + timeSlot + "']")).click();
        }
    }

    public void validateBookingDateTime(int year, String month, int date, String timeSlot) {
        int currentYear = DateUtils.getCurrentYear();
        int currentMonth = DateUtils.getCurrentMonthValue();
        int currentDate = DateUtils.getCurrentDate();

        // Convert selected month to its numeric equivalent
        int selectedMonth = DateUtils.convertMonthToNumber(month);


        // Validate if the selected date is today or in the future
        if (year > currentYear ||
                (year == currentYear && selectedMonth > currentMonth) ||
                (year == currentYear && selectedMonth == currentMonth && date > currentDate)) {
                     selectYear(year);
                     selectMonth(month);
                     selectDate(date);
                    selectTimeSlot(timeSlot);
            }
        else if (year == currentYear && selectedMonth == currentMonth && date == currentDate) {
            // Today's date: Validate time slot
            selectYear(year);
            selectMonth(month);
            selectDate(date);
            validateTimeSlot(timeSlot); // Only validate time for today's date
        }
        else {
            String pastField = null;
            if (year < currentYear) {
                pastField = "Year";
            } else if (selectedMonth < currentMonth) {
                pastField = "Month";
            } else {
                pastField = "Date";
            }
            switch (pastField) {
                case "Year":
                    System.out.println("select a future year");
                    break;
                case "Month":
                    System.out.println("select a future month");
                    break;
                case "Date":
                    System.out.println("select a future date");
                default:
                    System.out.println("All selections are valid.");
                    break;
            }
        }
        }

        public boolean isBookWorkOutButtonEnabled() {
            return bookWorkOutButton.isEnabled();
        }

        public void clickBookWorkOutButton() {
            bookWorkOutButton.click();
        }

    public String  getToastMessage() {
        WaitHelper.waitForElementToBeVisible(driver, toastElement, 15);
        return toastElement.getText();
    }

    public CancelAndPostFeedbackPage navigateToCancelAndPostFeedbackPage() {
        return new CancelAndPostFeedbackPage(driver);
    }
}
