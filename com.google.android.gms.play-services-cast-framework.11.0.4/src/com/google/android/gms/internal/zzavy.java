package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.lang.ref.WeakReference;

public final class zzavy extends UIController {
   private final View mView;
   private final WeakReference zzavm;
   private final OnClickListener zzavp;
   private final ComponentName zzavy;

   public zzavy(View var1, Activity var2) {
      this.mView = var1;
      this.zzavm = new WeakReference(var2);
      CastMediaOptions var3;
      if ((var3 = CastContext.getSharedInstance(var2).getCastOptions().getCastMediaOptions()) != null && !TextUtils.isEmpty(var3.getExpandedControllerActivityClassName())) {
         this.zzavy = new ComponentName(var2.getApplicationContext(), var3.getExpandedControllerActivityClassName());
         this.zzavp = new zzavz(this);
      } else {
         this.zzavy = null;
         this.zzavp = null;
      }
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.mView.setOnClickListener(this.zzavp);
   }

   public final void onSessionEnded() {
      this.mView.setOnClickListener((OnClickListener)null);
      super.onSessionEnded();
   }

   // $FF: synthetic method
   static WeakReference zza(zzavy var0) {
      return var0.zzavm;
   }

   // $FF: synthetic method
   static ComponentName zzb(zzavy var0) {
      return var0.zzavy;
   }
}
