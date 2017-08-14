package android.support.v4.util;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.util.Objects;

public class ObjectsCompat {
   private static final ObjectsCompat.ImplBase IMPL;

   public static boolean equals(Object a, Object b) {
      return IMPL.equals(a, b);
   }

   static {
      if (VERSION.SDK_INT >= 19) {
         IMPL = new ObjectsCompat.ImplApi19();
      } else {
         IMPL = new ObjectsCompat.ImplBase();
      }

   }

   @RequiresApi(19)
   private static class ImplApi19 extends ObjectsCompat.ImplBase {
      private ImplApi19() {
         super(null);
      }

      public boolean equals(Object a, Object b) {
         return Objects.equals(a, b);
      }

      // $FF: synthetic method
      ImplApi19(Object x0) {
         this();
      }
   }

   private static class ImplBase {
      private ImplBase() {
      }

      public boolean equals(Object a, Object b) {
         return a == b || a != null && a.equals(b);
      }

      // $FF: synthetic method
      ImplBase(Object x0) {
         this();
      }
   }
}
