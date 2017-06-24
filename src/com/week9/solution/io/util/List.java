package com.week9.solution.io.util;

import java.io.File;

/**
 * @author Mathias Menninghaus (mathias.mennighaus@uos.de)
 */
public class List {
   public static void main(String[] args) {

      /*
       * flags for the options
       */
      boolean recursive = false;
      /*
       * determine whether all arguments are read or not
       */
      boolean argumentsRead = false;

      int i = 0;
      while (!argumentsRead && i < args.length) {

         /*
          * read out the arguments
          */
         switch (args[i]) {
            case "-r":
               recursive = true;
               i++;
               break;
            default:
            /*
             * arguments must be set at the beginning. Every String which isn't
             * an argument, must be a path
             */
               argumentsRead = true;
               break;
         }
      }

      String dir;
      if (i == args.length) {
         dir = ".";
      } else {
         dir = args[i];
      }

      /*
       * list all files.
       */

      System.out.println("Listing " + dir);
      File f = new File(dir);
      if (!f.exists()) {
         System.out.println("...does not exist");
      } else {
         new FileSystem(f).accept(new DoList(f, recursive));
      }
   }

   private static class DoList implements FileVisitor {
      private StringBuffer indent = new StringBuffer();

      private boolean recursive;
      private File root;

      private DoList(File root, boolean recursive) {
         this.recursive = recursive;
         this.root = root;
      }

      @Override
      public FileVisitResult postVisitDirectory(File dir) {
         indent = indent.delete(indent.length() - 2, indent.length());
         return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult preVisitDirectory(File dir) {
         System.out.println(indent + "+ " + dir.getName());

         if (recursive || this.root.equals(dir)) {
            indent.append("| ");
            return FileVisitResult.CONTINUE;
         } else {
            return FileVisitResult.SKIP_SUBTREE;
         }
      }

      @Override
      public FileVisitResult visitFailed(File file) {
         System.out.print(indent);
         System.out.println(file.getName() + " (unreadable) ");
         return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(File file) {

         System.out.print(indent);

         System.out.println(file.getName());

         return FileVisitResult.CONTINUE;
      }
   }

}
