package hibiscus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

/*
Icon provided by https://www.flaticon.com/
 */

public class CpbdsGui extends Application{

    // Set scene size for all scenes
    final private int v = 1200;
    final private int v1 = 800;

    final private int MIN_LBL_WIDTH = 150;

    private Scene splashPage;
    private Scene addPersonPage;
    private Scene addFlowerPage;
    private Scene rptPersonPage;
    private Scene rptFlowerPage;
    private Stage primaryStage;

    private TableView<Flower> rptFlowerTable = new TableView<>();

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Marika's Biscuit Database");
        primaryStage.getIcons().add(new Image("hibiscus/hibiscus.png"));

        createSplashPage(primaryStage);
        createAddPersonPage(primaryStage);
        createRptPersonPage(primaryStage);
        createAddFlowerPage(primaryStage);
        createRptFlowerPage(primaryStage);

        primaryStage.setScene(splashPage);
        primaryStage.show();
    }

    public MenuBar addMenu(Stage primaryStage) {
        Menu home = new Menu("Home");
        Menu menu1 = new Menu("Add");
        MenuBar menuBar = new MenuBar();

        // TODO: Fix the menu bug on page one.  It doesn't impact performance, but it looks bad.
        // Adds and empty item to home menu bar.  When item is shown, the page changes to splashpage.
        home.setOnShown(e -> primaryStage.setScene(splashPage));
        menuBar.getMenus().add(home);
        MenuItem blankItem = new MenuItem("");
        home.getItems().add(blankItem);

        menuBar.getMenus().add(menu1);
        MenuItem menu11 = new MenuItem("Add Flower");
        MenuItem menu12 = new MenuItem("Add Person");

        Menu menu2 = new Menu("Reports");
        menuBar.getMenus().add(menu2);
        MenuItem menu21 = new MenuItem("Flowers");
        MenuItem menu22 = new MenuItem("People");

        menu1.getItems().addAll(menu11, menu12);
        menu2.getItems().addAll(menu21, menu22);

        // Associate menu items with corresponding scenes
        menu11.setOnAction(e -> primaryStage.setScene(addFlowerPage));
        menu12.setOnAction(e -> primaryStage.setScene(addPersonPage));
        menu21.setOnAction(e -> primaryStage.setScene(rptFlowerPage));
        menu22.setOnAction(e -> primaryStage.setScene(rptPersonPage));

        return menuBar;
    }

    private void createRptPersonPage(Stage primaryStage) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("theme");
        TableView<Person> rptPersonTable = new TableView<>();

        VBox vBoxTitle = setupTitleBox("People Table");

        rptPersonTable.setEditable(true);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        TableColumn<Person, String> stateCol = new TableColumn<>("State");
        TableColumn<Person, String> aboutMeCol = new TableColumn<>("About Me");

        //Drop extra column
        rptPersonTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        aboutMeCol.setCellValueFactory(new PropertyValueFactory<>("aboutMe"));
        //Add Data
        ObservableList<Person> personList = getPersonList();
        rptPersonTable.setItems(personList);
        //Add Column headers
        rptPersonTable.getColumns().addAll(nameCol, stateCol, aboutMeCol);

        vBox.setSpacing(5);
