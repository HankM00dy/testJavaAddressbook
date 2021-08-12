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
        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    /**
     * Тест модифицирует группу, чтобы модифицировать группу, она должна существовать. Проверяет, что при модификации группы, кол-во групп не изменяется
     */
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();


        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(beforeRunningTest.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData( "test1", "test2", "test3", beforeRunningTest.get(beforeRunningTest.size() - 1).getId());
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.getGroupHelper().getGroupList();
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
        app.getNavigationHelper().goToGroupPage();

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.getGroupHelper().getGroupList();
        int index = beforeRunningTest.size() - 1;
        GroupData group = new GroupData( "test1", "test2", "test3", beforeRunningTest.get(index).getId());

        app.getGroupHelper().modifyGroup(index, group);

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.getGroupHelper().getGroupList();
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
