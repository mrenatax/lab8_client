package ClientAnswer;
import javafx.scene.paint.Color;
import java.util.Objects;
public class ColorSet {
    private String login;
    private Color color;
    public ColorSet(String login, Color color){
        this.login = login;
        this.color = color;
    }
    public String getLogin(){
        return login;
    }
    public Color getColor(){
        return color;
    }
    public void setLogin(String log){
        this.login = log;
    }
    public void setColor(Color col){
        this.color = col;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorSet colorSet = (ColorSet) o;
        return Objects.equals(getLogin(), colorSet.getLogin()) &&
                Objects.equals(getColor(), colorSet.getColor());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getColor());
    }
    @Override
    public String toString() {
        return "login = " + login + "\n" +
                "color = " + color + "\n";
    }
}
