package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.fc;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;

public final class FaceDetector extends Detector {
   public static final int NO_LANDMARKS = 0;
   public static final int ALL_LANDMARKS = 1;
   public static final int NO_CLASSIFICATIONS = 0;
   public static final int ALL_CLASSIFICATIONS = 1;
   public static final int FAST_MODE = 0;
   public static final int ACCURATE_MODE = 1;
   private final zzc zzbNq;
   private final com.google.android.gms.vision.face.internal.client.zza zzbNr;
   private final Object mLock;
   private boolean zzbNs;

   public final void release() {
      super.release();
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbNs) {
            this.zzbNr.zzDQ();
            this.zzbNs = false;
         }
      }
   }

   protected final void finalize() throws Throwable {
      try {
         Object var1 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzbNs) {
               Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
               this.release();
            }
         }
      } finally {
         super.finalize();
      }

   }

   public final SparseArray detect(Frame var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No frame supplied.");
      } else {
         ByteBuffer var2 = var1.getGrayscaleImageData();
         Object var4 = this.mLock;
         Face[] var3;
         synchronized(this.mLock) {
            if (!this.zzbNs) {
               throw new RuntimeException("Cannot use detector after release()");
            }

            var3 = this.zzbNr.zzb(var2, fc.zzc(var1));
         }

         int var14 = 0;
         HashSet var5 = new HashSet();
         SparseArray var6 = new SparseArray(var3.length);
         Face[] var7 = var3;
         int var8 = var3.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            Face var10;
            int var11 = (var10 = var7[var9]).getId();
            var14 = Math.max(var14, var11);
            if (var5.contains(var11)) {
               ++var14;
               var11 = var14;
            }

            var5.add(var11);
            int var12 = this.zzbNq.zzbL(var11);
            var6.append(var12, var10);
         }

         return var6;
      }
   }

   public final boolean setFocus(int var1) {
      int var2 = this.zzbNq.zzbM(var1);
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzbNs) {
            throw new RuntimeException("Cannot use detector after release()");
         } else {
            return this.zzbNr.zzbN(var2);
         }
      }
   }

   public final boolean isOperational() {
      return this.zzbNr.isOperational();
   }

   private FaceDetector() {
      this.zzbNq = new zzc();
      this.mLock = new Object();
      this.zzbNs = true;
      throw new IllegalStateException("Default constructor called");
   }

   private FaceDetector(com.google.android.gms.vision.face.internal.client.zza var1) {
      this.zzbNq = new zzc();
      this.mLock = new Object();
      this.zzbNs = true;
      this.zzbNr = var1;
   }

   // $FF: synthetic method
   FaceDetector(com.google.android.gms.vision.face.internal.client.zza var1, zza var2) {
      this(var1);
   }

   public static class Builder {
      private final Context mContext;
      private int zzbNt = 0;
      private boolean zzbNu = false;
      private int zzbNv = 0;
      private boolean zzbNw = true;
      private int zzaLU = 0;
      private float zzbNx = -1.0F;

      public Builder(Context var1) {
         this.mContext = var1;
      }

      public FaceDetector.Builder setLandmarkType(int var1) {
         if (var1 != 0 && var1 != 1) {
            throw new IllegalArgumentException((new StringBuilder(34)).append("Invalid landmark type: ").append(var1).toString());
         } else {
            this.zzbNt = var1;
            return this;
         }
      }

      public FaceDetector.Builder setProminentFaceOnly(boolean var1) {
         this.zzbNu = var1;
         return this;
      }

      public FaceDetector.Builder setClassificationType(int var1) {
         if (var1 != 0 && var1 != 1) {
            throw new IllegalArgumentException((new StringBuilder(40)).append("Invalid classification type: ").append(var1).toString());
         } else {
            this.zzbNv = var1;
            return this;
         }
      }

      public FaceDetector.Builder setTrackingEnabled(boolean var1) {
         this.zzbNw = var1;
         return this;
      }

      public FaceDetector.Builder setMode(int var1) {
         switch(var1) {
         case 0:
         case 1:
            this.zzaLU = var1;
            return this;
         default:
            throw new IllegalArgumentException((new StringBuilder(25)).append("Invalid mode: ").append(var1).toString());
         }
      }

      public FaceDetector.Builder setMinFaceSize(float var1) {
         if (var1 >= 0.0F && var1 <= 1.0F) {
            this.zzbNx = var1;
            return this;
         } else {
            throw new IllegalArgumentException((new StringBuilder(47)).append("Invalid proportional face size: ").append(var1).toString());
         }
      }

      public FaceDetector build() {
         com.google.android.gms.vision.face.internal.client.zzc var1;
         (var1 = new com.google.android.gms.vision.face.internal.client.zzc()).mode = this.zzaLU;
         var1.zzbNG = this.zzbNt;
         var1.zzbNH = this.zzbNv;
         var1.zzbNI = this.zzbNu;
         var1.zzbNJ = this.zzbNw;
         var1.zzbNK = this.zzbNx;
         com.google.android.gms.vision.face.internal.client.zza var2 = new com.google.android.gms.vision.face.internal.client.zza(this.mContext, var1);
         return new FaceDetector(var2, (zza)null);
      }
   }
}
