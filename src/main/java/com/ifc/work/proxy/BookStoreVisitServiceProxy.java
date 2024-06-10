package com.ifc.work.proxy;

import com.ifc.work.services.BookStoreVisitServiceInterface;
import com.ifc.work.servicesImpl.BookStoreVisitService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookStoreVisitServiceProxy implements InvocationHandler {

    private final BookStoreVisitServiceInterface bookStoreVisitService;

    public BookStoreVisitServiceProxy(BookStoreVisitServiceInterface bookStoreVisitService) {
        this.bookStoreVisitService = bookStoreVisitService;
    }

    public static BookStoreVisitServiceInterface createProxy(BookStoreVisitServiceInterface bookStoreVisitService) {
        return (BookStoreVisitServiceInterface) Proxy.newProxyInstance(
                bookStoreVisitService.getClass().getClassLoader(),
                new Class[]{BookStoreVisitServiceInterface.class},
                new BookStoreVisitServiceProxy(bookStoreVisitService)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Logic before method invocation
        try{
        System.out.println("Before method: " + method.getName());

        // Invoking the actual method
        Object result = method.invoke(bookStoreVisitService, args);

        // Logic after method invocation
        System.out.println("After method: " + method.getName());

        return result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryConstants.SOMETHING_WENT_WRONG;
    }
}
