package com.google.android.gms.internal;

import android.graphics.PointF;
import java.util.ArrayList;

public final class ib {
   private ArrayList zzaLj = new ArrayList();
   private static final hz zzbUC = new hz();
   private static final hy zzbUD = new hy();

   public final void moveTo(float var1, float var2) {
      this.zzaLj.add(new ie(0.0F, 0.0F, false));
   }

   public final void cubicTo(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.zzaLj.add(new id(var1, 0.0F, var3, 1.0F, 1.0F, 1.0F, false));
   }

   final float[] zzf(float var1) {
      int var2 = this.zzaLj.size();
      ArrayList var3 = new ArrayList(var2 + 1);
      ArrayList var4 = new ArrayList(var2 + 1);
      PointF var5 = new PointF();

      for(int var6 = 0; var6 < var2; ++var6) {
         ((if)this.zzaLj.get(var6)).zza(var3, var4, 4.0000004E-6F, var5);
      }

      if (var3.isEmpty()) {
         var3.add(new PointF(0.0F, 0.0F));
         var4.add(0.0F);
      }

      float var12;
      if ((var12 = ((Float)var4.get(var4.size() - 1)).floatValue()) == 0.0F) {
         var3.add((PointF)var3.get(var3.size() - 1));
         var4.add(1.0F);
         var12 = 1.0F;
      }

      int var7;
      float[] var8 = new float[(var7 = var3.size()) * 3];
      int var9 = 0;

      for(int var10 = 0; var10 < var7; ++var10) {
         PointF var11 = (PointF)var3.get(var10);
         var8[var9++] = ((Float)var4.get(var10)).floatValue() / var12;
         var8[var9++] = var11.x;
         var8[var9++] = var11.y;
      }

      return var8;
   }

   private static void zza(PointF[] var0, hx var1, boolean var2, ArrayList var3, ArrayList var4, float var5, PointF var6) {
      ic var7 = new ic((ic)null, 1.0F, var1.zza(1.0F, var0, new PointF()));
      ic var8;
      ic var9 = var8 = new ic(var7, 0.0F, var1.zza(0.0F, var0, new PointF()));

      PointF var12;
      for(float[] var10 = new float[1]; var7 != null; var7 = var7.zzbUE) {
         boolean var11;
         do {
            var12 = new PointF();
            if (!(var11 = zza(var0, var1, var9.zzbUF, var9.zzbUG, var7.zzbUF, var7.zzbUG, var10, var12, var5)) && var2) {
               PointF var13 = new PointF();
               float[] var14 = new float[1];
               if (var11 = zza(var0, var1, var9.zzbUF, var9.zzbUG, var10[0], var12, var14, var13, var5)) {
                  var2 = false;
               }
            }

            if (var11) {
               var7 = new ic(var7, var10[0], var12);
               var9.zzbUE = var7;
            }
         } while(var11);

         var9 = var7;
      }

      var9 = var8;
      if (var3.isEmpty()) {
         var3.add(new PointF(0.0F, 0.0F));
         var4.add(0.0F);
         var6.set(0.0F, 0.0F);
      }

      var12 = (PointF)var3.get(var3.size() - 1);

      for(float var15 = ((Float)var4.get(var4.size() - 1)).floatValue(); var9 != null; var9 = var9.zzbUE) {
         var3.add(var9.zzbUG);
         var15 += PointF.length(var9.zzbUG.x - var12.x, var9.zzbUG.y - var12.y);
         var4.add(var15);
         var12 = var9.zzbUG;
      }

   }

   private static boolean zza(PointF[] var0, hx var1, float var2, PointF var3, float var4, PointF var5, float[] var6, PointF var7, float var8) {
      var6[0] = (var4 + var2) / 2.0F;
      float var9 = (var5.x + var3.x) / 2.0F;
      float var10 = (var5.y + var3.y) / 2.0F;
      var1.zza(var6[0], var0, var7);
      float var11 = var7.x - var9;
      float var12 = var7.y - var10;
      return var11 * var11 + var12 * var12 > var8;
   }

   // $FF: synthetic method
   static void zzb(PointF[] var0, hx var1, boolean var2, ArrayList var3, ArrayList var4, float var5, PointF var6) {
      zza(var0, var1, true, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   static hy zzEm() {
      return zzbUD;
   }
}
