package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzew extends zzez {
   private final zzct zzsi;
   private final zzcu zzsj;
   private final zzcr zzsk;
   private boolean zzsl = false;

   public zzew(String var1, Context var2, boolean var3) {
      this.zzsi = zzct.zza(var1, var2, var3);
      this.zzsj = new zzcu(this.zzsi);
      this.zzsk = var3 ? null : zzcr.zzb(var2);
   }

   public final String zzaf() {
      return "ms";
   }

   public final void zzb(String var1, String var2) {
      this.zzsj.zzb(var1, var2);
   }

   public final boolean zza(IObjectWrapper var1) {
      Uri var2 = (Uri)zzn.zzE(var1);
      return this.zzsj.zza(var2);
   }

   public final boolean zzb(IObjectWrapper var1) {
      Uri var2 = (Uri)zzn.zzE(var1);
      return this.zzsj.zzc(var2);
   }

   public final void zzk(String var1) {
      this.zzsj.zzk(var1);
   }

   public final IObjectWrapper zza(IObjectWrapper var1, IObjectWrapper var2) {
      return this.zza(var1, var2, true);
   }

   public final String zzc(IObjectWrapper var1) {
      return this.zza(var1, (byte[])null);
   }

   public final String zza(IObjectWrapper var1, byte[] var2) {
      Context var3 = (Context)zzn.zzE(var1);
      String var4 = this.zzsi.zza(var3, var2);
      if (this.zzsk != null && this.zzsl) {
         String var5 = this.zzsk.zza((Context)var3, (byte[])var2);
         var4 = zzcr.zza(var4, var5);
         this.zzsl = false;
      }

      return var4;
   }

   public final String zza(IObjectWrapper var1, String var2) {
      Context var3 = (Context)zzn.zzE(var1);
      return this.zzsi.zza(var3, var2);
   }

   public final void zzd(IObjectWrapper var1) {
      MotionEvent var2 = (MotionEvent)zzn.zzE(var1);
      this.zzsj.zza(var2);
   }

   public final IObjectWrapper zzb(IObjectWrapper var1, IObjectWrapper var2) {
      return this.zza(var1, var2, false);
   }

   public final boolean zzb(String var1, boolean var2) {
      if (this.zzsk == null) {
         return false;
      } else {
         Info var3 = new Info(var1, var2);
         this.zzsk.zza(var3);
         this.zzsl = true;
         return true;
      }
   }

   private final IObjectWrapper zza(IObjectWrapper var1, IObjectWrapper var2, boolean var3) {
      try {
         Uri var4 = (Uri)zzn.zzE(var1);
         Context var5 = (Context)zzn.zzE(var2);
         return zzn.zzw(var3 ? this.zzsj.zza(var4, var5) : this.zzsj.zza(var4, var5, (View)null));
      } catch (zzcv var8) {
         return null;
      }
   }
}
