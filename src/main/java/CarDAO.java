import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CarDAO {

    private Connection connection;

    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM car");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            Car car = new Car(id, name, country);
            cars.add(car);
        }
        return cars;
    }

    public void getWithPagination() throws SQLException {
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        System.out.println("У нас 99 машин. Введите количество машин на экране");
        int listSize = scanner.nextInt();
        System.out.println("Какую страницу Вы хотите посмотреть?");
        int site = scanner.nextInt();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM car LIMIT " + listSize + " OFFSET " + site*listSize);
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") + resultSet.getString("country"));
        }
    }


    public void insertNewCar(Car car) throws SQLException {
        Statement statement = connection.createStatement();
        int id = car.getId();
        String name = car.getName();
        String country = car.getCountry();
        String str = "INSERT INTO car VALUES (" + id + ", '" + name + "', '" + country + "');";
        statement.execute(str);
    }

    public void updateCarById(int id, Car car) throws SQLException {
        Statement statement = connection.createStatement();
        String name = car.getName();
        String country = car.getCountry();
        String str = "UPDATE car SET name = '" + name + "', country = '" + country + "' WHERE id = " + id;
        for (Car cara : getAllCars()) {
            if (cara.getId() == id){
                statement.execute(str);
            }
        }
    }
    public void deleteCarById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String str = "DELETE FROM car WHERE id = " + id;
        statement.execute(str);

    }

    public void fillTable() {
        File file = new File("./cars.txt");
        List<Car> cars = new ArrayList<>();
        AtomicInteger id = new AtomicInteger();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.lines()
                    .map(line -> {
                        String[] carData = line.split(", ");
                        Car car = new Car(id.incrementAndGet(), carData[0], carData[1]);
                        try {
                            insertNewCar(car);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return car;
                    })
                    .forEach(cars::add);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public CarDAO(Connection connection) {
        this.connection = connection;
    }
}
