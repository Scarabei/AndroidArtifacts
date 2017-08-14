package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.gms.cast.AdBreakInfo;
import java.util.Iterator;
import java.util.List;

public final class zzavr extends View {
   private final int zzavf;
   private int zzavg = 1;
   private List zzaqe;
   private Paint zzavh;

   public zzavr(Context var1) {
      super(var1);
      int var10001;
      Context var2;
      if ((var2 = this.getContext()) == null) {
         var10001 = (int)Math.round(3.0D);
      } else {
         DisplayMetrics var3 = var2.getResources().getDisplayMetrics();
         var10001 = (int)Math.round(3.0D * (double)var3.density);
      }

      this.zzavf = var10001;
   }

   public final synchronized void zzac(int var1) {
      this.zzavg = var1;
   }

   public final synchronized void zzb(List var1, @ColorInt int var2) {
      this.zzaqe = var1;
      this.zzavh = new Paint(1);
      this.zzavh.setColor(-1);
      this.zzavh.setStyle(Style.FILL);
      this.invalidate();
   }

   protected final synchronized void onDraw(@NonNull Canvas var1) {
      super.onDraw(var1);
      if (this.zzaqe != null && !this.zzaqe.isEmpty()) {
         int var2 = Math.round((float)this.getMeasuredHeight() / 2.0F);
         int var3 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
         Iterator var4 = this.zzaqe.iterator();

         while(var4.hasNext()) {
            AdBreakInfo var5;
            long var6;
            if ((var5 = (AdBreakInfo)var4.next()) != null && (var6 = var5.getPlaybackPositionInMs()) >= 0L && var6 <= (long)this.zzavg) {
               int var8 = (int)((double)var6 * (double)var3 / (double)this.zzavg);
               int var9 = this.getPaddingLeft() + var8;
               var1.drawCircle((float)var9, (float)var2, (float)this.zzavf, this.zzavh);
            }
         }

      }
   }
}
