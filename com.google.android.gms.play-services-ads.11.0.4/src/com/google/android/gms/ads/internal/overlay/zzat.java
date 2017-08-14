package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import com.google.android.gms.internal.zzzn;

@zzzn
@TargetApi(14)
public final class zzat implements OnAudioFocusChangeListener {
   private final AudioManager mAudioManager;
   private final zzau zzQL;
   private boolean zzQM;
   private boolean zzPw;
   private boolean zzQN;
   private float zzQO = 1.0F;

   public zzat(Context var1, zzau var2) {
      this.mAudioManager = (AudioManager)var1.getSystemService("audio");
      this.zzQL = var2;
   }

   public final void setMuted(boolean var1) {
      this.zzQN = var1;
      this.zzgn();
   }

   public final void zzb(float var1) {
      this.zzQO = var1;
      this.zzgn();
   }

   public final float zzgm() {
      float var1 = this.zzQN ? 0.0F : this.zzQO;
      return this.zzQM ? var1 : 0.0F;
   }

   public final void zzgj() {
      this.zzPw = true;
      this.zzgn();
   }

   public final void zzgk() {
      this.zzPw = false;
      this.zzgn();
   }

   public final void onAudioFocusChange(int var1) {
      this.zzQM = var1 > 0;
      this.zzQL.zzfH();
   }

   private final void zzgn() {
      boolean var1;
      int var3;
      if ((var1 = this.zzPw && !this.zzQN && this.zzQO > 0.0F) && !this.zzQM) {
         if (this.mAudioManager != null && !this.zzQM) {
            var3 = this.mAudioManager.requestAudioFocus(this, 3, 2);
            this.zzQM = var3 == 1;
         }

         this.zzQL.zzfH();
      } else {
         if (!var1 && this.zzQM) {
            if (this.mAudioManager != null && this.zzQM) {
               var3 = this.mAudioManager.abandonAudioFocus(this);
               this.zzQM = var3 == 0;
            }

            this.zzQL.zzfH();
         }

      }
   }
}
