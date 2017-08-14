package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.fc;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fq;
import com.google.android.gms.internal.fr;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.Frame.Metadata;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector {
   private final fq zzbNU;

   private TextRecognizer() {
      throw new IllegalStateException("Default constructor called");
   }

   private TextRecognizer(fq var1) {
      this.zzbNU = var1;
   }

   public final SparseArray detect(Frame var1) {
      fm var4 = new fm(new Rect());
      if (var1 == null) {
         throw new IllegalArgumentException("No frame supplied.");
      } else {
         fc var5 = fc.zzc(var1);
         Bitmap var6;
         int var9;
         int var10;
         int var10001;
         if (var1.getBitmap() != null) {
            var6 = var1.getBitmap();
         } else {
            Metadata var7 = var1.getMetadata();
            ByteBuffer var10000 = var1.getGrayscaleImageData();
            var10001 = var7.getFormat();
            int var11 = var5.height;
            var10 = var5.width;
            var9 = var10001;
            ByteBuffer var8 = var10000;
            byte[] var12;
            if (var10000.hasArray() && var8.arrayOffset() == 0) {
               var12 = var8.array();
            } else {
               var12 = new byte[var8.capacity()];
               var8.get(var12);
            }

            ByteArrayOutputStream var13 = new ByteArrayOutputStream();
            (new YuvImage(var12, var9, var10, var11, (int[])null)).compressToJpeg(new Rect(0, 0, var10, var11), 100, var13);
            byte[] var14;
            var6 = BitmapFactory.decodeByteArray(var14 = var13.toByteArray(), 0, var14.length);
         }

         Bitmap var15 = var6;
         int var17 = var6.getWidth();
         int var18 = var6.getHeight();
         if (var5.rotation != 0) {
            Matrix var19;
            Matrix var22 = var19 = new Matrix();
            short var24;
            switch(var5.rotation) {
            case 0:
               var24 = 0;
               break;
            case 1:
               var24 = 90;
               break;
            case 2:
               var24 = 180;
               break;
            case 3:
               var24 = 270;
               break;
            default:
               throw new IllegalArgumentException("Unsupported rotation degree.");
            }

            var22.postRotate((float)var24);
            var15 = Bitmap.createBitmap(var6, 0, 0, var17, var18, var19, false);
         }

         if (var5.rotation == 1 || var5.rotation == 3) {
            var5.width = var18;
            var5.height = var17;
         }

         if (!var4.zzbOh.isEmpty()) {
            Rect var23 = var4.zzbOh;
            var10001 = var1.getMetadata().getWidth();
            var10 = var1.getMetadata().getHeight();
            var9 = var10001;
            Rect var21 = var23;
            switch(var5.rotation) {
            case 1:
               var23 = new Rect(var10 - var21.bottom, var21.left, var10 - var21.top, var21.right);
               break;
            case 2:
               var23 = new Rect(var9 - var21.right, var10 - var21.bottom, var9 - var21.left, var10 - var21.top);
               break;
            case 3:
               var23 = new Rect(var21.top, var9 - var21.right, var21.bottom, var9 - var21.left);
               break;
            default:
               var23 = var21;
            }

            Rect var20 = var23;
            var4.zzbOh.set(var20);
         }

         var5.rotation = 0;
         return zza(this.zzbNU.zza(var15, var5, var4));
      }
   }

   private static SparseArray zza(fk[] var0) {
      SparseArray var1 = new SparseArray();
      fk[] var2 = var0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         fk var5 = var2[var4];
         SparseArray var6;
         if ((var6 = (SparseArray)var1.get(var5.zzbOf)) == null) {
            var6 = new SparseArray();
            var1.append(var5.zzbOf, var6);
         }

         var6.append(var5.zzbOg, var5);
      }

      SparseArray var7 = new SparseArray(var1.size());

      for(var3 = 0; var3 < var1.size(); ++var3) {
         var7.append(var1.keyAt(var3), new TextBlock((SparseArray)var1.valueAt(var3)));
      }

      return var7;
   }

   public final boolean isOperational() {
      return this.zzbNU.isOperational();
   }

   public final void release() {
      super.release();
      this.zzbNU.zzDQ();
   }

   // $FF: synthetic method
   TextRecognizer(fq var1, zzb var2) {
      this(var1);
   }

   public static class Builder {
      private Context mContext;
      private fr zzbNV;

      public Builder(Context var1) {
         this.mContext = var1;
         this.zzbNV = new fr();
      }

      public TextRecognizer build() {
         fq var1 = new fq(this.mContext, this.zzbNV);
         return new TextRecognizer(var1, (zzb)null);
      }
   }
}
