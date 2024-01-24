package com.anbai.sec.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMITestImpl extends UnicastRemoteObject implements RMITestInterface {

    private static final long serialVersionUID = 1L;

    protected RMITestImpl() throws RemoteException {
        super();
    }

    /**
     * RMI测试方法
     *
     * WARN  被服务端所真正调用。
     *
     * @return 返回测试字符串
     */
    @Override
    public Object test(Object o1) throws RemoteException {
        // return "Hello RMI~  （from Server）";
        return new RMIClientTest.Server_PayloadObject();
    }

}