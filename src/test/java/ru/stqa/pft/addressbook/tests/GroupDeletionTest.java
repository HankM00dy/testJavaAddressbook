package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

    /**
     * Тест удаляет группу, чтобы удалить группу, она должна существовать. Проверяет, что при удалении группы, кол-во групп уменьшается на еденицу
     */


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

    @Test
    public void testGroupDeletion() {

        // Получает значение списка количества групп до прохождения теста
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().delete(deletedGroup);

        // Получает значение списка количества групп после прохождения теста
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}

