package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Помогает работать с меню
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        // Проверяется, что одновременно присутсвует заголовок "Groups" и кнопка "New group", если это так, то дополнительных дейтсвий не требуется,
        // и мы уже находимся на страницы "Groups", в противном случае будет выполнен клик по кнопке "Groups" для перехода в нужны блок
        if (isElementPresent(By.xpath("//h1[text()='Groups']")) && isElementPresent(By.xpath("//input[@value='New group'][1]"))) {
            return;
        }
        click(By.xpath("//a[text()='groups']"));
    }

    public void goToHomePage() {
        // Проверяется, что на странице присутсвует таблица, если это так, то дополнительных дейтсвий не требуется, и мы уже находимся на страницы "Home"
        // в противном случае будет выполнен клик по кнопке "Home" для перехода в нужны блок
        if (isElementPresent(By.xpath("//table"))) {
            return;
        }
        click(By.xpath("//a[text()='home']"));
    }
}
