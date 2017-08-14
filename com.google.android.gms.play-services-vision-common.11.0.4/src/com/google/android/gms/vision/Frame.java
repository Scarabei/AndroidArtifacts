package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public class Frame {
   public static final int ROTATION_0 = 0;
   public static final int ROTATION_90 = 1;
   public static final int ROTATION_180 = 2;
   public static final int ROTATION_270 = 3;
   private Frame.Metadata zzbMV;
   private ByteBuffer zzbMW;
   private Bitmap mBitmap;

   public Frame.Metadata getMetadata() {
      return this.zzbMV;
   }

   public ByteBuffer getGrayscaleImageData() {
      if (this.mBitmap == null) {
         return this.zzbMW;
      } else {
         int var2 = this.mBitmap.getWidth();
         int var3 = this.mBitmap.getHeight();
         int[] var4 = new int[var2 * var3];
         this.mBitmap.getPixels(var4, 0, var2, 0, 0, var2, var3);
         byte[] var5 = new byte[var2 * var3];

         for(int var6 = 0; var6 < var4.length; ++var6) {
            var5[var6] = (byte)((int)((float)Color.red(var4[var6]) * 0.299F + (float)Color.green(var4[var6]) * 0.587F + (float)Color.blue(var4[var6]) * 0.114F));
         }

         return ByteBuffer.wrap(var5);
      }
   }

   public Bitmap getBitmap() {
      return this.mBitmap;
   }

   private Frame() {
      this.zzbMV = new Frame.Metadata();
      this.zzbMW = null;
      this.mBitmap = null;
   }

   // $FF: synthetic method
   Frame(zzb var1) {
      this();
   }

   public static class Metadata {
      private int zzrW;
      private int zzrX;
      private int mId;
      private long zzbcV;
      private int zzOa;
      private int format = -1;

      public Metadata() {
      }

      public Metadata(Frame.Metadata var1) {
         this.zzrW = var1.getWidth();
         this.zzrX = var1.getHeight();
         this.mId = var1.getId();
         this.zzbcV = var1.getTimestampMillis();
         this.zzOa = var1.getRotation();
      }

      public int getWidth() {
         return this.zzrW;
      }

      public int getHeight() {
         return this.zzrX;
      }

      public int getId() {
         return this.mId;
      }

      public long getTimestampMillis() {
         return this.zzbcV;
      }

      public int getRotation() {
         return this.zzOa;
      }

      public int getFormat() {
         return this.format;
      }

      public final void zzDN() {
         if (this.zzOa % 2 != 0) {
            int var1 = this.zzrW;
            this.zzrW = this.zzrX;
            this.zzrX = var1;
         }

         this.zzOa = 0;
      }
   }

   public static class Builder {
      private Frame zzbMX = new Frame((zzb)null);

      public Frame.Builder setBitmap(Bitmap var1) {
         int var2 = var1.getWidth();
         int var3 = var1.getHeight();
         this.zzbMX.mBitmap = var1;
         Frame.Metadata var4;
         (var4 = this.zzbMX.getMetadata()).zzrW = var2;
         var4.zzrX = var3;
         return this;
      }

      public Frame.Builder setImageData(ByteBuffer var1, int var2, int var3, int var4) {
         if (var1 == null) {
            throw new IllegalArgumentException("Null image data supplied.");
         } else if (var1.capacity() < var2 * var3) {
            throw new IllegalArgumentException("Invalid image data size.");
         } else {
            switch(var4) {
            case 16:
            case 17:
            case 842094169:
               this.zzbMX.zzbMW = var1;
               Frame.Metadata var5;
               (var5 = this.zzbMX.getMetadata()).zzrW = var2;
               var5.zzrX = var3;
               var5.format = var4;
               return this;
            default:
               throw new IllegalArgumentException((new StringBuilder(37)).append("Unsupported image format: ").append(var4).toString());
            }
         }
      }

      public Frame.Builder setId(int var1) {
         this.zzbMX.getMetadata().mId = var1;
         return this;
      }

      public Frame.Builder setTimestampMillis(long var1) {
         this.zzbMX.getMetadata().zzbcV = var1;
         return this;
      }

      public Frame.Builder setRotation(int var1) {
         this.zzbMX.getMetadata().zzOa = var1;
         return this;
      }

      public Frame build() {
         if (this.zzbMX.zzbMW == null && this.zzbMX.mBitmap == null) {
            throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
         } else {
            return this.zzbMX;
         }
      }
   }
}
