package com.cloud.storage.client;

import com.cloud.storage.common.DataPackage;
import com.cloud.storage.common.WriteData;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8188);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataPackage dataPackage = new DataPackage("File", ClientConnection.getInstance().getLogin());
            WriteData.Write("P1120372.jpg");
            ObjectEncoderOutputStream oeos = null;
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            oeos.writeObject(dataPackage);
            oeos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
