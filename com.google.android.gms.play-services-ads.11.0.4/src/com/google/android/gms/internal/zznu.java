package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzzn
public final class zznu extends zzpk implements zzoa {
   private final zznn zzHJ;
   private final String zzHR;
   private final SimpleArrayMap zzHS;
   private final SimpleArrayMap zzHT;
   @Nullable
   private zzks zzHK;
   @Nullable
   private View zzHL;
   private final Object mLock = new Object();
   private zzny zzHM;

   public zznu(String var1, SimpleArrayMap var2, SimpleArrayMap var3, zznn var4, zzks var5, View var6) {
      this.zzHR = var1;
      this.zzHS = var2;
      this.zzHT = var3;
      this.zzHJ = var4;
      this.zzHK = var5;
      this.zzHL = var6;
   }

   public final String zzP(String var1) {
      return (String)this.zzHT.get(var1);
   }

   public final zzos zzQ(String var1) {
      return (zzos)this.zzHS.get(var1);
   }

   public final List getAvailableAssetNames() {
      String[] var1 = new String[this.zzHS.size() + this.zzHT.size()];
      int var2 = 0;

      int var3;
      for(var3 = 0; var3 < this.zzHS.size(); ++var2) {
         var1[var2] = (String)this.zzHS.keyAt(var3);
         ++var3;
      }

      for(var3 = 0; var3 < this.zzHT.size(); ++var2) {
         var1[var2] = (String)this.zzHT.keyAt(var3);
         ++var3;
      }

      return Arrays.asList(var1);
   }

   public final String getCustomTemplateId() {
      return this.zzHR;
   }

   public final void zzb(zzny var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzHM = var1;
      }
   }

   public final IObjectWrapper zzei() {
      return zzn.zzw(this.zzHM);
   }

   public final String zzej() {
      return "3";
   }

   public final zznn zzek() {
      return this.zzHJ;
   }

   public final zzks getVideoController() {
      return this.zzHK;
   }

   public final boolean zzj(IObjectWrapper var1) {
      if (this.zzHM == null) {
         zzajc.e("Attempt to call renderVideoInMediaView before ad initialized.");
         return false;
      } else if (this.zzHL == null) {
         return false;
      } else {
         zznv var2 = new zznv(this);
         FrameLayout var3 = (FrameLayout)zzn.zzE(var1);
         this.zzHM.zza(var3, (zznw)var2);
         return true;
      }
   }

   public final View zzel() {
      return this.zzHL;
   }

   public final void performClick(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzajc.e("Attempt to call performClick before ad initialized.");
         } else {
            this.zzHM.zza((View)null, var1, (Bundle)null, (Map)null, (View)null);
         }
      }
   }

   public final void recordImpression() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            zzajc.e("Attempt to perform recordImpression before ad initialized.");
         } else {
            this.zzHM.zza((View)null, (Map)null);
         }
      }
   }

   public final IObjectWrapper zzen() {
      return zzn.zzw(this.zzHM.getContext().getApplicationContext());
   }

   public final void destroy() {
      this.zzHM = null;
      this.zzHK = null;
      this.zzHL = null;
   }
}
