package com.luch.rmi.server;

import com.luch.rmi.service.IUserService;
import com.luch.rmi.service.impl.IUserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author luch
 * @date 2021/11/22 22:46
 */
public class RMIServer {
    public static void main(String[] args) {
        try {
            // 1.注册registry实例，绑定端口
            Registry registry = LocateRegistry.createRegistry(9998);
            // 2.创建远程对象
            IUserService userService = new IUserServiceImpl();
            // 3.将远程对象注册到RMI服务器上（即服务器注册表上）
            registry.rebind("userService", userService);
            System.out.println("==========RMI服务端启动成功============");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
