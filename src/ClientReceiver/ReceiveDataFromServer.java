package ClientReceiver;
import ClientAnswer.Authorization;
import SpaceMarineData.SpaceMarine;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import static ClientAnswer.CommandReader.*;
public class ReceiveDataFromServer{
    int k = 0;
    String n ="";
    String name = "";
    String id ="";
    String creation ="";
    String height ="";
    String health ="";
    String weapon ="";
    String meleeWeapon ="";
    String x ="";
    String y ="";
    String chapter_name ="";
    String chapter_marinesCount ="";
    String login ="";
    public static String data;
    public static ReentrantLock locker = new ReentrantLock();
    public static List<String> loginList = new ArrayList<>();
    private DatagramSocket ds;
    public ReceiveDataFromServer(){}
    public void receive() {
        try {
            locker.lock();
            ds  = new DatagramSocket(55665);
            byte[] buf = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            ds.receive(packet);
            ds.close();
            data = new String(buf, StandardCharsets.UTF_8);
            data = data.replaceAll("�","");
            if (!data.contains("00010010")) {
                if (s == "show") {
                    sm.clear();
                    Scanner scanner = new Scanner(data);
                    while (scanner.hasNext()) {
                        if (k == 0) {
                            n = scanner.nextLine();
                            if (scanner.hasNext()) {
                                name = scanner.nextLine();
                                id = scanner.nextLine();
                                creation = scanner.nextLine();
                                height = scanner.nextLine();
                                health = scanner.nextLine();
                                weapon = scanner.nextLine();
                                meleeWeapon = scanner.nextLine();
                                x = scanner.nextLine();
                                y = scanner.nextLine();
                                chapter_name = scanner.nextLine();
                                chapter_marinesCount = scanner.nextLine();
                                login = scanner.nextLine();
                                showCol();
                            }
                        } else {
                            name = scanner.nextLine();
                            id = scanner.nextLine();
                            creation = scanner.nextLine();
                            height = scanner.nextLine();
                            health = scanner.nextLine();
                            weapon = scanner.nextLine();
                            meleeWeapon = scanner.nextLine();
                            x = scanner.nextLine();
                            y = scanner.nextLine();
                            chapter_name = scanner.nextLine();
                            chapter_marinesCount = scanner.nextLine();
                            login = scanner.nextLine();
                            showCol();
                        }
                    }
                    flag = "finish";
                    k = 0;
                } else if (s == "help") {
                    textHelp.setText(data);
                } else if (s == "execute_script") {
                    textScript.setText(data);
                } else if (s == "users"){
                    loginList.clear();
                    Scanner scanner = new Scanner(data);
                    while (scanner.hasNext()) {
                        if (k == 0) {
                            n = scanner.nextLine();
                            if (scanner.hasNext()) {
                                loginList.add(scanner.nextLine());
                                k++;
                            }
                        } else {
                            loginList.add(scanner.nextLine());
                            k++;
                        }
                    }
                    userFlag = "finish";
                    k = 0;
                }else {
                    textInfo.setText(data);
                }
            }
            else if (data.contains("00010010")) {
                textRegistr.setText("Пользователь с таким логином уже существует\n" +
                        " или пароль был введён неправильно.\n" +
                        " Повторите авторизацию.");
                Authorization a = new Authorization();
                a.authorize(getLogin,getPassword);
            }
        }catch (IOException | InterruptedException e){
              e.printStackTrace();
        }finally {
            locker.unlock();
        }
    }
    public void showCol(){
        SpaceMarine spaceM = new SpaceMarine();
        spaceM.setName(name);
        spaceM.setIdClient(Integer.parseInt(id));
        spaceM.setLogin(login);
        spaceM.setChapter(chapter_name, Long.parseLong(chapter_marinesCount));
        spaceM.setCoordinates(Float.parseFloat(x), Float.parseFloat(y));
        spaceM.setHeight(Long.parseLong(height));
        spaceM.setHealth(Long.parseLong(health));
        spaceM.setMeleeWeapon(meleeWeapon);
        spaceM.setWeaponType(weapon);
        sm.add(spaceM);
        k++;
    }
}

