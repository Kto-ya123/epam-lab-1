package com.company.model.utils;

import com.company.model.dao.Book;

import java.io.FileWriter;
import java.io.IOException;

public class StorageWriterUtil {
    private static final String recordSeparator  = "\r\n\r\n";

    public static void writeRecord(String storagePath, String record){
        try(FileWriter writer = new FileWriter(storagePath, true))
        {
            writer.append(recordSeparator);
            writer.append(record);
            writer.flush();
        }

        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
