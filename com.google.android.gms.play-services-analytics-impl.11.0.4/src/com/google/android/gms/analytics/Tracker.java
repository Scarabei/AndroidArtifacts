package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzalx;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzamh;
import com.google.android.gms.internal.zzamj;
import com.google.android.gms.internal.zzamu;
import com.google.android.gms.internal.zzano;
import com.google.android.gms.internal.zzaoa;
import com.google.android.gms.internal.zzaoc;
import com.google.android.gms.internal.zzaor;
import com.google.android.gms.internal.zzaos;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class Tracker extends zzamh {
   private boolean zzaeo;
   private final Map zzHa = new HashMap();
   private final Map zzaep = new HashMap();
   private final zzaoa zzaeq;
   private final Tracker.zza zzaer;
   private ExceptionReporter zzaes;
   private zzaor zzaet;

   Tracker(zzamj var1, String var2, zzaoa var3) {
      super(var1);
      if (var2 != null) {
         this.zzHa.put("&tid", var2);
      }

      this.zzHa.put("useSecure", "1");
      this.zzHa.put("&a", Integer.toString((new Random()).nextInt(Integer.MAX_VALUE) + 1));
      this.zzaeq = new zzaoa("tracking", this.zzkq());
      this.zzaer = new Tracker.zza(var1);
   }

   protected final void zzjD() {
      this.zzaer.initialize();
      String var1;
      if ((var1 = this.zzkx().zzjG()) != null) {
         this.set("&an", var1);
      }

      String var2;
      if ((var2 = this.zzkx().zzjH()) != null) {
         this.set("&av", var2);
      }

   }

   final void zza(zzaor var1) {
      this.zzbo("Loading Tracker config values");
      this.zzaet = var1;
      String var2;
      if (this.zzaet.zzado != null) {
         var2 = this.zzaet.zzado;
         this.set("&tid", var2);
         this.zza("trackingId loaded", var2);
      }

      if (this.zzaet.zzaiI >= 0.0D) {
         var2 = Double.toString(this.zzaet.zzaiI);
         this.set("&sf", var2);
         this.zza("Sample frequency loaded", var2);
      }

      if (this.zzaet.zzaiJ >= 0) {
         int var3 = this.zzaet.zzaiJ;
         this.setSessionTimeout((long)var3);
         this.zza("Session timeout loaded", var3);
      }

      boolean var4;
      if (this.zzaet.zzaiK != -1) {
         var4 = this.zzaet.zzaiK == 1;
         this.enableAutoActivityTracking(var4);
         this.zza("Auto activity tracking loaded", var4);
      }

      if (this.zzaet.zzaiL != -1) {
         if (var4 = this.zzaet.zzaiL == 1) {
            this.set("&aip", "1");
         }

         this.zza("Anonymize ip loaded", var4);
      }

      this.enableExceptionReporting(this.zzaet.zzaiM == 1);
   }

   public void enableExceptionReporting(boolean var1) {
      synchronized(this) {
         if (this.zzaes != null != var1) {
            if (var1) {
               Context var3 = this.getContext();
               UncaughtExceptionHandler var4 = Thread.getDefaultUncaughtExceptionHandler();
               this.zzaes = new ExceptionReporter(this, var4, var3);
               Thread.setDefaultUncaughtExceptionHandler(this.zzaes);
               this.zzbo("Uncaught exceptions will be reported to Google Analytics");
            } else {
               Thread.setDefaultUncaughtExceptionHandler(this.zzaes.zzjn());
               this.zzbo("Uncaught exceptions will not be reported to Google Analytics");
            }

         }
      }
   }

   public void setSessionTimeout(long var1) {
      this.zzaer.setSessionTimeout(var1 * 1000L);
   }

   public void enableAutoActivityTracking(boolean var1) {
      this.zzaer.enableAutoActivityTracking(var1);
   }

   private static String zza(Entry var0) {
      String var2 = (String)var0.getKey();
      var0.getValue();
      return !var2.startsWith("&") || var2.length() < 2 ? null : ((String)var0.getKey()).substring(1);
   }

   private static void zzb(Map var0, Map var1) {
      zzbo.zzu(var1);
      if (var0 != null) {
         Iterator var2 = var0.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3;
            String var4;
            if ((var4 = zza(var3 = (Entry)var2.next())) != null) {
               var1.put(var4, (String)var3.getValue());
            }
         }

      }
   }

   private static void zzc(Map var0, Map var1) {
      zzbo.zzu(var1);
      if (var0 != null) {
         Iterator var2 = var0.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3;
            String var4;
            if ((var4 = zza(var3 = (Entry)var2.next())) != null && !var1.containsKey(var4)) {
               var1.put(var4, (String)var3.getValue());
            }
         }

      }
   }

   public void send(Map var1) {
      long var2 = this.zzkq().currentTimeMillis();
      if (this.zzku().getAppOptOut()) {
         this.zzbp("AppOptOut is set to true. Not sending Google Analytics hit");
      } else {
         boolean var4 = this.zzku().isDryRunEnabled();
         HashMap var5 = new HashMap();
         zzb(this.zzHa, var5);
         zzb(var1, var5);
         boolean var6 = zzaos.zzf((String)this.zzHa.get("useSecure"), true);
         zzc(this.zzaep, var5);
         this.zzaep.clear();
         String var7;
         if (TextUtils.isEmpty(var7 = (String)var5.get("t"))) {
            this.zzkr().zze(var5, "Missing hit type parameter");
         } else {
            String var8;
            if (TextUtils.isEmpty(var8 = (String)var5.get("tid"))) {
               this.zzkr().zze(var5, "Missing tracking id parameter");
            } else {
               boolean var9 = this.zzaeo;
               synchronized(this) {
                  if ("screenview".equalsIgnoreCase(var7) || "pageview".equalsIgnoreCase(var7) || "appview".equalsIgnoreCase(var7) || TextUtils.isEmpty(var7)) {
                     int var11 = Integer.parseInt((String)this.zzHa.get("&a"));
                     ++var11;
                     var11 = var11 >= Integer.MAX_VALUE ? 1 : var11;
                     this.zzHa.put("&a", Integer.toString(var11));
                  }
               }

               this.zzkt().zzf(new zzp(this, var5, var9, var7, var2, var4, var6, var8));
            }
         }
      }
   }

   public String get(String var1) {
      this.zzkD();
      if (TextUtils.isEmpty(var1)) {
         return null;
      } else if (this.zzHa.containsKey(var1)) {
         return (String)this.zzHa.get(var1);
      } else if (var1.equals("&ul")) {
         return zzaos.zza(Locale.getDefault());
      } else if (var1.equals("&cid")) {
         return this.zzkz().zzli();
      } else if (var1.equals("&sr")) {
         return this.zzkC().zzlB();
      } else if (var1.equals("&aid")) {
         return this.zzkB().zzkW().zzhl();
      } else if (var1.equals("&an")) {
         return this.zzkB().zzkW().zzjG();
      } else if (var1.equals("&av")) {
         return this.zzkB().zzkW().zzjH();
      } else {
         return var1.equals("&aiid") ? this.zzkB().zzkW().zzjI() : null;
      }
   }

   public void set(String var1, String var2) {
      zzbo.zzb(var1, "Key should be non-null");
      if (!TextUtils.isEmpty(var1)) {
         this.zzHa.put(var1, var2);
      }
   }

   public void setSampleRate(double var1) {
      this.set("&sf", Double.toString(var1));
   }

   public void setUseSecure(boolean var1) {
      this.set("useSecure", zzaos.zzI(var1));
   }

   public void setScreenName(String var1) {
      this.set("&cd", var1);
   }

   public void setLocation(String var1) {
      this.set("&dl", var1);
   }

   public void setReferrer(String var1) {
      this.set("&dr", var1);
   }

   public void setPage(String var1) {
      this.set("&dp", var1);
   }

   public void setHostname(String var1) {
      this.set("&dh", var1);
   }

   public void setTitle(String var1) {
      this.set("&dt", var1);
   }

   public void setLanguage(String var1) {
      this.set("&ul", var1);
   }

   public void setEncoding(String var1) {
      this.set("&de", var1);
   }

   public void setScreenColors(String var1) {
      this.set("&sd", var1);
   }

   public void setScreenResolution(int var1, int var2) {
      if (var1 < 0 && var2 < 0) {
         this.zzbr("Invalid width or height. The values should be non-negative.");
      } else {
         this.set("&sr", (new StringBuilder(23)).append(var1).append("x").append(var2).toString());
      }
   }

   public void setViewportSize(String var1) {
      this.set("&vp", var1);
   }

   public void setClientId(String var1) {
      this.set("&cid", var1);
   }

   public void setAppName(String var1) {
      this.set("&an", var1);
   }

   public void setAppId(String var1) {
      this.set("&aid", var1);
   }

   public void setAppInstallerId(String var1) {
      this.set("&aiid", var1);
   }

   public void setAppVersion(String var1) {
      this.set("&av", var1);
   }

   public void setAnonymizeIp(boolean var1) {
      this.set("&aip", zzaos.zzI(var1));
   }

   public void setCampaignParamsOnNextHit(Uri var1) {
      if (var1 != null && !var1.isOpaque()) {
         String var2;
         if (!TextUtils.isEmpty(var2 = var1.getQueryParameter("referrer"))) {
            String var10001 = String.valueOf(var2);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "http://hostname/?".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("http://hostname/?");
            }

            Uri var3;
            String var4;
            if ((var4 = (var3 = Uri.parse(var10000)).getQueryParameter("utm_id")) != null) {
               this.zzaep.put("&ci", var4);
            }

            if ((var4 = var3.getQueryParameter("anid")) != null) {
               this.zzaep.put("&anid", var4);
            }

            if ((var4 = var3.getQueryParameter("utm_campaign")) != null) {
               this.zzaep.put("&cn", var4);
            }

            if ((var4 = var3.getQueryParameter("utm_content")) != null) {
               this.zzaep.put("&cc", var4);
            }

            if ((var4 = var3.getQueryParameter("utm_medium")) != null) {
               this.zzaep.put("&cm", var4);
            }

            if ((var4 = var3.getQueryParameter("utm_source")) != null) {
               this.zzaep.put("&cs", var4);
            }

            if ((var4 = var3.getQueryParameter("utm_term")) != null) {
               this.zzaep.put("&ck", var4);
            }

            if ((var4 = var3.getQueryParameter("dclid")) != null) {
               this.zzaep.put("&dclid", var4);
            }

            if ((var4 = var3.getQueryParameter("gclid")) != null) {
               this.zzaep.put("&gclid", var4);
            }

            if ((var4 = var3.getQueryParameter("aclid")) != null) {
               this.zzaep.put("&aclid", var4);
            }

         }
      }
   }

   public void enableAdvertisingIdCollection(boolean var1) {
      this.zzaeo = var1;
   }

   // $FF: synthetic method
   static zzalx zzb(Tracker var0) {
      return var0.zzkA();
   }

   // $FF: synthetic method
   static zzamu zzc(Tracker var0) {
      return var0.zzkB();
   }

   // $FF: synthetic method
   static zzano zzd(Tracker var0) {
      return var0.zzkC();
   }

   // $FF: synthetic method
   static zzano zze(Tracker var0) {
      return var0.zzkC();
   }

   // $FF: synthetic method
   static zzaoa zzf(Tracker var0) {
      return var0.zzaeq;
   }

   // $FF: synthetic method
   static zzaoc zzg(Tracker var0) {
      return var0.zzkr();
   }

   // $FF: synthetic method
   static zzaoc zzh(Tracker var0) {
      return var0.zzkr();
   }

   // $FF: synthetic method
   static zzaly zzi(Tracker var0) {
      return var0.zzkv();
   }

   // $FF: synthetic method
   static zzaly zzj(Tracker var0) {
      return var0.zzkv();
   }

   class zza extends zzamh implements GoogleAnalytics.zza {
      private boolean zzaeC;
      private int zzaeD;
      private long zzaeE;
      private boolean zzaeF;
      private long zzaeG;
      // $FF: synthetic field
      private Tracker zzaeB;

      protected zza(zzamj var2) {
         this.zzaeB = Tracker.this;
         super(var2);
         this.zzaeE = -1L;
      }

      protected final void zzjD() {
      }

      public final void setSessionTimeout(long var1) {
         this.zzaeE = var1;
         this.zzjF();
      }

      public final void enableAutoActivityTracking(boolean var1) {
         this.zzaeC = var1;
         this.zzjF();
      }

      public final synchronized boolean zzjE() {
         boolean var1 = this.zzaeF;
         this.zzaeF = false;
         return var1;
      }

      private final void zzjF() {
         if (this.zzaeE < 0L && !this.zzaeC) {
            this.zzku().zzb(this.zzaeB.zzaer);
         } else {
            this.zzku().zza(this.zzaeB.zzaer);
         }
      }

      public final void zzl(Activity var1) {
         if (this.zzaeD == 0 && this.zzkq().elapsedRealtime() >= this.zzaeG + Math.max(1000L, this.zzaeE)) {
            this.zzaeF = true;
         }

         ++this.zzaeD;
         if (this.zzaeC) {
            Intent var2;
            if ((var2 = var1.getIntent()) != null) {
               this.zzaeB.setCampaignParamsOnNextHit(var2.getData());
            }

            HashMap var3;
            (var3 = new HashMap()).put("&t", "screenview");
            Tracker var10000 = this.zzaeB;
            String var10;
            if (this.zzaeB.zzaet != null) {
               zzaor var10002 = this.zzaeB.zzaet;
               String var8 = var1.getClass().getCanonicalName();
               String var9;
               var10 = (var9 = (String)var10002.zzaiN.get(var8)) != null ? var9 : var8;
            } else {
               var10 = var1.getClass().getCanonicalName();
            }

            var10000.set("&cd", var10);
            if (TextUtils.isEmpty((CharSequence)var3.get("&dr"))) {
               zzbo.zzu(var1);
               String var4;
               Intent var6;
               String var7;
               if (!TextUtils.isEmpty(var4 = (var6 = var1.getIntent()) == null ? null : (TextUtils.isEmpty(var7 = var6.getStringExtra("android.intent.extra.REFERRER_NAME")) ? null : var7))) {
                  var3.put("&dr", var4);
               }
            }

            this.zzaeB.send(var3);
         }

      }

      public final void zzm(Activity var1) {
         --this.zzaeD;
         this.zzaeD = Math.max(0, this.zzaeD);
         if (this.zzaeD == 0) {
            this.zzaeG = this.zzkq().elapsedRealtime();
         }

      }
   }
}
