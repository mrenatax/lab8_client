import ClientAnswer.*;
import ClientReceiver.ReceiveDataFromServer;
import SpaceMarineData.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;
import java.util.*;
import static ClientAnswer.CommandReader.*;
import static ClientAnswer.Localize.*;
public class Client extends Application {
    public static void main(String[] args){
        launch();
    }
    private InterfaceOrganizer intorg = new InterfaceOrganizer();
    private static Localize localize = new Localize();
    public static String string = "";
    TableView tableView;
    @Override
    public void start(Stage primaryStage) throws Exception {
        intorg.enumOrganize();
        intorg.registr();
        intorg.addCommands();
        intorg.setScene();
        FlowPane root = new FlowPane(group,group1);
        root.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null))); // задаётся цвет фона
        Scene scene = new Scene(root,1000,700);
        currentUser.setOnAction(e->
        {
            if (getLogin.length()==0){
                user.setText(s1);
            }else {
                user.setText(getLogin);
            }
        });
        buttonGetHelp.setOnAction(e -> {
            CommandReader.s = "help";
            new CommandReader();
            FlowPane flowPane = new FlowPane();
            flowPane.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
            Scene secondScene = new Scene(flowPane, 565, 300);
            Stage newWindow = new Stage();
            newWindow.setTitle("HELP");
            newWindow.getIcons().add(new Image("help.png"));
            newWindow.setScene(secondScene);
            newWindow.show();
            flowPane.getChildren().add(textHelp);
        });
        primaryStage.setTitle("Клиент с графическим интерфейсом пользователя");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("company_logo.png"));
        primaryStage.show();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(commandBox);
        buttonBox.getChildren().add(buttonGetInfo);
        Image image1 = new Image(getClass().getResourceAsStream("images.png"));
        buttonBox.getChildren().add(new ImageView(image1));
        buttonBox.getChildren().add(buttonGetHelp);
        Image image = new Image(getClass().getResourceAsStream("help.png"));
        buttonBox.getChildren().add(new ImageView(image));
        intorg.setLanguage();
        Image image2 = new Image(getClass().getResourceAsStream("language.png"));
        hBox.getChildren().add(new ImageView(image2));
        buttonBox.getChildren().add(buttonShow);
        Image image3 = new Image(getClass().getResourceAsStream("show.png"));
        buttonBox.getChildren().add(new ImageView(image3));
        buttonBox.getChildren().add(buttonVisual);
        Image image4 = new Image(getClass().getResourceAsStream("visual.png"));
        buttonBox.getChildren().add(new ImageView(image4));


        buttonGetLanguage.setOnAction(e -> {
            String string = languageBox.getSelectionModel().getSelectedItem();
            if (string == "English") {
                primaryStage.setTitle("GUI client");
                localize.setEnglish();
            } else if (string == "Svenska") {
                primaryStage.setTitle("GUI-klient");
                localize.setSwedish();
            } else if (string == "Русский") {
                primaryStage.setTitle("Клиент с графическим интерфейсом пользователя");
                localize.setRussian();
            } else if (string == "Română") {
                primaryStage.setTitle("Client GUI");
                localize.setRomanian();
            }
        });
        buttonSendId.setOnAction(ep -> {
            if (string == "filter_greater_than_height") {
                if ((toRemove.getText() == "") || (toRemove.getText().trim().length() == 0) || (!toRemove.getText().matches("[\\d]+"))) {
                    text66.setText("Поле заполнено некорректно, повторите ввод");
                } else if ((Long.parseLong(toRemove.getText())) <= 0) {
                    text66.setText("Поле заполнено некорректно, повторите ввод");
                } else {
                    height = Long.parseLong(toRemove.getText());
                    remover.getChildren().remove(text6);
                    toRemove.clear();
                    remover.getChildren().remove(toRemove);
                    remover.getChildren().remove(buttonSendId);
                    remover.getChildren().remove(text66);
                    vBox.getChildren().remove(remover);
                    new CommandReader();
                }
            } else if (string == "execute_script") {
                if ((toRemove.getText() == "") || (toRemove.getText().trim().length() == 0)) {
                    text66.setText("Поле не может быть пустым");
                } else {
                    fileName = toRemove.getText();
                    remover.getChildren().remove(text6);
                    toRemove.clear();
                    remover.getChildren().remove(toRemove);
                    remover.getChildren().remove(buttonSendId);
                    remover.getChildren().remove(text66);
                    vBox.getChildren().remove(remover);
                    new CommandReader();
                    FlowPane flowPane = new FlowPane();
                    flowPane.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
                    ScrollPane scrollPane = new ScrollPane(textScript);
                    scrollPane.setPannable(true);
                    Scene secondScene = new Scene(scrollPane, 565, 300);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("SCRIPT");
                    newWindow.getIcons().add(new Image("visual.png"));
                    newWindow.setScene(secondScene);
                    newWindow.show();
                }
            }else if (string == "add_name") {
                if ((toAdd.getText() == "")||(toAdd.getText().trim().length() == 0)){
                    text6.setText("Поле name не может быть пустым");
                }else {
                    if (s == "add") {
                        spaceMarine.setName(toAdd.getText());
                        string = "add_height";
                        adder.setSpacing(20);
                        text6.setText("Введите значение HEIGHT:");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart();
                    } else if (s == "update_id"){
                        smr.setName(toAdd.getText());
                        spaceMarine.setName(toAdd.getText());
                        string = "add_height";
                        adder1.setSpacing(20);
                        text6.setText("Введите значение HEIGHT:");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart0();
                    }

                }
            }else if (string == "add_height") {
                if ((toAdd.getText() == "")|| (toAdd.getText().trim().length() == 0) || (!toAdd.getText().matches("[\\d]+"))) {
                    text6.setText("Поле не может быть пустым и не может содержать букв");
                } else if (Long.parseLong(toAdd.getText()) <= 0) {
                    text6.setText("Поле height не может быть отрицательным");
                } else {
                    if (s == "add") {
                        spaceMarine.setHeight(Long.parseLong(toAdd.getText()));
                        string = "add_health";
                        adder.setSpacing(20);
                        text6.setText("Введите значение HEALTH:");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart();
                    } else if (s == "update_id"){
                        smr.setHeight(Long.parseLong(toAdd.getText()));
                        string = "add_health";
                        adder1.setSpacing(20);
                        text6.setText("Введите значение HEALTH:");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart0();
                    }
                }
            } else if (string == "add_health") {
                if ((toAdd.getText() == "") || (toAdd.getText().trim().length() == 0) || (!toAdd.getText().matches("[\\d]+"))) {
                    text6.setText("Поле health не может быть пустым\n" +
                            " и не может содержать букв");
                } else if (Long.parseLong(toAdd.getText()) <= 0) {
                    text6.setText("Поле health не может\n" +
                            " быть отрицательным");
                } else {
                    if (s == "add") {
                        spaceMarine.setHealth(Long.parseLong(toAdd.getText()));
                        string = "add_weapon";
                        adder.setSpacing(20);
                        text6.setText("Выберите WEAPON:");
                        buttonSendId.setText("Подтвердить");
                        toAdd.clear();
                        adder.getChildren().remove(text6);
                        adder.getChildren().remove(buttonSendId);
                        vBox.getChildren().remove(adder);
                        adder.getChildren().remove(toAdd);
                        adder.getChildren().add(text6);
                        adder.getChildren().add(weaponBox);
                        adder.getChildren().add(buttonSendId);
                        vBox.getChildren().add(adder);
                    } else if (s == "update_id"){
                        smr.setHealth(Long.parseLong(toAdd.getText()));
                        string = "add_weapon";
                        adder1.setSpacing(20);
                        text6.setText("Выберите WEAPON:");
                        buttonSendId.setText("Подтвердить");
                        toAdd.clear();
                        adder1.getChildren().remove(text6);
                        adder1.getChildren().remove(buttonSendId);
                        vBox1.getChildren().remove(adder1);
                        adder1.getChildren().remove(toAdd);
                        adder1.getChildren().add(text6);
                        adder1.getChildren().add(weaponBox);
                        adder1.getChildren().add(buttonSendId);
                        vBox1.getChildren().add(adder1);
                    }
                }
            } else if (string == "add_weapon") {
                String strin = weaponBox.getSelectionModel().getSelectedItem();
                if ((strin == null)|| (strin == "") || (strin.trim().length() == 0)){
                    text6.setText("Выберите weaponType");
                } else {
                    if (s == "add") {
                        spaceMarine.setWeaponType(strin);
                        string = "add_meleeWeapon";
                        adder.getChildren().remove(text6);
                        adder.getChildren().remove(weaponBox);
                        adder.getChildren().remove(buttonSendId);
                        vBox.getChildren().remove(adder);
                        text6.setText("Выберите MELEEWEAPON:");
                        adder.getChildren().add(text6);
                        buttonSendId.setText("Подтвердить");
                        adder.getChildren().add(meleeWeaponBox);
                        adder.getChildren().add(buttonSendId);
                        vBox.getChildren().add(adder);
                    } else if (s == "update_id"){
                        smr.setWeaponType(strin);
                        string = "add_meleeWeapon";
                        adder1.getChildren().remove(text6);
                        adder1.getChildren().remove(weaponBox);
                        adder1.getChildren().remove(buttonSendId);
                        vBox1.getChildren().remove(adder1);
                        text6.setText("Выберите MELEEWEAPON:");
                        adder1.getChildren().add(text6);
                        buttonSendId.setText("Подтвердить");
                        adder1.getChildren().add(meleeWeaponBox);
                        adder1.getChildren().add(buttonSendId);
                        vBox1.getChildren().add(adder1);
                    }
                }
            } else if (string == "add_meleeWeapon") {
                String str = meleeWeaponBox.getSelectionModel().getSelectedItem();
                if ((str == null)|| (str == "") || (str.trim().length() == 0)) {
                    text6.setText("meleeWeapon не выбран,\n" +
                            " не может быть null");
                } else {
                    if (s == "add") {
                        spaceMarine.setMeleeWeapon(str);
                        string = "add_x";
                        adder.setSpacing(20);
                        text6.setText("Введите x (Coordinate)");
                        buttonSendId.setText("Подтвердить");
                        adder.getChildren().remove(meleeWeaponBox);
                        intorg.dublicatedPart();
                    } else if (s == "update_id"){
                        smr.setMeleeWeapon(str);
                        string = "add_x";
                        adder1.setSpacing(20);
                        text6.setText("Введите x (Coordinate)");
                        buttonSendId.setText("Подтвердить");
                        adder1.getChildren().remove(meleeWeaponBox);
                        intorg.dublicatedPart0();
                    }
                }
            } else if (string == "add_x") {
                if ((toAdd.getText() == "") || (toAdd.getText().trim().length() == 0) || (!toAdd.getText().matches("[\\d]+"))) {
                    text6.setText("Поле x не может быть пустым\n" +
                            " и не может содержать букв");
                } else {
                    x = Float.parseFloat(toAdd.getText());
                    string = "add_health";
                    if (s == "add") {
                        adder.setSpacing(20);
                        text6.setText("Введите y (Coordinate)");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart();
                        string = "add_y";
                    } else if (s == "update_id"){
                        adder1.setSpacing(20);
                        text6.setText("Введите y (Coordinate)");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart0();
                        string = "add_y";
                    }
                }
            } else if (string == "add_y") {
                if (!toAdd.getText().matches("[\\d]+")) {
                    text6.setText("Поле y не может содержать букв");
                } else {
                    text6.setText("Введите name (Chapter)");
                    string = "add_chapter_name";
                    buttonSendId.setText("Подтвердить");
                    y = Float.parseFloat(toAdd.getText());
                    if (s == "add") {
                        intorg.dublicatedPart();
                        spaceMarine.setCoordinates(x, y);
                    } else if (s == "update_id"){
                        intorg.dublicatedPart0();
                        smr.setCoordinates(x,y);
                    }
                }
            } else if (string == "add_chapter_name") {
                if ((toAdd.getText() == "") || (toAdd.getText().trim().length() == 0)) {
                    text6.setText("Поле chapter_name\n" +
                            " не может быть пустым");
                } else {
                    chapter = toAdd.getText();
                    string = "add_chapter_marines";
                    if (s == "add"){
                    adder.setSpacing(20);
                    text6.setText("Введите marinesCount (Chapter)");
                    buttonSendId.setText("Подтвердить");
                    intorg.dublicatedPart();
                    } else if (s == "update_id"){
                        adder1.setSpacing(20);
                        text6.setText("Введите marinesCount (Chapter)");
                        buttonSendId.setText("Подтвердить");
                        intorg.dublicatedPart0();
                    }
                }
            } else if (string == "add_chapter_marines"){
                if ((!toAdd.getText().matches("[\\d]+"))) {
                    text6.setText("Поле chapter_marines_count\n" +
                            " не может содержать букв");
                } else if ((Long.parseLong(toAdd.getText()) <= 0) || (Long.parseLong(toAdd.getText()) > 1000)) {
                    text6.setText("Значение поля marinesCount(Chapter)\n" +
                            " должно быть больше 0,\n" +
                            " Максимальное значение поля: 1000");
                } else {
                    marines = Long.parseLong(toAdd.getText());
                    if (s == "add") {
                        spaceMarine.setChapter(chapter, marines);
                        spaceMarine.setCreationDate();
                        adder.getChildren().remove(text6);
                        adder.getChildren().remove(buttonSendId);
                        vBox.getChildren().remove(adder);
                        toAdd.clear();
                        adder.getChildren().remove(toAdd);
                        new CommandReader();
                    } else if (s == "update_id"){
                        smr.setChapter(chapter, marines);
                        smr.setCreationDate();
                        adder1.getChildren().remove(text6);
                        adder1.getChildren().remove(buttonSendId);
                        vBox1.getChildren().remove(adder1);
                        toAdd.clear();
                        adder1.getChildren().remove(toAdd);
                        new CommandReader();
                    }
                }
            } else {
                if ((toRemove.getText() == "") || (toRemove.getText().trim().length() == 0) || (!toRemove.getText().matches("[\\d]+"))) {
                    text66.setText("Поле заполнено некорректно, повторите ввод");
                } else if ((Integer.parseInt(toRemove.getText())) <= 0) {
                    text66.setText("Поле заполнено некорректно, повторите ввод");
                } else {
                    removeId = Integer.parseInt(toRemove.getText());
                    toAdd.clear();
                    remover.getChildren().remove(text6);
                    toRemove.clear();
                    remover.getChildren().remove(toRemove);
                    remover.getChildren().remove(buttonSendId);
                    remover.getChildren().remove(text66);
                    vBox.getChildren().remove(remover);
                    new CommandReader();
                }
            }
        });
        buttonGetInfo.setOnAction(e -> {
            s = commandBox.getSelectionModel().getSelectedItem();
            if (CommandReader.s != null) {
                if (s == "remove_by_id"){
                    textInfo.setText(null);
                    string =  "remove_by_id";
                    text6.setText("Введите id объекта, который хотите удалить.");
                    intorg.dublicatedPart2();
                }else if (s == "filter_greater_than_height"){
                    textInfo.setText(null);
                    string = "filter_greater_than_height";
                    text6.setText("Введите граничное значение для поля height");
                    intorg.dublicatedPart2();
                } else if (s == "remove_greater") {
                    textInfo.setText(null);
                    string = "remove_greater";
                    text6.setText("Введите id граничного элемента.");
                    intorg.dublicatedPart2();
                } else if (s == "add") {
                    textInfo.setText(null);
                    string = "add_name";
                    adder.setSpacing(20);
                    text6.setText("Введите имя нового SpaceMarine");
                    adder.getChildren().remove(text6);
                    adder.getChildren().add(text6);
                    buttonSendId.setText("Подтвердить");
                    adder.getChildren().add(toAdd);
                    adder.getChildren().add(buttonSendId);
                    vBox.getChildren().add(adder);
                } else if (s == "execute_script") {
                    text6.setText("Введите имя файла с расширением .txt");
                    string = "execute_script";
                    intorg.getScriptName();
                } else{
                    new CommandReader();
                }
            } else {
                textInfo.setText(s2);
            }
        });
        buttonShow.setOnAction(e -> {
            vBox.getChildren().remove(tableView);
            textInfo.setText("Для появления таблицы нажмите ещё раз на кнопку SHOW");
            s = "show";
            new CommandReader();
            if (flag == "finish") {
                textInfo.setText("Для сортировки таблицы нажмите на название столбца с числовыми значениями\n" +
                        "поля id, creationDate и login не редактируются, для редактирования остальных\n" +
                        "дважды щёлкните по ячейке, а после нажмите enter.\n" +
                        "Если вводится некорректное значение, то поле не изменится и надо будет обновить таблицу.");
                ObservableList<SpaceMarine> marine = FXCollections.observableArrayList();
                marine.stream().filter(p->p.getLogin().length()<50).sorted();
                marine.stream().filter(p->p.getName().length()<50).sorted();
                marine.stream().filter(p->p.getChapterName().length()<50).sorted();

                tableView = new TableView(marine);
                tableView.setEditable(true);
                TableColumn<SpaceMarine, String> column1 = new TableColumn<>("NAME");
                TableColumn<String, SpaceMarine> column2 = new TableColumn<>("ID");
                TableColumn<SpaceMarine, Number> column3 = new TableColumn<>("HEIGHT");
                TableColumn<SpaceMarine, Number> column4 = new TableColumn<>("HEALTH");
                TableColumn<SpaceMarine, Weapon> column5 = new TableColumn<>("WEAPON");
                TableColumn<SpaceMarine, MeleeWeapon> column6 = new TableColumn<>("MELEEWEAPON");
                TableColumn<SpaceMarine, String> chapter_name = new TableColumn<>("CHAPTERNAME");
                TableColumn<SpaceMarine, Long> mrncnt = new TableColumn<>("MARINESCOUNT");
                TableColumn<String, SpaceMarine> column9 = new TableColumn<>("LOGIN");
                TableColumn<SpaceMarine, Float> x = new TableColumn<>("X");
                TableColumn<SpaceMarine, Float> y = new TableColumn<>("Y");
                for (SpaceMarine s : sm) {
                    marine.add(s);
                }

                column1.setCellValueFactory(new PropertyValueFactory<>("name"));
                column2.setCellValueFactory(new PropertyValueFactory<>("id"));
                column3.setCellValueFactory(new PropertyValueFactory<>("height"));
                column4.setCellValueFactory(new PropertyValueFactory<>("health"));
                column5.setCellValueFactory(new PropertyValueFactory<>("weapon"));
                column6.setCellValueFactory(new PropertyValueFactory<>("meleeWeapon"));
                x.setCellValueFactory(new PropertyValueFactory<>("x"));
                y.setCellValueFactory(new PropertyValueFactory<>("y"));
                chapter_name.setCellValueFactory(new PropertyValueFactory<>("chapterName"));
                mrncnt.setCellValueFactory(new PropertyValueFactory<>("marinesCount"));
                column9.setCellValueFactory(new PropertyValueFactory<>("login"));

                tableView.getColumns().add(column1);
                tableView.getColumns().add(column2);
                tableView.getColumns().add(column3);
                tableView.getColumns().add(column4);
                tableView.getColumns().add(column5);
                tableView.getColumns().add(column6);
                tableView.getColumns().addAll(x,y);
                tableView.getColumns().addAll(chapter_name,mrncnt);
                tableView.getColumns().add(column9);
                tableView.setMinSize(900, 250);
                tableView.setMaxSize(900, 300);
                vBox.getChildren().add(tableView);
                column1.setCellFactory(TextFieldTableCell.<SpaceMarine> forTableColumn());
                column1.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, String> event) -> {
                    TablePosition<SpaceMarine, String> pos = event.getTablePosition();
                    String newName = event.getNewValue();
                    if ((newName == "")||(newName.trim().length() == 0)){
                        textInfo.setText("Поле name не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        smr = event.getTableView().getItems().get(row);
                        smr.setName(newName);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        System.out.println(id2send);
                        s = "update_id";
                        new CommandReader();
                    }
                });
                column3.setCellFactory(TextFieldTableCell.<SpaceMarine, Number> forTableColumn(new NumberStringConverter()));
                column3.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Number> event) -> {
                    TablePosition<SpaceMarine, Number> pos = event.getTablePosition();
                    Number height = event.getNewValue();
                    if ((height == null)){
                        textInfo.setText("Поле height не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        Long lh = Long.parseLong(height.toString());
                        smr = event.getTableView().getItems().get(row);
                        smr.setHeight(lh);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        s = "update_id";
                        new CommandReader();
                    }
                });
                column4.setCellFactory(TextFieldTableCell.<SpaceMarine, Number> forTableColumn(new NumberStringConverter()));
                column4.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Number> event) -> {
                    TablePosition<SpaceMarine, Number> pos = event.getTablePosition();
                    Number health = event.getNewValue();
                    if (health == null){
                        textInfo.setText("Поле health не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        Long lh = Long.parseLong(health.toString());
                        if (lh <= 0){
                            textInfo.setText("Поле health не может быть отрицательным");
                        }else {
                            smr = event.getTableView().getItems().get(row);
                            smr.setHealth(lh);
                            smr.setCreationDate();
                            id2send = smr.getId();
                            s = "update_id";
                            new CommandReader();
                        }
                    }
                });
                ObservableList<Weapon> weaponList = FXCollections.observableArrayList(Weapon.values());
                column5.setCellFactory(ComboBoxTableCell.<SpaceMarine, Weapon> forTableColumn(weaponList));
                column5.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Weapon> event) -> {
                    TablePosition<SpaceMarine, Weapon> pos = event.getTablePosition();
                    Weapon newName = event.getNewValue();
                    int row = pos.getRow();
                    smr = event.getTableView().getItems().get(row);
                    smr.setWeaponType(newName.toString());
                    smr.setCreationDate();
                    id2send = smr.getId();
                    s = "update_id";
                    new CommandReader();
                });
                ObservableList<MeleeWeapon> meleeWeaponList = FXCollections.observableArrayList(MeleeWeapon.values());
                column6.setCellFactory(ComboBoxTableCell.<SpaceMarine, MeleeWeapon> forTableColumn(meleeWeaponList));
                column6.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, MeleeWeapon> event) -> {
                    TablePosition<SpaceMarine, MeleeWeapon> pos = event.getTablePosition();
                    MeleeWeapon newName = event.getNewValue();
                    int row = pos.getRow();
                    smr = event.getTableView().getItems().get(row);
                    smr.setMeleeWeapon(newName.toString());
                    smr.setCreationDate();
                    id2send = smr.getId();
                    s = "update_id";
                    new CommandReader();
                });
                x.setCellFactory(TextFieldTableCell.<SpaceMarine, Float> forTableColumn(new FloatStringConverter()));
                x.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Float> event) -> {
                    TablePosition<SpaceMarine, Float> pos = event.getTablePosition();
                    Float xx = event.getNewValue();
                    if ((xx == null)){
                        textInfo.setText("Поле x не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        smr = event.getTableView().getItems().get(row);
                        Float f = smr.getY();
                        smr.setCoordinates(xx, f);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        s = "update_id";
                        new CommandReader();
                    }
                });
                y.setCellFactory(TextFieldTableCell.<SpaceMarine, Float> forTableColumn(new FloatStringConverter()));
                y.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Float> event) -> {
                    TablePosition<SpaceMarine, Float> pos = event.getTablePosition();
                    Float yy = event.getNewValue();
                    if ((yy == null)){
                        textInfo.setText("Поле y не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        smr = event.getTableView().getItems().get(row);
                        Float f = smr.getX();
                        smr.setCoordinates(f,yy);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        s = "update_id";
                        new CommandReader();
                    }
                });
                mrncnt.setCellFactory(TextFieldTableCell.<SpaceMarine, Long> forTableColumn(new LongStringConverter()));
                mrncnt.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, Long> event) -> {
                    TablePosition<SpaceMarine, Long> pos = event.getTablePosition();
                    Long count = event.getNewValue();
                    if ((count == null)){
                        textInfo.setText("Поле marinesCount не может быть пустым");
                    } else if ((count <= 0)||(count > 1000)){
                        textInfo.setText("Поле marinesCount должно быть больше 0, Максимальное значение поля: 1000");
                    } else {
                        int row = pos.getRow();
                        smr = event.getTableView().getItems().get(row);
                        String n = smr.getChapterName();
                        smr.setChapter(n,count);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        s = "update_id";
                        new CommandReader();
                    }
                });
                chapter_name.setCellFactory(TextFieldTableCell.<SpaceMarine> forTableColumn());
                chapter_name.setOnEditCommit((TableColumn.CellEditEvent<SpaceMarine, String> event) -> {
                    TablePosition<SpaceMarine, String> pos = event.getTablePosition();
                    String newName = event.getNewValue();
                    if ((newName == "")||(newName.trim().length() == 0)){
                        textInfo.setText("Поле chapter_name не может быть пустым");
                    } else {
                        int row = pos.getRow();
                        smr = event.getTableView().getItems().get(row);
                        Long xr = smr.getMarinesCount();
                        smr.setChapter(newName,xr);
                        smr.setCreationDate();
                        id2send = smr.getId();
                        s = "update_id";
                        new CommandReader();
                    }
                });
                FilteredList<SpaceMarine> filteredData = new FilteredList(marine);
                SortedList<SpaceMarine> sortableData = new SortedList<>(filteredData);
                tableView.setItems(sortableData);
                sortableData.comparatorProperty().bind(tableView.comparatorProperty());
                try {
                    buttonBox.getChildren().add(buttonEndShow);
                    buttonEndShow.setOnAction(exp -> {
                        tableView.getColumns().remove(column1);
                        tableView.getColumns().remove(column2);
                        tableView.getColumns().remove(column3);
                        tableView.getColumns().remove(column4);
                        tableView.getColumns().remove(column5);
                        tableView.getColumns().remove(column6);
                        tableView.getColumns().remove(x);
                        tableView.getColumns().remove(y);
                        tableView.getColumns().remove(chapter_name);
                        tableView.getColumns().remove(mrncnt);
                        tableView.getColumns().remove(column9);
                        vBox.getChildren().remove(tableView);
                        buttonBox.getChildren().remove(buttonEndShow);
                        flag = "";
                        textInfo.setText(null);
                    });
                }catch(IllegalArgumentException ex){}
            }
        });
        buttonVisual.setOnAction(act ->{
            if (userFlag != "finish") {
                s = "users";
                new CommandReader();
                textInfo.setText("Для появления spaceMarines нажмите трижды на кнопку КАРТА КОРАБЛЕЙ");
            }
            if (userFlag == "finish") {
                s = "show";
                new CommandReader();
                textInfo.setText("Для появления spaceMarines нажмите ещё раз на кнопку КАРТА КОРАБЛЕЙ");
                if (flag == "finish") {
                    Group group = new Group();
                    PannableCanvas canvas = new PannableCanvas();
                    canvas.setTranslateX(100);
                    canvas.setTranslateY(100);
                    NodeGestures nodeGestures = new NodeGestures(canvas);
                    Label label = new Label("Для перемещения по сетке удерживайте правую кнопку\n" +
                            "Для увеличения и уменьшения поддерживается функция зума");
                    label.setTranslateX(60);
                    label.setTranslateY(60);

                    List<String> loginSet = new ArrayList<>(ReceiveDataFromServer.loginList);
                    List<ColorSet> colorSetList = new ArrayList<>();
                    for (String s: loginSet){
                        Random rand = new Random();
                        float r = rand.nextFloat();
                        float g = rand.nextFloat();
                        float b = rand.nextFloat();
                        Color randomColor = new Color(r, g, b, 1);
                        ColorSet colorSet = new ColorSet(s, randomColor);
                        colorSetList.add(colorSet);
                    }

                        for (SpaceMarine s : sm) {
                        Circle circle = new Circle();
                        for (ColorSet c: colorSetList){
                            if (s.getLogin().trim().equals(c.getLogin().trim())){
                                circle.setStroke(c.getColor());
                            }
                        }

                        if (s.getHeight() > 1000) {
                            circle.setRadius(60);
                        } else {
                            circle.setRadius(30);
                        }
                        circle.setTranslateX(s.getX());
                        circle.setTranslateY(s.getY());
                        canvas.getChildren().addAll(circle);

                        circle.setFill(Color.ORANGE);
                        circle.setStrokeWidth(10);
                        circle.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
                        circle.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

                    }
                    canvas.getChildren().add(label);
                    group.getChildren().add(canvas);
                    Scene scene33 = new Scene(group, 660, 660);
                    SceneGestures sceneGestures = new SceneGestures(canvas);
                    scene33.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
                    scene33.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
                    scene33.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());
                    Stage newWindow = new Stage();
                    newWindow.setScene(scene33);
                    newWindow.setTitle("КАРТА КОРАБЛЕЙ");
                    newWindow.getIcons().add(new Image("visual.png"));
                    newWindow.show();
                    canvas.addGrid(5000, 5000);
                    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            for (SpaceMarine s : sm) {
                                if (s.getHeight() > 1000) {
                                    if ((e.getX() >= s.getX() - 60) && (e.getX() <= s.getX() + 60) && (e.getY() >= s.getY() - 60) && (e.getY() <= s.getY() + 60)) {
                                        Text t = new Text();
                                        t.setText(s.toString());
                                        FlowPane flowPane = new FlowPane();
                                        flowPane.getChildren().add(t);
                                        flowPane.getChildren().add(editButton);
                                        flowPane.getChildren().add(vBox1);
                                        vBox1.setSpacing(20);
                                        flowPane.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
                                        editButton.setOnAction(acr -> {
                                            CommandReader.s = "update_id";
                                            id2send = s.getId();
                                            textInfo.setText(null);
                                            string = "add_name";
                                            adder1.setSpacing(20);
                                            text6.setText("Введите новое имя");
                                            adder1.getChildren().remove(text6);
                                            adder1.getChildren().add(text6);
                                            buttonSendId.setText("Подтвердить");
                                            adder1.getChildren().add(toAdd);
                                            adder1.getChildren().add(buttonSendId);
                                            vBox1.getChildren().add(adder1);
                                        });
                                        Scene thirdScene = new Scene(flowPane, 600, 400);
                                        Stage wind = new Stage();
                                        wind.setScene(thirdScene);
                                        wind.show();
                                    }
                                } else {
                                    if ((e.getX() >= s.getX() - 30) && (e.getX() <= s.getX() + 30) && (e.getY() >= s.getY() - 30) && (e.getY() <= s.getY() + 30)) {
                                        Text t = new Text();
                                        t.setText(s.toString());
                                        FlowPane flowPane = new FlowPane();
                                        flowPane.getChildren().add(t);
                                        flowPane.getChildren().add(editButton);
                                        flowPane.getChildren().add(vBox1);
                                        vBox1.setSpacing(20);
                                        flowPane.setBackground(new Background(new BackgroundFill(Color.BISQUE, null, null)));
                                        editButton.setOnAction(acr -> {
                                            CommandReader.s = "update_id";
                                            id2send = s.getId();
                                            textInfo.setText(null);
                                            string = "add_name";
                                            adder1.setSpacing(20);
                                            text6.setText("Введите новое имя");
                                            adder1.getChildren().remove(text6);
                                            adder1.getChildren().add(text6);
                                            buttonSendId.setText("Подтвердить");
                                            adder1.getChildren().add(toAdd);
                                            adder1.getChildren().add(buttonSendId);
                                            vBox1.getChildren().add(adder1);
                                        });
                                        Scene thirdScene = new Scene(flowPane, 600, 400);
                                        Stage wind = new Stage();
                                        wind.setScene(thirdScene);
                                        wind.show();
                                    }
                                }
                            }
                        }
                    };
                    canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                    textInfo.setText(null);
                }
            }
        });
    }
}
