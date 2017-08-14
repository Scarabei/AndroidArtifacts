package com.google.android.gms.internal;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzadu extends zzaeb {
   private volatile zzads zzWL;
   private volatile zzadv zzWz;
   private volatile zzadt zzWM;

   public zzadu(zzadt var1) {
      this.zzWM = var1;
   }

   public final void zza(zzads var1) {
      this.zzWL = var1;
   }

   public final void zza(zzadv var1) {
      this.zzWz = var1;
   }

   public final void zzq(IObjectWrapper var1) {
      if (this.zzWL != null) {
         this.zzWL.zzgT();
      }

   }

   public final void zzc(IObjectWrapper var1, int var2) {
      if (this.zzWL != null) {
         this.zzWL.zzv(var2);
      }

   }

   public final void zzr(IObjectWrapper var1) {
      if (this.zzWz != null) {
         this.zzWz.zzaw(zzn.zzE(var1).getClass().getName());
      }

   }

   public final void zzs(IObjectWrapper var1) {
      if (this.zzWM != null) {
         this.zzWM.onRewardedVideoAdOpened();
      }

   }

   public final void zzt(IObjectWrapper var1) {
      if (this.zzWM != null) {
         this.zzWM.onRewardedVideoStarted();
      }

   }

   public final void zzu(IObjectWrapper var1) {
      if (this.zzWM != null) {
         this.zzWM.onRewardedVideoAdClosed();
      }

   }

   public final void zza(IObjectWrapper var1, zzaee var2) {
      if (this.zzWM != null) {
         this.zzWM.zzc(var2);
      }

   }

   public final void zzv(IObjectWrapper var1) {
      if (this.zzWM != null) {
         this.zzWM.zzgQ();
      }

   }

   public final void zzd(IObjectWrapper var1, int var2) {
      if (this.zzWz != null) {
         this.zzWz.zza(zzn.zzE(var1).getClass().getName(), var2);
      }

   }

   public final void zzw(IObjectWrapper var1) {
      if (this.zzWM != null) {
         this.zzWM.onRewardedVideoAdLeftApplication();
      }

   }
}
