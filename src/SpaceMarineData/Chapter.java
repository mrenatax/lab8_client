package SpaceMarineData;
import CommandProcessing.Commands;
import java.io.Serializable;
import java.util.InputMismatchException;
public class Chapter implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private static final long serialVersionUID = 1949811830372121638L;
    public Chapter(){}
    public Chapter(String name, long marinesCount){
        if (isChapterNameValid(name))  throw new IllegalArgumentException("Поле name(Chapter) не может быть null, Строка не может быть пустой");
        this.name = name;
        if (isChapterMarinesCount(marinesCount))
            throw new IllegalArgumentException("Значение поля marinesCount(Chapter) должно быть больше 0, Максимальное значение поля: 1000");
        this.marinesCount = marinesCount;
    }
    public String getChapterName(){
        return name;
    }
    public long getMarinesCount(){
        return marinesCount;
    }
    public void setChapterFields(String nm, Long mrncnt){
            name = nm;
            marinesCount = mrncnt;
    }
    public void setChapterFieldsForScript(){
        try{
            name = Commands.slmmsk.nextLine();
            marinesCount = Long.parseLong(Commands.slmmsk.nextLine());
        }catch (InputMismatchException e){
            System.out.println("Поля chapter в скрипте указаны некорректно");
        }
    }
    private  boolean isChapterNameValid(String p) { //обобщённый метод
        return ((p == null)||(p.trim().length()==0));
    }
    private boolean isChapterMarinesCount(long p){
        return ((p<0)||(p>1000));
    }
    @Override
    public String toString() {
        return (name + " " + marinesCount);    }
}
