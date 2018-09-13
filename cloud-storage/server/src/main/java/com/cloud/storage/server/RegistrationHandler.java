package com.cloud.storage.server;

import com.cloud.storage.common.Const;
import com.cloud.storage.common.Encryption;
import com.cloud.storage.common.RegistrationPackage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RegistrationHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Registration new User");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg == null ) return;
        if(!(msg instanceof RegistrationPackage)) {
            ctx.fireChannelRead(msg);
            System.out.println("Server received wrong object!");
            //необходимо вернуть клиенту объект с ответом!!!
            return;
        }
        RegistrationPackage regPac = (RegistrationPackage) msg;
        Database db = new Database();
        db.connect();
        String[] st = {regPac.getUserName(), regPac.getUserSurename(), regPac.getLoginName(), regPac.getEmail(),
        regPac.getCity(), regPac.getGender(), Encryption.decode(regPac.getPass(), Const.KEY)};
        boolean auth = false;
        if(!db.addUserInTable(st)) auth = true;
        regPac.setAuth(auth);
        regPac.nullField();
        db.disconnect();
        ctx.write(regPac);
        ctx.flush();
        System.out.println("пакет данных Registration отправлен на клиент");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

