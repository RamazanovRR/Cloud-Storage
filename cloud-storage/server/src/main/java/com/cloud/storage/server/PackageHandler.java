package com.cloud.storage.server;

import com.cloud.storage.common.Const;
import com.cloud.storage.common.DataPackage;
import com.cloud.storage.common.DirsAndFiles;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class PackageHandler extends ChannelInboundHandlerAdapter implements Const {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataPackage datPac;
        try {
            if (msg == null)
                return;
            System.out.println(msg.getClass());
            if (msg instanceof DataPackage) {
                System.out.println("Получен пакет данных");
                datPac = (DataPackage) msg;
            } else {
                System.out.printf("Server received wrong object!");
                return;
            }
            switch (datPac.getTypeMsg()) {
                case CREATE_DIRS :
                    DirsAndFiles.createServerDirs(datPac.getLogin());
                    ctx.write(datPac);
                    ctx.flush();
                    break;
                case REFRESH_FILES :
                    datPac.setFiles(DirsAndFiles.returnServerList(datPac.getLogin()));
                    ctx.write(datPac);
                    ctx.flush();
                    break;
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
