package com.week9.timertask;


import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

/**
 * Created by Julia on 21.06.2017.
 */
public class SizeWatcher {
    private static File file;
    private static Timer timer = new Timer();
    private static Thread end = new EndingThread();


    public static void main(String[] args) {
        if (args.length != 1) throw new RuntimeException("Bitte Dateinamen angeben");
        file = new File(args[0]);
        timer.schedule(new MyTimerTask(),0 , 1000);
        Runtime.getRuntime().addShutdownHook(end);
    }

    private static class MyTimerTask extends TimerTask {
        long currentSize;

        /**
         * Is executed every second because of the timer's settings
         * Compares old size to newly estimated size,
         * prints out information, in case a change is detected
         * sets new currentSize
         */
        @Override
        public void run() {
            long newSize = folderSize(file);
            if (currentSize != newSize) {
                currentSize = newSize;
                printInformation();
            }
        }

        /**
         * Calculates the exact size of directory, by regarding every contained file recursively
         * @param directory
         * @return
         */
        public long folderSize(File directory) {
            long length = 0;
            if (file.isFile()) return file.length();
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file);
            }
            return length;
        }

        /**
         * Provides information about change of size
         */
        private void printInformation() {
            System.out.println("Estimated size of " + file.getName() + " : " + currentSize);
        }

    }

    private static class EndingThread extends Thread {
        public void run() {
            System.out.println("***SizeWatcher wurde beendet!***");
        }

    }


}
