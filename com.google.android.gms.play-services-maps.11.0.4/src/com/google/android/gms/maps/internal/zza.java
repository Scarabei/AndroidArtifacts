package com.google.android.gms.maps.internal;

public final class zza {
   public static Boolean zza(byte var0) {
      switch(var0) {
      case 0:
         return Boolean.FALSE;
      case 1:
         return Boolean.TRUE;
      default:
         return null;
      }
   }

   public static byte zzb(Boolean var0) {
      if (var0 != null) {
         return (byte)(var0.booleanValue() ? 1 : 0);
      } else {
         return -1;
      }
   }
}
