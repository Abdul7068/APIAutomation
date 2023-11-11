package org.example.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FillowUtils {

    static String FilePath = System.getProperty("user.dir")+"src/main/java/org/example/resources/TestData.xlsx";
    public static String fetchDataFromXLSx(String sheetName ,String id , String fieldName) throws FilloException {
        String value = null;
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(FilePath);
        String query = "Select * from " + sheetName + "" + "where ID=" + id + "";
        Recordset recordset = connection.executeQuery(query);
        while (recordset.next()){
            value = recordset.getField(fieldName);
        }
        recordset.close();
        connection.close();
        return value;
    }
}
