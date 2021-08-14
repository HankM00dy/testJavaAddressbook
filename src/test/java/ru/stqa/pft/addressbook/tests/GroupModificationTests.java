package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    /**
     * Выставляем аннотацию @BeforeMethod потому что проверка должна выполняться перед каждым тестом в классе
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    /**
     * Тест модифицирует группу, чтобы модифицировать группу, она должна существовать. Проверяет, что при модификации группы, кол-во групп не изменяется
     */
    @Test
    public void testGroupModificationWithSortedCollections() {

        // Получает значение списка количества групп до прохождения теста
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");

        app.group().modify(group);

        // Получает значение списка количества групп после прохождения теста
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));

        /**
         * Задача проверить, что после модификации списки остались одинаковыми до и после теста
         */
        // При модификации группы, сменится ее имя, группа может попасть в рандомное место списка по алфавиту
        // Для такой проверки нужно использовать неупорядоченные множества


        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
