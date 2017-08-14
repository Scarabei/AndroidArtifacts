package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzbio {
   private final aci zzaKP;

   public static zzbio zzaJ(int var0) {
      return new zzbio(zza(1, 0L, var0));
   }

   public static zzbio zzsC() {
      return new zzbio(zza(2, 3000L, 0));
   }

   public static zzbio zzsD() {
      return new zzbio(zza(3, 3000L, 0));
   }

   private static aci zza(int var0, long var1, int var3) {
      aci var4;
      (var4 = new aci()).zzcqq = var0;
      var4.zzcqr = var1;
      switch(var0) {
      case 1:
      case 2:
      case 3:
         var4.zzcqt = var3;
         break;
      case 4:
      case 5:
      case 6:
         var4.zzcqu = var3;
         break;
      case 7:
      case 8:
      case 9:
         var4.zzcqv = var3;
         break;
      case 10:
      case 11:
      case 12:
         var4.zzcqw = var3;
         break;
      case 13:
      case 14:
      case 15:
         var4.zzcqx = var3;
         break;
      case 16:
      case 17:
      case 18:
         var4.zzcqy = var3;
         break;
      default:
         zzeq.zza("AudioStateFenceStub", "Unknown trigger type=%s", (Object)var0);
      }

      return var4;
   }

   private zzbio(aci var1) {
      this.zzaKP = (aci)zzbo.zzu(var1);
   }

   public final aci zzsE() {
      return this.zzaKP;
   }
}
