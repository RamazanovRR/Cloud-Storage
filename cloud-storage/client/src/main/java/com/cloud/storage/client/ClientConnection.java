package com.cloud.storage.client;

import com.cloud.storage.common.ServerConst;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientConnection implements ServerConst {
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
        try {
            socket = new Socket(HOST,PORT);
            dataOS = new DataOutputStream(socket.getOutputStream());
            dataIN = new DataInputStream(socket.getInputStream());
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            odis = new ObjectDecoderInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Ошибка в подключении");
            e.printStackTrace();
        }

    }

    public void disconnect() {
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
