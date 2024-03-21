package Larionova;


import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;

/**
 * Unit тесты для методов класса Main.
 */
public class TestMain
{
    String url = "jdbc:mysql://localhost:3306/new_db";
    String user = "root";
    String pass = "testtest";

    // Проверка правильности соеднинения с БД
    @Test
    public void testConnection() throws SQLException {

        Connection actualConnection = Main.getConnection(url, user, pass);

        assertFalse(actualConnection.isClosed());
        assertEquals(actualConnection.getMetaData().getURL(), url);
        assertEquals(actualConnection.getMetaData().getUserName(), "root@localhost");

        actualConnection.close();
    }

    // Проверка правильности возвращаемого значения SQL запросом
    @Test
    public void testQuerySQL() throws SQLException {
        Connection testConnection = DriverManager.getConnection(url, user, pass);
        Statement testStatement = testConnection.createStatement();

        String sql = "SELECT name FROM clients WHERE card_number = 4114638275091638";
        String actualName = Main.createQuerySQL(testConnection, sql);

        String exceptedName = "Волкова Марина Олеговна";

        assertEquals(exceptedName, actualName);

        testConnection.close();
        testStatement.close();

    }

    // Проверка правильности разделения текста на слова
    @Test
    public void testSplit()
    {
        String name = "Волкова Марина Олеговна";

        String[] expextedWords = {"Волкова", "Марина", "Олеговна"};
        String[] actualWords = Main.splitText(name);

        assertArrayEquals(expextedWords, actualWords);
    }
}
