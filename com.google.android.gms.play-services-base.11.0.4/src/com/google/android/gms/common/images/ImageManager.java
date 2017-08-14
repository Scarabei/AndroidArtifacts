package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzbfm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
   private static final Object zzaFR = new Object();
   private static HashSet zzaFS = new HashSet();
   private static ImageManager zzaFT;
   private final Context mContext;
   private final Handler mHandler;
   private final ExecutorService zzaFU;
   private final ImageManager.zza zzaFV;
   private final zzbfm zzaFW;
   private final Map zzaFX;
   private final Map zzaFY;
   private final Map zzaFZ;

   public static ImageManager create(Context var0) {
      if (zzaFT == null) {
         zzaFT = new ImageManager(var0, false);
      }

      return zzaFT;
   }

   private ImageManager(Context var1, boolean var2) {
      this.mContext = var1.getApplicationContext();
      this.mHandler = new Handler(Looper.getMainLooper());
      this.zzaFU = Executors.newFixedThreadPool(4);
      this.zzaFV = null;
      this.zzaFW = new zzbfm();
      this.zzaFX = new HashMap();
      this.zzaFY = new HashMap();
      this.zzaFZ = new HashMap();
   }

   public final void loadImage(ImageView var1, Uri var2) {
      zzc var3 = new zzc(var1, var2);
      this.zza((zza)var3);
   }

   public final void loadImage(ImageView var1, int var2) {
      zzc var3 = new zzc(var1, var2);
      this.zza((zza)var3);
   }

   public final void loadImage(ImageView var1, Uri var2, int var3) {
      zzc var4;
      (var4 = new zzc(var1, var2)).zzaGh = var3;
      this.zza((zza)var4);
   }

   public final void loadImage(ImageManager.OnImageLoadedListener var1, Uri var2) {
      zzd var3 = new zzd(var1, var2);
      this.zza((zza)var3);
   }

   public final void loadImage(ImageManager.OnImageLoadedListener var1, Uri var2, int var3) {
      zzd var4;
      (var4 = new zzd(var1, var2)).zzaGh = var3;
      this.zza((zza)var4);
   }

   private final void zza(zza var1) {
      com.google.android.gms.common.internal.zzc.zzcz("ImageManager.loadImage() must be called in the main thread");
      (new ImageManager.zzc(var1)).run();
   }

   private final Bitmap zza(zzb var1) {
      return this.zzaFV == null ? null : (Bitmap)this.zzaFV.get(var1);
   }

   static final class zza extends LruCache {
      // $FF: synthetic method
      protected final int sizeOf(Object var1, Object var2) {
         Bitmap var3;
         return (var3 = (Bitmap)var2).getHeight() * var3.getRowBytes();
      }

      // $FF: synthetic method
      protected final void entryRemoved(boolean var1, Object var2, Object var3, Object var4) {
         zzb var10001 = (zzb)var2;
         Bitmap var10002 = (Bitmap)var3;
         Bitmap var7 = (Bitmap)var4;
         Bitmap var6 = var10002;
         zzb var5 = var10001;
         super.entryRemoved(var1, var5, var6, var7);
      }
   }

   final class zzd implements Runnable {
      private final Uri mUri;
      private final Bitmap mBitmap;
      private final CountDownLatch zztJ;
      private boolean zzaGe;
      // $FF: synthetic field
      private ImageManager zzaGb;

      public zzd(Uri var2, Bitmap var3, boolean var4, CountDownLatch var5) {
         this.zzaGb = ImageManager.this;
         super();
         this.mUri = var2;
         this.mBitmap = var3;
         this.zzaGe = var4;
         this.zztJ = var5;
      }

      public final void run() {
         com.google.android.gms.common.internal.zzc.zzcz("OnBitmapLoadedRunnable must be executed in the main thread");
         boolean var1 = this.mBitmap != null;
         if (this.zzaGb.zzaFV != null) {
            if (this.zzaGe) {
               this.zzaGb.zzaFV.evictAll();
               System.gc();
               this.zzaGe = false;
               this.zzaGb.mHandler.post(this);
               return;
            }

            if (var1) {
               this.zzaGb.zzaFV.put(new zzb(this.mUri), this.mBitmap);
            }
         }

         ImageManager.ImageReceiver var2;
         if ((var2 = (ImageManager.ImageReceiver)this.zzaGb.zzaFY.remove(this.mUri)) != null) {
            boolean var7 = var1;
            ImageManager.zzd var5 = this;
            ArrayList var8 = var2.zzaGa;
            int var9 = 0;

            for(int var10 = var8.size(); var9 < var10; ++var9) {
               zza var11 = (zza)var8.get(var9);
               if (var7) {
                  var11.zza(var5.zzaGb.mContext, var5.mBitmap, false);
               } else {
                  var5.zzaGb.zzaFZ.put(var5.mUri, SystemClock.elapsedRealtime());
                  var11.zza(var5.zzaGb.mContext, var5.zzaGb.zzaFW, false);
               }

               if (!(var11 instanceof zzd)) {
                  var5.zzaGb.zzaFX.remove(var11);
               }
            }
         }

         this.zztJ.countDown();
         synchronized(ImageManager.zzaFR) {
            ImageManager.zzaFS.remove(this.mUri);
         }
      }
   }

   final class zzb implements Runnable {
      private final Uri mUri;
      private final ParcelFileDescriptor zzaGc;
      // $FF: synthetic field
      private ImageManager zzaGb;

      public zzb(Uri var2, ParcelFileDescriptor var3) {
         this.zzaGb = ImageManager.this;
         super();
         this.mUri = var2;
         this.zzaGc = var3;
      }

      public final void run() {
         String var6 = "LoadBitmapFromDiskRunnable can't be executed in the main thread";
         if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            String var7 = String.valueOf(Thread.currentThread());
            String var8 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", (new StringBuilder(56 + String.valueOf(var7).length() + String.valueOf(var8).length())).append("checkNotMainThread: current thread ").append(var7).append(" IS the main thread ").append(var8).append("!").toString());
            throw new IllegalStateException(var6);
         } else {
            boolean var1 = false;
            Bitmap var2 = null;
            if (this.zzaGc != null) {
               try {
                  var2 = BitmapFactory.decodeFileDescriptor(this.zzaGc.getFileDescriptor());
               } catch (OutOfMemoryError var11) {
                  String var4 = String.valueOf(this.mUri);
                  Log.e("ImageManager", (new StringBuilder(34 + String.valueOf(var4).length())).append("OOM while loading bitmap for uri: ").append(var4).toString(), var11);
                  var1 = true;
               }

               try {
                  this.zzaGc.close();
               } catch (IOException var10) {
                  Log.e("ImageManager", "closed failed", var10);
               }
            }

            CountDownLatch var3 = new CountDownLatch(1);
            this.zzaGb.mHandler.post(this.zzaGb.new zzd(this.mUri, var2, var1, var3));

            try {
               var3.await();
            } catch (InterruptedException var9) {
               String var5 = String.valueOf(this.mUri);
               Log.w("ImageManager", (new StringBuilder(32 + String.valueOf(var5).length())).append("Latch interrupted while posting ").append(var5).toString());
            }
         }
      }
   }

   @KeepName
   final class ImageReceiver extends ResultReceiver {
      private final Uri mUri;
      private final ArrayList zzaGa;
      // $FF: synthetic field
      private ImageManager zzaGb;

      ImageReceiver(Uri var2) {
         this.zzaGb = ImageManager.this;
         super(new Handler(Looper.getMainLooper()));
         this.mUri = var2;
         this.zzaGa = new ArrayList();
      }

      public final void zzb(zza var1) {
         com.google.android.gms.common.internal.zzc.zzcz("ImageReceiver.addImageRequest() must be called in the main thread");
         this.zzaGa.add(var1);
      }

      public final void zzc(zza var1) {
         com.google.android.gms.common.internal.zzc.zzcz("ImageReceiver.removeImageRequest() must be called in the main thread");
         this.zzaGa.remove(var1);
      }

      public final void zzqV() {
         Intent var1;
         (var1 = new Intent("com.google.android.gms.common.images.LOAD_IMAGE")).putExtra("com.google.android.gms.extras.uri", this.mUri);
         var1.putExtra("com.google.android.gms.extras.resultReceiver", this);
         var1.putExtra("com.google.android.gms.extras.priority", 3);
         this.zzaGb.mContext.sendBroadcast(var1);
      }

      public final void onReceiveResult(int var1, Bundle var2) {
         ParcelFileDescriptor var3 = (ParcelFileDescriptor)var2.getParcelable("com.google.android.gms.extra.fileDescriptor");
         this.zzaGb.zzaFU.execute(this.zzaGb.new zzb(this.mUri, var3));
      }
   }

   final class zzc implements Runnable {
      private final zza zzaGd;
      // $FF: synthetic field
      private ImageManager zzaGb;

      public zzc(zza var2) {
         this.zzaGb = ImageManager.this;
         super();
         this.zzaGd = var2;
      }

      public final void run() {
         com.google.android.gms.common.internal.zzc.zzcz("LoadImageRunnable must be executed on the main thread");
         ImageManager.ImageReceiver var1;
         if ((var1 = (ImageManager.ImageReceiver)this.zzaGb.zzaFX.get(this.zzaGd)) != null) {
            this.zzaGb.zzaFX.remove(this.zzaGd);
            var1.zzc(this.zzaGd);
         }

         zzb var2 = this.zzaGd.zzaGf;
         if (this.zzaGd.zzaGf.uri == null) {
            this.zzaGd.zza(this.zzaGb.mContext, this.zzaGb.zzaFW, true);
         } else {
            Bitmap var3;
            if ((var3 = this.zzaGb.zza(var2)) != null) {
               this.zzaGd.zza(this.zzaGb.mContext, var3, true);
            } else {
               Long var4;
               if ((var4 = (Long)this.zzaGb.zzaFZ.get(var2.uri)) != null) {
                  if (SystemClock.elapsedRealtime() - var4.longValue() < 3600000L) {
                     this.zzaGd.zza(this.zzaGb.mContext, this.zzaGb.zzaFW, true);
                     return;
                  }

                  this.zzaGb.zzaFZ.remove(var2.uri);
               }

               this.zzaGd.zza(this.zzaGb.mContext, this.zzaGb.zzaFW);
               if ((var1 = (ImageManager.ImageReceiver)this.zzaGb.zzaFY.get(var2.uri)) == null) {
                  var1 = this.zzaGb.new ImageReceiver(var2.uri);
                  this.zzaGb.zzaFY.put(var2.uri, var1);
               }

               var1.zzb(this.zzaGd);
               if (!(this.zzaGd instanceof zzd)) {
                  this.zzaGb.zzaFX.put(this.zzaGd, var1);
               }

               synchronized(ImageManager.zzaFR) {
                  if (!ImageManager.zzaFS.contains(var2.uri)) {
                     ImageManager.zzaFS.add(var2.uri);
                     var1.zzqV();
                  }

               }
            }
         }
      }
   }

   public interface OnImageLoadedListener {
      void onImageLoaded(Uri var1, Drawable var2, boolean var3);
   }
}
