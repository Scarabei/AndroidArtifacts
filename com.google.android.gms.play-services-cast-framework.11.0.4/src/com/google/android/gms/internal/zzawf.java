package com.google.android.gms.internal;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawf extends UIController {
   private static final zzayo zzarK = new zzayo("MuteToggleUIController");
   private final ImageView zzavr;
   private final String zzavD;
   private final String zzavE;
   private final Context zzarM;
   private final Listener zzaoY;
   private final OnClickListener zzavp;

   public zzawf(ImageView var1, Context var2) {
      this.zzavr = var1;
      this.zzarM = var2.getApplicationContext();
      this.zzavD = this.zzarM.getString(string.cast_mute);
      this.zzavE = this.zzarM.getString(string.cast_unmute);
      this.zzaoY = new zzawg(this);
      this.zzavp = new zzawh(this);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzavr.setOnClickListener(this.zzavp);
      var1.addCastListener(this.zzaoY);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.zzavr.setOnClickListener((OnClickListener)null);
      CastSession var1;
      if ((var1 = CastContext.getSharedInstance(this.zzarM).getSessionManager().getCurrentCastSession()) != null) {
         var1.removeCastListener(this.zzaoY);
      }

      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzavr.setEnabled(true);
   }

   public final void onSendingRemoteMediaRequest() {
      this.zzavr.setEnabled(false);
   }

   private final void zzok() {
      CastSession var1;
      if ((var1 = CastContext.getSharedInstance(this.zzarM).getSessionManager().getCurrentCastSession()) != null && var1.isConnected()) {
         this.zzavr.setEnabled(true);
         if (var1.isMute()) {
            this.zzW(false);
         } else {
            this.zzW(true);
         }
      } else {
         this.zzavr.setEnabled(false);
      }
   }

   private final void zzW(boolean var1) {
      this.zzavr.setSelected(var1);
      this.zzavr.setContentDescription(var1 ? this.zzavD : this.zzavE);
   }

   // $FF: synthetic method
   static void zza(zzawf var0) {
      var0.zzok();
   }

   // $FF: synthetic method
   static Context zzb(zzawf var0) {
      return var0.zzarM;
   }

   // $FF: synthetic method
   static void zza(zzawf var0, boolean var1) {
      var0.zzW(var1);
   }

   // $FF: synthetic method
   static zzayo zzon() {
      return zzarK;
   }
}
