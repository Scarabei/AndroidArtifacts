package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;

public final class zzctz {
   private static String TAG = "WakeLock";
   private static String zzbCW = "*gcore*:";
   private static boolean DEBUG = false;
   private final WakeLock zzbCX;
   private WorkSource zzbCY;
   private final int zzbCZ;
   private final String zzaJp;
   private final String zzbDa;
   private final String zzaJr;
   private final Context mContext;
   private boolean zzbDb;
   private int zzbDc;
   private int zzbDd;

   @SuppressLint({"UnwrappedWakeLock"})
   private zzctz(Context var1, int var2, String var3, String var4, String var5) {
      this(var1, 1, var3, (String)null, var5, (String)null);
   }

   @SuppressLint({"UnwrappedWakeLock"})
   private zzctz(Context var1, int var2, String var3, String var4, String var5, String var6) {
      this.zzbDb = true;
      zzbo.zzh(var3, "Wake lock name can NOT be empty");
      this.zzbCZ = var2;
      this.zzbDa = null;
      this.zzaJr = null;
      this.mContext = var1.getApplicationContext();
      if (!"com.google.android.gms".equals(var1.getPackageName())) {
         String var10001 = String.valueOf(zzbCW);
         String var10002 = String.valueOf(var3);
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            String var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }

         this.zzaJp = var10001;
      } else {
         this.zzaJp = var3;
      }

      this.zzbCX = ((PowerManager)var1.getSystemService("power")).newWakeLock(var2, var3);
      if (com.google.android.gms.common.util.zzx.zzaM(this.mContext)) {
         String var7;
         if (com.google.android.gms.common.util.zzt.zzcL(var5)) {
            var7 = var1.getPackageName();
         } else {
            var7 = var5;
         }

         this.zzbCY = com.google.android.gms.common.util.zzx.zzD(var1, var7);
         WorkSource var9 = this.zzbCY;
         if (var9 != null && com.google.android.gms.common.util.zzx.zzaM(this.mContext)) {
            if (this.zzbCY != null) {
               this.zzbCY.add(var9);
            } else {
               this.zzbCY = var9;
            }

            WorkSource var11 = this.zzbCY;
            zzctz var10 = this;

            try {
               var10.zzbCX.setWorkSource(var11);
               return;
            } catch (IllegalArgumentException var13) {
               Log.wtf(TAG, var13.toString());
            }
         }
      }

   }

   public zzctz(Context var1, int var2, String var3) {
      this(var1, 1, var3, (String)null, var1 == null ? null : var1.getPackageName());
   }

   public final void acquire(long var1) {
      zzctz var3 = this;
      boolean var4 = this.zzeV((String)null);
      String var5 = this.zzi((String)null, var4);
      synchronized(this) {
         if (var3.zzbDb && (var3.zzbDc++ == 0 || var4) || !var3.zzbDb && var3.zzbDd == 0) {
            com.google.android.gms.common.stats.zze.zzrX();
            com.google.android.gms.common.stats.zze.zza(var3.mContext, com.google.android.gms.common.stats.zzc.zza(var3.zzbCX, var5), 7, var3.zzaJp, var5, (String)null, var3.zzbCZ, com.google.android.gms.common.util.zzx.zzb(var3.zzbCY), 1000L);
            ++var3.zzbDd;
         }
      }

      this.zzbCX.acquire(1000L);
   }

   public final void release() {
      zzctz var1 = this;
      boolean var2 = this.zzeV((String)null);
      String var3 = this.zzi((String)null, var2);
      synchronized(this) {
         if (var1.zzbDb && (--var1.zzbDc == 0 || var2) || !var1.zzbDb && var1.zzbDd == 1) {
            com.google.android.gms.common.stats.zze.zzrX();
            com.google.android.gms.common.stats.zze.zza(var1.mContext, com.google.android.gms.common.stats.zzc.zza(var1.zzbCX, var3), 8, var1.zzaJp, var3, (String)null, var1.zzbCZ, com.google.android.gms.common.util.zzx.zzb(var1.zzbCY));
            --var1.zzbDd;
         }
      }

      this.zzbCX.release();
   }

   private final boolean zzeV(String var1) {
      return !TextUtils.isEmpty((CharSequence)null) && !null.equals(this.zzbDa);
   }

   private final String zzi(String var1, boolean var2) {
      if (this.zzbDb) {
         return var2 ? null : this.zzbDa;
      } else {
         return this.zzbDa;
      }
   }

   public final void setReferenceCounted(boolean var1) {
      this.zzbCX.setReferenceCounted(false);
      this.zzbDb = false;
   }

   public final boolean isHeld() {
      return this.zzbCX.isHeld();
   }
}
