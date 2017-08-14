package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;
import com.google.android.gms.internal.zzzn;

@zzzn
@TargetApi(14)
public abstract class zzy extends TextureView implements zzau {
   protected final zzak zzPp = new zzak();
   protected final zzat zzPq;

   public zzy(Context var1) {
      super(var1);
      this.zzPq = new zzat(var1, this);
   }

   public abstract String zzfD();

   public abstract void zza(zzx var1);

   public abstract void setVideoPath(String var1);

   public abstract void play();

   public abstract void stop();

   public abstract void pause();

   public abstract void seekTo(int var1);

   public abstract void zza(float var1, float var2);

   public abstract int getCurrentPosition();

   public abstract int getDuration();

   public abstract int getVideoWidth();

   public abstract int getVideoHeight();

   public abstract void zzfH();
}
