package ru.netology;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger {
    private static Logger logger = null;
    private FileWriter fileLog;

    private Logger() {
        try {
            fileLog = new FileWriter("file.log", true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void log(String name, String msg) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        try {
            fileLog.write("[" + formattedDate + " " + name + "] " + msg + "\n");
            fileLog.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
