package com.week9.timertask;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Julia on 21.06.2017.
 */
public class TestSizeWatcher {
    private static String content = "";
    private static String path = "C:\\Users\\Julia\\IdeaProjects\\binf\\src\\com\\week9\\timertask\\abc.txt";
    private static String changedDirectory = "C:\\Users\\Julia\\IdeaProjects\\binf\\src\\com\\week9";

    public static void main (String[] args){
        SizeWatcher.main(new String[] {changedDirectory});
        Timer timer = new Timer();
        timer.schedule(new SizeChanger(), 3000, 3000);
    }

    /**
     * TimerTask to help change the size of the testing-file
     * Runs concurrently to actual size-watching TimerTask
     */
    private static class SizeChanger extends TimerTask {
        /**
         * Changes the size of a testing-file by adding String every three seconds
         */
        @Override
        public void run() {
            File file = new File(path);
            try (BufferedWriter buf = new BufferedWriter(new FileWriter(path))) {
                buf.write(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            content += "haha";
        }
    }


}
