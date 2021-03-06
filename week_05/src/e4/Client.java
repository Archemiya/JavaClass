package e4;

import e1.MyBC;
import e3.Crypt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 匪夷所思 on 2018/5/28.
 */
public class Client {
    public static void main(String[] args) {
        Socket mysocket ;
        DataInputStream in=null;
        DataOutputStream out=null;
        byte[] publicKey1 = null,privateKey1 = null,key=null;
        try {
            Map<String, Object> keyMap1 = DHCoder.initKey();
            publicKey1 = DHCoder.getPublicKey(keyMap1);
            privateKey1 = DHCoder.getPrivateKey(keyMap1);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Scanner sc = new Scanner(System.in);
            mysocket=new Socket("localhost",9219);
            in=new DataInputStream(mysocket.getInputStream());
            out=new DataOutputStream(mysocket.getOutputStream());
            out.writeUTF( Crypt.parseByte2HexStr(publicKey1));
            String publicKey2=in.readUTF();
            key = DHCoder.getSecretKey(Crypt.parseHexStr2Byte(publicKey2),privateKey1);

            System.out.println("连接成功，请中缀输入表达式" );
            String expression=sc.nextLine();
            MyBC exchange=new MyBC();
            String postfix=exchange.exc(expression);
            System.out.println("转化为后缀表达式为：" + postfix);
            String c = Crypt.parseByte2HexStr(Crypt.encrypt(postfix,Crypt.parseByte2HexStr(key)));
            System.out.println("加密后得到：" + c );
            out.writeUTF(c);
            String m =in.readUTF();
            System.out.println("客户收到服务器的回答:");
            System.out.println(expression + " = " + m);
        }
        catch(Exception e) {
            System.out.println("服务器已断开"+e);
        }
    }
}
