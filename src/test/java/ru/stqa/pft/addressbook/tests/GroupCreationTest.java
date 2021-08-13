package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {

    /**
     * Тест создания новой группы. Проверяет, что при создании новой группы, кол-во групп увеличивается на еденицу
     */
    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        // Получает значение списка количества групп до прохождения теста
        Set<GroupData> beforeRunningTest = app.group().all();

        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        // Получает значение списка количества групп после прохождения теста
        Set<GroupData> afterRunningTest = app.group().all();
        Assert.assertEquals(afterRunningTest.size(), beforeRunningTest.size() + 1);

        group
                // Нужно взять максимальный идентификатор
                // Берем группу с уже известными идентификаторами
                .withId(afterRunningTest
                        // Превращаем ее в поток
                        .stream()
                        // Поток GroupData превращается в поток чисел с помощью мапинга
                        .mapToInt((g)
                                // Получаем поток чисел
                                -> g.getId())
                        // сравниваем числа
                        .max()
                        .getAsInt());

        // Добавляем новую группу
        beforeRunningTest.add(group);
        Assert.assertEquals(afterRunningTest, beforeRunningTest);
    }
}
