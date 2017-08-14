package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzahq;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzax extends zzko {
   private final Context mContext;
   private static final Object zzuF = new Object();
   @Nullable
   private static zzax zzuG;
   private final Object mLock = new Object();
   private boolean zzuH;
   private boolean zzuI;
   private float zzuJ = -1.0F;
   private zzaje zzuK;

   public static zzax zza(Context var0, zzaje var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         if (zzuG == null) {
            zzuG = new zzax(var0.getApplicationContext(), var1);
         }

         return zzuG;
      }
   }

   @Nullable
   public static zzax zzbe() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         return zzuG;
      }
   }

   private zzax(Context var1, zzaje var2) {
      this.mContext = var1;
      this.zzuK = var2;
      this.zzuH = false;
   }

   public final void initialize() {
      Object var1 = zzuF;
      synchronized(zzuF) {
         if (this.zzuH) {
            zzafr.zzaT("Mobile ads is initialized already.");
            return;
         }

         this.zzuH = true;
      }

      zzmo.initialize(this.mContext);
      zzbs.zzbD().zzd(this.mContext, this.zzuK);
      zzbs.zzbE().initialize(this.mContext);
   }

   public final void zzu(String var1) {
      zzmo.initialize(this.mContext);
      if (!TextUtils.isEmpty(var1)) {
         zzme var2 = zzmo.zzFO;
         if (((Boolean)zzbs.zzbL().zzd(var2)).booleanValue()) {
            zzbs.zzbV().zza(this.mContext, this.zzuK, var1, (Runnable)null);
         }
      }

   }

   public final void zzc(String var1, IObjectWrapper var2) {
      if (!TextUtils.isEmpty(var1)) {
         zzmo.initialize(this.mContext);
         zzme var6 = zzmo.zzFO;
         boolean var10000 = ((Boolean)zzbs.zzbL().zzd(var6)).booleanValue();
         var6 = zzmo.zzDH;
         boolean var3 = var10000 | ((Boolean)zzbs.zzbL().zzd(var6)).booleanValue();
         zzay var4 = null;
         var6 = zzmo.zzDH;
         if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
            var3 = true;
            Runnable var5 = (Runnable)com.google.android.gms.dynamic.zzn.zzE(var2);
            var4 = new zzay(this, var5);
         }

         if (var3) {
            zzbs.zzbV().zza(this.mContext, this.zzuK, var1, var4);
         }

      }
   }

   public final void setAppVolume(float var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzuJ = var1;
      }
   }

   public final float zzbf() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzuJ;
      }
   }

   public final boolean zzbg() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzuJ >= 0.0F;
      }
   }

   public final void setAppMuted(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzuI = var1;
      }
   }

   public final boolean zzbh() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzuI;
      }
   }

   public final void zzb(IObjectWrapper var1, String var2) {
      if (var1 == null) {
         zzafr.e("Wrapped context is null. Failed to open debug menu.");
      } else {
         Context var3;
         if ((var3 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1)) == null) {
            zzafr.e("Context is null. Failed to open debug menu.");
         } else {
            zzahq var4;
            (var4 = new zzahq(var3)).setAdUnitId(var2);
            var4.zzaO(this.zzuK.zzaP);
            var4.showDialog();
         }
      }
   }

   // $FF: synthetic method
   static Context zza(zzax var0) {
      return var0.mContext;
   }
}
