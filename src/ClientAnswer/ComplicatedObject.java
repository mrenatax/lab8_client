package ClientAnswer;
import SpaceMarineData.SpaceMarine;
import java.io.File;
import java.io.Serializable;
public class ComplicatedObject implements Serializable {
    private String command;
    private SpaceMarine spaceMarine;
    private int id;
    private Long param;
    private int p;
    private String history;
    private String login;
    private File fileName;
    String locale;
    public ComplicatedObject(String command){
        this.command = command;
    }
    public ComplicatedObject(String command, SpaceMarine spaceMarine, String login){
        this.command = command;
        this.spaceMarine = spaceMarine;
        this.login = login;
    }
    public ComplicatedObject(String command,String login, String password){
        this.login = login;
        this.command = command;
    }
    public ComplicatedObject(String command, String history){
        this.command = command;
        this.history = history;
    }
    public ComplicatedObject(String command, File fileName, String login, String locale){
        this.command = command;
        this.fileName = fileName;
        this.login = login;
        this.locale = locale;
    }
    public ComplicatedObject(String command, String locale, String password, String login){
        this.command = command;
        this.locale = locale;
    }
    public ComplicatedObject(String command, int id, SpaceMarine spaceMarine, String login){
        this.command = command;
        this.id = id;
        this.spaceMarine = spaceMarine;
        this.login = login;
    }
    public ComplicatedObject(String command, Long param, String login){
        this.command = command;
        this.param = param;
        this.login = login;
    }
    public ComplicatedObject(String command, int p, String login){
        this.command = command;
        this.p = p;
        this.login = login;
    }
    public String getCommand(){
        return command;
    }
    public SpaceMarine getSpaceMarine(){
        return spaceMarine;
    }
    public Long getParam(){return param;}
    public String getLocale(){
        return locale;
    }
    public int getId(){
        return id;
    }
    public int getP(){return p;}
    public String getLogin(){return login;}
    public String getHistory(){return history;}
    public File getFileName(){return fileName;}
    @Override
    public String toString() {
        return ("command: " + command + "\n" );
    }
}
