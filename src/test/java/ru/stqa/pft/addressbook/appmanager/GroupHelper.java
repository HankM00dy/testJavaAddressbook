package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Помогает работать с группами
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.xpath("//a[text()='group page']"));
    }

    public void submitGroupCreation() {
        click(By.xpath("//input[@value='Enter information']"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.xpath("//input[@name='group_name']"), groupData.getName());
        type(By.xpath("//textarea[@name='group_header']"), groupData.getHeader());
        type(By.xpath("//textarea[@name='group_footer']"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.xpath("//input[@name='new'][1]"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//input[@name='delete'][1]"));
    }

    // Выбирает произвольную группу
    public void selectGroup(int index) {
        // Находится список элементов, затем по нему производится клик (ищется индекс, поэтому всегда значение "index - 1"
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
    }

    // Выбирает произвольную группу
    public void selectGroupById(int id) {
        // Находится список элементов, затем по нему производится клик (ищется индекс, поэтому всегда значение "index - 1"
        wd.findElement(By.xpath("//input[@value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.xpath("//input[@name='edit'][1]"));
    }

    public void submitGroupModification() {
        click(By.xpath("//input[@name='update']"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        returnToGroupPage();
    }

    // Проверяет, что существует группа
    public boolean size() {
        return isElementPresent(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.xpath("//input[@name='selected[]']")).size();
    }

    public List<GroupData> list() {

        // Список, который будем заполнять, этот же список метод будет возвращать в конце
        List<GroupData> groups = new ArrayList<>();

        // Находим нужные элементы и получаем их в список
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='group']"));

        // Выполняем цикл
        for (WebElement element : elements) {
            String nameOfGroup = element.getText();

            // Добавляем поиск по "value", т.к он является уникальным идентификатором, который мжно использовать для сравнения неупорядоченных списков (множеств)
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            // Создаем объект, в который вносим имя группы
            GroupData group = new GroupData().withId(id).withName(nameOfGroup);

            // Добавляем в список
            groups.add(group);
        }
        return groups;
    }

    public Set<GroupData> all() {

        // Список, который будем заполнять, этот же список метод будет возвращать в конце
        Set<GroupData> groups = new HashSet<>();

        // Находим нужные элементы и получаем их в список
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='group']"));

        // Выполняем цикл
        for (WebElement element : elements) {
            String nameOfGroup = element.getText();

            // Добавляем поиск по "value", т.к он является уникальным идентификатором, который мжно использовать для сравнения неупорядоченных списков (множеств)
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            // Создаем объект, в который вносим имя группы
            GroupData group = new GroupData().withId(id).withName(nameOfGroup);

            // Добавляем в список
            groups.add(group);
        }
        return groups;
    }
}
