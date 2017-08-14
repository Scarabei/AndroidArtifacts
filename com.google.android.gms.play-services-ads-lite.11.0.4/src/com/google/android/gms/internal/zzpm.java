package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.zzn;
import java.util.List;

@zzzn
public final class zzpm implements NativeCustomTemplateAd {
   private final zzpj zzIJ;
   private final MediaView zzIK;
   private final VideoController zzBd = new VideoController();

   public zzpm(zzpj var1) {
      this.zzIJ = var1;
      Context var2 = null;

      try {
         var2 = (Context)zzn.zzE(var1.zzen());
      } catch (RemoteException | NullPointerException var7) {
         zzajc.zzb("Unable to inflate MediaView.", var7);
      }

      MediaView var3 = null;
      if (var2 != null) {
         var3 = new MediaView(var2);

         try {
            if (!this.zzIJ.zzj(zzn.zzw(var3))) {
               var3 = null;
            }
         } catch (RemoteException var6) {
            var3 = null;
            zzajc.zzb("Unable to render video in MediaView.", var6);
         }
      }

      this.zzIK = var3;
   }

   public final zzpj zzex() {
      return this.zzIJ;
   }

   public final CharSequence getText(String var1) {
      try {
         return this.zzIJ.zzP(var1);
      } catch (RemoteException var3) {
         zzajc.zzb("Failed to get string.", var3);
         return null;
      }
   }

   public final NativeAd.Image getImage(String var1) {
      try {
         zzos var2;
         if ((var2 = this.zzIJ.zzQ(var1)) != null) {
            return new zzov(var2);
         }
      } catch (RemoteException var3) {
         zzajc.zzb("Failed to get image.", var3);
      }

      return null;
   }

   public final VideoController getVideoController() {
      try {
         zzks var1;
         if ((var1 = this.zzIJ.getVideoController()) != null) {
            this.zzBd.zza(var1);
         }
      } catch (RemoteException var2) {
         zzajc.zzb("Exception occurred while getting video controller", var2);
      }

      return this.zzBd;
   }

   public final MediaView getVideoMediaView() {
      return this.zzIK;
   }

   public final List getAvailableAssetNames() {
      try {
         return this.zzIJ.getAvailableAssetNames();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get available asset names.", var2);
         return null;
      }
   }

   public final String getCustomTemplateId() {
      try {
         return this.zzIJ.getCustomTemplateId();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get custom template id.", var2);
         return null;
      }
   }

   public final void performClick(String var1) {
      try {
         this.zzIJ.performClick(var1);
      } catch (RemoteException var3) {
         zzajc.zzb("Failed to perform click.", var3);
      }
   }

   public final void recordImpression() {
      try {
         this.zzIJ.recordImpression();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to record impression.", var2);
      }
   }

   public final void destroy() {
      try {
         this.zzIJ.destroy();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to destroy ad.", var2);
      }
   }
}
