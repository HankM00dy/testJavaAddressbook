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
        app.goTo().groupPage();
        // Проверяется, что существует какая-либо группа, если ее нет, то группа предварительно создается
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {

        // Получает значение списка количества групп до прохождения теста
        List<GroupData> beforeRunningTest = app.group().list();

        int index = beforeRunningTest.size() - 1;
        app.group().delete(index);

        // Получает значение списка количества групп после прохождения теста
        List<GroupData> afterRunningTest = app.group().list();
        Assert.assertEquals(afterRunningTest.size(), index);

        // Уменьшаем кол-во элементов списка на еденицу, т.к была удалена одна группа
        beforeRunningTest.remove(index);

        // Сравниваем два списка (список beforeRunningTest станет на еденицу меньше)
        Assert.assertEquals(beforeRunningTest, afterRunningTest);
    }
}

