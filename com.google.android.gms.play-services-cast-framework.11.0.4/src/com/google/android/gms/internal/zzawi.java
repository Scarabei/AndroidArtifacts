package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawi extends UIController {
   private final ImageView zzavr;
   private final View zzavG;
   private final boolean zzavH;
   private final Drawable zzavI;
   private final String zzavJ;
   private final Drawable zzavK;
   private final String zzavL;
   private final Drawable zzavM;
   private final String zzavN;
   private final OnClickListener zzavp;

   public zzawi(@NonNull ImageView var1, Context var2, @NonNull Drawable var3, @NonNull Drawable var4, Drawable var5, View var6, boolean var7) {
      this.zzavr = var1;
      this.zzavI = var3;
      this.zzavK = var4;
      this.zzavM = var5 != null ? var5 : var4;
      this.zzavJ = var2.getString(string.cast_play);
      this.zzavL = var2.getString(string.cast_pause);
      this.zzavN = var2.getString(string.cast_stop);
      this.zzavG = var6;
      this.zzavH = var7;
      this.zzavp = new zzawj(this);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzavr.setOnClickListener(this.zzavp);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.zzavr.setOnClickListener((OnClickListener)null);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   public final void onSendingRemoteMediaRequest() {
      this.zzX(true);
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         if (var1.isPaused()) {
            this.zza(this.zzavI, this.zzavJ);
         } else if (var1.isPlaying()) {
            if (var1.isLiveStream()) {
               this.zza(this.zzavM, this.zzavN);
            } else {
               this.zza(this.zzavK, this.zzavL);
            }
         } else if (var1.isBuffering()) {
            this.zzX(false);
         } else {
            if (var1.isLoadingNextItem()) {
               this.zzX(true);
            }

         }
      }
   }

   private final void zza(Drawable var1, String var2) {
      this.zzavr.setImageDrawable(var1);
      this.zzavr.setContentDescription(var2);
      this.zzavr.setVisibility(0);
      this.zzavr.setEnabled(true);
      if (this.zzavG != null) {
         this.zzavG.setVisibility(8);
      }

   }

   private final void zzX(boolean var1) {
      if (this.zzavG != null) {
         this.zzavG.setVisibility(0);
      }

      this.zzavr.setVisibility(this.zzavH ? 4 : 0);
      this.zzavr.setEnabled(!var1);
   }

   // $FF: synthetic method
   static RemoteMediaClient zza(zzawi var0) {
      return var0.getRemoteMediaClient();
   }
}
