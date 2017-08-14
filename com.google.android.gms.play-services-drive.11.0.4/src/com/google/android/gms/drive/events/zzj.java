package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;

public final class zzj {
   public static boolean zza(int var0, DriveId var1) {
      switch(var0) {
      case 1:
      case 8:
         if (var1 != null) {
            return true;
         }

         return false;
      case 2:
      case 3:
      case 5:
      case 6:
      default:
         return false;
      case 4:
      case 7:
         return var1 == null;
      }
   }
}
