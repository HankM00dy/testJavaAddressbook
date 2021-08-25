package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testContactCreation() {
        app.goTo().goToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("test_name", "test_surname", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void test1() {
        System.out.println("Test1 success");
    }

    @Test
    public void test2() {
        System.out.println("Test2 success");
    }

    @Test
    public void test3() {
        System.out.println("Test3 success");
    }
}
