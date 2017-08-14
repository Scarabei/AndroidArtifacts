package com.google.android.gms.cast.framework.media.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.google.android.gms.internal.zzayo;

final class zzb {
   private static final zzayo zzarK = new zzayo("WidgetUtil");

   @TargetApi(17)
   public static Bitmap zza(Context var0, Bitmap var1, float var2, float var3) {
      zzarK.zzb("Begin blurring bitmap %s, original width = %d, original height = %d.", new Object[]{var1, var1.getWidth(), var1.getHeight()});
      int var4 = Math.round((float)var1.getWidth() * 0.25F);
      int var5 = Math.round((float)var1.getHeight() * 0.25F);
      var1 = Bitmap.createScaledBitmap(var1, var4, var5, false);
      Bitmap var6 = Bitmap.createBitmap(var4, var5, var1.getConfig());
      RenderScript var7;
      Allocation var8 = Allocation.createFromBitmap(var7 = RenderScript.create(var0), var1);
      Allocation var9 = Allocation.createTyped(var7, var8.getType());
      ScriptIntrinsicBlur var10;
      (var10 = ScriptIntrinsicBlur.create(var7, var8.getElement())).setInput(var8);
      var10.setRadius(7.5F);
      var10.forEach(var9);
      var9.copyTo(var6);
      var7.destroy();
      zzarK.zzb("End blurring bitmap %s, original width = %d, original height = %d.", new Object[]{var1, var4, var5});
      return var6;
   }

   public static Drawable zza(Context var0, int var1, @DrawableRes int var2) {
      return zza(var0, var1, var2, 16842800, 0);
   }

   public static Drawable zzb(Context var0, int var1, @DrawableRes int var2) {
      return zza(var0, var1, var2, 0, 17170443);
   }

   private static Drawable zza(Context var0, int var1, @DrawableRes int var2, @AttrRes int var3, @ColorRes int var4) {
      Drawable var5;
      DrawableCompat.setTintMode(var5 = DrawableCompat.wrap(var0.getResources().getDrawable(var2).mutate()), Mode.SRC_IN);
      ColorStateList var6;
      if (var1 != 0) {
         var6 = ContextCompat.getColorStateList(var0, var1);
      } else {
         int var7;
         if (var3 != 0) {
            TypedArray var8;
            var7 = (var8 = var0.obtainStyledAttributes(new int[]{var3})).getColor(0, 0);
            var8.recycle();
         } else {
            var7 = ContextCompat.getColor(var0, var4);
         }

         int var11 = ColorUtils.setAlphaComponent(var7, 128);
         int[] var9 = new int[]{var7, var11};
         int[][] var10 = new int[][]{{16842910}, {-16842910}};
         var6 = new ColorStateList(var10, var9);
      }

      DrawableCompat.setTintList(var5, var6);
      return var5;
   }
}
