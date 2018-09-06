package com.cloud.storage.server;

import com.cloud.storage.common.AuthPackage;
import com.cloud.storage.common.Encryption;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHendler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...authorization...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg == null ) return;
        if(!(msg instanceof AuthPackage)) {
            System.out.println("Server received wrong object!");
            return;
        }
        AuthPackage aPac = (AuthPackage) msg;
        Database db = new Database();
        db.connect();
        boolean auth = db.authInDatabase(aPac.getLogin(), Encryption.decode(aPac.getPass(), "крафт"));
        aPac.setAuth(auth);
        db.disconnect();
        ctx.write(aPac);
        ctx.flush();
        System.out.println("пакет данных отправлен на клиент, в ожидании");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
