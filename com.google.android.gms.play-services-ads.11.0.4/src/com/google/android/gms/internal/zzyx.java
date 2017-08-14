package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.ads.internal.zzbl;
import java.lang.ref.WeakReference;

@zzzn
public final class zzyx {
   private final Object mLock = new Object();
   private final Context mContext;
   private final zzcu zzIc;
   private final zzafg zzQQ;
   private final zznb zzsK;
   private final zzbb zzRm;
   private OnGlobalLayoutListener zzRX;
   private OnScrollChangedListener zzRY;
   private zzair zzwx;
   private int zzwv = -1;
   private int zzww = -1;

   public zzyx(Context var1, zzcu var2, zzafg var3, zznb var4, zzbb var5) {
      this.mContext = var1;
      this.zzIc = var2;
      this.zzQQ = var3;
      this.zzsK = var4;
      this.zzRm = var5;
      this.zzwx = new zzair(200L);
   }

   private final void zzi(zzaka var1) {
      zzakb var2;
      (var2 = var1.zziw()).zza("/video", zzqn.zzJg);
      var2.zza("/videoMeta", zzqn.zzJh);
      var2.zza("/precache", zzqn.zzJj);
      var2.zza("/delayPageLoaded", zzqn.zzJm);
      var2.zza("/instrument", zzqn.zzJk);
      var2.zza("/log", zzqn.zzJb);
      var2.zza("/videoClicked", zzqn.zzJc);
      var2.zza((String)"/trackActiveViewUnit", (zzrd)(new zzzb(this)));
      var2.zza((String)"/untrackActiveViewUnit", (zzrd)(new zzzc(this)));
   }

   private final OnGlobalLayoutListener zza(WeakReference var1) {
      if (this.zzRX == null) {
         this.zzRX = new zzzd(this, var1);
      }

      return this.zzRX;
   }

   private final OnScrollChangedListener zzb(WeakReference var1) {
      if (this.zzRY == null) {
         this.zzRY = new zzze(this, var1);
      }

      return this.zzRY;
   }

   private final void zza(WeakReference var1, boolean var2) {
      if (var1 != null) {
         zzaka var3;
         if ((var3 = (zzaka)var1.get()) != null && var3.getView() != null) {
            if (!var2 || this.zzwx.tryAcquire()) {
               View var4 = var3.getView();
               int[] var5 = new int[2];
               var4.getLocationOnScreen(var5);
               zzji.zzds();
               int var6 = zzaiy.zzd(this.mContext, var5[0]);
               zzji.zzds();
               int var7 = zzaiy.zzd(this.mContext, var5[1]);
               Object var8 = this.mLock;
               synchronized(this.mLock) {
                  if (this.zzwv != var6 || this.zzww != var7) {
                     this.zzwv = var6;
                     this.zzww = var7;
                     var3.zziw().zza(this.zzwv, this.zzww, !var2);
                  }

               }
            }
         }
      }
   }

   final zzaka zzgz() throws zzakm {
      return com.google.android.gms.ads.internal.zzbs.zzbA().zza(this.mContext, zziv.zzg(this.mContext), false, false, this.zzIc, this.zzQQ.zzUj.zzvT, this.zzsK, (zzbl)null, this.zzRm.zzak(), this.zzQQ.zzXX);
   }

   // $FF: synthetic method
   static zzbb zza(zzyx var0) {
      return var0.zzRm;
   }

   // $FF: synthetic method
   static OnGlobalLayoutListener zza(zzyx var0, WeakReference var1) {
      return var0.zza(var1);
   }

   // $FF: synthetic method
   static OnScrollChangedListener zzb(zzyx var0, WeakReference var1) {
      return var0.zzb(var1);
   }

   // $FF: synthetic method
   static void zza(zzyx var0, zzaka var1) {
      var0.zzi(var1);
   }

   // $FF: synthetic method
   static void zza(zzyx var0, WeakReference var1, boolean var2) {
      var0.zza(var1, var2);
   }
}
