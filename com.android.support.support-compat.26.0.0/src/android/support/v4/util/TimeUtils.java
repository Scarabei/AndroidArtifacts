package android.support.v4.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.io.PrintWriter;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class TimeUtils {
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final int HUNDRED_DAY_FIELD_LEN = 19;
   private static final int SECONDS_PER_MINUTE = 60;
   private static final int SECONDS_PER_HOUR = 3600;
   private static final int SECONDS_PER_DAY = 86400;
   private static final Object sFormatSync = new Object();
   private static char[] sFormatStr = new char[24];

   private static int accumField(int amt, int suffix, boolean always, int zeropad) {
      if (amt <= 99 && (!always || zeropad < 3)) {
         if (amt > 9 || always && zeropad >= 2) {
            return 2 + suffix;
         } else {
            return !always && amt <= 0 ? 0 : 1 + suffix;
         }
      } else {
         return 3 + suffix;
      }
   }

   private static int printField(char[] formatStr, int amt, char suffix, int pos, boolean always, int zeropad) {
      if (always || amt > 0) {
         int dig;
         if (always && zeropad >= 3 || amt > 99) {
            dig = amt / 100;
            formatStr[pos] = (char)(dig + 48);
            ++pos;
            amt -= dig * 100;
         }

         if (always && zeropad >= 2 || amt > 9 || pos != pos) {
            dig = amt / 10;
            formatStr[pos] = (char)(dig + 48);
            ++pos;
            amt -= dig * 10;
         }

         formatStr[pos] = (char)(amt + 48);
         ++pos;
         formatStr[pos] = suffix;
         ++pos;
      }

      return pos;
   }

   private static int formatDurationLocked(long duration, int fieldLen) {
      if (sFormatStr.length < fieldLen) {
         sFormatStr = new char[fieldLen];
      }

      char[] formatStr = sFormatStr;
      byte prefix;
      if (duration == 0L) {
         prefix = 0;
         --fieldLen;

         while(prefix < fieldLen) {
            formatStr[prefix] = ' ';
         }

         formatStr[prefix] = '0';
         return prefix + 1;
      } else {
         if (duration > 0L) {
            prefix = 43;
         } else {
            prefix = 45;
            duration = -duration;
         }

         int millis = (int)(duration % 1000L);
         int seconds = (int)Math.floor((double)(duration / 1000L));
         int days = 0;
         int hours = 0;
         int minutes = 0;
         if (seconds > 86400) {
            days = seconds / 86400;
            seconds -= days * 86400;
         }

         if (seconds > 3600) {
            hours = seconds / 3600;
            seconds -= hours * 3600;
         }

         if (seconds > 60) {
            minutes = seconds / 60;
            seconds -= minutes * 60;
         }

         int pos = 0;
         int myLen;
         if (fieldLen != 0) {
            myLen = accumField(days, 1, false, 0);
            myLen += accumField(hours, 1, myLen > 0, 2);
            myLen += accumField(minutes, 1, myLen > 0, 2);
            myLen += accumField(seconds, 1, myLen > 0, 2);

            for(myLen += accumField(millis, 2, true, myLen > 0 ? 3 : 0) + 1; myLen < fieldLen; ++myLen) {
               formatStr[pos] = ' ';
               ++pos;
            }
         }

         formatStr[pos] = (char)prefix;
         ++pos;
         myLen = pos;
         boolean zeropad = fieldLen != 0;
         pos = printField(formatStr, days, 'd', pos, false, 0);
         pos = printField(formatStr, hours, 'h', pos, pos != myLen, zeropad ? 2 : 0);
         pos = printField(formatStr, minutes, 'm', pos, pos != myLen, zeropad ? 2 : 0);
         pos = printField(formatStr, seconds, 's', pos, pos != myLen, zeropad ? 2 : 0);
         pos = printField(formatStr, millis, 'm', pos, true, zeropad && pos != myLen ? 3 : 0);
         formatStr[pos] = 's';
         return pos + 1;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void formatDuration(long duration, StringBuilder builder) {
      Object var3 = sFormatSync;
      synchronized(sFormatSync) {
         int len = formatDurationLocked(duration, 0);
         builder.append(sFormatStr, 0, len);
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void formatDuration(long duration, PrintWriter pw, int fieldLen) {
      Object var4 = sFormatSync;
      synchronized(sFormatSync) {
         int len = formatDurationLocked(duration, fieldLen);
         pw.print(new String(sFormatStr, 0, len));
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void formatDuration(long duration, PrintWriter pw) {
      formatDuration(duration, pw, 0);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void formatDuration(long time, long now, PrintWriter pw) {
      if (time == 0L) {
         pw.print("--");
      } else {
         formatDuration(time - now, pw, 0);
      }
   }
}
