package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@zzzn
public final class zzaew {
   private final AtomicReference zzXz = new AtomicReference((Object)null);
   private final Object zzXA = new Object();
   @Nullable
   private String zzXB = null;
   private AtomicBoolean zzXC = new AtomicBoolean(false);
   private final AtomicInteger zzXD = new AtomicInteger(-1);
   private final AtomicReference zzXE = new AtomicReference((Object)null);
   private final AtomicReference zzXF = new AtomicReference((Object)null);
   private ConcurrentMap zzXG = new ConcurrentHashMap(9);

   public final boolean zzp(Context var1) {
      zzme var2 = zzmo.zzDs;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && !this.zzXC.get()) {
         if (this.zzXD.get() == -1) {
            zzji.zzds();
            if (!zzaiy.zzX(var1)) {
               zzji.zzds();
               if (zzaiy.zzY(var1)) {
                  zzafr.zzaT("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                  this.zzXD.set(0);
                  return this.zzXD.get() == 1;
               }
            }

            this.zzXD.set(1);
         }

         return this.zzXD.get() == 1;
      } else {
         return false;
      }
   }

   public final boolean zzq(Context var1) {
      zzme var2 = zzmo.zzDt;
      return ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && this.zzp(var1);
   }

   public final boolean zzr(Context var1) {
      zzme var2 = zzmo.zzDu;
      return ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && this.zzp(var1);
   }

   public final boolean zzs(Context var1) {
      zzme var2 = zzmo.zzDv;
      return ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && this.zzp(var1);
   }

   public final void zzc(Context var1, String var2) {
      if (this.zzp(var1)) {
         this.zza(var1, var2, "beginAdUnitExposure");
      }
   }

   public final void zzd(Context var1, String var2) {
      if (this.zzp(var1)) {
         this.zza(var1, var2, "endAdUnitExposure");
      }
   }

   public final String zzt(Context var1) {
      if (!this.zzp(var1)) {
         return "";
      } else if (!this.zza(var1, "com.google.android.gms.measurement.AppMeasurement", this.zzXE, true)) {
         return "";
      } else {
         try {
            String var3;
            if ((var3 = (String)this.zzl(var1, "getCurrentScreenName").invoke(this.zzXE.get())) == null) {
               var3 = (String)this.zzl(var1, "getCurrentScreenClass").invoke(this.zzXE.get());
            }

            return var3 != null ? var3 : "";
         } catch (Exception var4) {
            this.zza(var4, "getCurrentScreenName", false);
            return "";
         }
      }
   }

   public final void zze(Context var1, String var2) {
      if (this.zzp(var1)) {
         if (var1 instanceof Activity) {
            if (this.zza(var1, "com.google.firebase.analytics.FirebaseAnalytics", this.zzXF, false)) {
               Method var3 = this.zzm(var1, "setCurrentScreen");

               try {
                  Activity var4 = (Activity)var1;
                  var3.invoke(this.zzXF.get(), var4, var2, var1.getPackageName());
               } catch (Exception var5) {
                  this.zza(var5, "setCurrentScreen", false);
               }
            }
         }
      }
   }

   @Nullable
   public final String zzu(Context var1) {
      if (!this.zzp(var1)) {
         return null;
      } else {
         Object var2 = this.zzXA;
         synchronized(this.zzXA) {
            if (this.zzXB != null) {
               return this.zzXB;
            } else {
               this.zzXB = (String)this.zza("getGmpAppId", var1);
               return this.zzXB;
            }
         }
      }
   }

   @Nullable
   public final String zzv(Context var1) {
      if (!this.zzp(var1)) {
         return null;
      } else {
         zzme var6 = zzmo.zzDB;
         long var2;
         if ((var2 = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).longValue()) < 0L) {
            return (String)this.zza("getAppInstanceId", var1);
         } else {
            if (this.zzXz.get() == null) {
               AtomicReference var10000 = this.zzXz;
               zzme var8 = zzmo.zzDC;
               int var10004 = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).intValue();
               var8 = zzmo.zzDC;
               var10000.compareAndSet((Object)null, new ThreadPoolExecutor(var10004, ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).intValue(), 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzaey(this)));
            }

            Future var4 = ((ThreadPoolExecutor)this.zzXz.get()).submit(new zzaex(this, var1));

