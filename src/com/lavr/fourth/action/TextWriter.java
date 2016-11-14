package com.lavr.fourth.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 123 on 14.11.2016.
 */
public class TextWriter {
    private static final Logger LOG = LogManager.getLogger();
    public String writeText(final String PATH, String text) {
        try {
            FileOutputStream outFile = new FileOutputStream(PATH);
            outFile.write(text.getBytes());
            outFile.flush();
            outFile.close();
        }
        catch (IOException e) {
            LOG.fatal("File reading error", e);
            throw  new RuntimeException(e);
        }
        return text;
    }
}
