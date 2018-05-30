package com.tutorialspoint;

import java.lang.*;

public class ClassDemo {
   // constructor
   public ClassDemo() {

      // class Outer as inner class for class ClassDemo
      class Outer {

         public void show() {
            // inner class of Class Outer
            class Inner {

               public void show() {
                  System.out.print("1 "+getClass().getName() + " inner in...");
                  System.out.println("2 "+getClass().getEnclosingClass());    
               }
            }
            System.out.print("3 "+getClass().getName() + " inner in...");
            System.out.println("4 "+getClass().getEnclosingClass());

            // inner class show() function
            Inner i = new Inner();
            i.show();
         }
      }

      // outer class show() function
      Outer o = new Outer();
      o.show();
   }

   public static void main(String[] args) {
      ClassDemo cls = new ClassDemo();
   }
}