package com.google.android.gms.wearable.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzbaz;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public final class zzfw extends com.google.android.gms.common.internal.zzz {
   private final ExecutorService zzbrV;
   private final zzdp zzbTg;
   private final zzdp zzbTh;
   private final zzdp zzbTi;
   private final zzdp zzbTj;
   private final zzdp zzbTk;
   private final zzdp zzbTl;
   private final zzdp zzbTm;
   private final zzdp zzbTn;
   private final zzgh zzbTo;

   public zzfw(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, com.google.android.gms.common.internal.zzq var5) {
      this(var1, var2, var3, var4, var5, Executors.newCachedThreadPool(), zzgh.zzbz(var1));
   }

   private zzfw(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, com.google.android.gms.common.internal.zzq var5, ExecutorService var6, zzgh var7) {
      super(var1, var2, 14, var5, var3, var4);
      this.zzbTg = new zzdp();
      this.zzbTh = new zzdp();
      this.zzbTi = new zzdp();
      this.zzbTj = new zzdp();
      this.zzbTk = new zzdp();
      this.zzbTl = new zzdp();
      this.zzbTm = new zzdp();
      this.zzbTn = new zzdp();
      this.zzbrV = (ExecutorService)com.google.android.gms.common.internal.zzbo.zzu(var6);
      this.zzbTo = var7;
   }

   protected final String zzdb() {
      return "com.google.android.gms.wearable.BIND";
   }

   protected final String zzqZ() {
      return this.zzbTo.zzgm("com.google.android.wearable.app.cn") ? "com.google.android.wearable.app.cn" : "com.google.android.gms";
   }

   protected final String zzdc() {
      return "com.google.android.gms.wearable.internal.IWearableService";
   }

   public final boolean zzpe() {
      return !this.zzbTo.zzgm("com.google.android.wearable.app.cn");
   }

   protected final void zza(int var1, IBinder var2, Bundle var3, int var4) {
      if (Log.isLoggable("WearableClient", 2)) {
         Log.d("WearableClient", (new StringBuilder(41)).append("onPostInitHandler: statusCode ").append(var1).toString());
      }

      if (var1 == 0) {
         this.zzbTg.zzam(var2);
         this.zzbTh.zzam(var2);
         this.zzbTi.zzam(var2);
         this.zzbTj.zzam(var2);
         this.zzbTk.zzam(var2);
         this.zzbTl.zzam(var2);
         this.zzbTm.zzam(var2);
         this.zzbTn.zzam(var2);
      }

      super.zza(var1, var2, var3, var4);
   }

   public final void zza(@NonNull com.google.android.gms.common.internal.zzj var1) {
      if (!this.zzpe()) {
         try {
            Bundle var2;
            int var10000 = (var2 = this.getContext().getPackageManager().getApplicationInfo("com.google.android.wearable.app.cn", 128).metaData) != null ? var2.getInt("com.google.android.wearable.api.version", 0) : 0;
            int var3 = var10000;
            if (var10000 < com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
               int var4 = com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
               Log.w("WearableClient", (new StringBuilder(80)).append("Android Wear out of date. Requires API version ").append(var4).append(" but found ").append(var3).toString());
               Context var5;
               Context var10003 = var5 = this.getContext();
               Intent var6 = (new Intent("com.google.android.wearable.app.cn.UPDATE_ANDROID_WEAR")).setPackage("com.google.android.wearable.app.cn");
               Intent var10005;
               if (var5.getPackageManager().resolveActivity(var6, 65536) != null) {
                  var10005 = var6;
               } else {
                  Uri var7 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.wearable.app.cn").build();
                  var10005 = new Intent("android.intent.action.VIEW", var7);
               }

               this.zza(var1, 6, PendingIntent.getActivity(var10003, 0, var10005, 0));
               return;
            }
         } catch (NameNotFoundException var8) {
            this.zza(var1, 16, (PendingIntent)null);
            return;
         }
      }

      super.zza(var1);
   }

   public final void zza(zzbaz var1, PutDataRequest var2) throws RemoteException {
      Iterator var3 = var2.getAssets().entrySet().iterator();

      Asset var5;
      do {
         if (!var3.hasNext()) {
            PutDataRequest var17;
            (var17 = PutDataRequest.zzt(var2.getUri())).setData(var2.getData());
            if (var2.isUrgent()) {
               var17.setUrgent();
            }

            ArrayList var4 = new ArrayList();
            Iterator var18 = var2.getAssets().entrySet().iterator();

            while(var18.hasNext()) {
               String var9;
               Entry var19;
               Asset var20;
               if ((var20 = (Asset)(var19 = (Entry)var18.next()).getValue()).getData() != null) {
                  ParcelFileDescriptor[] var8;
                  String var10;
                  try {
                     var8 = ParcelFileDescriptor.createPipe();
                  } catch (IOException var15) {
                     var10 = String.valueOf(var2);
                     throw new IllegalStateException((new StringBuilder(60 + String.valueOf(var10).length())).append("Unable to create ParcelFileDescriptor for asset in request: ").append(var10).toString(), var15);
                  }

                  if (Log.isLoggable("WearableClient", 3)) {
                     var9 = String.valueOf(var20);
                     var10 = String.valueOf(var8[0]);
                     String var11 = String.valueOf(var8[1]);
                     Log.d("WearableClient", (new StringBuilder(61 + String.valueOf(var9).length() + String.valueOf(var10).length() + String.valueOf(var11).length())).append("processAssets: replacing data with FD in asset: ").append(var9).append(" read:").append(var10).append(" write:").append(var11).toString());
                  }

                  var17.putAsset((String)var19.getKey(), Asset.createFromFd(var8[0]));
                  ParcelFileDescriptor var10001 = var8[1];
                  byte[] var14 = var20.getData();
                  ParcelFileDescriptor var13 = var10001;
                  FutureTask var21 = new FutureTask(new zzfx(this, var13, var14));
                  var4.add(var21);
                  this.zzbrV.submit(var21);
               } else if (var20.getUri() != null) {
                  try {
                     Asset var22 = Asset.createFromFd(this.getContext().getContentResolver().openFileDescriptor(var20.getUri(), "r"));
                     var17.putAsset((String)var19.getKey(), var22);
                  } catch (FileNotFoundException var16) {
                     (new zzfr(var1, var4)).zza(new zzem(4005, (zzcb)null));
                     var9 = String.valueOf(var20.getUri());
                     Log.w("WearableClient", (new StringBuilder(28 + String.valueOf(var9).length())).append("Couldn't resolve asset URI: ").append(var9).toString());
                     return;
                  }
               } else {
                  var17.putAsset((String)var19.getKey(), var20);
               }
            }

            ((zzdn)this.zzrf()).zza(new zzfr(var1, var4), (PutDataRequest)var17);
            return;
         }
      } while((var5 = (Asset)((Entry)var3.next()).getValue()).getData() != null || var5.getDigest() != null || var5.getFd() != null || var5.getUri() != null);

      String var6 = String.valueOf(var2.getUri());
      String var7 = String.valueOf(var5);
      throw new IllegalArgumentException((new StringBuilder(33 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("Put for ").append(var6).append(" contains invalid asset: ").append(var7).toString());
   }

   public final void zza(zzbaz var1, Asset var2) throws RemoteException {
      ((zzdn)this.zzrf()).zza(new zzfn(var1), (Asset)var2);
   }

   public final void zza(zzbaz var1, String var2, Uri var3, boolean var4) {
      try {
         ExecutorService var10000 = this.zzbrV;
         com.google.android.gms.common.internal.zzbo.zzu(var1);
         com.google.android.gms.common.internal.zzbo.zzu(var2);
         com.google.android.gms.common.internal.zzbo.zzu(var3);
         var10000.execute(new zzfy(this, var3, var1, var4, var2));
      } catch (RuntimeException var11) {
         var1.zzr(new Status(8));
         throw var11;
      }
   }

   public final void zza(zzbaz var1, String var2, Uri var3, long var4, long var6) {
      try {
         ExecutorService var10000 = this.zzbrV;
         com.google.android.gms.common.internal.zzbo.zzu(var1);
         com.google.android.gms.common.internal.zzbo.zzu(var2);
         com.google.android.gms.common.internal.zzbo.zzu(var3);
         com.google.android.gms.common.internal.zzbo.zzb(var4 >= 0L, "startOffset is negative: %s", new Object[]{var4});
         com.google.android.gms.common.internal.zzbo.zzb(var6 >= -1L, "invalid length: %s", new Object[]{var6});
         var10000.execute(new zzfz(this, var3, var1, var2, var4, var6));
      } catch (RuntimeException var17) {
         var1.zzr(new Status(8));
         throw var17;
      }
   }

   public final void zza(zzbaz var1, DataApi.DataListener var2, zzbdw var3, IntentFilter[] var4) throws RemoteException {
      this.zzbTj.zza(this, var1, var2, zzga.zza(var3, var4));
   }

   public final void zza(zzbaz var1, MessageApi.MessageListener var2, zzbdw var3, IntentFilter[] var4) throws RemoteException {
      this.zzbTk.zza(this, var1, var2, zzga.zzb(var3, var4));
   }

   public final void zza(zzbaz var1, NodeApi.NodeListener var2, zzbdw var3, IntentFilter[] var4) throws RemoteException {
      this.zzbTl.zza(this, var1, var2, zzga.zzc(var3, var4));
   }

   public final void zza(zzbaz var1, CapabilityApi.CapabilityListener var2, zzbdw var3, IntentFilter[] var4) throws RemoteException {
      this.zzbTn.zza(this, var1, var2, zzga.zze(var3, var4));
   }

   public final void zza(zzbaz var1, ChannelApi.ChannelListener var2, zzbdw var3, String var4, IntentFilter[] var5) throws RemoteException {
      if (var4 == null) {
         this.zzbTi.zza(this, var1, var2, zzga.zzd(var3, var5));
      } else {
         zzeu var6 = new zzeu(var4, var2);
         this.zzbTi.zza(this, var1, var6, zzga.zza(var3, var4, var5));
      }
   }

   public final void zza(zzbaz var1, DataApi.DataListener var2) throws RemoteException {
      this.zzbTj.zza(this, var1, var2);
   }

   public final void zza(zzbaz var1, MessageApi.MessageListener var2) throws RemoteException {
      this.zzbTk.zza(this, var1, var2);
   }

   public final void zza(zzbaz var1, NodeApi.NodeListener var2) throws RemoteException {
      this.zzbTl.zza(this, var1, var2);
   }

   public final void zza(zzbaz var1, CapabilityApi.CapabilityListener var2) throws RemoteException {
      this.zzbTn.zza(this, var1, var2);
   }

   public final void zza(zzbaz var1, ChannelApi.ChannelListener var2, String var3) throws RemoteException {
      if (var3 == null) {
         this.zzbTi.zza(this, var1, var2);
      } else {
         zzeu var4 = new zzeu(var3, var2);
         this.zzbTi.zza(this, var1, var4);
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService")) instanceof zzdn ? (zzdn)var3 : new zzdo(var1));
      }
   }
}
