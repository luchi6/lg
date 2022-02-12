package com.luch.rmi.service;

import com.luch.rmi.pojo.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author luch
 * @date 2021/11/22 22:39
 */
public interface IUserService extends Remote {
    User getByUserId(int id) throws RemoteException;
}
