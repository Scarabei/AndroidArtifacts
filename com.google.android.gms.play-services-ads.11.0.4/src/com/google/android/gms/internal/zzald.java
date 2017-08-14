package com.google.android.gms.internal;

import android.webkit.ConsoleMessage.MessageLevel;

// $FF: synthetic class
final class zzald {
   // $FF: synthetic field
   static final int[] zzacK = new int[MessageLevel.values().length];

   static {
      try {
         zzacK[MessageLevel.ERROR.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
         ;
      }

      try {
         zzacK[MessageLevel.WARNING.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         zzacK[MessageLevel.LOG.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         zzacK[MessageLevel.TIP.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
         ;
      }

      try {
         zzacK[MessageLevel.DEBUG.ordinal()] = 5;
      } catch (NoSuchFieldError var0) {
         ;
      }
   }
}
