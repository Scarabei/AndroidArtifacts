package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;

@TargetApi(19)
public final class zzazc extends zzaza {
   private final zzazf zzaza;
   // $FF: synthetic field
   private zzazb zzazb;

   public zzazc(zzazb var1, zzazf var2) {
      this.zzazb = var1;
      super(var1.zzayZ);
      this.zzaza = var2;
   }

   public final void zza(int var1, int var2, Surface var3) {
      zzayw.zzoQ().zzb("onConnected");
      DisplayManager var4;
      if ((var4 = (DisplayManager)this.zzaza.getContext().getSystemService("display")) == null) {
         zzayw.zzoQ().zzc("Unable to get the display manager");
         this.zzazb.setResult(new zzaze(Status.zzaBo));
      } else {
         zzayw.zza(this.zzazb.zzayZ);
         int var5 = (var1 < var2 ? var1 : var2) * 320 / 1080;
         zzayw.zza(this.zzazb.zzayZ, var4.createVirtualDisplay("private_display", var1, var2, var5, var3, 2));
         if (zzayw.zzd(this.zzazb.zzayZ) == null) {
            zzayw.zzoQ().zzc("Unable to create virtual display");
            this.zzazb.setResult(new zzaze(Status.zzaBo));
         } else if (zzayw.zzd(this.zzazb.zzayZ).getDisplay() == null) {
            zzayw.zzoQ().zzc("Virtual display does not have a display");
            this.zzazb.setResult(new zzaze(Status.zzaBo));
         } else {
            try {
               zzazf var10000 = this.zzaza;
               int var8 = zzayw.zzd(this.zzazb.zzayZ).getDisplay().getDisplayId();
               ((zzazj)var10000.zzrf()).zza(this, var8);
            } catch (IllegalStateException | RemoteException var9) {
               zzayw.zzoQ().zzc("Unable to provision the route's new virtual Display");
               this.zzazb.setResult(new zzaze(Status.zzaBo));
            }
         }
      }
   }

   public final void zzoR() {
      zzayw.zzoQ().zzb("onConnectedWithDisplay");
      if (zzayw.zzd(this.zzazb.zzayZ) == null) {
         zzayw.zzoQ().zzc("There is no virtual display");
         this.zzazb.setResult(new zzaze(Status.zzaBo));
      } else {
         Display var1;
         if ((var1 = zzayw.zzd(this.zzazb.zzayZ).getDisplay()) != null) {
            this.zzazb.setResult(new zzaze(var1));
         } else {
            zzayw.zzoQ().zzc("Virtual display no longer has a display");
            this.zzazb.setResult(new zzaze(Status.zzaBo));
         }
      }
   }

   public final void onError(int var1) throws RemoteException {
      zzayw.zzoQ().zzb("onError: %d", var1);
      zzayw.zza(this.zzazb.zzayZ);
      this.zzazb.setResult(new zzaze(Status.zzaBo));
   }
}