//        vBox.setPadding(new Insets(10, 0, 0, 10));
        vBox.getChildren().addAll(addMenu(primaryStage), vBoxTitle, rptPersonTable);
        rptPersonPage = new Scene(vBox, v, v1);
        rptPersonPage.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void createRptFlowerPage(Stage primaryStage) {

        VBox vBox = new VBox();
        vBox.getStyleClass().add("theme");

        VBox vBoxTitle = setupTitleBox("All My Flowers");

        rptFlowerTable.setEditable(true);

        TableColumn<Flower, String> nameCol = new TableColumn<>("Name");
        TableColumn<Flower, String> momCol = new TableColumn<>("Mom");
        TableColumn<Flower, String> dadCol = new TableColumn<>("Dad");
        TableColumn<Flower, String> hybridizerCol = new TableColumn<>("Hybridizer");
        TableColumn<Flower, String> growerCol = new TableColumn<>("Grower");
        TableColumn<Flower, String> mainColorCol = new TableColumn<>("Main Color");
        TableColumn<Flower, String> freeTextCol = new TableColumn<>("Free Text");

        //Drop extra column
        rptFlowerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        momCol.setCellValueFactory(new PropertyValueFactory<>("momName"));
        dadCol.setCellValueFactory(new PropertyValueFactory<>("dadName"));
        hybridizerCol.setCellValueFactory(new PropertyValueFactory<>("hybridizerName"));
        growerCol.setCellValueFactory(new PropertyValueFactory<>("growerName"));
        mainColorCol.setCellValueFactory(new PropertyValueFactory<>("mainColor"));
        freeTextCol.setCellValueFactory(new PropertyValueFactory<>("freeText"));

        //Add Data
        ObservableList<Flower> flowerList = getFlowerList();
        rptFlowerTable.setItems(flowerList);
        //Add Column headers
        rptFlowerTable.getColumns().addAll(nameCol, momCol, dadCol, hybridizerCol, growerCol, mainColorCol, freeTextCol);

        vBox.setSpacing(5);
//        vBox.setPadding(new Insets(10, 0, 0, 10));
        vBox.getChildren().addAll(addMenu(primaryStage), vBoxTitle, rptFlowerTable);
        rptFlowerPage = new Scene(vBox, v, v1);
        rptFlowerPage.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private ObservableList<Flower> getFlowerList() {
        ObservableList<Flower> flowerList;
        CpbdsDb db = new CpbdsDb();
        flowerList = db.getFlowerList();
        return flowerList;
    }

    private ObservableList<Person> getPersonList() {
        ObservableList<Person> personList;
        CpbdsDb db = new CpbdsDb();
        personList = db.getPersonList();
        return personList;
    }

    private void createSplashPage(Stage primaryStage) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("theme");

        VBox vBoxTitle =  setupTitleBox("Marika's Biscuit Database!!");

        VBox buttonBox = new VBox();
        HBox rowOneBox = new HBox();
        HBox rowTwoBox = new HBox();

        Button addPersonBTN = new Button("Add New Person");
        addPersonBTN.setOnAction(e -> primaryStage.setScene(addPersonPage));
        Button addFlowerBTN = new Button("Add New Flower");
        addFlowerBTN.setOnAction(e -> primaryStage.setScene(addFlowerPage));
        rowOneBox.getChildren().addAll(addPersonBTN, addFlowerBTN);

        Button getPersonRptBTN = new Button("View People");
        getPersonRptBTN.setOnAction(e -> primaryStage.setScene(rptPersonPage));
        Button getFlowerRptBTN = new Button("View Flowers");
        getFlowerRptBTN.setOnAction(e -> primaryStage.setScene(rptFlowerPage));
        rowTwoBox.getChildren().addAll(getPersonRptBTN, getFlowerRptBTN);

        buttonBox.getChildren().addAll(rowOneBox, rowTwoBox);
        vBox.getChildren().addAll(addMenu(primaryStage), vBoxTitle, buttonBox);
        splashPage = new Scene(vBox, v, v1);

        //Styling
        vBoxTitle.getStyleClass().add("splashTtl");
        addPersonBTN.getStyleClass().add("splashBtn");
        addFlowerBTN.getStyleClass().add("splashBtn");
        getPersonRptBTN.getStyleClass().add("splashBtn");
        getFlowerRptBTN.getStyleClass().add("splashBtn");
        rowOneBox.getStyleClass().add("splashBtnBox");
        rowTwoBox.getStyleClass().add("splashBtnBox");

        splashPage.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void createAddFlowerPage(Stage primaryStage){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("theme");

        ObservableList<Flower> flowerList = FXCollections.observableArrayList(getFlowerList());
        ObservableList<Person> personList = FXCollections.observableArrayList(getPersonList());

        VBox vBoxTitle = setupTitleBox("Add New Flower");

        //Add labels, text boxes and buttons
        //TODO: Add combo boxes instead of allowing user to type in the text field
        HBox momBox = new HBox();
        Label momLBL = new Label("Mom");
        momLBL.setMinWidth(MIN_LBL_WIDTH);
        ComboBox<Flower> momCB = new ComboBox<>(flowerList);
        momBox.getChildren().addAll(momLBL, momCB);
        momBox.setAlignment(CENTER);

        HBox dadBox = new HBox();
        Label dadLBL = new Label("Dad");
        dadLBL.setMinWidth(MIN_LBL_WIDTH);
        ComboBox<Flower> dadCB = new ComboBox<>(flowerList);
        dadBox.getChildren().addAll(dadLBL, dadCB);
        dadBox.setAlignment(CENTER);

        HBox hybridizerBox = new HBox();
        Label hybridizerLBL = new Label("Hybridizer");
        hybridizerLBL.setMinWidth(MIN_LBL_WIDTH);
        ComboBox<Person> hybridizerCB = new ComboBox<>(personList);
        hybridizerBox.getChildren().addAll(hybridizerLBL, hybridizerCB);
        hybridizerBox.setAlignment(CENTER);

        HBox growerBox = new HBox();
        Label growerLBL = new Label("Grower");
        growerLBL.setMinWidth(MIN_LBL_WIDTH);
        ComboBox<Person> growerCB = new ComboBox<>(personList);
        growerBox.getChildren().addAll(growerLBL, growerCB);
        growerBox.setAlignment(CENTER);

        HBox nameBox = new HBox();
        Label nameLBL = new Label("Flower Name");
        nameLBL.setMinWidth(MIN_LBL_WIDTH);
        TextField nameTF = new TextField();
        nameBox.getChildren().addAll(nameLBL, nameTF);
        nameBox.setAlignment(CENTER);

        HBox mainColorBox = new HBox();
        Label mainColorLBL = new Label("Main Color");
        mainColorLBL.setMinWidth(MIN_LBL_WIDTH);
        TextField mainColorTF = new TextField();
        mainColorBox.getChildren().addAll(mainColorLBL, mainColorTF);
        mainColorBox.setAlignment(CENTER);

        HBox freeTextBox = new HBox();
        Label freeTextLBL = new Label("Free Text");
        freeTextLBL.setMinWidth(MIN_LBL_WIDTH);
        TextField freeTextTF = new TextField();
        freeTextBox.getChildren().addAll(freeTextLBL, freeTextTF);
        freeTextBox.setAlignment(CENTER);

        HBox btnBox = new HBox();
        Button addBTN = new Button("Add");
        btnBox.getChildren().addAll(addBTN);
        btnBox.setAlignment(CENTER);
        btnBox.setPadding(new Insets(20, 0, 0, 0));
        addBTN.setOnAction(e -> addFlower(momCB, dadCB, hybridizerCB, growerCB, nameTF, mainColorTF, freeTextTF));

        vBox.getChildren().addAll(addMenu(primaryStage), vBoxTitle, momBox, dadBox, hybridizerBox, growerBox, nameBox, mainColorBox, freeTextBox, btnBox);

        //Styling
        momBox.getStyleClass().add("lbls");
        dadBox.getStyleClass().add("lbls");
        hybridizerBox.getStyleClass().add("lbls");
        growerBox.getStyleClass().add("lbls");
        nameBox.getStyleClass().add("lbls");
        mainColorBox.getStyleClass().add("lbls");
        freeTextBox.getStyleClass().add("lbls");

        addFlowerPage = new Scene(vBox, v, v1);
        addFlowerPage.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    // TODO: Add exception handling
    // TODO: Check for empty text fields and display errors

    /**
     * Adds new flower tod database from user input.
     * @param momCB - This ComboBox retrieves the id of the mom flower.
     * @param dadCB - This ComboBox retrieves the id of the dad flower.
     * @param hybridizerCB - This ComboBox retrieves the id of the hybridizer.
     * @param growerCB - This ComboBox retrieves the id of the grower.
     * @param nameTF - This is the name of the flower.
     * @param mainColorTF - This is the main color of the flower.
     * @param freeTextTF - This is a free text field to input details about the flower.
     */
    private void addFlower(ComboBox<Flower> momCB, ComboBox<Flower> dadCB, ComboBox<Person> hybridizerCB, ComboBox<Person> growerCB,
                           TextField nameTF, TextField mainColorTF, TextField freeTextTF) {
        CpbdsDb db = new CpbdsDb();

        int momId = momCB.getValue().getFlowerId();
        int dadId = dadCB.getValue().getFlowerId();
        int hybridizerId = hybridizerCB.getValue().getPersonId();
        int growerId = growerCB.getValue().getPersonId();
        boolean success = db.createFlower(momId, dadId, hybridizerId, growerId, nameTF.getText(), mainColorTF.getText(), freeTextTF.getText());
        if (success){
            System.out.println("Flower Successfully Added!");
        } else {
            System.out.println("Problem Adding Flower");
        }
        ObservableList<Flower> flowerList = getFlowerList();
        rptFlowerTable.setItems(flowerList);
    }

    private void createAddPersonPage(Stage primaryStage){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("theme");

        VBox vBoxTitle = setupTitleBox("Add New Person");

        //Add labels, text boxes and buttons
        HBox nameBox = new HBox();
        Label nameLBL = new Label("Name");
        TextField nameTF = new TextField();
        nameBox.getChildren().addAll(nameLBL, nameTF);
        nameBox.setAlignment(CENTER);

        HBox stateBox = new HBox();
        Label stateLBL = new Label("State");
        TextField stateTF = new TextField();
        stateBox.getChildren().addAll(stateLBL, stateTF);
        stateBox.setAlignment(CENTER);

        HBox aboutMeBox = new HBox();
        Label aboutMeLBL = new Label("About Me");
        TextField aboutMeTF = new TextField();
        aboutMeBox.getChildren().addAll(aboutMeLBL, aboutMeTF);
        aboutMeBox.setAlignment(CENTER);

        HBox btnBox = new HBox();
        Button addBTN = new Button("Add");
        btnBox.getChildren().addAll(addBTN);
        btnBox.setAlignment(CENTER);
        addBTN.setOnAction(e -> addPerson(nameTF, stateTF, aboutMeTF));

        vBox.getChildren().addAll(addMenu(primaryStage), vBoxTitle, nameBox, stateBox, aboutMeBox, btnBox);
        addPersonPage = new Scene(vBox, v, v1);

        nameBox.getStyleClass().add("lbls");
        stateBox.getStyleClass().add("lbls");
        aboutMeBox.getStyleClass().add("lbls");
        nameTF.getStyleClass().add("txtFields");
        stateTF.getStyleClass().add("txtFields");
        aboutMeTF.getStyleClass().add("txtFields");

        addPersonPage.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    // TODO: Add exception handling
    // TODO: Check for empty text fields and display errors
    private void addPerson(TextField nameTF, TextField stateTF, TextField aboutMeTF) {
        CpbdsDb db = new CpbdsDb();

        boolean success = db.createPerson(nameTF.getText(), stateTF.getText(), aboutMeTF.getText());
        if (success){
            System.out.println("Person Successfully Added!");
            nameTF.clear();
            stateTF.clear();
            aboutMeTF.clear();
        } else {
            System.out.println("Problem Adding Person");
        }
    }

    private VBox setupTitleBox(String title) {
        Label titleLBL = new Label(title);
        //TODO: Fix font
        VBox vBoxTitle = new VBox();
        titleLBL.getStyleClass().add("title");
        vBoxTitle.setAlignment(Pos.CENTER);
        vBoxTitle.getChildren().add(titleLBL);
        return vBoxTitle;
    }
}
