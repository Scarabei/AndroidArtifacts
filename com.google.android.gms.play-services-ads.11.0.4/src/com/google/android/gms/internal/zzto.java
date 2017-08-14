package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;

final class zzto {
   zzal zzKv;
   @Nullable
   zzir zzKw;
   zzsj zzKx;
   long zzKy;
   boolean zzKz;
   boolean zzKA;
   // $FF: synthetic field
   private zztn zzKB;

   zzto(zztn var1, zzsi var2) {
      this.zzKB = var1;
      super();
      this.zzKv = var2.zzX(zztn.zza(var1));
      this.zzKx = new zzsj();
      zzal var4 = this.zzKv;
      zzsj var3 = this.zzKx;
      var4.zza(new zzsk(var3));
      var4.zza(new zzss(var3));
      var4.zza(new zzsu(var3));
      var4.zza(new zzsw(var3));
      var4.zza(new zzsy(var3));
   }

   zzto(zztn var1, zzsi var2, zzir var3) {
      this(var1, var2);
      this.zzKw = var3;
   }

   final boolean load() {
      if (this.zzKz) {
         return false;
      } else {
         zzir var1 = zztl.zzj(this.zzKw != null ? this.zzKw : zztn.zzb(this.zzKB));
         this.zzKA = this.zzKv.zza(var1);
         this.zzKz = true;
         this.zzKy = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
         return true;
      }
   }
}
