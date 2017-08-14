package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzno extends RelativeLayout {
   private static final float[] zzHx = new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
   @Nullable
   private AnimationDrawable zzHy;

   public zzno(Context var1, zznn var2, LayoutParams var3) {
      super(var1);
      zzbo.zzu(var2);
      ShapeDrawable var4;
      (var4 = new ShapeDrawable(new RoundRectShape(zzHx, (RectF)null, (float[])null))).getPaint().setColor(var2.getBackgroundColor());
      this.setLayoutParams(var3);
      com.google.android.gms.ads.internal.zzbs.zzbB().setBackground(this, var4);
      LayoutParams var5 = new LayoutParams(-2, -2);
      if (!TextUtils.isEmpty(var2.getText())) {
         LayoutParams var6 = new LayoutParams(-2, -2);
         TextView var7;
         (var7 = new TextView(var1)).setLayoutParams(var6);
         var7.setId(1195835393);
         var7.setTypeface(Typeface.DEFAULT);
         var7.setText(var2.getText());
         var7.setTextColor(var2.getTextColor());
         var7.setTextSize((float)var2.getTextSize());
         zzji.zzds();
         int var10001 = zzaiy.zzc(var1, 4);
         zzji.zzds();
         var7.setPadding(var10001, 0, zzaiy.zzc(var1, 4), 0);
         this.addView(var7);
         var5.addRule(1, var7.getId());
      }

      ImageView var10;
      (var10 = new ImageView(var1)).setLayoutParams(var5);
      var10.setId(1195835394);
      List var11;
      if ((var11 = var2.zzec()).size() > 1) {
         this.zzHy = new AnimationDrawable();
         Iterator var8 = var11.iterator();

         while(var8.hasNext()) {
            Drawable var9 = (Drawable)var8.next();
            this.zzHy.addFrame(var9, var2.zzed());
         }

         com.google.android.gms.ads.internal.zzbs.zzbB().setBackground(var10, this.zzHy);
      } else if (var11.size() == 1) {
         var10.setImageDrawable((Drawable)var11.get(0));
      }

      this.addView(var10);
   }

   public final void onAttachedToWindow() {
      if (this.zzHy != null) {
         this.zzHy.start();
      }

      super.onAttachedToWindow();
   }
}
