package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzae extends FrameLayout implements OnClickListener {
   private final ImageButton zzPG;
   private final zzaj zzPH;

   public zzae(Context var1, zzaf var2, zzaj var3) {
      super(var1);
      this.zzPH = var3;
      this.setOnClickListener(this);
      this.zzPG = new ImageButton(var1);
      this.zzPG.setImageResource(17301527);
      this.zzPG.setBackgroundColor(0);
      this.zzPG.setOnClickListener(this);
      ImageButton var10000 = this.zzPG;
      zzji.zzds();
      int var10001 = zzaiy.zzc(var1, var2.paddingLeft);
      zzji.zzds();
      int var10002 = zzaiy.zzc(var1, 0);
      zzji.zzds();
      int var10003 = zzaiy.zzc(var1, var2.paddingRight);
      zzji.zzds();
      var10000.setPadding(var10001, var10002, var10003, zzaiy.zzc(var1, var2.paddingBottom));
      this.zzPG.setContentDescription("Interstitial close button");
      zzji.zzds();
      zzaiy.zzc(var1, var2.size);
      ImageButton var4 = this.zzPG;
      zzji.zzds();
      int var10004 = zzaiy.zzc(var1, var2.size + var2.paddingLeft + var2.paddingRight);
      zzji.zzds();
      this.addView(var4, new LayoutParams(var10004, zzaiy.zzc(var1, var2.size + var2.paddingBottom), 17));
   }

   public final void onClick(View var1) {
      if (this.zzPH != null) {
         this.zzPH.zzfJ();
      }

   }

   public final void zza(boolean var1, boolean var2) {
      if (var2) {
         if (var1) {
            this.zzPG.setVisibility(4);
         } else {
            this.zzPG.setVisibility(8);
         }
      } else {
         this.zzPG.setVisibility(0);
      }
   }
}
