package android.support.v4.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.io.Writer;

@RestrictTo({Scope.LIBRARY_GROUP})
public class LogWriter extends Writer {
   private final String mTag;
   private StringBuilder mBuilder = new StringBuilder(128);

   public LogWriter(String tag) {
      this.mTag = tag;
   }

   public void close() {
      this.flushBuilder();
   }

   public void flush() {
      this.flushBuilder();
   }

   public void write(char[] buf, int offset, int count) {
      for(int i = 0; i < count; ++i) {
         char c = buf[offset + i];
         if (c == '\n') {
            this.flushBuilder();
         } else {
            this.mBuilder.append(c);
         }
      }

   }

   private void flushBuilder() {
      if (this.mBuilder.length() > 0) {
         Log.d(this.mTag, this.mBuilder.toString());
         this.mBuilder.delete(0, this.mBuilder.length());
      }

   }
}
