import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public void insertNewCar(Car car) throws SQLException {
        Statement statement = connection.createStatement();
        int id = car.getId();
        String name = car.getName();
        String country = car.getCountry();
        String str = "INSERT INTO car VALUES (" + id + ", '" + name + "', '" + country + "');";
        statement.execute(str);
        System.out.println(str);
    }

    public void updateCarById(int id, Car car) throws SQLException {
        Statement statement = connection.createStatement();
        String name = car.getName();
        String country = car.getCountry();
        String str = "UPDATE car SET name = '" + name + "', country = '" + country + "' WHERE id = " + id;
        System.out.println(str);
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
        System.out.println(str);

    }

    public CarDAO(Connection connection) {
        this.connection = connection;
    }
}
