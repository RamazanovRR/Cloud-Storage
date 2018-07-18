package com.cloud.storage.client;

import com.cloud.storage.common.DataPackage;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    @FXML
    TextArea textArea;

    public void btnClickMeAction() {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            DataPackage dataPackage = new DataPackage("File");
            dataPackage.Write("P1120372.jpg","DSC_1111.jpg");
            ObjectEncoderOutputStream oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            oeos.writeObject(dataPackage);
            oeos.flush();
            textArea.appendText("Файл отправлен на сервер\n");

            // не приходит ответ от сервера об успешной записи файла, или не успешной

//            int coutn = 0;
//            byte[] bytesStr = new byte[64];
//            while (in.hasNextByte()) {
//                bytesStr[coutn] = in.nextByte();
//                coutn++;
//            }
//            String str = new String(bytesStr, "UTF-8");
//            System.out.println(str);
//            textArea.appendText(str + " тест\n");
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
