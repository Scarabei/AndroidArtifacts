package android.support.v4.os;

import android.os.Trace;
import android.os.Build.VERSION;

public final class TraceCompat {
   public static void beginSection(String sectionName) {
      if (VERSION.SDK_INT >= 18) {
         Trace.beginSection(sectionName);
      }

   }

   public static void endSection() {
      if (VERSION.SDK_INT >= 18) {
         Trace.endSection();
      }

   }
}
