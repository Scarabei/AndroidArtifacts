package com.google.android.gms.games.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

@TargetApi(12)
final class zzq extends zzn implements OnAttachStateChangeListener, OnGlobalLayoutListener {
   private WeakReference zzbaO;
   private boolean zzaZu = false;

   protected zzq(GamesClientImpl var1, int var2) {
      super(var1, var2, (zzo)null);
   }

   protected final void zzbb(int var1) {
      this.zzbaL = new zzp(var1, (IBinder)null, (zzo)null);
   }

   @TargetApi(16)
   public final void zzt(View var1) {
      this.zzbaK.zzuP();
      if (this.zzbaO != null) {
         View var2 = (View)this.zzbaO.get();
         Context var3 = this.zzbaK.getContext();
         if (var2 == null && var3 instanceof Activity) {
            var2 = ((Activity)var3).getWindow().getDecorView();
         }

         if (var2 != null) {
            var2.removeOnAttachStateChangeListener(this);
            ViewTreeObserver var4 = var2.getViewTreeObserver();
            if (com.google.android.gms.common.util.zzq.zzrZ()) {
               var4.removeOnGlobalLayoutListener(this);
            } else {
               var4.removeGlobalOnLayoutListener(this);
            }
         }
      }

      this.zzbaO = null;
      Context var5 = this.zzbaK.getContext();
      if (var1 == null && var5 instanceof Activity) {
         if ((var1 = ((Activity)var5).findViewById(16908290)) == null) {
            var1 = ((Activity)var5).getWindow().getDecorView();
         }

         zze.zzy("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
      }

      if (var1 != null) {
         this.zzu(var1);
         this.zzbaO = new WeakReference(var1);
         var1.addOnAttachStateChangeListener(this);
         var1.getViewTreeObserver().addOnGlobalLayoutListener(this);
      } else {
         zze.zzz("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
      }
   }

   public final void zzuV() {
      if (this.zzbaL.zzbaM != null) {
         super.zzuV();
      } else {
         this.zzaZu = this.zzbaO != null;
      }
   }

   public final void onViewAttachedToWindow(View var1) {
      this.zzu(var1);
   }

   public final void onViewDetachedFromWindow(View var1) {
      this.zzbaK.zzuP();
      var1.removeOnAttachStateChangeListener(this);
   }

   public final void onGlobalLayout() {
      if (this.zzbaO != null) {
         View var1;
         if ((var1 = (View)this.zzbaO.get()) != null) {
            this.zzu(var1);
         }
      }
   }

   @TargetApi(17)
   private final void zzu(View var1) {
      int var2 = -1;
      if (com.google.android.gms.common.util.zzq.zzsa()) {
         Display var3;
         if ((var3 = var1.getDisplay()) != null) {
            var2 = var3.getDisplayId();
         } else {
            var2 = -1;
         }
      }

      IBinder var7 = var1.getWindowToken();
      int[] var4 = new int[2];
      var1.getLocationInWindow(var4);
      int var5 = var1.getWidth();
      int var6 = var1.getHeight();
      this.zzbaL.zzbaN = var2;
      this.zzbaL.zzbaM = var7;
      this.zzbaL.left = var4[0];
      this.zzbaL.top = var4[1];
      this.zzbaL.right = var4[0] + var5;
      this.zzbaL.bottom = var4[1] + var6;
      if (this.zzaZu) {
         this.zzuV();
         this.zzaZu = false;
      }

   }
}
