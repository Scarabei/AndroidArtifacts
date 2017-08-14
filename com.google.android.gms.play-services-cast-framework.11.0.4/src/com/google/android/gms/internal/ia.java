package com.google.android.gms.internal;

import android.view.animation.Interpolator;

public final class ia implements Interpolator {
   private float[] zzbUA;
   private float[] zzbUB;

   public ia(float var1, float var2, float var3, float var4) {
      ib var5;
      (var5 = new ib()).moveTo(0.0F, 0.0F);
      var5.cubicTo(var1, 0.0F, var3, 1.0F, 1.0F, 1.0F);
      this.zza(var5);
   }

   private final void zza(ib var1) {
      float[] var2;
      int var3 = (var2 = var1.zzf(0.002F)).length / 3;
      if (var2[1] == 0.0F && var2[2] == 0.0F && var2[var2.length - 2] == 1.0F && var2[var2.length - 1] == 1.0F) {
         this.zzbUA = new float[var3];
         this.zzbUB = new float[var3];
         float var4 = 0.0F;
         float var5 = 0.0F;
         int var6 = 0;

         for(int var7 = 0; var7 < var3; ++var7) {
            float var8 = var2[var6++];
            float var9 = var2[var6++];
            float var10 = var2[var6++];
            if (var8 == var5 && var9 != var4) {
               throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis.");
            }

            if (var9 < var4) {
               throw new IllegalArgumentException("The Path cannot loop back on itself.");
            }

            this.zzbUA[var7] = var9;
            this.zzbUB[var7] = var10;
            var4 = var9;
            var5 = var8;
         }

      } else {
         throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");
      }
   }

   public final float getInterpolation(float var1) {
      if (var1 <= 0.0F) {
         return 0.0F;
      } else if (var1 >= 1.0F) {
         return 1.0F;
      } else {
         int var2 = 0;
         int var3 = this.zzbUA.length - 1;

         while(var3 - var2 > 1) {
            int var4 = (var2 + var3) / 2;
            if (var1 < this.zzbUA[var4]) {
               var3 = var4;
            } else {
               var2 = var4;
            }
         }

         float var8;
         if ((var8 = this.zzbUA[var3] - this.zzbUA[var2]) == 0.0F) {
            return this.zzbUB[var2];
         } else {
            float var5 = (var1 - this.zzbUA[var2]) / var8;
            float var6 = this.zzbUB[var2];
            float var7 = this.zzbUB[var3];
            return var6 + var5 * (var7 - var6);
         }
      }
   }
}
