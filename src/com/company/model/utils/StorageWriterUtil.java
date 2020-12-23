package com.company.model.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * StorageWriterUtil.
 *
 * @author Artsiom Mazhylouski
 */
public class StorageWriterUtil {
    private static final String recordSeparator = "\n\n";

    public static void writeRecord(String storagePath, String record) {
        try (FileWriter writer = new FileWriter(storagePath, true)) {
            writer.append(record);
            writer.append(recordSeparator);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void writeRecords(String storagePath, List<String> records) throws IOException {
        File file = new File(storagePath);
        file.delete();
        file.createNewFile();
        for (String record : records) {
            writeRecord(storagePath, record);
        }
    }
}
