package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbdw;
import java.util.List;

public final class zzga extends zzdl {
   private zzbdw zzbTs;
   private zzbdw zzbTt;
   private zzbdw zzbTu;
   private zzbdw zzbTv;
   private zzbdw zzbTw;
   private zzbdw zzbTx;
   private zzbdw zzbTy;
   private zzbdw zzbTz;
   private final IntentFilter[] zzbSW;
   private final String zzbTA;

   public static zzga zza(zzbdw var0, IntentFilter[] var1) {
      zzga var2;
      (var2 = new zzga(var1, (String)null)).zzbTu = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var2;
   }

   public static zzga zzb(zzbdw var0, IntentFilter[] var1) {
      zzga var2;
      (var2 = new zzga(var1, (String)null)).zzbTv = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var2;
   }

   public static zzga zzc(zzbdw var0, IntentFilter[] var1) {
      zzga var2;
      (var2 = new zzga(var1, (String)null)).zzbTw = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var2;
   }

   public static zzga zzd(zzbdw var0, IntentFilter[] var1) {
      zzga var2;
      (var2 = new zzga(var1, (String)null)).zzbTy = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var2;
   }

   public static zzga zza(zzbdw var0, String var1, IntentFilter[] var2) {
      zzga var3;
      (var3 = new zzga(var2, (String)com.google.android.gms.common.internal.zzbo.zzu(var1))).zzbTy = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var3;
   }

   public static zzga zze(zzbdw var0, IntentFilter[] var1) {
      zzga var2;
      (var2 = new zzga(var1, (String)null)).zzbTz = (zzbdw)com.google.android.gms.common.internal.zzbo.zzu(var0);
      return var2;
   }

   private zzga(IntentFilter[] var1, String var2) {
      this.zzbSW = (IntentFilter[])com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbTA = var2;
   }

   public final void clear() {
      zzk((zzbdw)null);
      this.zzbTs = null;
      zzk((zzbdw)null);
      this.zzbTt = null;
      zzk(this.zzbTu);
      this.zzbTu = null;
      zzk(this.zzbTv);
      this.zzbTv = null;
      zzk(this.zzbTw);
      this.zzbTw = null;
      zzk((zzbdw)null);
      this.zzbTx = null;
      zzk(this.zzbTy);
      this.zzbTy = null;
      zzk(this.zzbTz);
      this.zzbTz = null;
   }

   public final void zza(zzl var1) {
   }

   public final void zza(zzi var1) {
   }

   public final void zzS(DataHolder var1) {
      if (this.zzbTu != null) {
         this.zzbTu.zza(new zzgb(var1));
      } else {
         var1.close();
      }
   }

   public final void zza(zzdx var1) {
      if (this.zzbTv != null) {
         this.zzbTv.zza(new zzgc(var1));
      }

   }

   public final void zza(zzeg var1) {
      if (this.zzbTw != null) {
         this.zzbTw.zza(new zzgd(var1));
      }

   }

   public final void zzb(zzeg var1) {
      if (this.zzbTw != null) {
         this.zzbTw.zza(new zzge(var1));
      }

   }

   public final void onConnectedNodes(List var1) {
   }

   public final void zza(zzai var1) {
      if (this.zzbTy != null) {
         this.zzbTy.zza(new zzgf(var1));
      }

   }

   public final void zza(zzaa var1) {
      if (this.zzbTz != null) {
         this.zzbTz.zza(new zzgg(var1));
      }

   }

   public final IntentFilter[] zzDY() {
      return this.zzbSW;
   }

   public final String zzDZ() {
      return this.zzbTA;
   }

   private static void zzk(zzbdw var0) {
      if (var0 != null) {
         var0.clear();
      }

   }
}
