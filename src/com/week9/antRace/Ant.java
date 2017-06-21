package com.week9.antRace;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Ant implements Runnable {
   AntField fields;
   int x;
   int y;
   int stepCount;
   boolean changedPosition;

   /**
    * Constructor updates value of this ant's first position
    *
    * @param fields
    *           the {@code AntField} on which this {@code Ant} operates
    * @param x
    *           x-axis value of the starting position
    * @param y
    *           y-axis value of the starting position
    * @param stepCount
    *           initial stepCount of this {@code Ant}.
    * 
    * @throws IllegalArgumentException
    *            If the {@code Field} at position {@code x,y} does not exist, or
    *            if its value is < 0
    */
   public Ant(AntField fields, int x, int y, int stepCount) {
      this.fields = fields;
      this.x = x;
      this.y = y;
      this.stepCount = stepCount;
      fields.getField(x, y).setValue(stepCount);
   }

   /**
    * Is started in AntRace.java
    */
   public void run() {
      visit(x, y, stepCount);
   }

   /**
    * Checks each neighbouring field for possible update
    * @param x current x
    * @param y current y
    * @param stepCount current stepCount
    */
   private void visit (int x, int y, int stepCount) {
         changedPosition = false;
         if (neighbour(x + 1, y, stepCount)) newPath(x + 1, y, stepCount);
         if (neighbour(x + 1, y + 1, stepCount)) newPath(x + 1, y + 1, stepCount);
         if (neighbour(x, y + 1, stepCount)) newPath(x, y + 1, stepCount);
         if (neighbour(x - 1, y + 1, stepCount)) newPath(x - 1, y + 1, stepCount);
         if (neighbour(x - 1, y, stepCount)) newPath(x - 1, y, stepCount);
         if (neighbour(x - 1, y - 1, stepCount)) newPath(x - 1, y - 1, stepCount);
         if (neighbour(x, y - 1, stepCount)) newPath(x, y - 1, stepCount);
         if (neighbour(x + 1, y - 1, stepCount)) newPath(x + 1, y - 1, stepCount);
   }

   /**
    * Checks whether regarded field can be updated
    * Synchronized blocks to prevent lost updates caused by interfering Threads
    * @param nextX
    * @param nextY
    * @param stepCount
    * @return true if field can be updated
    */
   private boolean neighbour(int nextX, int nextY, int stepCount) {
      int currentCount;
      AntField.Field newField;
        synchronized (fields) {
           newField = fields.getField(nextX, nextY);
        }
         if (newField == null) return false;
        synchronized (fields) {
           currentCount = newField.getValue();
        }
         if (currentCount == 0) return true;
         if (currentCount > stepCount + 1) return true;

      return false;
   }

   /**
    * Makes this Ant visit and update new field
    * or sends new Ant to visit and update new field
    * Synchronized blocks to prevent lost updates caused by interfering Threads
    * @param nextX
    * @param nextY
    * @param stepCount
    */
   private void newPath(int nextX, int nextY, int stepCount) {
      if (changedPosition) {
         Ant newOne = new Ant(fields, nextX, nextY, stepCount + 1);
         new Thread(newOne).start();
      }
      else {
         x = nextX;
         y = nextY;
         synchronized (fields) {
            //Avoid unwanted but possible exceptions
            if (neighbour(nextX, nextY, stepCount))
            fields.getField(x, y).setValue(++this.stepCount);
         }
         visit(x, y, this.stepCount);
         changedPosition = true;
      }
   }

}
