package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String st = "";
        final InputStream targetStream;
        byte[] bytes;
        try {
            targetStream = new DataInputStream(new FileInputStream(file));
            bytes = targetStream.readAllBytes();
            st = new String(bytes, "UTF-8");
            targetStream.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        String name = "";
        Integer age = null;
        String email = "";
        Long phone = null;
        String[] lines = st.split("\\r?\\n", -1);

        for (int i = 0; i < lines.length - 1; i++) {
            KeyValue keyValue = new KeyValue(lines[i]);
            if(keyValue.key.equals("Name")) {
                name = keyValue.value;
                System.out.println("name: " + name);
            } else if (keyValue.key.equals("Age")) {
                age = Integer.parseInt(keyValue.value);
            } else if (keyValue.key.equals("Email")) {
                email = keyValue.value;
            } else if (keyValue.key.equals("Phone")) {
                phone = Long.parseLong(keyValue.value);
            };
        }
        Profile profile = new Profile(name, age, email, phone);

        return profile;
    }
}

class KeyValue {
    String key;
    String value;

    public KeyValue(String line) {
        String[] splitted = line.split(": ");
        this.key = splitted[0];
        this.value = splitted[1];
    };
}
