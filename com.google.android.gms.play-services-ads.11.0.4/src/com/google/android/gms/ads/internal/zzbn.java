package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;

final class zzbn extends WebViewClient {
   // $FF: synthetic field
   private zzbm zzvf;

   zzbn(zzbm var1) {
      this.zzvf = var1;
      super();
   }

   public final boolean shouldOverrideUrlLoading(WebView var1, String var2) {
      if (var2.startsWith(this.zzvf.zzbq())) {
         return false;
      } else {
         zzme var4 = zzmo.zzFS;
         if (var2.startsWith((String)zzbs.zzbL().zzd(var4))) {
            if (zzbm.zza(this.zzvf) != null) {
               try {
                  zzbm.zza(this.zzvf).onAdFailedToLoad(3);
               } catch (RemoteException var5) {
                  zzafr.zzc("Could not call AdListener.onAdFailedToLoad().", var5);
               }
            }

            this.zzvf.zzf(0);
            return true;
         } else {
            var4 = zzmo.zzFT;
            if (var2.startsWith((String)zzbs.zzbL().zzd(var4))) {
               if (zzbm.zza(this.zzvf) != null) {
                  try {
                     zzbm.zza(this.zzvf).onAdFailedToLoad(0);
                  } catch (RemoteException var6) {
                     zzafr.zzc("Could not call AdListener.onAdFailedToLoad().", var6);
                  }
               }

               this.zzvf.zzf(0);
               return true;
            } else {
               var4 = zzmo.zzFU;
               if (var2.startsWith((String)zzbs.zzbL().zzd(var4))) {
                  if (zzbm.zza(this.zzvf) != null) {
                     try {
                        zzbm.zza(this.zzvf).onAdLoaded();
                     } catch (RemoteException var7) {
                        zzafr.zzc("Could not call AdListener.onAdLoaded().", var7);
                     }
                  }

                  int var9 = this.zzvf.zzv(var2);
                  this.zzvf.zzf(var9);
                  return true;
               } else if (var2.startsWith("gmsg://")) {
                  return true;
               } else {
                  if (zzbm.zza(this.zzvf) != null) {
                     try {
                        zzbm.zza(this.zzvf).onAdLeftApplication();
                     } catch (RemoteException var8) {
                        zzafr.zzc("Could not call AdListener.onAdLeftApplication().", var8);
                     }
                  }

                  String var3 = zzbm.zza(this.zzvf, var2);
                  zzbm.zzb(this.zzvf, var3);
                  return true;
               }
            }
         }
      }
   }

   public final void onReceivedError(WebView var1, WebResourceRequest var2, WebResourceError var3) {
      if (zzbm.zza(this.zzvf) != null) {
         try {
            zzbm.zza(this.zzvf).onAdFailedToLoad(0);
            return;
         } catch (RemoteException var5) {
            zzafr.zzc("Could not call AdListener.onAdFailedToLoad().", var5);
         }
      }

   }
}
