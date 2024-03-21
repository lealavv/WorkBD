package Larionova;

import java.sql.*;

/**
 * Класс для запуска программы вывода количества слов из записи, полученной SQl запросом.
 */
public class Main
{
    private static final String url = "jdbc:mysql://localhost:3306/new_db";
    private static final String user = "root";
    private static final String pass = "testtest";
    static String name;
    static String sql = "SELECT name FROM clients WHERE card_number = 4114638275091638";

    public static void main( String[] args )
    {
        try {
            // Создаем соединение с БД через метод getConnection
            Connection connection = getConnection(url, user, pass);

            // Отправляем SQL запрос и принимаем данные
            String name = createQuerySQL(connection, sql);

            // Делим полученный текст на слова
            String[] words = splitText(name);

            // Выводим сообщнение о количестве слов
            System.out.println("В полученной записи \"" + name + "\" " + words.length + " слов(а)");

            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception in main class");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url, String user, String pass)
    {
        /**
         * Этот метод выполняет соединения с базой данных
         * @param url адрес БД
         * @param user никнейм пользователя
         * @param pass пароль пользователя
         * @return соединение с БД
         */
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed");
            e.printStackTrace();
        }

        return connection;
    }

    public static String createQuerySQL(Connection connection, String sql)
    {
        /**
         * Этот метод выполняет SQl запрос и переводит полученные данные в String
         * @param connection соединение с БД
         * @param sql текст SQL запроса
         * @return запись в формате String
         */

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                name = resultSet.getString("name");
            }
            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println("Statement failed");
            e.printStackTrace();
        }

        return name;

    }

    public static String[]  splitText(String name)
    {
        /**
         * Этот метод делит полученную запись на отдельные слова
         * @param name String запись
         * @return массив слов
         */

        String[] words = new String[]{};
        try
        {
            words = name.split("\\s+");

        }
        catch (Exception e)
        {
            System.out.println("Split failed");
            e.printStackTrace();
        }
        return words;
    }



}
