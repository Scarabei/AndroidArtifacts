package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.lang.Thread.UncaughtExceptionHandler;

public class zzamj {
   private static volatile zzamj zzafM;
   private final Context mContext;
   private final Context zzafN;
   private final zze zzvw;
   private final zzank zzafO;
   private final zzaoc zzafP;
   private final zzl zzafQ;
   private final zzaly zzafR;
   private final zzanp zzafS;
   private final zzaot zzafT;
   private final zzaog zzafU;
   private final GoogleAnalytics zzafV;
   private final zzanb zzafW;
   private final zzalx zzafX;
   private final zzamu zzafY;
   private final zzano zzafZ;

   private zzamj(zzaml var1) {
      Context var2;
      zzbo.zzb(var2 = var1.getApplicationContext(), "Application context can't be null");
      Context var3;
      zzbo.zzu(var3 = var1.zzkE());
      this.mContext = var2;
      this.zzafN = var3;
      this.zzvw = zzi.zzrY();
      this.zzafO = new zzank(this);
      zzaoc var4;
      (var4 = new zzaoc(this)).initialize();
      this.zzafP = var4;
      zzaoc var10000 = this.zzkr();
      String var5 = zzami.VERSION;
      var10000.zzbq((new StringBuilder(134 + String.valueOf(var5).length())).append("Google Analytics ").append(var5).append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4").toString());
      zzaog var17;
      (var17 = new zzaog(this)).initialize();
      this.zzafU = var17;
      zzaot var6;
      (var6 = new zzaot(this)).initialize();
      this.zzafT = var6;
      zzaly var7 = new zzaly(this, var1);
      zzanb var8 = new zzanb(this);
      zzalx var9 = new zzalx(this);
      zzamu var10 = new zzamu(this);
      zzano var11 = new zzano(this);
      zzl var12;
      (var12 = zzl.zzae(var2)).zza((UncaughtExceptionHandler)(new zzamk(this)));
      this.zzafQ = var12;
      GoogleAnalytics var13 = new GoogleAnalytics(this);
      var8.initialize();
      this.zzafW = var8;
      var9.initialize();
      this.zzafX = var9;
      var10.initialize();
      this.zzafY = var10;
      var11.initialize();
      this.zzafZ = var11;
      zzanp var14;
      (var14 = new zzanp(this)).initialize();
      this.zzafS = var14;
      var7.initialize();
      this.zzafR = var7;
      var13.initialize();
      this.zzafV = var13;
      var7.start();
   }

   public static zzamj zzaf(Context var0) {
      zzbo.zzu(var0);
      if (zzafM == null) {
         Class var1 = zzamj.class;
         synchronized(zzamj.class) {
            if (zzafM == null) {
               zze var2;
               long var3 = (var2 = zzi.zzrY()).elapsedRealtime();
               zzaml var5 = new zzaml(var0);
               zzamj var6;
               zzafM = var6 = new zzamj(var5);
               GoogleAnalytics.zzjo();
               long var7 = var2.elapsedRealtime() - var3;
               long var9 = ((Long)zzans.zzahU.get()).longValue();
               if (var7 > var9) {
                  var6.zzkr().zzc("Slow initialization (ms)", var7, var9);
               }
            }
         }
      }

      return zzafM;
   }

   public final Context getContext() {
      return this.mContext;
   }

   public final Context zzkE() {
      return this.zzafN;
   }

   public final zze zzkq() {
      return this.zzvw;
   }

   public final zzank zzks() {
      return this.zzafO;
   }

   public final zzaoc zzkr() {
      zza(this.zzafP);
      return this.zzafP;
   }

   public final zzaoc zzkF() {
      return this.zzafP;
   }

   public final zzl zzkt() {
      zzbo.zzu(this.zzafQ);
      return this.zzafQ;
   }

   public final zzaly zzkv() {
      zza(this.zzafR);
      return this.zzafR;
   }

   public final zzanp zzkw() {
      zza(this.zzafS);
      return this.zzafS;
   }

   public final GoogleAnalytics zzkG() {
      zzbo.zzu(this.zzafV);
      zzbo.zzb(this.zzafV.isInitialized(), "Analytics instance not initialized");
      return this.zzafV;
   }

   public final zzaot zzkx() {
      zza(this.zzafT);
      return this.zzafT;
   }

   public final zzaog zzky() {
      zza(this.zzafU);
      return this.zzafU;
   }

   public final zzaog zzkH() {
      return this.zzafU != null && this.zzafU.isInitialized() ? this.zzafU : null;
   }

   public final zzalx zzkI() {
      zza(this.zzafX);
      return this.zzafX;
   }

   public final zzanb zzkJ() {
      zza(this.zzafW);
      return this.zzafW;
   }

   public final zzamu zzkB() {
      zza(this.zzafY);
      return this.zzafY;
   }

   public final zzano zzkC() {
      return this.zzafZ;
   }

   private static void zza(zzamh var0) {
      zzbo.zzb(var0, "Analytics service not created/initialized");
      zzbo.zzb(var0.isInitialized(), "Analytics service not initialized");
   }
}
