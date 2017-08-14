package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import java.lang.ref.WeakReference;

final class zzaqf extends zzaqa {
   private final WeakReference zzaka;
   private final boolean zzakb;
   private final Intent zzakc;

   public zzaqf(zzapy var1, GoogleApiClient var2, Activity var3, boolean var4) {
      super(var2);
      this.zzakb = var4;
      this.zzaka = new WeakReference(var3);
      this.zzakc = var3 != null ? var3.getIntent() : null;
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzaqh var3 = (zzaqh)var1;
      if (AppInviteReferral.hasReferral(this.zzakc)) {
         this.setResult(new zzaqi(Status.zzaBm, this.zzakc));
         var3.zza((zzaqj)null);
      } else {
         zzaqg var4 = new zzaqg(this);
         var3.zza(var4);
      }
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzaqi(var1, new Intent());
   }

   // $FF: synthetic method
   static boolean zza(zzaqf var0) {
      return var0.zzakb;
   }

   // $FF: synthetic method
   static WeakReference zzb(zzaqf var0) {
      return var0.zzaka;
   }
}
