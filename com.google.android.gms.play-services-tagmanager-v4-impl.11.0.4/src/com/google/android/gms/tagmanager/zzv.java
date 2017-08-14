package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;

final class zzv implements ContainerHolder {
   private final Looper zzrM;
   private Container zzbDE;
   private Container zzbDF;
   private Status mStatus;
   private zzx zzbDG;
   private zzw zzbDH;
   private boolean zzaNd;
   private TagManager zzbDI;

   public zzv(Status var1) {
      this.mStatus = var1;
      this.zzrM = null;
   }

   public zzv(TagManager var1, Looper var2, Container var3, zzw var4) {
      this.zzbDI = var1;
      this.zzrM = var2 != null ? var2 : Looper.getMainLooper();
      this.zzbDE = var3;
      this.zzbDH = var4;
      this.mStatus = Status.zzaBm;
      var1.zza(this);
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final synchronized Container getContainer() {
      if (this.zzaNd) {
         zzdj.e("ContainerHolder is released.");
         return null;
      } else {
         if (this.zzbDF != null) {
            this.zzbDE = this.zzbDF;
            this.zzbDF = null;
         }

         return this.zzbDE;
      }
   }

   public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener var1) {
      if (this.zzaNd) {
         zzdj.e("ContainerHolder is released.");
      } else if (var1 == null) {
         this.zzbDG = null;
      } else {
         this.zzbDG = new zzx(this, var1, this.zzrM);
         if (this.zzbDF != null) {
            this.zzAL();
         }

      }
   }

   public final synchronized void refresh() {
      if (this.zzaNd) {
         zzdj.e("Refreshing a released ContainerHolder.");
      } else {
         this.zzbDH.zzAM();
      }
   }

   public final synchronized void release() {
      if (this.zzaNd) {
         zzdj.e("Releasing a released ContainerHolder.");
      } else {
         this.zzaNd = true;
         this.zzbDI.zzb(this);
         this.zzbDE.release();
         this.zzbDE = null;
         this.zzbDF = null;
         this.zzbDH = null;
         this.zzbDG = null;
      }
   }

   public final synchronized void zza(Container var1) {
      if (!this.zzaNd) {
         this.zzbDF = var1;
         this.zzAL();
      }
   }

   public final synchronized void zzeZ(String var1) {
      if (!this.zzaNd) {
         this.zzbDE.zzeZ(var1);
      }
   }

   final String getContainerId() {
      if (this.zzaNd) {
         zzdj.e("getContainerId called on a released ContainerHolder.");
         return "";
      } else {
         return this.zzbDE.getContainerId();
      }
   }

   final void zzfa(String var1) {
      if (this.zzaNd) {
         zzdj.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
      } else {
         this.zzbDH.zzfa(var1);
      }
   }

   final String zzAK() {
      if (this.zzaNd) {
         zzdj.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
         return "";
      } else {
         return this.zzbDH.zzAK();
      }
   }

   private final void zzAL() {
      if (this.zzbDG != null) {
         zzx var10000 = this.zzbDG;
         String var2 = this.zzbDF.zzAI();
         var10000.sendMessage(var10000.obtainMessage(1, var2));
      }

   }
}
