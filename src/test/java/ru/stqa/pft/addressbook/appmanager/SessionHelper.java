package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Помогает осуществлять логин и логаут
 */
public class SessionHelper extends HelperBase {

    // ссылка на драйвер не нужна, т.к наследуется из базового класса
    //    WebDriver wd;

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        type(By.xpath("//input[@name='user']"), username);
        type(By.xpath("//input[@name='pass']"), password);
        click(By.xpath("//input[@type='submit']"));
    }
}
