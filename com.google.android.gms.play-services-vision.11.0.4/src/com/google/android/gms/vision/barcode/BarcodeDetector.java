package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.eu;
import com.google.android.gms.internal.ew;
import com.google.android.gms.internal.fc;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.nio.ByteBuffer;

public final class BarcodeDetector extends Detector {
   private final ew zzbNg;

   private BarcodeDetector() {
      throw new IllegalStateException("Default constructor called");
   }

   private BarcodeDetector(ew var1) {
      this.zzbNg = var1;
   }

   public final void release() {
      super.release();
      this.zzbNg.zzDQ();
   }

   public final SparseArray detect(Frame var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No frame supplied.");
      } else {
         fc var2 = fc.zzc(var1);
         Barcode[] var3;
         if (var1.getBitmap() != null) {
            if ((var3 = this.zzbNg.zza(var1.getBitmap(), var2)) == null) {
               throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
         } else {
            ByteBuffer var4 = var1.getGrayscaleImageData();
            var3 = this.zzbNg.zza(var4, var2);
         }

         SparseArray var9 = new SparseArray(var3.length);
         Barcode[] var5 = var3;
         int var6 = var3.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            Barcode var8 = var5[var7];
            var9.append(var8.rawValue.hashCode(), var8);
         }

         return var9;
      }
   }

   public final boolean isOperational() {
      return this.zzbNg.isOperational();
   }

   // $FF: synthetic method
   BarcodeDetector(ew var1, zzc var2) {
      this(var1);
   }

   public static class Builder {
      private Context mContext;
      private eu zzbNh;

      public Builder(Context var1) {
         this.mContext = var1;
         this.zzbNh = new eu();
      }

      public BarcodeDetector.Builder setBarcodeFormats(int var1) {
         this.zzbNh.zzbNi = var1;
         return this;
      }

      public BarcodeDetector build() {
         ew var1 = new ew(this.mContext, this.zzbNh);
         return new BarcodeDetector(var1, (zzc)null);
      }
   }
}
