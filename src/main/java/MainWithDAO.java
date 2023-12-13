import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainWithDAO {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Griz4623120@");
            CarDAO carDAO = new CarDAO(connection);
        List<Car> cars = carDAO.getAllCars();
        Car car = new Car(10, "qwerty", "Germany");
        carDAO.insertNewCar(car);
        carDAO.updateCarById(10, new Car(10, "lexus", "Japan"));
        carDAO.deleteCarById(1);
        }
}
