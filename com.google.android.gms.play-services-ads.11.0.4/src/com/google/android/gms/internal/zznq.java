package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

@zzzn
public final class zznq extends zzpc implements zzob {
   private String zzHB;
   private List zzHC;
   private String zzHD;
   private zzos zzHE;
   private String zzHF;
   private double zzHG;
   private String zzHH;
   private String zzHI;
   @Nullable
   private zznn zzHJ;
   private Bundle mExtras;
   @Nullable
   private zzks zzHK;
   @Nullable
   private View zzHL;
   private Object mLock = new Object();
   private zzny zzHM;

   public zznq(String var1, List var2, String var3, zzos var4, String var5, double var6, String var8, String var9, @Nullable zznn var10, Bundle var11, zzks var12, View var13) {
      this.zzHB = var1;
      this.zzHC = var2;
      this.zzHD = var3;
      this.zzHE = var4;
      this.zzHF = var5;
      this.zzHG = var6;
      this.zzHH = var8;
      this.zzHI = var9;
      this.zzHJ = var10;
      this.mExtras = var11;
      this.zzHK = var12;
      this.zzHL = var13;
   }

   public final String getHeadline() {
      return this.zzHB;
   }

   public final List getImages() {
      return this.zzHC;
   }

   public final String getBody() {
      return this.zzHD;
   }

   public final zzos zzeh() {
      return this.zzHE;
   }

   public final String getCallToAction() {
      return this.zzHF;
   }

   public final double getStarRating() {
      return this.zzHG;
   }

   public final String getStore() {
      return this.zzHH;
   }

   public final String getPrice() {
      return this.zzHI;
   }

   public final zzks getVideoController() {
      return this.zzHK;
   }

   public final IObjectWrapper zzei() {
      return zzn.zzw(this.zzHM);
   }

   public final void zzb(zzny var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzHM = var1;
      }
   }

   public final String zzej() {
      return "2";
   }

   public final String getCustomTemplateId() {
      return "";
   }

   public final zznn zzek() {
      return this.zzHJ;
   }

   public final Bundle getExtras() {
      return this.mExtras;
   }

   public final View zzel() {
      return this.zzHL;
   }

   public final void destroy() {
      zzagz.zzZr.post(new zznr(this));
      this.zzHB = null;
      this.zzHC = null;
      this.zzHD = null;
      this.zzHE = null;
      this.zzHF = null;
      this.zzHG = 0.0D;
      this.zzHH = null;
      this.zzHI = null;
      this.zzHJ = null;
      this.mExtras = null;
      this.mLock = null;
      this.zzHK = null;
      this.zzHL = null;
   }

   public final void zzc(Bundle var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzafr.e("Attempt to perform click before app install ad initialized.");
         } else {
            this.zzHM.zzc(var1);
         }
      }
   }

   public final boolean zzd(Bundle var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzafr.e("Attempt to record impression before app install ad initialized.");
            return false;
         } else {
            return this.zzHM.zzd(var1);
         }
      }
   }

   public final void zze(Bundle var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzafr.e("Attempt to perform click before app install ad initialized.");
         } else {
            this.zzHM.zze(var1);
         }
      }
   }

   // $FF: synthetic method
   static zzny zzb(zznq var0) {
      return var0.zzHM;
   }

   // $FF: synthetic method
   static zzny zza(zznq var0, zzny var1) {
      return var0.zzHM = null;
   }
}
