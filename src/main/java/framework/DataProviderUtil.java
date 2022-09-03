package framework;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.User;
import models.UserRole;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtil {
    @DataProvider(name = "LoginCsvDataProvider")
    public static Iterator<Object[]> createDataFromFile() throws IOException, CsvValidationException {
        List<Object[]> list = new ArrayList<>();
        File usersList = new File("./src/test/resources/Users.csv");
        String filePath = usersList.getAbsolutePath();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] csvRow;
        reader.skip(1);
        while ((csvRow = reader.readNext()) != null) {
            User user = new User();
            user.setUsername(csvRow[0]);
            user.setFirstName(csvRow[1]);
            user.setLastName(csvRow[2]);
            user.setPassword(csvRow[3]);
            user.setRole(UserRole.valueOf(csvRow[4]));
            list.add(new Object[]{user});
        }
        return list.listIterator();
    }
}
