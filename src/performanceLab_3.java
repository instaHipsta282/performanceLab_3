import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class performanceLab_3 {
    /*
     3. В магазине 5 касс, в каждый момент времени к кассе стоит очередь некоторой длины. Каждые 30 минут измеряется средняя длина
     очереди в каждую кассу и для каждой кассы это значение (число вещественное) записывается в соответсвующий ей файл (всего 5 файлов),
     магазин работает 8 часов в день. Рассматривается только один день. На момент запуска приложения все значения уже находятся в файлах.
     Написать программу, которая по данным замеров определяет интервал времени, когда в магазине было наибольшее количество посетителей за день.
     */
    public static void main(String[] args) {
        Map<String, Double> timeMap = new HashMap<>();
        BufferedReader reader = null;
        try {
            for (int i = 0; i < args.length; i++) {
                reader = new BufferedReader(new FileReader(args[i]));
                String s;
                while ((s = reader.readLine()) != null) {
                    String intervalOfTime = s.split(" ")[0];
                    Double clientAVG = Double.parseDouble(s.split(" ")[1]);
                    if (!timeMap.containsKey(intervalOfTime)) {
                        timeMap.put(intervalOfTime, clientAVG);
                    }
                    else {
                        timeMap.put(intervalOfTime, timeMap.get(intervalOfTime) + clientAVG);
                    }
                }
            }
            Double maxClients = 0.0;
            Iterator<Map.Entry<String, Double>> iterator = timeMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> entry = iterator.next();
                if (entry.getValue() > maxClients) maxClients = entry.getValue();
            }
            Iterator<Map.Entry<String, Double>> iterator2 = timeMap.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry<String, Double> entry = iterator2.next();
                if (entry.getValue().equals(maxClients)) System.out.println(entry.getKey());
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            if (reader != null) {
                try { reader.close(); }
                catch (IOException ignore) {}
            }
        }
    }
}
