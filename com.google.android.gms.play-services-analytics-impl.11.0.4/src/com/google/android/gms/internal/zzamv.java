package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzamv extends zzamh {
   private boolean mStarted;
   private final zzams zzagv;
   private final zzaoe zzagw;
   private final zzaod zzagx;
   private final zzamn zzagy;
   private long zzagz;
   private final zzanm zzagA;
   private final zzanm zzagB;
   private final zzaoo zzagC;
   private long zzagD;
   private boolean zzagE;

   protected zzamv(zzamj var1, zzaml var2) {
      super(var1);
      zzbo.zzu(var2);
      this.zzagz = Long.MIN_VALUE;
      this.zzagx = new zzaod(var1);
      this.zzagv = new zzams(var1);
      this.zzagw = new zzaoe(var1);
      this.zzagy = new zzamn(var1);
      this.zzagC = new zzaoo(this.zzkq());
      this.zzagA = new zzamw(this, var1);
      this.zzagB = new zzamx(this, var1);
   }

   protected final void zzjD() {
      this.zzagv.initialize();
      this.zzagw.initialize();
      this.zzagy.initialize();
   }

   final void start() {
      this.zzkD();
      zzbo.zza(!this.mStarted, "Analytics backend already started");
      this.mStarted = true;
      this.zzkt().zzf(new zzamy(this));
   }

   private final boolean zzbv(String var1) {
      return zzbha.zzaP(this.getContext()).checkCallingOrSelfPermission(var1) == 0;
   }

   protected final void zzkX() {
      this.zzkD();
      zzl.zzjC();
      Context var2;
      if (!zzaoj.zzac(var2 = this.zzkp().getContext())) {
         this.zzbr("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
      } else if (!zzaok.zzad(var2)) {
         this.zzbs("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
      }

      if (!CampaignTrackingReceiver.zzac(var2)) {
         this.zzbr("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
      } else if (!CampaignTrackingService.zzad(var2)) {
         this.zzbr("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
      }

      this.zzky().zzlU();
      if (!this.zzbv("android.permission.ACCESS_NETWORK_STATE")) {
         this.zzbs("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
         this.zzlh();
      }

      if (!this.zzbv("android.permission.INTERNET")) {
         this.zzbs("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
         this.zzlh();
      }

      if (zzaok.zzad(this.getContext())) {
         this.zzbo("AnalyticsService registered in the app manifest and enabled");
      } else {
         this.zzbr("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
      }

      if (!this.zzagE && !this.zzagv.isEmpty()) {
         this.zzla();
      }

      this.zzld();
   }

   private final void zzkY() {
      this.zzb((zzanq)(new zzamz(this)));
   }

   final void zzko() {
      zzl.zzjC();
      this.zzagD = this.zzkq().currentTimeMillis();
   }

   protected final void onServiceConnected() {
      zzl.zzjC();
      zzamv var1 = this;
      zzl.zzjC();
      this.zzkD();
      if (!zzank.zzlo()) {
         this.zzbr("Service client disabled. Can't dispatch local hits to device AnalyticsService");
      }

      if (!this.zzagy.isConnected()) {
         this.zzbo("Service not connected");
      } else if (!this.zzagv.isEmpty()) {
         this.zzbo("Dispatching local hits to device AnalyticsService");

         while(true) {
            List var2;
            try {
               if ((var2 = var1.zzagv.zzo((long)zzank.zzls())).isEmpty()) {
                  var1.zzld();
                  return;
               }
            } catch (SQLiteException var6) {
               var1.zze("Failed to read hits from store", var6);
               var1.zzlf();
               return;
            }

            while(!var2.isEmpty()) {
               zzanx var3 = (zzanx)var2.get(0);
               if (!var1.zzagy.zzb(var3)) {
                  var1.zzld();
                  return;
               }

               var2.remove(var3);

               try {
                  var1.zzagv.zzp(var3.zzlF());
               } catch (SQLiteException var5) {
                  var1.zze("Failed to remove hit that was send for delivery", var5);
                  var1.zzlf();
                  return;
               }
            }
         }
      }
   }

   private final void zzkZ() {
      try {
         this.zzagv.zzkS();
         this.zzld();
      } catch (SQLiteException var2) {
         this.zzd("Failed to delete stale hits", var2);
      }

      this.zzagB.zzs(86400000L);
   }

   protected final void zzb(zzamm var1) {
      zzl.zzjC();
      this.zzb("Sending first hit to property", var1.zzkL());
      if (!this.zzky().zzlV().zzu(zzank.zzly())) {
         String var2;
         if (!TextUtils.isEmpty(var2 = this.zzky().zzlY())) {
            zzall var3 = zzaos.zza(this.zzkr(), var2);
            this.zzb("Found relevant installation campaign", var3);
            this.zza(var1, var3);
         }
      }
   }

   public final void zzr(long var1) {
      zzl.zzjC();
      this.zzkD();
      if (var1 < 0L) {
         var1 = 0L;
      }

      this.zzagz = var1;
      this.zzld();
   }

   private final void zzla() {
      if (!this.zzagE) {
         if (zzank.zzlo()) {
            if (!this.zzagy.isConnected()) {
               long var1 = ((Long)zzans.zzahS.get()).longValue();
               if (this.zzagC.zzu(var1)) {
                  this.zzagC.start();
                  this.zzbo("Connecting to service");
                  if (this.zzagy.connect()) {
                     this.zzbo("Connected to service");
                     this.zzagC.clear();
                     this.onServiceConnected();
                  }
               }

            }
         }
      }
   }

   public final long zza(zzamm var1, boolean var2) {
      zzbo.zzu(var1);
      this.zzkD();
      zzl.zzjC();

      try {
         this.zzagv.beginTransaction();
         zzams var10000 = this.zzagv;
         long var10001 = var1.zzkK();
         String var12 = var1.zzjX();
         long var10 = var10001;
         zzams var9 = var10000;
         zzbo.zzcF(var12);
         var9.zzkD();
         zzl.zzjC();
         int var13;
         if ((var13 = var9.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(var10), var12})) > 0) {
            var9.zza("Deleted property records", var13);
         }

         long var3 = this.zzagv.zza(var1.zzkK(), var1.zzjX(), var1.zzkL());
         var1.zzm(var3 + 1L);
         zzams var14 = this.zzagv;
         zzbo.zzu(var1);
         var14.zzkD();
         zzl.zzjC();
         SQLiteDatabase var16 = var14.getWritableDatabase();
         Map var20;
         zzbo.zzu(var20 = var1.zzdV());
         Builder var21 = new Builder();
         Iterator var22 = var20.entrySet().iterator();

         while(var22.hasNext()) {
            Entry var23;
            String var24 = (String)(var23 = (Entry)var22.next()).getKey();
            var21.appendQueryParameter(var24, (String)var23.getValue());
         }

         String var35;
         String var17 = (var35 = var21.build().getEncodedQuery()) == null ? "" : var35;
         ContentValues var18;
         (var18 = new ContentValues()).put("app_uid", var1.zzkK());
         var18.put("cid", var1.zzjX());
         var18.put("tid", var1.zzkL());
         var18.put("adid", var1.zzkM() ? 1 : 0);
         var18.put("hits_count", var1.zzkN());
         var18.put("params", var17);

         try {
            if (var16.insertWithOnConflict("properties", (String)null, var18, 5) == -1L) {
               var14.zzbs("Failed to insert/update a property (got -1)");
            }
         } catch (SQLiteException var32) {
            var14.zze("Error storing a property", var32);
         }

         this.zzagv.setTransactionSuccessful();
         return var3;
      } catch (SQLiteException var33) {
         this.zze("Failed to update Analytics property", var33);
      } finally {
         try {
            this.zzagv.endTransaction();
         } catch (SQLiteException var31) {
            this.zze("Failed to end transaction", var31);
         }

      }

      return -1L;
   }

   public final void zza(zzanx var1) {
      zzbo.zzu(var1);
      zzl.zzjC();
      this.zzkD();
      if (this.zzagE) {
         this.zzbp("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
      } else {
         this.zza("Delivering hit", var1);
      }

      zzanx var10000;
      if (!TextUtils.isEmpty(var1.zzlK())) {
         var10000 = var1;
      } else {
         Pair var5;
         if ((var5 = this.zzky().zzlZ().zzmb()) == null) {
            var10000 = var1;
         } else {
            Long var6 = (Long)var5.second;
            String var7 = (String)var5.first;
            String var9 = String.valueOf(var6);
            String var8 = (new StringBuilder(1 + String.valueOf(var9).length() + String.valueOf(var7).length())).append(var9).append(":").append(var7).toString();
            HashMap var14;
            (var14 = new HashMap(var1.zzdV())).put("_m", var8);
            var10000 = new zzanx(this, var14, var1.zzlG(), var1.zzlI(), var1.zzlF(), var1.zzlE(), var1.zzlH());
         }
      }

      var1 = var10000;
      this.zzla();
      if (this.zzagy.zzb(var1)) {
         this.zzbp("Hit sent to the device AnalyticsService for delivery");
      } else {
         try {
            this.zzagv.zzc(var1);
            this.zzld();
         } catch (SQLiteException var13) {
            this.zze("Delivery failed to save hit to a database", var13);
            this.zzkr().zza(var1, "deliver: failed to insert hit to database");
         }
      }
   }

   public final void zzkk() {
      zzl.zzjC();
      this.zzkD();
      this.zzbo("Delete all hits from local store");

      try {
         zzams var2 = this.zzagv;
         zzl.zzjC();
         var2.zzkD();
         var2.getWritableDatabase().delete("hits2", (String)null, (String[])null);
         var2 = this.zzagv;
         zzl.zzjC();
         var2.zzkD();
         var2.getWritableDatabase().delete("properties", (String)null, (String[])null);
         this.zzld();
      } catch (SQLiteException var3) {
         this.zzd("Failed to delete hits from store", var3);
      }

      this.zzla();
      if (this.zzagy.zzkO()) {
         this.zzbo("Device service unavailable. Can't clear hits stored on the device service.");
      }

   }

   private final boolean zzlb() {
      zzl.zzjC();
      this.zzkD();
      this.zzbo("Dispatching a batch of local hits");
      boolean var1 = !this.zzagy.isConnected();
      boolean var2 = !this.zzagw.zzlQ();
      if (var1 && var2) {
         this.zzbo("No network or service available. Will retry later");
         return false;
      } else {
         long var3 = (long)Math.max(zzank.zzls(), zzank.zzlt());
         ArrayList var5 = new ArrayList();
         long var6 = 0L;

         while(true) {
            try {
               this.zzagv.beginTransaction();
               var5.clear();

               List var8;
               try {
                  if ((var8 = this.zzagv.zzo(var3)).isEmpty()) {
                     this.zzbo("Store is empty, nothing to dispatch");
                     this.zzlf();
                     return false;
                  }

                  this.zza("Hits loaded from store. count", var8.size());
               } catch (SQLiteException var29) {
                  this.zzd("Failed to read hits from persisted store", var29);
                  this.zzlf();
                  return false;
               }

               Iterator var9 = var8.iterator();

               while(var9.hasNext()) {
                  if (((zzanx)var9.next()).zzlF() == var6) {
                     this.zzd("Database contains successfully uploaded hit", var6, var8.size());
                     this.zzlf();
                     return false;
                  }
               }

               if (this.zzagy.isConnected()) {
                  this.zzbo("Service connected, sending hits to the service");

                  while(!var8.isEmpty()) {
                     zzanx var32 = (zzanx)var8.get(0);
                     if (!this.zzagy.zzb(var32)) {
                        break;
                     }

                     var6 = Math.max(var6, var32.zzlF());
                     var8.remove(var32);
                     this.zzb("Hit sent do device AnalyticsService for delivery", var32);

                     try {
                        this.zzagv.zzp(var32.zzlF());
                        var5.add(var32.zzlF());
                     } catch (SQLiteException var30) {
                        this.zze("Failed to remove hit that was send for delivery", var30);
                        this.zzlf();
                        return false;
                     }
                  }
               }

               if (this.zzagw.zzlQ()) {
                  List var33;
                  Long var11;
                  for(Iterator var10 = (var33 = this.zzagw.zzu(var8)).iterator(); var10.hasNext(); var6 = Math.max(var6, var11.longValue())) {
                     var11 = (Long)var10.next();
                  }

                  try {
                     this.zzagv.zzs(var33);
                     var5.addAll(var33);
                  } catch (SQLiteException var28) {
                     this.zze("Failed to remove successfully uploaded hits", var28);
                     this.zzlf();
                     return false;
                  }
               }

               if (var5.isEmpty()) {
                  return false;
               }
            } finally {
               try {
                  this.zzagv.setTransactionSuccessful();
                  this.zzagv.endTransaction();
               } catch (SQLiteException var27) {
                  this.zze("Failed to commit local dispatch transaction", var27);
                  this.zzlf();
                  return false;
               }
            }
         }
      }
   }

   public final void zzb(zzanq var1) {
      long var4 = this.zzagD;
      zzanq var3 = var1;
      zzamv var2 = this;
      zzl.zzjC();
      this.zzkD();
      long var6 = -1L;
      long var8;
      if ((var8 = this.zzky().zzlW()) != 0L) {
         var6 = Math.abs(this.zzkq().currentTimeMillis() - var8);
      }

      this.zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", var6);
      this.zzla();

      try {
         var2.zzlb();
         var2.zzky().zzlX();
         var2.zzld();
         if (var3 != null) {
            var3.zzc((Throwable)null);
         }

         if (var2.zzagD != var4) {
            var2.zzagx.zzlP();
         }

      } catch (Throwable var11) {
         this.zze("Local dispatch failed", var11);
         this.zzky().zzlX();
         this.zzld();
         if (var1 != null) {
            var1.zzc(var11);
         }

      }
   }

   public final void zzlc() {
      zzl.zzjC();
      this.zzkD();
      this.zzbp("Sync dispatching local hits");
      long var1 = this.zzagD;
      this.zzla();

      try {
         this.zzlb();
         this.zzky().zzlX();
         this.zzld();
         if (this.zzagD != var1) {
            this.zzagx.zzlP();
         }

      } catch (Throwable var4) {
         this.zze("Sync local dispatch failed", var4);
         this.zzld();
      }
   }

   private final long zzkT() {
      zzl.zzjC();
      this.zzkD();

      try {
         return this.zzagv.zzkT();
      } catch (SQLiteException var2) {
         this.zze("Failed to get min/max hit times from local store", var2);
         return 0L;
      }
   }

   public final void zzld() {
      zzl.zzjC();
      this.zzkD();
      if (this.zzagE || this.zzlg() <= 0L) {
         this.zzagx.unregister();
         this.zzlf();
      } else if (this.zzagv.isEmpty()) {
         this.zzagx.unregister();
         this.zzlf();
      } else {
         boolean var1;
         if (!((Boolean)zzans.zzahN.get()).booleanValue()) {
            this.zzagx.zzlN();
            var1 = this.zzagx.isConnected();
         } else {
            var1 = true;
         }

         if (var1) {
            this.zzle();
            long var3 = this.zzlg();
            long var5;
            long var7;
            long var9;
            if ((var7 = this.zzky().zzlW()) != 0L) {
               var9 = Math.abs(this.zzkq().currentTimeMillis() - var7);
               long var11;
               if ((var11 = var3 - var9) > 0L) {
                  var5 = var11;
               } else {
                  var5 = Math.min(zzank.zzlq(), var3);
               }
            } else {
               var5 = Math.min(zzank.zzlq(), var3);
            }

            this.zza("Dispatch scheduled (ms)", var5);
            if (this.zzagA.zzbo()) {
               var9 = Math.max(1L, var5 + this.zzagA.zzlz());
               this.zzagA.zzt(var9);
            } else {
               this.zzagA.zzs(var5);
            }
         } else {
            this.zzlf();
            this.zzle();
         }
      }
   }

   private final void zzle() {
      zzanp var1;
      if ((var1 = this.zzkw()).zzlC()) {
         long var2;
         if (!var1.zzbo() && (var2 = this.zzkT()) != 0L && Math.abs(this.zzkq().currentTimeMillis() - var2) <= ((Long)zzans.zzahr.get()).longValue()) {
            long var4 = zzank.zzlr();
            this.zza("Dispatch alarm scheduled (ms)", var4);
            var1.schedule();
         }

      }
   }

   private final void zzlf() {
      if (this.zzagA.zzbo()) {
         this.zzbo("All hits dispatched or no network/service. Going to power save mode");
      }

      this.zzagA.cancel();
      zzanp var2;
      if ((var2 = this.zzkw()).zzbo()) {
         var2.cancel();
      }

   }

   private final long zzlg() {
      if (this.zzagz != Long.MIN_VALUE) {
         return this.zzagz;
      } else {
         long var1 = ((Long)zzans.zzahm.get()).longValue();
         zzaot var3;
         (var3 = this.zzkx()).zzkD();
         if (var3.zzaiP) {
            (var3 = this.zzkx()).zzkD();
            var1 = (long)var3.zzahZ * 1000L;
         }

         return var1;
      }
   }

   public final void zzbw(String var1) {
      zzbo.zzcF(var1);
      zzl.zzjC();
      zzall var2;
      if ((var2 = zzaos.zza(this.zzkr(), var1)) == null) {
         this.zzd("Parsing failed. Ignoring invalid campaign data", var1);
      } else {
         String var3 = this.zzky().zzlY();
         if (var1.equals(var3)) {
            this.zzbr("Ignoring duplicate install campaign");
         } else if (!TextUtils.isEmpty(var3)) {
            this.zzd("Ignoring multiple install campaigns. original, new", var3, var1);
         } else {
            this.zzky().zzbz(var1);
            if (this.zzky().zzlV().zzu(zzank.zzly())) {
               this.zzd("Campaign received too late, ignoring", var2);
            } else {
               this.zzb("Received installation campaign", var2);
               Iterator var4 = this.zzagv.zzq(0L).iterator();

               while(var4.hasNext()) {
                  zzamm var5 = (zzamm)var4.next();
                  this.zza(var5, var2);
               }

            }
         }
      }
   }

   private final void zza(zzamm var1, zzall var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      zza var3;
      (var3 = new zza(this.zzkp())).zzaY(var1.zzkL());
      var3.enableAdvertisingIdCollection(var1.zzkM());
      zzi var4;
      zzalt var5;
      (var5 = (zzalt)(var4 = var3.zzjj()).zzb(zzalt.class)).zzbj("data");
      var5.zzH(true);
      var4.zza((zzj)var2);
      zzalo var6 = (zzalo)var4.zzb(zzalo.class);
      zzalk var7 = (zzalk)var4.zzb(zzalk.class);
      Iterator var8 = var1.zzdV().entrySet().iterator();

      while(var8.hasNext()) {
         Entry var9;
         String var10 = (String)(var9 = (Entry)var8.next()).getKey();
         String var11 = (String)var9.getValue();
         if ("an".equals(var10)) {
            var7.setAppName(var11);
         } else if ("av".equals(var10)) {
            var7.setAppVersion(var11);
         } else if ("aid".equals(var10)) {
            var7.setAppId(var11);
         } else if ("aiid".equals(var10)) {
            var7.setAppInstallerId(var11);
         } else if ("uid".equals(var10)) {
            var5.setUserId(var11);
         } else {
            var6.set(var10, var11);
         }
      }

      this.zzb("Sending installation campaign to", var1.zzkL(), var2);
      var4.zzl(this.zzky().zzlU());
      var4.zzjt();
   }

   private final void zzlh() {
      this.zzkD();
      zzl.zzjC();
      this.zzagE = true;
      this.zzagy.disconnect();
      this.zzld();
   }

   // $FF: synthetic method
   static void zza(zzamv var0) {
      var0.zzkY();
   }

   // $FF: synthetic method
   static void zzb(zzamv var0) {
      var0.zzkZ();
   }
}
