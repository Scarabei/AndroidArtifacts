package android.support.test.internal.util;

import android.os.Looper;

public final class Checks {
   public static Object checkNotNull(Object reference) {
      if (reference == null) {
         throw new NullPointerException();
      } else {
         return reference;
      }
   }

   public static Object checkNotNull(Object reference, Object errorMessage) {
      if (reference == null) {
         throw new NullPointerException(String.valueOf(errorMessage));
      } else {
         return reference;
      }
   }

   public static Object checkNotNull(Object reference, String errorMessageTemplate, Object... errorMessageArgs) {
      if (reference == null) {
         throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
      } else {
         return reference;
      }
   }

   public static void checkState(boolean expression) {
      if (!expression) {
         throw new IllegalStateException();
      }
   }

   public static void checkState(boolean expression, Object errorMessage) {
      if (!expression) {
         throw new IllegalStateException(String.valueOf(errorMessage));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
      }
   }

   public static void checkMainThread() {
      checkState(Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called off the main application thread (on: %s)", Thread.currentThread().getName());
   }

   public static void checkNotMainThread() {
      checkState(!Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called on the main application thread (on: %s)", Thread.currentThread().getName());
   }

   private static String format(String template, Object... args) {
      template = String.valueOf(template);
      StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
      int templateStart = 0;

      int i;
      int placeholderStart;
      for(i = 0; i < args.length; templateStart = placeholderStart + 2) {
         placeholderStart = template.indexOf("%s", templateStart);
         if (placeholderStart == -1) {
            break;
         }

         builder.append(template.substring(templateStart, placeholderStart));
         builder.append(args[i++]);
      }

      builder.append(template.substring(templateStart));
      if (i < args.length) {
         builder.append(" [");
         builder.append(args[i++]);

         while(i < args.length) {
            builder.append(", ");
            builder.append(args[i++]);
         }

         builder.append(']');
      }

      return builder.toString();
   }
}
