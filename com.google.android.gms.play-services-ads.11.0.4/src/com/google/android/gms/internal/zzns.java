package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

@zzzn
public final class zzns extends zzpg implements zzob {
   private String zzHB;
   private List zzHC;
   private String zzHD;
   private zzos zzHO;
   private String zzHF;
   private String zzHP;
   @Nullable
   private zznn zzHJ;
   private Bundle mExtras;
   @Nullable
   private zzks zzHK;
   @Nullable
   private View zzHL;
   private Object mLock = new Object();
   private zzny zzHM;

   public zzns(String var1, List var2, String var3, zzos var4, String var5, String var6, @Nullable zznn var7, Bundle var8, zzks var9, View var10) {
      this.zzHB = var1;
      this.zzHC = var2;
      this.zzHD = var3;
      this.zzHO = var4;
      this.zzHF = var5;
      this.zzHP = var6;
      this.zzHJ = var7;
      this.mExtras = var8;
      this.zzHK = var9;
      this.zzHL = var10;
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

   public final zzos zzem() {
      return this.zzHO;
   }

   public final String getCallToAction() {
      return this.zzHF;
   }

   public final String getAdvertiser() {
      return this.zzHP;
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
      return "1";
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
      zzagz.zzZr.post(new zznt(this));
      this.zzHB = null;
      this.zzHC = null;
      this.zzHD = null;
      this.zzHO = null;
      this.zzHF = null;
      this.zzHP = null;
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
            zzafr.e("Attempt to perform click before content ad initialized.");
         } else {
            this.zzHM.zzc(var1);
         }
      }
   }

   public final boolean zzd(Bundle var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzafr.e("Attempt to record impression before content ad initialized.");
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
   static zzny zzb(zzns var0) {
      return var0.zzHM;
   }

   // $FF: synthetic method
   static zzny zza(zzns var0, zzny var1) {
      return var0.zzHM = null;
   }
}
