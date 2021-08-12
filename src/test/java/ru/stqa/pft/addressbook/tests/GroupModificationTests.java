package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    /**
     * Выставляем аннотацию @BeforeMethod потому что проверка должна выполняться перед каждым тестом в классе
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    /**
     * Тест модифицирует группу, чтобы модифицировать группу, она должна существовать. Проверяет, что при модификации группы, кол-во групп не изменяется
     */
    @Test
    public void testGroupModification() {

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.group().list();

        app.group().selectGroup(beforeRunningTest.size() - 1);
        app.group().initGroupModification();
        GroupData group = new GroupData( "test1", "test2", "test3", beforeRunningTest.get(beforeRunningTest.size() - 1).getId());
        app.group().fillGroupForm(group);
        app.group().submitGroupModification();
        app.group().returnToGroupPage();

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.group().list();
        Assert.assertEquals(afterRunningTest.size(), beforeRunningTest.size());

        /**
         * Задача проверить, что после модификации списки остались одинаковыми до и после теста
         */
        // При модификации группы, сменится ее имя, группа может попасть в рандомное место списка по алфавиту
        // Для такой проверки нужно использовать неупорядоченные множества

        // Удаляем из списка группу, которую мы выбрали для модификации, т.к она перестанет существовать
        beforeRunningTest.remove(beforeRunningTest.size() - 1);

        // Вместо нее добавляем новую группу
        beforeRunningTest.add(group);

        // Сравниваем неупорядоченные множества, проблема в том, что множества не позволяют дублирование элементов -> группы с оиднаковыми именами будут считаться за одну группу
        // Для этого вводим уникальный идентифиактор "id"
        Assert.assertEquals(new HashSet<Object>(afterRunningTest), new HashSet<Object>(beforeRunningTest));
    }


    /**
     * Тест модифицирует группу, чтобы модифицировать группу, она должна существовать. Проверяет, что при модификации группы, кол-во групп не изменяется
     */
    @Test
    public void testGroupModificationWithSortedCollections() {

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.group().list();
        int index = beforeRunningTest.size() - 1;
        GroupData group = new GroupData( "test1", "test2", "test3", beforeRunningTest.get(index).getId());

        app.group().modify(index, group);

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.group().list();
        Assert.assertEquals(afterRunningTest.size(), beforeRunningTest.size());

        /**
         * Задача проверить, что после модификации списки остались одинаковыми до и после теста
         */
        // При модификации группы, сменится ее имя, группа может попасть в рандомное место списка по алфавиту
        // Для такой проверки нужно использовать неупорядоченные множества

        // Удаляем из списка группу, которую мы выбрали для модификации, т.к она перестанет существовать
        beforeRunningTest.remove(index);

        // Вместо нее добавляем новую группу
        beforeRunningTest.add(group);

        // Сравниваем неупорядоченные множества, проблема в том, что множества не позволяют дублирование элементов -> группы с оиднаковыми именами будут считаться за одну группу
        // Для этого вводим уникальный идентифиактор "id"
        
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        beforeRunningTest.sort(byId);
        afterRunningTest.sort(byId);
        Assert.assertEquals(afterRunningTest, beforeRunningTest);
    }
}
