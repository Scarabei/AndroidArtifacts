package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzv;

@zzzn
public final class zzsi {
   private final Context mContext;
   private final zzuq zzsX;
   private final zzaje zztW;
   private final zzv zzsS;

   zzsi(Context var1, zzuq var2, zzaje var3, zzv var4) {
      this.mContext = var1;
      this.zzsX = var2;
      this.zztW = var3;
      this.zzsS = var4;
   }

   public final zzal zzW(String var1) {
      return new zzal(this.mContext, new zziv(), var1, this.zzsX, this.zztW, this.zzsS);
   }

   public final zzal zzX(String var1) {
      return new zzal(this.mContext.getApplicationContext(), new zziv(), var1, this.zzsX, this.zztW, this.zzsS);
   }

   public final Context getApplicationContext() {
      return this.mContext.getApplicationContext();
   }

   public final zzsi zzeF() {
      return new zzsi(this.mContext.getApplicationContext(), this.zzsX, this.zztW, this.zzsS);
   }
}
