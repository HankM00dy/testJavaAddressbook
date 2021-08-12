package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    // Метод проверяет, есть ли какой-то текст для ввода в параметре, если есть, то он будети введен, если в методе выставлено значение null, то текст введен не будет.
    // Проверка происходит именно с помощью "==", т.к нужно проверить именно наличие самой ссылки на какой-то объект String в поле
    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {

            // Также можно реализовать проверку, которая позволит не вводить какое-либо текстовое значение, если точно такое же значение уже введено в поле
            // Проверка в таком случае производится с помощью метода .equals, т.к проверяем именно содержимое объекта. Знак "!" означает "не равно"
            // блока else в таком методе может не быть вообще
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

//    // Обычный метод type по умолчанию
//    protected void type(By locator, String text) {
//        click(locator);
//        wd.findElement(locator).clear();
//        wd.findElement(locator).sendKeys(text);
//    }

    public boolean isAllertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}