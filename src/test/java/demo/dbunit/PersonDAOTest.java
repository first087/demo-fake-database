package demo.dbunit;

import static org.junit.Assert.fail;

import java.io.File;
import java.nio.charset.Charset;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonDAOTest {

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String UTF8 = "UTF8";

    @BeforeClass
    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "schema/person.sql", Charset.forName(UTF8), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("./data/person.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
