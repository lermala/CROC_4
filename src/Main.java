import java.io.IOException;

/**
 *     ДЗ 4 (Stream IO)
 *     В текстовом файле слова могут быть разделены одним или несколькими пробелами, или символами перевода строки.
 *     Необходимо реализовать программу, считающую количество слов в файле и выводящую результат на экран.
 *     Путь к файлу задается первым аргументом командной строки (args[0]).
 *
 *     В случае, если аргумент не задан – кидать IllegalArgumentException.
 *     При ошибке открытия файла сообщать об этом в консоль без вывода стектрейса.
 */
public class Main {

    public static String INPUT_STR = "Забыл Панкрат      Кондратьевич домкрат,\n" +
            "А без домкрату ну  не поднять на тракте трактор.";

    public static void main(String[] args) throws IOException {

        // Наполняем файл текстом
        WorkWithIO workWithIOstart = new WorkWithIO("input.txt");
        workWithIOstart.writeStr(INPUT_STR);

        // Начинаем работу с файлом
        WorkWithIO workWithIO = new WorkWithIO();
        // Просим ввести в консоль путь к файлу (input.txt)
        String inp = workWithIO.inputData("Введите путь к файлу: "); // считываем значение из консоли
        workWithIO.setPATH(inp); // устанавливаем введенное значение как путь к файлу

        String content = workWithIO.getContent(); // получаем содержимое файла

        System.out.println("Содержимое файла:\n" + content);
        System.out.println("\nКоличество слов в файле: " + workWithIO.getWordCount());
    }
}
