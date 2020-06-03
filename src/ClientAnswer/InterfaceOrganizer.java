package ClientAnswer;
import ClientReceiver.ReceiveDataFromServer;
import javafx.geometry.Insets;
import java.io.IOException;
import static ClientAnswer.CommandReader.*;
import static ClientAnswer.Localize.*;
import static ClientAnswer.Localize.buttonAddUser;
public class InterfaceOrganizer {
    public void dublicatedPart(){
        adder.getChildren().remove(text6);
        adder.getChildren().remove(buttonSendId);
        vBox.getChildren().remove(adder);
        toAdd.clear();
        adder.getChildren().remove(toAdd);
        adder.setSpacing(20);
        adder.getChildren().add(text6);
        adder.getChildren().add(toAdd);
        adder.getChildren().add(buttonSendId);
        vBox.getChildren().add(adder);
    }
    public void dublicatedPart0(){
        adder1.getChildren().remove(text6);
        adder1.getChildren().remove(buttonSendId);
        vBox1.getChildren().remove(adder1);
        toAdd.clear();
        adder1.getChildren().remove(toAdd);
        adder1.setSpacing(20);
        adder1.getChildren().add(text6);
        adder1.getChildren().add(toAdd);
        adder1.getChildren().add(buttonSendId);
        vBox1.getChildren().add(adder1);
    }
    public void dublicatedPart2(){
        remover.setSpacing(20);
        remover.getChildren().remove(text6);
        remover.getChildren().remove(toRemove);
        remover.getChildren().remove(buttonSendId);
        remover.getChildren().remove(text66);
        vBox.getChildren().remove(remover);
        remover.getChildren().add(text6);
        remover.getChildren().add(toRemove);
        remover.getChildren().add(buttonSendId);
        remover.getChildren().add(text66);
        vBox.getChildren().add(remover);
    }
    public void getScriptName(){
        remover.setSpacing(20);
        remover.getChildren().remove(text6);
        remover.getChildren().remove(toRemove);
        remover.getChildren().remove(buttonSendId);
        remover.getChildren().remove(text66);
        vBox.getChildren().remove(remover);
        remover.getChildren().add(text6);
        remover.getChildren().add(toRemove);
        remover.getChildren().add(buttonSendId);
        remover.getChildren().add(text66);
        vBox.getChildren().add(remover);
    }
    public void registrInterface(){
        registration.getChildren().add(text5);
        registration.setSpacing(10);
        registration.getChildren().add(text2);
        registration.getChildren().add(login);
        registration.getChildren().add(text3);
        registration.getChildren().add(password);
        registration.getChildren().add(buttonAddUser);
        registration.getChildren().add(textRegistr);
        vBox.getChildren().add(registration);
    }
    public void setLanguage(){
        languages.add("Русский");
        languages.add("Română");
        languages.add("Svenska");
        languages.add("English");
        languageBox.getItems().addAll(languages);
        hBox.setSpacing(10);
        hBox.getChildren().add(languageBox);
        hBox.getChildren().add(buttonGetLanguage);
    }
    public void commandInterface(){
        vBox.getChildren().remove(registration);
        vBox.getChildren().add(currentUser);
        vBox.getChildren().add(user);
        vBox.getChildren().add(text4);
        vBox.getChildren().add(buttonBox);
        vBox.getChildren().add(textInfo);
    }
    public void enumOrganize(){
        weapon.add("BOLTGUN");
        weapon.add("HEAVY_BOLTGUN");
        weapon.add("GRENADE_LAUNCHER");
        weapon.add("HEAVY_FLAMER");
        weapon.add("MULTI_MELTA");
        meleeWeapon.add("CHAIN_SWORD");
        meleeWeapon.add("CHAIN_AXE");
        meleeWeapon.add("LIGHTING_CLAW");
        meleeWeapon.add("POWER_FIST");
        weaponBox.getItems().addAll(weapon);
        meleeWeaponBox.getItems().addAll(meleeWeapon);
        vBox.setSpacing(10);
    }
    public void addCommands(){
        commands.add("info");
        commands.add("head");
        commands.add("add");
        commands.add("clear");
        commands.add("remove_by_id");
        commands.add("remove_greater");
        commands.add("sum_of_height");
        commands.add("max_by_name");
        commands.add("filter_greater_than_height");
        commands.add("history");
        commands.add("remove_greater");
        commands.add("execute_script");
        CommandReader.commandBox.getItems().addAll(commands);
    }
    public void setScene(){
        group.getChildren().add(vBox);
        group1.getChildren().add(vertical);
        vBox.setPadding(new Insets(10, 30, 10, 30));
        vBox.setSpacing(20);
        vBox.getChildren().add(text1);
        vBox.getChildren().add(hBox);
    }
    public void registr(){
        registrInterface();
        buttonAddUser.setOnAction(e -> {
            getLogin = login.getText();
            getPassword = password.getText();
            Authorization authorization = new Authorization();
            try {
                authorization.authorize(getLogin,getPassword);
                ReceiveDataFromServer r = new ReceiveDataFromServer();
                r.receive();
                if (!ReceiveDataFromServer.data.contains("00010010")){
                    commandInterface();
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
