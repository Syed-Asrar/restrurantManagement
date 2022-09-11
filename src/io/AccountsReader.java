package io;

import java.io.FileNotFoundException;
import java.util.List;

public class AccountsReader {
    public double balance(String filePath) throws FileNotFoundException {
        List<String> accounts= CustomFileReader.readFile(filePath);
        return Double.parseDouble(accounts.get(0));
    }
}
