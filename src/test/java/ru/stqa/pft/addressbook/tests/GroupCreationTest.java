package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    /**
     * Тест создания новой группы. Проверяет, что при создании новой группы, кол-во групп увеличивается на еденицу
     */
    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        // Получает значение списка количества групп до прохождения теста
        Groups before = app.group().all();

        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        // Получает значение списка количества групп после прохождения теста
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
