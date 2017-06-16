package com.week8.io;

import com.week7.Visitor;

import java.io.*;

/**
 * Created by Julia on 16.06.2017.
 */
public class Replace {
    private static boolean recursive = false;
    private static String search;
    private static String replacement;
    private static String path;

    private static String getCompleteString(String path) {
        String whole = "";
        String lastLine;
        try (BufferedReader buf = new BufferedReader(new FileReader(path))) {
            lastLine = buf.readLine();

            while(lastLine != null) {
                whole = whole + lastLine + System.getProperty("line.separator");
                lastLine = buf.readLine();
            }
            return whole;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void putBackInFile(String path, String complete) {
        try (BufferedWriter buf = new BufferedWriter(new FileWriter(path))) {
            buf.write(complete);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean correctUsage(String[] args) {
        int length = args.length;
        switch (length) {
            case 3 :
                search = args[0];
                replacement = args[1];
                path = args[2];
                return true;
            case 4:
                if (!args[0].toLowerCase().equals("-r")) return false;
                recursive = true;
                search = args[1];
                replacement = args[2];
                path = args[3];
                return true;
            default: return false;
        }
    }

    public static void main(String[] args) {
        String manual = "Correct Usage: [-r] Search Replacement FileOrDirectory";
        if (!correctUsage(args)) throw new RuntimeException(manual);
        FileSystem fileSystem = new FileSystem(new File(path));
        fileSystem.accept(new MyVisitor());

    }

    protected static class MyVisitor implements FileVisitor{

        @Override
        public FileVisitResult postVisitDirectory(File dir) {
            return null;
        }

        @Override
        public FileVisitResult preVisitDirectory(File dir) {
            return null;
        }

        @Override
        public FileVisitResult visitFailed(File dir) {
            return null;
        }

        @Override
        public FileVisitResult visitFile(File file) {
            String complete = getCompleteString(file.getPath());
            complete = complete.replaceAll(search, replacement);
            putBackInFile(file.getPath(), complete);
            if (recursive && file.isDirectory()) {
                for (File f : file.listFiles()) {
                    visitFile(f);
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
