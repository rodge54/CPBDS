package hibiscus;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CpbdsDb db = new CpbdsDb();
        db.databaseSetup();

        ArrayList<Person> peopleList = db.readAllPeople();
        for (Person person:peopleList) {
            System.out.println(person);
        }
//        db.createFlower(1,1,1,1, "Batman", "UNK", "Here is some fun facts");
        ObservableList<Flower> flowerList = db.getFlowerList();
        for (Flower flower:flowerList) {
            System.out.println(flower);
        }
    }
}
