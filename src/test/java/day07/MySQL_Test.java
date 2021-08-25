package day07;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

/*
library1.database.url=jdbc:mysql://18.233.97.71:3306/library1
library1.database.username=library1_client
library1.database.password=WVF4NdGXCKHeE6VQ

You need to add JDBC dependency below:
<dependency>
            <groupId>com.oracle.ojdbc</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>19.3.0.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>
 */
public class MySQL_Test {

    // add your dependency for mysql driver
    // add your db_utility class
    // add your configuration reader
    // DB_Utility class has a method called create connection
    // and it accept env as string and add .database.. at the end to find correct credenatos
    // for example if we pass library1
    // it will pick up library1.database.url library1.database.username library1.database.password
    @BeforeAll
    public static void initDB() {
        DB_Utility.createConnection("library1");
    }


    @DisplayName("Quick DB Check")
    @Test
    public void testthingsout() {
        DB_Utility.runQuery("SELECT * FROM books");
        DB_Utility.displayAllData();

    }

    @AfterAll
    public static void destroy() {
        DB_Utility.destroy();

    }
}
