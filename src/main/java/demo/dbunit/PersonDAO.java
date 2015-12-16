package demo.dbunit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();

        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection("");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TABLE");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                persons.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

}
