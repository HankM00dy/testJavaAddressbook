package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.testng.Assert.*;

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
        assertEquals(afterRunningTest.size(), beforeRunningTest.size() + 1);

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
        assertEquals(afterRunningTest, beforeRunningTest);
    }


    /**
     * Тест создания новой группы. Проверяет, нельзя создать группу со знаком "'" в названии
     */
    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> beforeRunningTest = app.group().all();
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);
        assertEquals(app.group().count(), beforeRunningTest.size());
        Set<GroupData> afterRunningTest = app.group().all();
        group.withId(afterRunningTest.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertEquals(afterRunningTest, beforeRunningTest);
    }
}
