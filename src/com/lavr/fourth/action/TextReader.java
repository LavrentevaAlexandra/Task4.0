package com.lavr.fourth.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 123 on 02.11.2016.
 */
public class TextReader {
    private static final Logger LOG = LogManager.getLogger();
    public String loadText(final String PATH) {
        String text;
        try {
            FileInputStream inFile = new FileInputStream(PATH);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            text = new String(str); // String with all text
            inFile.close();
        }
        catch (IOException e) {
            LOG.fatal("File reading error", e);
            throw  new RuntimeException(e);
        }
        return text;
    }
}
