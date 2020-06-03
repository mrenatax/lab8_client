package SpaceMarineData;
import CommandProcessing.Commands;
import java.io.Serializable;
import java.util.InputMismatchException;
public class Coordinates implements Serializable {
    private Float x; //Поле не может быть null
    private float y;
    private static final long serialVersionUID = -861582331502172795L;
    Coordinates(){}
    public Coordinates(Float x, float y){
        if ((checker(x))||(checker(y)))
            throw new IllegalArgumentException("Поле x и y coordinates не может быть null");
        this.x = x;
        this.y = y;
    }
    public Float getX(){
        return x;
    }
    public Float getY(){return y;}
    void setXY(float xn, float yn){
            x = xn;
            y = yn;
    }
    void setXYForScript(){
        try{
            x = Float.parseFloat(Commands.slmmsk.nextLine());
            y = Float.parseFloat(Commands.slmmsk.nextLine());
            if (x == null){System.out.println("поле х заполнено некорректно, не может быть null");}
        }catch(InputMismatchException e){
            System.out.println("Поля x и у в скрипте указаны некорректно");
        }
    }
    private <T> boolean checker(T p) {
        return (p == null);
    }
    @Override
    public String toString() {
        return ( x + " " + y);
    }
}
