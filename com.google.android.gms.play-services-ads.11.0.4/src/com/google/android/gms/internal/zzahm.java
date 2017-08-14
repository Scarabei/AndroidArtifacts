package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.WebSettings;

@TargetApi(16)
public class zzahm extends zzahj {
   public final void zzb(Activity var1, OnGlobalLayoutListener var2) {
      Window var3;
      if ((var3 = var1.getWindow()) != null && var3.getDecorView() != null && var3.getDecorView().getViewTreeObserver() != null) {
         var3.getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(var2);
      }

   }

   public final void setBackground(View var1, Drawable var2) {
      var1.setBackground(var2);
   }

   public final void zza(ViewTreeObserver var1, OnGlobalLayoutListener var2) {
      var1.removeOnGlobalLayoutListener(var2);
   }

   public boolean zza(Context var1, WebSettings var2) {
      super.zza(var1, var2);
      var2.setAllowFileAccessFromFileURLs(false);
      var2.setAllowUniversalAccessFromFileURLs(false);
      return true;
   }
}
