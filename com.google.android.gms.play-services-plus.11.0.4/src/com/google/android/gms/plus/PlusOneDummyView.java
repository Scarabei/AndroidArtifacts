package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class PlusOneDummyView extends FrameLayout {
   public static final String TAG = "PlusOneDummyView";

   public PlusOneDummyView(Context var1, int var2) {
      super(var1);
      Button var3;
      (var3 = new Button(var1)).setEnabled(false);
      Object var7;
      if (!((PlusOneDummyView.zzd)(var7 = new PlusOneDummyView.zzb(this.getContext(), (zzd)null))).isValid()) {
         var7 = new PlusOneDummyView.zzc(this.getContext(), (zzd)null);
      }

      if (!((PlusOneDummyView.zzd)var7).isValid()) {
         var7 = new PlusOneDummyView.zza(this.getContext(), (zzd)null);
      }

      Drawable var4 = ((PlusOneDummyView.zzd)var7).getDrawable(var2);
      var3.setBackgroundDrawable(var4);
      Point var8 = new Point();
      byte var9;
      byte var10;
      switch(var2) {
      case 0:
         var9 = 24;
         var10 = 14;
         break;
      case 1:
         var9 = 32;
         var10 = 20;
         break;
      case 2:
         var9 = 50;
         var10 = 20;
         break;
      default:
         var9 = 38;
         var10 = 24;
      }

      DisplayMetrics var11 = this.getResources().getDisplayMetrics();
      float var12 = TypedValue.applyDimension(1, (float)var9, var11);
      float var13 = TypedValue.applyDimension(1, (float)var10, var11);
      var8.x = (int)((double)var12 + 0.5D);
      var8.y = (int)((double)var13 + 0.5D);
      this.addView(var3, new LayoutParams(var8.x, var8.y, 17));
   }

   static class zza implements PlusOneDummyView.zzd {
      private Context mContext;

      private zza(Context var1) {
         this.mContext = var1;
      }

      public final Drawable getDrawable(int var1) {
         return this.mContext.getResources().getDrawable(17301508);
      }

      public final boolean isValid() {
         return true;
      }

      // $FF: synthetic method
      zza(Context var1, zzd var2) {
         this(var1);
      }
   }

   static class zzc implements PlusOneDummyView.zzd {
      private Context mContext;

      private zzc(Context var1) {
         this.mContext = var1;
      }

      public final Drawable getDrawable(int var1) {
         String var2;
         switch(var1) {
         case 0:
            var2 = "ic_plusone_small_off_client";
            break;
         case 1:
            var2 = "ic_plusone_medium_off_client";
            break;
         case 2:
            var2 = "ic_plusone_tall_off_client";
            break;
         default:
            var2 = "ic_plusone_standard_off_client";
         }

         int var3 = this.mContext.getResources().getIdentifier(var2, "drawable", this.mContext.getPackageName());
         return this.mContext.getResources().getDrawable(var3);
      }

      public final boolean isValid() {
         int var1 = this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName());
         int var2 = this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName());
         int var3 = this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName());
         int var4 = this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName());
         return var1 != 0 && var2 != 0 && var3 != 0 && var4 != 0;
      }

      // $FF: synthetic method
      zzc(Context var1, zzd var2) {
         this(var1);
      }
   }

   static class zzb implements PlusOneDummyView.zzd {
      private Context mContext;

      private zzb(Context var1) {
         this.mContext = var1;
      }

      public final Drawable getDrawable(int var1) {
         try {
            Resources var2 = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
            String var3 = "com.google.android.gms";
            String var4;
            switch(var1) {
            case 0:
               var4 = "ic_plusone_small";
               break;
            case 1:
               var4 = "ic_plusone_medium";
               break;
            case 2:
               var4 = "ic_plusone_tall";
               break;
            default:
               var4 = "ic_plusone_standard";
            }

            int var5 = var2.getIdentifier(var4, "drawable", var3);
            return var2.getDrawable(var5);
         } catch (NameNotFoundException var6) {
            return null;
         }
      }

      public final boolean isValid() {
         try {
            this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
            return true;
         } catch (NameNotFoundException var1) {
            return false;
         }
      }

      // $FF: synthetic method
      zzb(Context var1, zzd var2) {
         this(var1);
      }
   }

   interface zzd {
      Drawable getDrawable(int var1);

      boolean isValid();
   }
}
