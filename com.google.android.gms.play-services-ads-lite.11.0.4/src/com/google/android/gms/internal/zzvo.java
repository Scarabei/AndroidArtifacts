package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzzn
public final class zzvo extends zzuu {
   private final MediationAdapter zzNi;
   private final NetworkExtras zzNj;

   public zzvo(MediationAdapter var1, NetworkExtras var2) {
      this.zzNi = var1;
      this.zzNj = var2;
   }

   public final IObjectWrapper getView() throws RemoteException {
      if (!(this.zzNi instanceof MediationBannerAdapter)) {
         String var10001 = String.valueOf(this.zzNi.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationBannerAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationBannerAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         try {
            return zzn.zzw(((MediationBannerAdapter)this.zzNi).getBannerView());
         } catch (Throwable var2) {
            zzajc.zzc("Could not get banner view from adapter.", var2);
            throw new RemoteException();
         }
      }
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, zzuw var5) throws RemoteException {
      this.zza(var1, var2, var3, var4, (String)null, var5);
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, String var5, zzuw var6) throws RemoteException {
      if (!(this.zzNi instanceof MediationBannerAdapter)) {
         String var10001 = String.valueOf(this.zzNi.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationBannerAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationBannerAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Requesting banner ad from adapter.");

         try {
            ((MediationBannerAdapter)this.zzNi).requestBannerAd(new zzvp(var6), (Activity)zzn.zzE(var1), this.zza(var4, var3.zzzR, var5), zzwb.zzb(var2), zzwb.zzn(var3), this.zzNj);
         } catch (Throwable var8) {
            zzajc.zzc("Could not request banner ad from adapter.", var8);
            throw new RemoteException();
         }
      }
   }

   public final Bundle zzfs() {
      return new Bundle();
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzuw var4) throws RemoteException {
      this.zza(var1, (zzir)var2, (String)var3, (String)null, (zzuw)var4);
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5) throws RemoteException {
      if (!(this.zzNi instanceof MediationInterstitialAdapter)) {
         String var10001 = String.valueOf(this.zzNi.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationInterstitialAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Requesting interstitial ad from adapter.");

         try {
            ((MediationInterstitialAdapter)this.zzNi).requestInterstitialAd(new zzvp(var5), (Activity)zzn.zzE(var1), this.zza(var3, var2.zzzR, var4), zzwb.zzn(var2), this.zzNj);
         } catch (Throwable var7) {
            zzajc.zzc("Could not request interstitial ad from adapter.", var7);
            throw new RemoteException();
         }
      }
   }

   public final Bundle getInterstitialAdapterInfo() {
      return new Bundle();
   }

   public final void setImmersiveMode(boolean var1) {
   }

   public final zzks getVideoController() {
      return null;
   }

   public final void showInterstitial() throws RemoteException {
      if (!(this.zzNi instanceof MediationInterstitialAdapter)) {
         String var10001 = String.valueOf(this.zzNi.getClass().getCanonicalName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("MediationAdapter is not a MediationInterstitialAdapter: ");
         }

         zzajc.zzaT(var10000);
         throw new RemoteException();
      } else {
         zzajc.zzaC("Showing interstitial from adapter.");

         try {
            ((MediationInterstitialAdapter)this.zzNi).showInterstitial();
         } catch (Throwable var2) {
            zzajc.zzc("Could not show interstitial from adapter.", var2);
            throw new RemoteException();
         }
      }
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5, zzon var6, List var7) {
   }

   public final Bundle zzft() {
      return new Bundle();
   }

   public final zzvc zzfq() {
      return null;
   }

   public final zzvf zzfr() {
      return null;
   }

   public final zzpj zzfv() {
      return null;
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzaea var4, String var5) throws RemoteException {
   }

   public final void zza(IObjectWrapper var1, zzaea var2, List var3) {
   }

   public final boolean zzfu() {
      return false;
   }

   public final void zza(zzir var1, String var2, String var3) {
   }

   public final void zzc(zzir var1, String var2) {
   }

   public final void showVideo() {
   }

   public final boolean isInitialized() {
      return true;
   }

   public final void destroy() throws RemoteException {
      try {
         this.zzNi.destroy();
      } catch (Throwable var2) {
         zzajc.zzc("Could not destroy adapter.", var2);
         throw new RemoteException();
      }
   }

   public final void pause() throws RemoteException {
      throw new RemoteException();
   }

   public final void resume() throws RemoteException {
      throw new RemoteException();
   }

   public final void zzk(IObjectWrapper var1) throws RemoteException {
   }

   private final MediationServerParameters zza(String var1, int var2, String var3) throws RemoteException {
      try {
         HashMap var4;
         if (var1 != null) {
            JSONObject var5 = new JSONObject(var1);
            var4 = new HashMap(var5.length());
            Iterator var6 = var5.keys();

            while(var6.hasNext()) {
               String var7 = (String)var6.next();
               var4.put(var7, var5.getString(var7));
            }
         } else {
            var4 = new HashMap(0);
         }

         Class var9 = this.zzNi.getServerParametersType();
         MediationServerParameters var10 = null;
         if (var9 != null) {
            (var10 = (MediationServerParameters)var9.newInstance()).load(var4);
         }

         return var10;
      } catch (Throwable var8) {
         zzajc.zzc("Could not get MediationServerParameters.", var8);
         throw new RemoteException();
      }
   }
}
