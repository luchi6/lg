package com.luch.rmi.service.impl;

import com.luch.rmi.pojo.User;
import com.luch.rmi.service.IUserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luch
 * @date 2021/11/22 22:40
 */
public class IUserServiceImpl extends UnicastRemoteObject implements IUserService {
    Map<Integer, User> userMap = new HashMap<>();

    public IUserServiceImpl() throws RemoteException {
        super();
        User user1 = new User();
        user1.setId(1);
        user1.setName("LiLei");

        User user2 = new User();
        user2.setId(2);
        user2.setName("HanMeiMei");

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
    }

    @Override
    public User getByUserId(int id) throws RemoteException {
        return userMap.get(id);
    }
}
