package ClientAnswer;
import ClientReceiver.ReceiveDataFromServer;
import SpaceMarineData.SpaceMarine;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.nio.*;
import java.util.concurrent.ConcurrentSkipListSet;
public class CommandReader implements Runnable {
    public static VBox vBox = new VBox();
    public static VBox vBox1 = new VBox();
    public static VBox vertical = new VBox();
    public static HBox buttonBox = new HBox();
    private static ArrayList<String> history = new ArrayList<>();
    public static Text textInfo = new Text();
    public static Text textHelp = new Text();
    public static Text textScript = new Text();
    public static Text textRegistr = new Text();
    public static ComboBox<String> commandBox = new ComboBox<>();
    public static ComboBox<String> languageBox = new ComboBox<>();
    public static ArrayList<String> weapon = new ArrayList<>();
    public static ArrayList<String> meleeWeapon = new ArrayList<>();
    public static ComboBox<String> weaponBox = new ComboBox<>();
    public static ComboBox<String> meleeWeaponBox = new ComboBox<>();
    static ArrayList<String> commands = new ArrayList<>();
    public static ArrayList<String> languages = new ArrayList<>();
    public static  Label textLbl = new Label();
    public static Group group = new Group();
    public static Group group1 = new Group();
    public static HBox hBox = new HBox();
    public static HBox adder = new HBox();
    public static HBox adder1 = new HBox();
    public static HBox remover = new HBox();
    static HBox registration = new HBox();
    public static String s ;
    public static String getLogin = "";
    public static String getPassword = "";
    public static String locale = "Русский";
    public static String flag = "";
    public static String userFlag = "";
    public static TextField login = new TextField();
    public static TextField password = new TextField();
    public static TextField toRemove = new TextField();
    public static TextField toAdd = new TextField();
    public static Text text6 = new Text();
    public static Text text66 = new Text();
    public static Text user = new Text();
    public static int removeId = 0;
    public static long height = 0;
    public static SpaceMarine smr = new SpaceMarine();
    public static SpaceMarine spaceMarine = new SpaceMarine();
    public static int id2send;
    public static ConcurrentSkipListSet<SpaceMarine> sm = new ConcurrentSkipListSet<>();
    public static float x = 0;
    public static String fileName;
    public static float y = 0;
    public static long marines = 0;
    public static String chapter = "";
    public static String scriptFlag = "";
    public CommandReader() {
        Thread thread = new Thread(this, "Поток клиента с отправкой команд");
        thread.start();
    }
    private SocketChannel createChannel() throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16384);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        SocketAddress socketAddress = new InetSocketAddress("localhost", 55665);
        try {
            socketChannel.connect(socketAddress);
        } catch (ConnectException e) {
            createChannel();
        }
        return socketChannel;
    }
    public void run() {
        try {
            switch (s) {
                case "info":
                    history.add(s);
                    ComplicatedObject info = new ComplicatedObject("info", locale, "","");
                    SocketChannel socketchannel = createChannel();
                    ObjectOutputStream outputStream = new ObjectOutputStream(socketchannel.socket().getOutputStream());
                    outputStream.writeObject(info);
                    ReceiveDataFromServer r = new ReceiveDataFromServer();
                    r.receive();
                    socketchannel.close();
                    outputStream.close();
                    break;
                case "help":
                    history.add(s);
                    ComplicatedObject help = new ComplicatedObject("help");
                    SocketChannel socketchannel1 = createChannel();
                    ObjectOutputStream outputStream1 = new ObjectOutputStream(socketchannel1.socket().getOutputStream());
                    outputStream1.writeObject(help);
                    ReceiveDataFromServer r3 = new ReceiveDataFromServer();
                    r3.receive();
                    socketchannel1.close();
                    outputStream1.close();
                    break;
                case "head":
                    history.add(s);
                    ComplicatedObject head = new ComplicatedObject("head",locale, "","");
                    SocketChannel socketchannel2 = createChannel();
                    ObjectOutputStream outputStream2 = new ObjectOutputStream(socketchannel2.socket().getOutputStream());
                    outputStream2.writeObject(head);
                    ReceiveDataFromServer r1 = new ReceiveDataFromServer();
                    r1.receive();
                    socketchannel2.close();
                    outputStream2.close();
                    break;
                case "clear":
                    SocketChannel socketchannel3 = createChannel();
                    ObjectOutputStream outputStream3 = new ObjectOutputStream(socketchannel3.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject clear = new ComplicatedObject("clear", getLogin, getPassword);
                    outputStream3.writeObject(clear);
                    ReceiveDataFromServer r34 = new ReceiveDataFromServer();
                    r34.receive();
                    socketchannel3.close();
                    outputStream3.close();
                    break;
                case "show":
                    SocketChannel socketchannel4 = createChannel();
                    ObjectOutputStream outputStream4 = new ObjectOutputStream(socketchannel4.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject show = new ComplicatedObject("show");
                    outputStream4.writeObject(show);
                    ReceiveDataFromServer r4 = new ReceiveDataFromServer();
                    r4.receive();
                    socketchannel4.close();
                    outputStream4.close();
                    break;
                case "users":
                    SocketChannel socketchannel44 = createChannel();
                    ObjectOutputStream outputStream44 = new ObjectOutputStream(socketchannel44.socket().getOutputStream());
                    ComplicatedObject users = new ComplicatedObject("users");
                    outputStream44.writeObject(users);
                    ReceiveDataFromServer r44 = new ReceiveDataFromServer();
                    r44.receive();
                    socketchannel44.close();
                    outputStream44.close();
                    break;
                case "add":
                    ReceiveDataFromServer r5 = new ReceiveDataFromServer();
                    history.add(s);
                    ComplicatedObject co = new ComplicatedObject("add", spaceMarine, getLogin);
                    SocketChannel socketchannel5 = createChannel();
                    ObjectOutputStream outputStream5 = new ObjectOutputStream(socketchannel5.socket().getOutputStream());
                    outputStream5.writeObject(co);
                    r5.receive();
                    socketchannel5.close();
                    outputStream5.close();
                    break;
                case "update_id":
                    ReceiveDataFromServer r6 = new ReceiveDataFromServer();
                    history.add(s);
                    ComplicatedObject obj = new ComplicatedObject("update_id", id2send, smr, getLogin);
                    SocketChannel socketchannel6 = createChannel();
                    ObjectOutputStream outputStream6 = new ObjectOutputStream(socketchannel6.socket().getOutputStream());
                    outputStream6.writeObject(obj);
                    r6.receive();
                    outputStream6.close();
                    socketchannel6.close();
                    break;
                case "remove_by_id":
                    ReceiveDataFromServer r7 = new ReceiveDataFromServer();
                    history.add(s);
                    ComplicatedObject remove = new ComplicatedObject("remove_by_id", removeId, getLogin);
                    SocketChannel socketchannel7 = createChannel();
                    ObjectOutputStream outputStream7 = new ObjectOutputStream(socketchannel7.socket().getOutputStream());
                    outputStream7.writeObject(remove);
                    r7.receive();
                    outputStream7.close();
                    socketchannel7.close();
                    break;
                case "sum_of_height":
                    SocketChannel socketchannel8 = createChannel();
                    ObjectOutputStream outputStream8 = new ObjectOutputStream(socketchannel8.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject heightSum = new ComplicatedObject("sum_of_height");
                    outputStream8.writeObject(heightSum);
                    ReceiveDataFromServer r8 = new ReceiveDataFromServer();
                    r8.receive();
                    outputStream8.close();
                    socketchannel8.close();
                    break;
                case "max_by_name":
                    SocketChannel socketchannel9 = createChannel();
                    ObjectOutputStream outputStream9 = new ObjectOutputStream(socketchannel9.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject maxName = new ComplicatedObject("max_by_name");
                    outputStream9.writeObject(maxName);
                    ReceiveDataFromServer r9 = new ReceiveDataFromServer();
                    r9.receive();
                    outputStream9.close();
                    socketchannel9.close();
                    break;
                case "filter_greater_than_height":
                    SocketChannel socketchannel10 = createChannel();
                    ObjectOutputStream outputStream10 = new ObjectOutputStream(socketchannel10.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject filter = new ComplicatedObject("filter_greater_than_height", height, getLogin);
                    outputStream10.writeObject(filter);
                    ReceiveDataFromServer r10 = new ReceiveDataFromServer();
                    r10.receive();
                    outputStream10.close();
                    socketchannel10.close();
                    break;
                case "remove_greater":
                    ReceiveDataFromServer r11 = new ReceiveDataFromServer();
                    SocketChannel socketchannel11 = createChannel();
                    ObjectOutputStream outputStream11 = new ObjectOutputStream(socketchannel11.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject removeGr = new ComplicatedObject("remove_greater", removeId, getLogin);
                    outputStream11.writeObject(removeGr);
                    r11.receive();
                    outputStream11.close();
                    socketchannel11.close();
                    break;
                case "history":
                    SocketChannel socketchannel12 = createChannel();
                    ObjectOutputStream outputStream12 = new ObjectOutputStream(socketchannel12.socket().getOutputStream());
                    history.add(s);
                    ComplicatedObject history1 = new ComplicatedObject("history", history.toString());
                    outputStream12.writeObject(history1);
                    ReceiveDataFromServer r12 = new ReceiveDataFromServer();
                    r12.receive();
                    outputStream12.close();
                    socketchannel12.close();
                    break;
                case "exit":
                    SocketChannel socketchanne101 = createChannel();
                    ObjectOutputStream outputStream101 = new ObjectOutputStream(socketchanne101.socket().getOutputStream());
                    ComplicatedObject exit = new ComplicatedObject("exit");
                    outputStream101.writeObject(exit);
                    //  thread.interrupt();
                    outputStream101.close();
                    socketchanne101.close();
                    break;
                case "execute_script":
                    history.add(s);
                    File file = new File(fileName);
                    ComplicatedObject executeScript = new ComplicatedObject("execute_script", file, getLogin,locale);
                    SocketChannel socketchanne100 = createChannel();
                    ObjectOutputStream outputStream100 = new ObjectOutputStream(socketchanne100.socket().getOutputStream());
                    outputStream100.writeObject(executeScript);
                    ReceiveDataFromServer r13 = new ReceiveDataFromServer();
                    r13.receive();
                    outputStream100.close();
                    socketchanne100.close();
                    break;
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}