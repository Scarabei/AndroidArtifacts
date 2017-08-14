package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
   private final TagManager.zza zzbGT;
   private final Context mContext;
   private final DataLayer zzbDx;
   private final zzfn zzbGU;
   private final ConcurrentMap zzbGV;
   private final zzal zzbFW;
   private static TagManager zzbGW;

   private TagManager(Context var1, TagManager.zza var2, DataLayer var3, zzfn var4) {
      if (var1 == null) {
         throw new NullPointerException("context cannot be null");
      } else {
         this.mContext = var1.getApplicationContext();
         this.zzbGU = var4;
         this.zzbGT = var2;
         this.zzbGV = new ConcurrentHashMap();
         this.zzbDx = var3;
         this.zzbDx.zza((DataLayer.zzb)(new zzgb(this)));
         this.zzbDx.zza((DataLayer.zzb)(new zzg(this.mContext)));
         this.zzbFW = new zzal();
         this.mContext.registerComponentCallbacks(new zzgd(this));
         zza.zzbl(this.mContext);
      }
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public static TagManager getInstance(Context var0) {
      Class var1 = TagManager.class;
      synchronized(TagManager.class) {
         if (zzbGW == null) {
            if (var0 == null) {
               zzdj.e("TagManager.getInstance requires non-null context.");
               throw new NullPointerException();
            }

            zzgc var2 = new zzgc();
            zzat var3 = new zzat(var0);
            zzbGW = new TagManager(var0, var2, new DataLayer(var3), zzfo.zzBV());
         }

         return zzbGW;
      }
   }

   public DataLayer getDataLayer() {
      return this.zzbDx;
   }

   public PendingResult loadContainerDefaultOnly(String var1, @RawRes int var2) {
      zzy var3;
      (var3 = this.zzbGT.zza(this.mContext, this, (Looper)null, var1, var2, this.zzbFW)).zzAN();
      return var3;
   }

   public PendingResult loadContainerDefaultOnly(String var1, @RawRes int var2, Handler var3) {
      zzy var4;
      (var4 = this.zzbGT.zza(this.mContext, this, var3.getLooper(), var1, var2, this.zzbFW)).zzAN();
      return var4;
   }

   public PendingResult loadContainerPreferNonDefault(String var1, @RawRes int var2) {
      zzy var3;
      (var3 = this.zzbGT.zza(this.mContext, this, (Looper)null, var1, var2, this.zzbFW)).zzAO();
      return var3;
   }

   public PendingResult loadContainerPreferNonDefault(String var1, @RawRes int var2, Handler var3) {
      zzy var4;
      (var4 = this.zzbGT.zza(this.mContext, this, var3.getLooper(), var1, var2, this.zzbFW)).zzAO();
      return var4;
   }

   public PendingResult loadContainerPreferFresh(String var1, @RawRes int var2) {
      zzy var3;
      (var3 = this.zzbGT.zza(this.mContext, this, (Looper)null, var1, var2, this.zzbFW)).zzAP();
      return var3;
   }

   public PendingResult loadContainerPreferFresh(String var1, @RawRes int var2, Handler var3) {
      zzy var4;
      (var4 = this.zzbGT.zza(this.mContext, this, var3.getLooper(), var1, var2, this.zzbFW)).zzAP();
      return var4;
   }

   public void dispatch() {
      this.zzbGU.dispatch();
   }

   public void setVerboseLoggingEnabled(boolean var1) {
      zzdj.setLogLevel(var1 ? 2 : 5);
   }

   final synchronized boolean zzr(Uri var1) {
      zzei var2;
      if (!(var2 = zzei.zzBD()).zzr(var1)) {
         return false;
      } else {
         String var3 = var2.getContainerId();
         switch(zzge.zzbGY[var2.zzBE().ordinal()]) {
         case 1:
            zzv var4;
            if ((var4 = (zzv)this.zzbGV.get(var3)) != null) {
               var4.zzfa((String)null);
               var4.refresh();
            }
            break;
         case 2:
         case 3:
            Iterator var5 = this.zzbGV.keySet().iterator();

            while(var5.hasNext()) {
               String var6 = (String)var5.next();
               zzv var7 = (zzv)this.zzbGV.get(var6);
               if (var6.equals(var3)) {
                  var7.zzfa(var2.zzBF());
                  var7.refresh();
               } else if (var7.zzAK() != null) {
                  var7.zzfa((String)null);
                  var7.refresh();
               }
            }
         }

         return true;
      }
   }

   public final int zza(zzv var1) {
      this.zzbGV.put(var1.getContainerId(), var1);
      return this.zzbGV.size();
   }

   public final boolean zzb(zzv var1) {
      return this.zzbGV.remove(var1.getContainerId()) != null;
   }

   private final void zzfu(String var1) {
      Iterator var2 = this.zzbGV.values().iterator();

      while(var2.hasNext()) {
         ((zzv)var2.next()).zzeZ(var1);
      }

   }

   // $FF: synthetic method
   static void zza(TagManager var0, String var1) {
      var0.zzfu(var1);
   }

   public interface zza {
      zzy zza(Context var1, TagManager var2, Looper var3, String var4, int var5, zzal var6);
   }
}
