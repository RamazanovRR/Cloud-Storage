package com.cloud.storage.client;

import com.cloud.storage.common.Const;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientConnection implements Const {
    private static ClientConnection instance;
    private static Socket socket;
    private static DataOutputStream dataOS;
    private static DataInputStream dataIN;
    private static ObjectEncoderOutputStream oeos;
    private static ObjectDecoderInputStream odis;
    private ClientConnection() {

    }

    static ClientConnection getInstance() {
        if(instance == null) {
            instance = new ClientConnection();
        }
        return instance;
    }

    public void connection() {
        if (!isConnection()) {
            try {
                socket = new Socket(Const.HOST,Const.PORT);
                dataOS = new DataOutputStream(socket.getOutputStream());
                dataIN = new DataInputStream(socket.getInputStream());
                oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
                odis = new ObjectDecoderInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println("Ошибка в подключении");
                e.printStackTrace();
            }
        } else System.out.println("Соединение установлено ранее.");

    }

    public void disconnect() {
        if (!isConnection()) {
            try {
                odis.close();
                oeos.close();
                dataIN.close();
                dataOS.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("ошибка в отключении сокета");
                e.printStackTrace();
            }
        } else System.out.println("Соединение было закрыто ранее");
    }

    public boolean isConnection() {
        boolean isConnection = false;
        if(!(socket == null) & !(dataOS == null) & !(dataIN == null) & !(oeos == null) & !(odis == null)){
            isConnection = true;
        }

        return isConnection;
    }

    public Object sendPackage(Object obj) {
        try {
            oeos.writeObject(obj);
            oeos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getPackage();
    }

    public Object getPackage() {
        Object obj = null;
        try {
            obj = odis.readObject();
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
