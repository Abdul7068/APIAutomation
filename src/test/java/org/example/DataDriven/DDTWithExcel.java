package org.example.DataDriven;

import org.example.utils.UtilExcel;
import org.testng.annotations.Test;

public class DDTWithExcel {

    @Test(dataProvider = "getData" , dataProviderClass = UtilExcel.class)

    public void testLoginData(String username , String password){
        System.out.println("username:-" +username);
        System.out.println("password:-" +password);
    }
}
