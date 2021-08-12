package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.xpath("//a[text()='add new']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastName());

        if (creation) {
            new Select(wd.findElement(By.xpath("//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse((isElementPresent(By.xpath("//select[@name='new_group']"))));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void returnToHomePage() {
        click(By.xpath("//a[text()='home']"));
    }

    public void initContactModification() {
        click(By.xpath("(//img[@alt='Edit'])[1]"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[@name='update'][2]"));
    }
}
