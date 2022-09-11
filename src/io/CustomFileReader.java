package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomFileReader {
    public static List<String> readFile(String filePath) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(filePath);
        Scanner sc = new Scanner((inputStream));
        List<String> result = new ArrayList<>();
        while(sc.hasNext()){
            result.add(sc.nextLine());
        }
        return result;
    }
}
