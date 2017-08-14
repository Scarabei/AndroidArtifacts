package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzpi extends NativeContentAd {
   private final zzpf zzIH;
   private final List zzIF = new ArrayList();
   private final zzov zzII;
   private final VideoController zzBd = new VideoController();

   public zzpi(zzpf var1) {
      this.zzIH = var1;

      try {
         List var2;
         if ((var2 = this.zzIH.getImages()) != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               Object var5;
               IBinder var6;
               IInterface var7;
               Object var10000 = (var5 = var3.next()) instanceof IBinder && (var6 = (IBinder)var5) != null ? ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage")) instanceof zzos ? (zzos)var7 : new zzou(var6)) : null;
               Object var4 = var10000;
               if (var10000 != null) {
                  this.zzIF.add(new zzov((zzos)var4));
               }
            }
         }
      } catch (RemoteException var9) {
         zzajc.zzb("Failed to get image.", var9);
      }

      zzov var10 = null;

      try {
         zzos var11;
         if ((var11 = this.zzIH.zzem()) != null) {
            var10 = new zzov(var11);
         }
      } catch (RemoteException var8) {
         zzajc.zzb("Failed to get icon.", var8);
      }

      this.zzII = var10;
   }

   private final IObjectWrapper zzei() {
      try {
         return this.zzIH.zzei();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to retrieve native ad engine.", var2);
         return null;
      }
   }

   public final CharSequence getHeadline() {
      try {
         return this.zzIH.getHeadline();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get headline.", var2);
         return null;
      }
   }

   public final List getImages() {
      return this.zzIF;
   }

   public final CharSequence getBody() {
      try {
         return this.zzIH.getBody();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get body.", var2);
         return null;
      }
   }

   public final NativeAd.Image getLogo() {
      return this.zzII;
   }

   public final CharSequence getCallToAction() {
      try {
         return this.zzIH.getCallToAction();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get call to action.", var2);
         return null;
      }
   }

   public final CharSequence getAdvertiser() {
      try {
         return this.zzIH.getAdvertiser();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to get attribution.", var2);
         return null;
      }
   }

   public final VideoController getVideoController() {
      try {
         if (this.zzIH.getVideoController() != null) {
            this.zzBd.zza(this.zzIH.getVideoController());
         }
      } catch (RemoteException var2) {
         zzajc.zzb("Exception occurred while getting video controller", var2);
      }

      return this.zzBd;
   }

   public final Bundle getExtras() {
      try {
         return this.zzIH.getExtras();
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to get extras", var2);
         return null;
      }
   }

   public final void destroy() {
      try {
         this.zzIH.destroy();
      } catch (RemoteException var2) {
         zzajc.zzb("Failed to destroy", var2);
      }
   }

   // $FF: synthetic method
   protected final Object zzag() {
      return this.zzei();
   }
}
