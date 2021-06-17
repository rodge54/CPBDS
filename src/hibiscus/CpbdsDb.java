package hibiscus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class CpbdsDb {

    final String dbConnString = "jdbc:sqlite:hibiscus.db";

    public void databaseSetup(){
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            // Create new tables if they do not exist
            statement.execute("CREATE TABLE IF NOT EXISTS person(person_id INTEGER PRIMARY KEY, name TEXT, state TEXT, about_me TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS flower(flower_id INTEGER PRIMARY KEY, mom_id INTEGER, dad_id INTEGER, hybridizer_id INTEGER NOT NULL, grower_id INTEGER NOT NULL, name TEXT NOT NULL, main_color TEXT, free_text TEXT," +
                    "FOREIGN KEY(mom_id) REFERENCES flower(flower_id), FOREIGN KEY(dad_id) REFERENCES flower(flower_id), FOREIGN KEY(hybridizer_id) REFERENCES person(person_id), FOREIGN KEY(grower_id) REFERENCES person(person_id))");

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public boolean createPerson(String name, String state, String aboutMe){
        boolean success;
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("INSERT INTO person(name, state, about_me) VALUES('"+ name + "', '" + state + "', '" + aboutMe + "')");
            System.out.println("New Person Created!");
            success = true;

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            success = false;
        }
        return success;
    }

    //TODO: readPerson() method has not been started.  Will probably need to be renamed
    public void readPerson(){
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();
//            statement.execute("INSERT INTO person(name, state, about_me) VALUES('Marika Rodgers', 'FL', 'I Love Hibiscus!')");
            statement.execute("SELECT * FROM person");



            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Connects and reads all people in database.
     * @return arrayList of people objects
     */
    public ArrayList<Person> readAllPeople(){

        ArrayList<Person> peopleList = new ArrayList<>();

        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("SELECT * FROM person");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String state = resultSet.getString("state");
                String aboutMe = resultSet.getString("about_me");
                Person person = new Person(personId, name, state, aboutMe);
                peopleList.add(person);
            }

            // Closes connection to DB
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return peopleList;
    }

    public boolean updatePerson(int id, String name, String state, String aboutMe){
        boolean success;
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("UPDATE person SET name = '"+ name + "', state = '" + state + "', about_me = '" + aboutMe + "' WHERE person_id = " + id);
            System.out.println("Person Updated!");
            success = true;

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            success = false;
        }
        return success;

    }

    public boolean deletePerson(int id){
        boolean success;
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("DELETE FROM person WHERE person_id = " + id);
            System.out.println("Person Deleted!");
            success = true;

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Creates new flower in database.
     * @param momId
     * @param dadId
     * @param hybridizerId
     * @param growerId
     * @param name
     * @param mainColor
     * @param freeText
     */
    public boolean createFlower(int momId, int dadId, int hybridizerId, int growerId, String name, String mainColor, String freeText){
        boolean success;
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("INSERT INTO flower(mom_id, dad_id, hybridizer_id, grower_id, name, main_color, free_text) " +
                    "VALUES(" + momId + ", " + dadId + ", " + hybridizerId + ", " + growerId + ", '" + name + "', '" + mainColor + "', '" + freeText + "')");

            System.out.println("New Flower Created!");
            success = true;

            // Closes connection to DB
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            success = false;
        }
        return success;
    }

    // TODO: Two more overload methods required for instances when mom or dad id is missing.
    /**
     * Overload method: Creates new flower in database without mom_id and dad_id
     * @param hybridizerId
     * @param growerId
     * @param name
     * @param mainColor
     * @param freeText
     */
    public boolean createFlower(int hybridizerId, int growerId, String name, String mainColor, String freeText){
        boolean success;
        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("INSERT INTO flower(hybridizer_id, grower_id, name, main_color, free_text) " +
                    "VALUES("+ hybridizerId + ", " + growerId + ", '" + name + "', '" + mainColor + "', '" + freeText + "')");
            System.out.println("New Flower Created!");
            success = true;

            // Closes connection to DB
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            success = false;
        }
        return success;
    }

//    public ArrayList<Flower> readAllFlowers(){
//
//        ArrayList<Flower> flowerList = new ArrayList<>();
//
//        try{
//            // Establish DB connection
//            Connection conn = DriverManager.getConnection(dbConnString);
//            Statement statement = conn.createStatement();
//
//            //TODO: Join tables to return flowers with the growers/hybridizer names as well as mom and dad names
//            statement.execute("SELECT * FROM flower");
//
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()) {
//                int flowerId = resultSet.getInt("flower_id");
//                int momId = resultSet.getInt("mom_id");
//                int dadId = resultSet.getInt("dad_id");
//                int hybridizerId = resultSet.getInt("hybridizer_id");
//                int growerId = resultSet.getInt("grower_id");
//                String name = resultSet.getString("name");
//                String mainColor = resultSet.getString("main_color");
//                String freeText = resultSet.getString("free_text");
//                Flower flower = new Flower(flowerId, momId, dadId, hybridizerId, growerId, name, mainColor, freeText);
//                flowerList.add(flower);
//            }
//
//            // Closes connection to DB
//            statement.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//        return flowerList;
//    }

    public ObservableList<Person> getPersonList() {
        ObservableList<Person> personList = FXCollections.observableArrayList();

        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            statement.execute("SELECT * FROM person");

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String state = resultSet.getString("state");
                String aboutMe = resultSet.getString("about_me");
                Person person = new Person(personId, name, state, aboutMe);
                personList.add(person);
            }

            // Closes connection to DB
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return personList;
    }

    public ObservableList<Flower> getFlowerList() {
        ObservableList<Flower> flowerList = FXCollections.observableArrayList();

        try{
            // Establish DB connection
            Connection conn = DriverManager.getConnection(dbConnString);
            Statement statement = conn.createStatement();

            //TODO: Join tables to return flowers with the growers/hybridizer names as well as mom and dad names
            String sql = "SELECT i.flower_id, i.mom_id, i.dad_id, i.hybridizer_id, i.grower_id, i.name, i.main_color, " +
                    "i.free_text, (SELECT m.name FROM flower m WHERE m.flower_id = i.mom_id) AS momName, " +
                    "(SELECT d.name FROM flower d WHERE d.flower_id = i.dad_id) AS dadName, " +
                    "(SELECT name FROM person WHERE person.person_id = i.hybridizer_id) AS hybridizerName, " +
                    "(SELECT name FROM person WHERE person_id = i.grower_id) AS growerName from flower i";
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int flowerId = resultSet.getInt("flower_id");
                int momId = resultSet.getInt("mom_id");
                int dadId = resultSet.getInt("dad_id");
                int hybridizerId = resultSet.getInt("hybridizer_id");
                int growerId = resultSet.getInt("grower_id");
                String name = resultSet.getString("name");
                String mainColor = resultSet.getString("main_color");
                String freeText = resultSet.getString("free_text");
                String momName = resultSet.getString("momName");
                String dadName = resultSet.getString("dadName");
                String hybridizerName = resultSet.getString("hybridizerName");
                String growerName = resultSet.getString("growerName");
                Flower flower = new Flower(flowerId, momId, dadId, hybridizerId, growerId, name, mainColor, freeText,
                        momName, dadName, hybridizerName, growerName);
                flowerList.add(flower);
            }

            // Closes connection to DB
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return flowerList;
    }
}
