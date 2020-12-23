package com.company.model.utils;

import java.io.FileReader;
import java.io.IOException;

/**
 *  StorageReaderUtil.
 *
 * @author Artsiom Mazhylouski
 */
public class StorageReaderUtil {
    private static final String recordSeparator  = "\r\n\r\n";

    public static String[] readFile(String storagePath) throws IOException {
        try(FileReader reader = new FileReader(storagePath))
        {
            char[] buffer = new char[256];
            StringBuilder storageBuilder = new StringBuilder();

            int length;
            while((length = reader.read(buffer))>0){
                storageBuilder.append(buffer, 0, length);
            }

            String storage = storageBuilder.toString();

            if(storage.isEmpty()){
                return new String[]{};
            }else {
                return storage.split(recordSeparator);
            }
        }
        catch(IOException ex){
            throw ex;
        }
    }
}
