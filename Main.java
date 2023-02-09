import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Тестирование ветки
public class Main {
    private static final int amountOper = 1000000;
    private static final int amountForAverage = 5000;

    private static ArrayList arrList;
    private static LinkedList lnkList;
    private static class TypeOper {
        public static int add = 2;
        public static int delete = 1;
        public static int paste = 3;
    }

    private static int addPasteOrDelElementsToList(List list, int operation) {
        int allSumDurationArrList = 0;
        LocalDateTime timeBeg;
        LocalDateTime timeEnd;
        int averageSum = 0;
        for (int j = 0; j < amountForAverage; j++) {
            if(operation == TypeOper.add || operation == TypeOper.paste)
                list.clear();
            else
                //Заполнить список перед удалением элементов
                if((operation == TypeOper.delete) && list.isEmpty()) {
                    for (int i = 0; i < amountOper; i++) {
                        list.add(i);
                    }
                }
            for (int i = 0; i < amountOper; i++) {
                timeBeg = LocalDateTime.now();
                if (operation == TypeOper.add)
                    list.add(i);
                else
                if (operation == TypeOper.delete)
                    list.remove(0);
                else
                if (operation == TypeOper.paste)
                    //вставляем в середину списка
                    list.add(list.size()/2, i);

                timeEnd = LocalDateTime.now();
                Duration duration = Duration.between(timeBeg, timeEnd);
                allSumDurationArrList += duration.toMillis();
            }
            averageSum+=allSumDurationArrList;
            allSumDurationArrList=0;
        }
        return averageSum/amountForAverage;
    }

    private static void WriteInfo(String nameOperation, int arrListTime, int lnkListTime) {
        String str = nameOperation + " быстрее в %s в %d раз на %d мс";
        if (arrListTime > lnkListTime)
            System.out.printf(str, "ArrayList", (lnkListTime>0)?arrListTime/lnkListTime:0, arrListTime - lnkListTime);
        else
            System.out.printf(str, "LinkedList", (arrListTime>0)?lnkListTime/arrListTime:0, lnkListTime - arrListTime);
    }

    public static void main(String[] args) {

        //добавление элементов
        arrList = new ArrayList();
        lnkList = new LinkedList();
        int allSumDurationArrList = addPasteOrDelElementsToList(arrList, TypeOper.add);

        int allSumDurationLnkList = addPasteOrDelElementsToList(lnkList, TypeOper.add);
        WriteInfo("Добавление", allSumDurationArrList, allSumDurationLnkList);

        System.out.println();
        //удаление элементов
        allSumDurationArrList = addPasteOrDelElementsToList(arrList, TypeOper.delete);

        allSumDurationLnkList = addPasteOrDelElementsToList(lnkList, TypeOper.delete);
        WriteInfo("Удаление", allSumDurationArrList, allSumDurationLnkList);

        System.out.println();
        //вставка элементов
        allSumDurationArrList = addPasteOrDelElementsToList(arrList, TypeOper.paste);

        allSumDurationLnkList = addPasteOrDelElementsToList(lnkList, TypeOper.paste);
        WriteInfo("Вставка", allSumDurationArrList, allSumDurationLnkList);
    }
}
