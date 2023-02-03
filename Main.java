import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final int amountOper = 1000000;
    private static final int amountForAvearge = 1;

    private static ArrayList arrList;
    private static LinkedList lnkList;
    private static class TypeOper {
        public static int paste = 2;
        public static int delete = 1;
    }

    private static int addOrDelElementsToList(List list, int operation) {
        int allSumDurationArrList = 0;
        LocalDateTime timeBeg;
        LocalDateTime timeEnd;
        for (int j = 0; j < amountForAvearge; j++) {
            list = new ArrayList();
            timeBeg = LocalDateTime.now();
            for (int i = 0; i < amountOper; i++) {
                if (operation == TypeOper.paste)
                    list.add(i);
                else
                if (operation == TypeOper.delete)
                    list.remove(i);
            }
            timeEnd = LocalDateTime.now();
            Duration duration = Duration.between(timeBeg, timeEnd);
            allSumDurationArrList += duration.toMillis();
        }
        return allSumDurationArrList;
    }

    private static void WriteInfo(String nameOperation, int arrListTime, int lnkListTime) {
        String str = nameOperation + " быстрее в %s в %d раз на %d мс";
        if (arrListTime > lnkListTime)
            System.out.printf(str, "ArrayList", arrListTime / lnkListTime, arrListTime - lnkListTime);
        else
            System.out.printf(str, "LinkedList", lnkListTime / arrListTime, lnkListTime - arrListTime);
    }

    public static void main(String[] args) {

        //добавление элементов
        arrList = new ArrayList();
        int allSumDurationArrList = addOrDelElementsToList(arrList, TypeOper.paste);

        lnkList = new LinkedList();
        int allSumDurationLnkList = addOrDelElementsToList(lnkList, TypeOper.paste);
        WriteInfo("Добавление", allSumDurationArrList, allSumDurationLnkList);

        //удаление элементов
        allSumDurationArrList = addOrDelElementsToList(arrList, TypeOper.delete);

        allSumDurationLnkList = addOrDelElementsToList(lnkList, TypeOper.delete);
        WriteInfo("Удаление", allSumDurationArrList, allSumDurationLnkList);
    }
}
