package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzr {
   public final int index;
   public final LayoutParams zzPa;
   public final ViewGroup parent;
   public final Context zzqD;

   public zzr(zzaka var1) throws zzp {
      this.zzPa = var1.getLayoutParams();
      ViewParent var2 = var1.getParent();
      this.zzqD = var1.zzit();
      if (var2 != null && var2 instanceof ViewGroup) {
         this.parent = (ViewGroup)var2;
         this.index = this.parent.indexOfChild(var1.getView());
         this.parent.removeView(var1.getView());
         var1.zzA(true);
      } else {
         throw new zzp("Could not get the parent of the WebView for an overlay.");
      }
   }
}
