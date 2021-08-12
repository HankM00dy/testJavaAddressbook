package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    /**
     * Тест создания новой группы. Проверяет, что при создании новой группы, кол-во групп увеличивается на еденицу
     */
    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterRunningTest.size(), beforeRunningTest.size() + 1);

        // Сравниваем неупорядоченные множества
        // Проблема в том, что мы не знаем уникальный индентификатор новой группы, которая создается, принимаем, что это самое большое значение среди всех идентификаторов

//        // В цикле сравниваются все идентификаторы
//        int max = 0;
//        for (GroupData g : afterRunningTest) {
//            if (g.getId() > max ) {
//                max = g.getId();
//            }
//        }

        /**
         * Упрощение решения задачи с помощью особенностей JAVA 8
         */

//        // Компоратор в данном случае является сравнивателем двух объектов
//        int max = afterRunningTest
//
//                // Список превращаем в поток
//                .stream()
//
//                // По потоку пробегает функция сравниватель и находит максимальный элемент
//                .max(Comparator.comparingInt(GroupData::getId))
//
//                // Получаем группу с максимальным идентификатором
//                .get()
//
//                // Забираем ее идентификатор
//                .getId();
//
//        // Получаем самый большой Id
//        group.setId(max);

        // Добавляем новую группу
        beforeRunningTest.add(group);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        beforeRunningTest.sort(byId);
        afterRunningTest.sort(byId);

        Assert.assertEquals(afterRunningTest, beforeRunningTest);
    }
}
