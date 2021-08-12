package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    /**
     * Тест удаляет группу, чтобы удалить группу, она должна существовать. Проверяет, что при удалении группы, кол-во групп уменьшается на еденицу
     */


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

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();


        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(beforeRunningTest.size() - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterRunningTest.size(), beforeRunningTest.size() - 1);

        // Уменьшаем кол-во элементов списка на еденицу, т.к была удалена одна группа
        beforeRunningTest.remove(beforeRunningTest.size() - 1);

        // Сравниваем два списка (список beforeRunningTest станет на еденицу меньше)
        Assert.assertEquals(beforeRunningTest, afterRunningTest);
    }
}

