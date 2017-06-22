package com.week9.io;

import com.common.util.lang.StringUtil;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Julia on 22.06.2017.
 */
public class Search {

    private static String manual = "Correct Usage: Search FileOrDirectory";
    private static FileSystem fileSystem;
    private static String search;
    private static String path;
    private static boolean recursive;
    private static File file;
    private static ConcurrentHashMap<String, LinkedList<Entry> > map = new ConcurrentHashMap<>();

    /**
     * Check for correct usage.
     *
     * @param args
     * @return
     */
    private static boolean correctUsage(String[] args) {
        int length = args.length;
        switch (length) {
            case 2 :
                search = args[0];
                path = args[1];
                return true;
            case 3:
                if (!args[0].toLowerCase().equals("-r")) return false;
                recursive = true;
                search = args[1];
                path = args[2];
                return true;
            default: return false;
        }
    }

    private static void printResults() {
        for (LinkedList linkedList : map.values()) {
            System.out.println(((Entry)linkedList.peek()).name + " : ");
            for (Object entry : linkedList) {
                System.out.print(StringUtil.padLeft("ln " +((Entry)entry).lineNumber+"  ", 5));
                System.out.print(((Entry)entry).line.trim()+"  ");
                System.out.print("matches"+((Entry)entry).amountOfMatches+"  ");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        if (!correctUsage(args)) throw new RuntimeException(manual);
        file = new File(path);
        fileSystem = new FileSystem(file);
        fileSystem.accept(new MyVisitor());
        Runtime.getRuntime().addShutdownHook(new Hook());
    }

    protected static class Entry {
        private String line;
        private int lineNumber;
        protected int amountOfMatches;
        protected String name;

        public Entry(String line, int number, int matches, String name) {
            this.line = line;
            lineNumber = number;
            amountOfMatches = matches;
            this.name = name;
        }

    }

    protected static class Hook extends Thread{
        public void run() {
           printResults();
        }
    }
    protected static class MyThreadVisitor implements Runnable {
        private File file;

        public MyThreadVisitor(File root) {
            file = root;
        }

        @Override
        public void run() {

            try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
                SearchLineReader lineReader = new SearchLineReader(buf, search);
                String lastLine = lineReader.readLine();
                LinkedList linkedList = new LinkedList();
                while(lastLine != null) {
                    if (lineReader.getAmountOfMatches() > 0) {
                        int lineNumber = lineReader.getLineNumber();
                        int matches = lineReader.getAmountOfMatches();
                        linkedList.add(new Entry(lastLine, lineNumber, matches, file.getName()));
                        lineReader.setToZero();
                    }
                    lastLine = lineReader.readLine();
                }
                if (linkedList.size() > 0) map.put(file.getName(), linkedList);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyVisitor extends FileVisitorAdapter{

        @Override
        public FileVisitResult preVisitDirectory(File dir) {
            if (recursive || dir == file) {
                return FileVisitResult.CONTINUE;
            } else {
                return FileVisitResult.SKIP_SUBTREE;
            }
        }

        @Override
        public FileVisitResult visitFile(File file) {
            Thread t = new Thread(new MyThreadVisitor(file));
            t.start();
            return FileVisitResult.CONTINUE;
        }

    }

}
