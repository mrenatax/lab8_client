package ClientAnswer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import static ClientAnswer.CommandReader.*;
public class Authorization implements Serializable {
    public String logg;
    public String passw;
    private boolean isConnected = false;
    public Authorization(){}
    public Authorization(String login,String password){
        this.logg = login;
        this.passw = password;
    }
    public void checkLog(String log) throws InterruptedException {
            if ((log == null) || (log.trim().length() == 0)) {
                textRegistr.setText("Логин не может быть пустым.");
                getLog(log);
            }
    }
    public void getLog(String log) throws InterruptedException {
        getLogin = login.getText();
        checkLog(log);
    }
    public void getPass(String pass) throws InterruptedException {
        getPassword = password.getText();
        checkPass(pass);
    }
    public void checkPass(String pass) throws InterruptedException {
            if ((pass == null) || (pass.trim().length() == 0)) {
                textRegistr.setText("Пароль не может быть пустым.");
                getPass(pass);
            }
    }
    public void authorize(String log, String pass) throws IOException, InterruptedException {
        getLog(log);
        getPass(pass);
        Authorization user = new Authorization(log,pass);
        while (!isConnected) {
            isConnected = true;
            SocketChannel socketChannel = createChannel();
            ObjectOutputStream outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            outputStream.writeObject(user);
            socketChannel.close();
            outputStream.close();
        }
        isConnected = false;
    }
    private SocketChannel createChannel() throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16384);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        SocketAddress socketAddress = new InetSocketAddress("localhost", 55665);
        try {
            socketChannel.connect(socketAddress);
        }catch (ConnectException e){
            createChannel();
        }
        return socketChannel;
    }
    public  String getLogin(){
        return logg;
    }
    public String getPassword(){
        return passw;
    }
}
