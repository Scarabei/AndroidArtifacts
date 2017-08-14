package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzadd;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjl;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzxg;
import com.google.android.gms.internal.zzxo;
import com.google.android.gms.internal.zzzn;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@zzzn
public final class zzbm extends zzka {
   private final zzaje zztW;
   private final zziv zzuZ;
   private final Future zzva;
   private final Context mContext;
   private final zzbr zzvb;
   @Nullable
   private WebView zzvc;
   @Nullable
   private zzjo zztK;
   @Nullable
   private zzeu zzvd;
   private AsyncTask zzve;

   public zzbm(Context var1, zziv var2, String var3, zzaje var4) {
      this.mContext = var1;
      this.zztW = var4;
      this.zzuZ = var2;
      this.zzvc = new WebView(this.mContext);
      this.zzva = zzagt.zza((Callable)(new zzbp(this)));
      this.zzvb = new zzbr(var3);
      this.zzf(0);
      this.zzvc.setVerticalScrollBarEnabled(false);
      this.zzvc.getSettings().setJavaScriptEnabled(true);
      this.zzvc.setWebViewClient(new zzbn(this));
      this.zzvc.setOnTouchListener(new zzbo(this));
   }

   public final IObjectWrapper zzal() throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzcz("getAdFrame must be called on the main UI thread.");
      return com.google.android.gms.dynamic.zzn.zzw(this.zzvc);
   }

   public final void destroy() throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzcz("destroy must be called on the main UI thread.");
      this.zzve.cancel(true);
      this.zzva.cancel(true);
      this.zzvc.destroy();
      this.zzvc = null;
   }

   public final boolean isReady() throws RemoteException {
      return false;
   }

   public final boolean zza(zzir var1) throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzb(this.zzvc, "This Search Ad has already been torn down");
      this.zzvb.zza(var1, this.zztW);
      this.zzve = (new zzbq(this, (zzbn)null)).execute(new Void[0]);
      return true;
   }

   public final void pause() throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzcz("pause must be called on the main UI thread.");
   }

   public final void resume() throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzcz("resume must be called on the main UI thread.");
   }

   public final void zza(zzjo var1) throws RemoteException {
      this.zztK = var1;
   }

   public final void zza(zzke var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void showInterstitial() throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void stopLoading() throws RemoteException {
   }

   public final void zzao() throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final zziv zzam() throws RemoteException {
      return this.zzuZ;
   }

   public final void zza(zziv var1) throws RemoteException {
      throw new IllegalStateException("AdSize must be set before initialization");
   }

   public final void zza(zzxg var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void zza(zzxo var1, String var2) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   @Nullable
   public final String getMediationAdapterClassName() throws RemoteException {
      return null;
   }

   @Nullable
   public final String zzaI() throws RemoteException {
      return null;
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

   public final void zza(zznh var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void zza(zzjl var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void zza(zzkk var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void setManualImpressionsEnabled(boolean var1) throws RemoteException {
   }

   public final boolean isLoading() throws RemoteException {
      return false;
   }

   public final void zza(zzadd var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   public final void setUserId(String var1) throws RemoteException {
      throw new IllegalStateException("Unused method");
   }

   @Nullable
   public final zzks getVideoController() {
      return null;
   }

   public final void zza(zzlx var1) {
      throw new IllegalStateException("Unused method");
   }

   public final void zza(zzky var1) {
      throw new IllegalStateException("Unused method");
   }

   public final void setImmersiveMode(boolean var1) {
      throw new IllegalStateException("Unused method");
   }

   final int zzv(String var1) {
      String var2;
      if (TextUtils.isEmpty(var2 = Uri.parse(var1).getQueryParameter("height"))) {
         return 0;
      } else {
         try {
            zzji.zzds();
            return zzaiy.zzc(this.mContext, Integer.parseInt(var2));
         } catch (NumberFormatException var3) {
            return 0;
         }
      }
   }

   final void zzf(int var1) {
      if (this.zzvc != null) {
         LayoutParams var2 = new LayoutParams(-1, var1);
         this.zzvc.setLayoutParams(var2);
      }
   }

   final String zzbp() {
      Builder var1;
      Builder var10000 = (var1 = new Builder()).scheme("https://");
      zzme var6 = zzmo.zzFV;
      var10000.appendEncodedPath((String)zzbs.zzbL().zzd(var6));
      var1.appendQueryParameter("query", this.zzvb.getQuery());
      var1.appendQueryParameter("pubId", this.zzvb.zzbs());
      Map var2;
      Iterator var3 = (var2 = this.zzvb.zzbt()).keySet().iterator();

      String var4;
      while(var3.hasNext()) {
         var4 = (String)var3.next();
         var1.appendQueryParameter(var4, (String)var2.get(var4));
      }

      Uri var8 = var1.build();
      if (this.zzvd != null) {
         try {
            var8 = this.zzvd.zzb(var8, this.mContext);
         } catch (RemoteException | zzev var7) {
            zzafr.zzc("Unable to process ad data", var7);
         }
      }

      var4 = String.valueOf(this.zzbq());
      String var5 = String.valueOf(var8.getEncodedQuery());
      return (new StringBuilder(1 + String.valueOf(var4).length() + String.valueOf(var5).length())).append(var4).append("#").append(var5).toString();
   }

   final String zzbq() {
      String var1;
      if (TextUtils.isEmpty(var1 = this.zzvb.zzbr())) {
         var1 = "www.google.com";
      }

      String var2 = String.valueOf("https://");
      zzme var5 = zzmo.zzFV;
      String var4 = (String)zzbs.zzbL().zzd(var5);
      return (new StringBuilder(String.valueOf(var2).length() + String.valueOf(var1).length() + String.valueOf(var4).length())).append(var2).append(var1).append(var4).toString();
   }

   private final String zzw(String var1) {
      if (this.zzvd == null) {
         return var1;
      } else {
         Uri var2 = Uri.parse(var1);

         try {
            var2 = this.zzvd.zzc(var2, this.mContext);
         } catch (RemoteException var4) {
            zzafr.zzc("Unable to process ad data", var4);
         } catch (zzev var5) {
            zzafr.zzc("Unable to parse ad click url", var5);
         }

         return var2.toString();
      }
   }

   private final void zzx(String var1) {
      Intent var2;
      (var2 = new Intent("android.intent.action.VIEW")).setData(Uri.parse(var1));
      this.mContext.startActivity(var2);
   }

   // $FF: synthetic method
   static zzjo zza(zzbm var0) {
      return var0.zztK;
   }

   // $FF: synthetic method
   static String zza(zzbm var0, String var1) {
      return var0.zzw(var1);
   }

   // $FF: synthetic method
   static void zzb(zzbm var0, String var1) {
      var0.zzx(var1);
   }

   // $FF: synthetic method
   static zzeu zzb(zzbm var0) {
      return var0.zzvd;
   }

   // $FF: synthetic method
   static zzaje zzc(zzbm var0) {
      return var0.zztW;
   }

   // $FF: synthetic method
   static Context zzd(zzbm var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static zzeu zza(zzbm var0, zzeu var1) {
      return var0.zzvd = var1;
   }

   // $FF: synthetic method
   static Future zze(zzbm var0) {
      return var0.zzva;
   }

   // $FF: synthetic method
   static WebView zzf(zzbm var0) {
      return var0.zzvc;
   }
}
