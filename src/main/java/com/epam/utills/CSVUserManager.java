package com.epam.utills;

import com.epam.model.Role;
import com.epam.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVUserManager {
    public static String[] HEADERS = {"UserName", "Password", "Role"};

    private static InputStreamReader newReader(final InputStream inputStream) {
        return new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
    }

    public static List<User> readUsers(File file) {
        List<User> list = new ArrayList<>();
        try(InputStream in = new FileInputStream(file);) {
            Iterable<CSVRecord> parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true).parse(newReader(in));
            for (CSVRecord record : parser) {
                list.add(new User(record.get("UserName"), record.get("Password"), new Role(record.get("Role"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeBooks(List<User> userList, File file) {
        Writer out = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withNullString("");

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            csvFilePrinter = new CSVPrinter(out, csvFileFormat);

            csvFilePrinter.printRecord(HEADERS);

            for(User user : userList){
                csvFilePrinter.printRecord(user.getUsername(), user.getPassword(), user.getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
