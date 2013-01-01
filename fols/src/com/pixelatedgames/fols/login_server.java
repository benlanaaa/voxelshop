package com.pixelatedgames.fols;

import com.pixelatedgames.fols.handlers.login_initializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class login_server {
    // login server
    private ServerBootstrap loginBootstrap = new ServerBootstrap();

    public void run() throws Exception {
        try {
            // create the login server
            loginBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .localAddress(7633)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new IdleStateHandler(60, 30, 0),
                                    new LoggingHandler(LogLevel.INFO),
                                    new login_initializer()
                            );
                        }
                    });
            // start the login server
            ChannelFuture f = loginBootstrap.bind().sync();

            // Wait until the server socket is closed.
            // this is what locks things up and forces the jar not to exit
            // not sure if it matters which we sync to
            // if there is more than one handler bound
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            loginBootstrap.shutdown();
        }
    }
}