            try {
               return (String)var4.get(var2, TimeUnit.MILLISECONDS);
            } catch (Exception var9) {
               var4.cancel(true);
               return var9 instanceof TimeoutException ? "TIME_OUT" : null;
            }
         }
      }
   }

   @Nullable
   public final String zzw(Context var1) {
      if (!this.zzp(var1)) {
         return null;
      } else {
         Object var2;
         return (var2 = this.zza("generateEventId", var1)) != null ? var2.toString() : null;
      }
   }

   public final void zzf(Context var1, String var2) {
      if (this.zzp(var1)) {
         Bundle var3;
         (var3 = zzj(var1, var2)).putInt("_r", 1);
         this.zza(var1, "_ac", var3);
      }
   }

   public final void zzg(Context var1, String var2) {
      if (this.zzp(var1)) {
         this.zza(var1, "_ai", zzj(var1, var2));
      }
   }

   public final void zzh(Context var1, String var2) {
      if (this.zzp(var1)) {
         this.zza(var1, "_aq", zzj(var1, var2));
      }
   }

   public final void zzi(Context var1, String var2) {
      if (this.zzp(var1)) {
         this.zza(var1, "_aa", zzj(var1, var2));
      }
   }

   private static Bundle zzj(Context var0, String var1) {
      Bundle var2;
      (var2 = new Bundle()).putString("_aeid", var1);
      return var2;
   }

   private final void zza(Context var1, String var2, Bundle var3) {
      if (this.zza(var1, "com.google.android.gms.measurement.AppMeasurement", this.zzXE, true)) {
         Method var4 = this.zzx(var1);

         try {
            var4.invoke(this.zzXE.get(), "am", var2, var3);
         } catch (Exception var6) {
            this.zza(var6, "logEventInternal", true);
         }
      }
   }

   private final Method zzx(Context var1) {
      Method var2;
      if ((var2 = (Method)this.zzXG.get("logEventInternal")) != null) {
         return var2;
      } else {
         try {
            var2 = var1.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
            this.zzXG.put("logEventInternal", var2);
            return var2;
         } catch (Exception var4) {
            this.zza(var4, "logEventInternal", true);
            return null;
         }
      }
   }

   private final Method zzk(Context var1, String var2) {
      Method var3;
      if ((var3 = (Method)this.zzXG.get(var2)) != null) {
         return var3;
      } else {
         try {
            var3 = var1.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(var2, String.class);
            this.zzXG.put(var2, var3);
            return var3;
         } catch (Exception var5) {
            this.zza(var5, var2, false);
            return null;
         }
      }
   }

   private final Method zzl(Context var1, String var2) {
      Method var3;
      if ((var3 = (Method)this.zzXG.get(var2)) != null) {
         return var3;
      } else {
         try {
            var3 = var1.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(var2);
            this.zzXG.put(var2, var3);
            return var3;
         } catch (Exception var5) {
            this.zza(var5, var2, false);
            return null;
         }
      }
   }

   private final Method zzm(Context var1, String var2) {
      Method var3;
      if ((var3 = (Method)this.zzXG.get(var2)) != null) {
         return var3;
      } else {
         try {
            var3 = var1.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(var2, Activity.class, String.class, String.class);
            this.zzXG.put(var2, var3);
            return var3;
         } catch (Exception var5) {
            this.zza(var5, var2, false);
            return null;
         }
      }
   }

   private final void zza(Context var1, String var2, String var3) {
      if (this.zza(var1, "com.google.android.gms.measurement.AppMeasurement", this.zzXE, true)) {
         Method var4 = this.zzk(var1, var3);

         try {
            var4.invoke(this.zzXE.get(), var2);
            String var10001 = String.valueOf(var3);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Invoke Firebase method ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Invoke Firebase method ");
            }

            zzafr.v(var10000);
         } catch (Exception var6) {
            this.zza(var6, var3, false);
         }
      }
   }

   private final Object zza(String var1, Context var2) {
      Object var3 = null;
      if (!this.zza(var2, "com.google.android.gms.measurement.AppMeasurement", this.zzXE, true)) {
         return null;
      } else {
         Method var4 = this.zzl(var2, var1);

         try {
            var3 = var4.invoke(this.zzXE.get());
         } catch (Exception var6) {
            this.zza(var6, var1, true);
         }

         return var3;
      }
   }

   private final void zza(Exception var1, String var2, boolean var3) {
      if (!this.zzXC.get()) {
         zzafr.zzc((new StringBuilder(30 + String.valueOf(var2).length())).append("Invoke Firebase method ").append(var2).append(" error.").toString(), var1);
         if (var3) {
            zzafr.zzaT("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firbase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
            this.zzXC.set(true);
         }
      }

   }

   private final boolean zza(Context var1, String var2, AtomicReference var3, boolean var4) {
      if (var3.get() == null) {
         try {
            Method var6 = var1.getClassLoader().loadClass(var2).getDeclaredMethod("getInstance", Context.class);
            var3.compareAndSet((Object)null, var6.invoke((Object)null, var1));
         } catch (Exception var7) {
            this.zza(var7, "getInstance", var4);
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   final String zzy(Context var1) throws Exception {
      return (String)this.zza("getAppInstanceId", var1);
   }
}
