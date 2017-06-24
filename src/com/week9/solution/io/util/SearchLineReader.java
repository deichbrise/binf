package com.week9.solution.io.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Extends the LineNumberReader by searching for matches with a given regular
 * expression and counting them while reading.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class SearchLineReader extends LineNumberReader {

   /**
    * {@code Pattern} to search matches for
    */
   private final Pattern pattern;

   /**
    * total amount of currently found matches with {@code pattern}
    */
   private int count;

   /**
    * @param in     {@code Reader} to be wrapped by this {@code
    *               SearchLineReader}
    * @param search regular expression to search for
    * @throws PatternSyntaxException if {@code search} is not a valid regular
    *                                expression.
    */
   public SearchLineReader(Reader in, String search) {
      super(in);
      this.pattern = Pattern.compile(search);
      this.count = 0;
   }

   /**
    * Works as {@link LineNumberReader#readLine()} with the additional function
    * that with every execution the number of matches of the given search
    * pattern in the last read line is computed and may be accessed via {@link
    * #getAmountOfMatches()}.
    *
    * @throws IOException if an IO error occurs
    */
   @Override
   public String readLine() throws IOException {

      String line = super.readLine();
      count = 0;

      if (line != null) {
         Matcher m = this.pattern.matcher(line);
         while (m.find()) {
            count++;
         }
      }
      return line;
   }

   /**
    * Get the number of matches with the given regular expression in the latest
    * read line via {@link #readLine()}.
    *
    * @return number of already found matches in the last call of {@link
    * #readLine()} with the given regular expression of this {@code
    * SearchLineReader}
    */
   public int getAmountOfMatches() {
      return this.count;
   }

}
