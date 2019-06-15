package com.epam.utills;

import com.epam.model.Role;
import com.epam.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVUserManager {
    private static String[] HEADERS = {"UserName", "Password", "Role"};
    private static Logger LOGGER = LogManager.getLogger(CSVUserManager.class);

    private static InputStreamReader newReader(final InputStream inputStream) {
        return new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
    }

    public static List<User> readUsers(File file) {
        List<User> list = new ArrayList<>();
        try (InputStream in = new FileInputStream(file)) {
            Iterable<CSVRecord> parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true).parse(newReader(in));
            for (CSVRecord record : parser) {
                list.add(new User(record.get("UserName"), record.get("Password"), new Role(record.get("Role"))));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public static void writeUsers(List<User> userList, File file) {
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withNullString("");

        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            csvFilePrinter = new CSVPrinter(out, csvFileFormat);

            csvFilePrinter.printRecord(HEADERS);

            for (User user : userList) {
                csvFilePrinter.printRecord(user.getUsername(), user.getPassword(), user.getRole());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
