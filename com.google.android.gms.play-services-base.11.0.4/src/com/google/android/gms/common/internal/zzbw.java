package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.R.color;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.util.zzj;

public final class zzbw extends Button {
   public zzbw(Context var1) {
      this(var1, (AttributeSet)null);
   }

   private zzbw(Context var1, AttributeSet var2) {
      super(var1, (AttributeSet)null, 16842824);
   }

   public final void zza(Resources var1, int var2, int var3) {
      this.setTypeface(Typeface.DEFAULT_BOLD);
      this.setTextSize(14.0F);
      float var6 = var1.getDisplayMetrics().density;
      this.setMinHeight((int)(var6 * 48.0F + 0.5F));
      this.setMinWidth((int)(var6 * 48.0F + 0.5F));
      int var10001 = zzf(var3, drawable.common_google_signin_btn_icon_dark, drawable.common_google_signin_btn_icon_light, drawable.common_google_signin_btn_icon_light);
      int var12 = zzf(var3, drawable.common_google_signin_btn_text_dark, drawable.common_google_signin_btn_text_light, drawable.common_google_signin_btn_text_light);
      int var11 = var10001;
      int var10000;
      switch(var2) {
      case 0:
      case 1:
         var10000 = var12;
         break;
      case 2:
         var10000 = var11;
         break;
      default:
         throw new IllegalStateException((new StringBuilder(32)).append("Unknown button size: ").append(var2).toString());
      }

      int var8 = var10000;
      Drawable var9;
      DrawableCompat.setTintList(var9 = DrawableCompat.wrap(var1.getDrawable(var8)), var1.getColorStateList(color.common_google_signin_btn_tint));
      DrawableCompat.setTintMode(var9, Mode.SRC_ATOP);
      this.setBackgroundDrawable(var9);
      var8 = zzf(var3, color.common_google_signin_btn_text_dark, color.common_google_signin_btn_text_light, color.common_google_signin_btn_text_light);
      this.setTextColor((ColorStateList)zzbo.zzu(var1.getColorStateList(var8)));
      switch(var2) {
      case 0:
         this.setText(var1.getString(string.common_signin_button_text));
         break;
      case 1:
         this.setText(var1.getString(string.common_signin_button_text_long));
         break;
      case 2:
         this.setText((CharSequence)null);
         break;
      default:
         throw new IllegalStateException((new StringBuilder(32)).append("Unknown button size: ").append(var2).toString());
      }

      this.setTransformationMethod((TransformationMethod)null);
      if (zzj.zzaG(this.getContext())) {
         this.setGravity(19);
      }

   }

   private static int zzf(int var0, int var1, int var2, int var3) {
      switch(var0) {
      case 0:
         return var1;
      case 1:
         return var2;
      case 2:
         return var3;
      default:
         throw new IllegalStateException((new StringBuilder(33)).append("Unknown color scheme: ").append(var0).toString());
      }
   }
}
