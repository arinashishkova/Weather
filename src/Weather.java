import java.util.Random;
import java.util.Scanner;

public class Weather {
    static enum MONTH {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
        JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int minTemp = -30;
        int maxTemp = 30;

        // Массив для хранения погоды
        int[][] weather = new int[12][];

        weather[0] = new int[31];
        weather[1] = new int[28];
        weather[2] = new int[31];
        weather[3] = new int[30];
        weather[4] = new int[31];
        weather[5] = new int[30];
        weather[6] = new int[31];
        weather[7] = new int[31];
        weather[8] = new int[30];
        weather[9] = new int[31];
        weather[10] = new int[30];
        weather[11] = new int[31];

        // Заполнение массива случайными температурами
        for (int i = 0; i < weather.length; i++) {
            for (int j = 0; j < weather[i].length; j++) {
                switch (i) {
                    case 11: case 0: case 1: minTemp = -30; maxTemp = -5; break;
                    case 2: case 3: case 4: minTemp = -10; maxTemp = 10; break;
                    case 5: case 6: case 7: minTemp = 10; maxTemp = 30; break;
                    case 8: case 9: case 10: minTemp = -5; maxTemp = 15; break;
                }
                weather[i][j] = random.nextInt(maxTemp - minTemp + 1) + minTemp;
            }
        }

        // Ввод месяца и дня от пользователя
        System.out.println("Введите месяц (1 - январь, 12 - декабрь): ");
        int month = scanner.nextInt() - 1; // приводим к индексу массива
        System.out.println("Введите день: ");
        int day = scanner.nextInt() - 1; // приводим к индексу массива

        // Проверка, что день существует в этом месяце
        if (month < 0 || month > 11 || day < 0 || day >= weather[month].length) {
            System.out.println("Некорректная дата.");
        } else {
            System.out.println("Температура на указанную дату: " + weather[month][day] + " °C");
        }

        // Поиск самой теплой и самой холодной температуры
        int maxTemperature = Integer.MIN_VALUE;
        int minTemperature = Integer.MAX_VALUE;
        int warmestDayMonth = 0, warmestDay = 0;
        int coldestDayMonth = 0, coldestDay = 0;

        for (int i = 0; i < weather.length; i++) {
            for (int j = 0; j < weather[i].length; j++) {
                if (weather[i][j] > maxTemperature) {
                    maxTemperature = weather[i][j];
                    warmestDayMonth = i;
                    warmestDay = j;
                }
                if (weather[i][j] < minTemperature) {
                    minTemperature = weather[i][j];
                    coldestDayMonth = i;
                    coldestDay = j;
                }
            }
        }

        // Вывод самых теплых и холодных дней
        System.out.println("Самый теплый день: " + (warmestDay + 1) + " " + MONTH.values()[warmestDayMonth] +
                " с температурой " + maxTemperature + " °C");
        System.out.println("Самый холодный день: " + (coldestDay + 1) + " " + MONTH.values()[coldestDayMonth] +
                " с температурой " + minTemperature + " °C");

        // Расчет и вывод средней температуры по каждому месяцу
        System.out.println("Средняя температура по каждому месяцу:");
        for (int i = 0; i < weather.length; i++) {
            int sum = 0;
            for (int j = 0; j < weather[i].length; j++) {
                sum += weather[i][j];
            }
            double average = (double) sum / weather[i].length;
            System.out.printf("%10s: %.2f °C\n", MONTH.values()[i], average);
        }
    }
}