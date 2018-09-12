package com.cloud.storage.server;

import com.cloud.storage.common.DataPackage;
import com.cloud.storage.common.WriteData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.File;
import java.io.FileOutputStream;

public class PackageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str;
        try {
            if (msg == null)
                return;
            System.out.println(msg.getClass());
            if (msg instanceof DataPackage) {
                System.out.println("Получен пакет данных");
            } else {
                System.out.printf("Server received wrong object!");
                return;
            }
            DataPackage dataPackage = (DataPackage) msg;
            if(dataPackage.getCheckSumData().equals(WriteData.checkSum(dataPackage.getData()))) {
                FileOutputStream fout = new FileOutputStream(dataPackage.getFileName());
                fout.write(dataPackage.getData());
                str = "файл успешно записан в облачное хранилище";
            } else {
                str = "Контрольная сумма не совпадает, файл испорчен";
            }
            byte[] arr = str.getBytes();
            ByteBufAllocator al = new PooledByteBufAllocator();
            ByteBuf buf = al.buffer(arr.length);
            buf.writeBytes(arr);
            ctx.write(buf);
            ctx.flush();

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
