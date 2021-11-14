import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для работы с текстовым файлом.
 */
public class WorkWithIO {
    String PATH; // путь к файлу
    String CHARSET_NAME = "UTF-8"; // кодировка
    //String CHARSET_NAME = "Windows-1251"; // кодировка (не работает)

    public WorkWithIO(String PATH) {
        this.PATH = PATH;
    }

    public WorkWithIO() {
    }

    /**
     * Запись текста в файл.
     * @param writtenStr - строка, которая запишется в файл.
     */
    public void writeStr(String writtenStr){
        try (OutputStream out = new FileOutputStream(PATH)) {
            out.write(writtenStr.getBytes(Charset.forName("UTF-8")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Функция для получения содержимого файла
     * @return content - текст, который содержится в файле
     * @throws FileNotFoundException
     */
    public String getContent() throws FileNotFoundException {
        String content = "";
        try (Reader r = new InputStreamReader(
                new FileInputStream(PATH), CHARSET_NAME)) {
            int c;
            while ((c = r.read()) != - 1)
                content += (char) c;
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    /**
     * Функция, считающая количество слов в файле
     * @return количество слов в файле
     */
    public int getWordCount() throws FileNotFoundException {
        String content = getContent(); // получаем содержимое файла
        String[] words = content.split("\\s+"); // разделяем строку на массив слов при помощи разделителя пробел
        return words.length;
    }

    /**
     * Функция, которая выводит сообщение в консоль и считывает введенное значение.
     * @param outputStr - сообщение, которое выводится в консоль
     * @return введенное в консоль значение
     */
    public String inputData(String outputStr){
        Scanner in = new Scanner(System.in);
        System.out.print(outputStr); //выводим сообщение в cmd
        String str = in.nextLine(); // считываем

        // если строка пустая = если аргумент не задан выбрасываем IllegalArgumentException
        if (str.equals("")) {
            throw new IllegalArgumentException("Аргумент не задан");
        }

        in.close(); // закрываем
        return str;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    /**
     * Запись строки в конец файла
     * @param writtenStr - строка, которая запишется в конец файла.
     */
    public void writeStrToEnd(String writtenStr){
        try (OutputStream out = new FileOutputStream(PATH, true)){
            out.write(writtenStr.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывание байт
     * @throws IOException
     */
    public void readByte() throws IOException {
        // Буферизированный фильтр читает порцию данных во временную память (буфер) и работает с ней даже при чтении по одному байту.
        // Существенно ускоряет работу с файлами и другими медленными источниками данных.
        try (InputStream in = new BufferedInputStream (
                new FileInputStream(PATH))) { // буферизация
            int b;
            while ((b = in.read()) != -1)
                System.out.println("Next byte: " + b);
        }
    }

    /**
     * Удаление файла.
     * @throws IOException
     */
    public void deleteFile() throws IOException {
        Path path = Paths.get(PATH);
        Files.deleteIfExists(path);
    }

    /**
     * Получение содержимого файла в виде строк
     * @return contentByLines - список строк файла
     */
    public List<String> getContentByLines(){
        List<String> contentByLines = new ArrayList<>(); // список строк файла

        try (Scanner s = new Scanner(new FileReader(PATH))) {
            while (s.hasNextLine()){
                contentByLines.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return contentByLines;
    }
}
