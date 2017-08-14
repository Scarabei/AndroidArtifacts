package com.google.android.gms.tagmanager;

final class zzcb implements Runnable {
   // $FF: synthetic field
   private zzbz zzbEU;
   // $FF: synthetic field
   private long zzbEV;
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private zzca zzbEW;

   zzcb(zzca var1, zzbz var2, long var3, String var5) {
      this.zzbEW = var1;
      this.zzbEU = var2;
      this.zzbEV = var3;
      this.zzsD = var5;
      super();
   }

   public final void run() {
      if (zzca.zza(this.zzbEW) == null) {
         zzfo var1;
         (var1 = zzfo.zzBV()).zza(zzca.zzb(this.zzbEW), this.zzbEU);
         zzca.zza(this.zzbEW, var1.zzBW());
      }

      zzca.zza(this.zzbEW).zzb(this.zzbEV, this.zzsD);
   }
}
