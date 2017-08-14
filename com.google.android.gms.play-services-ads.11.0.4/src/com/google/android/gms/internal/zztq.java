package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.IObjectWrapper;

@zzzn
public final class zztq extends zzka {
   private final String zztV;
   private boolean zzuj;
   private final zzsi zzKq;
   @Nullable
   private zzal zzKv;
   private final zzti zzKI;

   private zztq(String var1, zzsi var2) {
      this.zztV = var1;
      this.zzKq = var2;
      this.zzKI = new zzti();
      com.google.android.gms.ads.internal.zzbs.zzbN().zza(var2);
   }

   public zztq(Context var1, String var2, zzuq var3, zzaje var4, zzv var5) {
      this(var2, new zzsi(var1, var3, var4, var5));
   }

   @Nullable
   public final IObjectWrapper zzal() throws RemoteException {
      return this.zzKv != null ? this.zzKv.zzal() : null;
   }

   public final void destroy() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.destroy();
      }

   }

   public final boolean isReady() throws RemoteException {
      return this.zzKv != null && this.zzKv.isReady();
   }

   public final boolean zza(zzir var1) throws RemoteException {
      if (!zztl.zzi(var1).contains("gw")) {
         this.abort();
      }

      if (zztl.zzi(var1).contains("_skipMediation")) {
         this.abort();
      }

      if (var1.zzzU != null) {
         this.abort();
      }

      if (this.zzKv != null) {
         return this.zzKv.zza(var1);
      } else {
         zztl var2 = com.google.android.gms.ads.internal.zzbs.zzbN();
         if (zztl.zzi(var1).contains("_ad")) {
            var2.zzb(var1, this.zztV);
         }

         zzto var3;
         if ((var3 = var2.zza(var1, this.zztV)) != null) {
            if (!var3.zzKz) {
               var3.load();
               zztp.zzeN().zzeR();
            } else {
               zztp.zzeN().zzeQ();
            }

            this.zzKv = var3.zzKv;
            var3.zzKx.zza(this.zzKI);
            this.zzKI.zzd(this.zzKv);
            return var3.zzKA;
         } else {
            this.abort();
            zztp.zzeN().zzeR();
            return this.zzKv.zza(var1);
         }
      }
   }

   public final void pause() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.pause();
      }

   }

   public final void resume() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.resume();
      }

   }

   public final void zza(zzjo var1) throws RemoteException {
      this.zzKI.zztK = var1;
      if (this.zzKv != null) {
         this.zzKI.zzd(this.zzKv);
      }

   }

   public final void zza(zzadd var1) {
      this.zzKI.zzKl = var1;
      if (this.zzKv != null) {
         this.zzKI.zzd(this.zzKv);
      }

   }

   public final void setUserId(String var1) {
   }

   public final void zza(zzke var1) throws RemoteException {
      this.zzKI.zzKi = var1;
      if (this.zzKv != null) {
         this.zzKI.zzd(this.zzKv);
      }

   }

   public final void showInterstitial() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.setImmersiveMode(this.zzuj);
         this.zzKv.showInterstitial();
      } else {
         zzafr.zzaT("Interstitial ad must be loaded before showInterstitial().");
      }
   }

   public final void stopLoading() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.stopLoading();
      }

   }

   public final void zzao() throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.zzao();
      } else {
         zzafr.zzaT("Interstitial ad must be loaded before pingManualTrackingUrl().");
      }
   }

   @Nullable
   public final zziv zzam() throws RemoteException {
      return this.zzKv != null ? this.zzKv.zzam() : null;
   }

   public final void zza(zziv var1) throws RemoteException {
      if (this.zzKv != null) {
         this.zzKv.zza(var1);
      }

   }

   public final void zza(zzxg var1) throws RemoteException {
      zzafr.zzaT("setInAppPurchaseListener is deprecated and should not be called.");
   }

   public final void zza(zzxo var1, String var2) throws RemoteException {
      zzafr.zzaT("setPlayStorePurchaseParams is deprecated and should not be called.");
   }

   @Nullable
   public final String getMediationAdapterClassName() throws RemoteException {
      return this.zzKv != null ? this.zzKv.getMediationAdapterClassName() : null;
   }

   @Nullable
   public final String zzaI() throws RemoteException {
      return this.zzKv != null ? this.zzKv.zzaI() : null;
   }

   public final void zza(zznh var1) throws RemoteException {
      this.zzKI.zzKj = var1;
      if (this.zzKv != null) {
         this.zzKI.zzd(this.zzKv);
      }

   }

   public final void zza(zzjl var1) throws RemoteException {
      this.zzKI.zzKk = var1;
      if (this.zzKv != null) {
         this.zzKI.zzd(this.zzKv);
      }

   }

   public final void zza(zzkk var1) throws RemoteException {
      this.abort();
      if (this.zzKv != null) {
         this.zzKv.zza(var1);
      }

   }

   public final void setManualImpressionsEnabled(boolean var1) throws RemoteException {
      this.abort();
      if (this.zzKv != null) {
         this.zzKv.setManualImpressionsEnabled(var1);
      }

   }

   public final boolean isLoading() throws RemoteException {
      return this.zzKv != null && this.zzKv.isLoading();
   }

   public final zzks getVideoController() {
      throw new IllegalStateException("getVideoController not implemented for interstitials");
   }

   public final String getAdUnitId() {
      throw new IllegalStateException("getAdUnitId not implemented");
   }

   public final zzke zzax() {
      throw new IllegalStateException("getIAppEventListener not implemented");
   }

   public final zzjo zzay() {
      throw new IllegalStateException("getIAdListener not implemented");
   }

   public final void zza(zzlx var1) {
      throw new IllegalStateException("getVideoController not implemented for interstitials");
   }

   public final void zza(zzky var1) {
      throw new IllegalStateException("Unused method");
   }

   public final void setImmersiveMode(boolean var1) {
      this.zzuj = var1;
   }

   private final void abort() {
      if (this.zzKv == null) {
         this.zzKv = this.zzKq.zzW(this.zztV);
         this.zzKI.zzd(this.zzKv);
      }
   }
}
