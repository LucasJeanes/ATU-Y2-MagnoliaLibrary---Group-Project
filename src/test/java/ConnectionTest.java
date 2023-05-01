import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    static Connection connection;
    static {

        dbConnection dbConnection = new dbConnection();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = dbConnection.connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void testConnection() throws Exception {
        String testDbName = connection.getCatalog();
        assertEquals("magnolia_rebornlib", testDbName);
        connection.close();
    }
}
