package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

@zzzn
public final class zzaks extends zzkt {
   private final zzaka zzJH;
   private final Object mLock = new Object();
   private final boolean zzact;
   private final float zzacu;
   private int zzacv;
   private zzkv zzacw;
   private boolean zzacx;
   private boolean zzacy = true;
   private float zzacz;
   private float zzacA;
   private boolean zzsf = true;
   private boolean zzsg;

   public zzaks(zzaka var1, float var2, boolean var3) {
      this.zzJH = var1;
      this.zzacu = var2;
      this.zzact = var3;
   }

   public final void play() {
      String var1 = "play";
      this.zzc("play", (Map)null);
   }

   public final void pause() {
      String var1 = "pause";
      this.zzc("pause", (Map)null);
   }

   public final void mute(boolean var1) {
      String var2;
      this.zzc(var2 = var1 ? "mute" : "unmute", (Map)null);
   }

   public final void zzb(zzlx var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzsf = var1.zzBJ;
         this.zzsg = var1.zzBK;
      }

      this.zzc("initialState", zzf.zza("muteStart", var1.zzBJ ? "1" : "0", "customControlsRequested", var1.zzBK ? "1" : "0"));
   }

   private final void zzc(String var1, @Nullable Map var2) {
      HashMap var3;
      (var3 = var2 == null ? new HashMap() : new HashMap(var2)).put("action", var1);
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.runOnUiThread(new zzakt(this, var3));
   }

   public final boolean isMuted() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacy;
      }
   }

   public final int getPlaybackState() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacv;
      }
   }

   public final float getAspectRatio() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacA;
      }
   }

   public final float zzdv() {
      return this.zzacu;
   }

   public final float zzdw() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacz;
      }
   }

   public final void zza(zzkv var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzacw = var1;
      }
   }

   public final boolean isCustomControlsEnabled() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzact && this.zzsg;
      }
   }

   public final void zza(float var1, int var2, boolean var3, float var4) {
      Object var7 = this.mLock;
      int var5;
      boolean var6;
      synchronized(this.mLock) {
         this.zzacz = var1;
         var6 = this.zzacy;
         this.zzacy = var3;
         var5 = this.zzacv;
         this.zzacv = var2;
         this.zzacA = var4;
      }

      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.runOnUiThread(new zzaku(this, var5, var2, var6, var3));
   }

   // $FF: synthetic method
   static zzaka zzb(zzaks var0) {
      return var0.zzJH;
   }

   // $FF: synthetic method
   static Object zzc(zzaks var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static boolean zzd(zzaks var0) {
      return var0.zzacx;
   }

   // $FF: synthetic method
   static boolean zza(zzaks var0, boolean var1) {
      return var0.zzacx = var1;
   }

   // $FF: synthetic method
   static zzkv zze(zzaks var0) {
      return var0.zzacw;
   }
}
