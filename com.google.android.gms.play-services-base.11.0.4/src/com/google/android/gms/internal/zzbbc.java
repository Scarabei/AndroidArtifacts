package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzbbc implements Runnable {
   private final zzbbb zzaBR;
   // $FF: synthetic field
   final zzbba zzaBS;

   zzbbc(zzbba var1, zzbbb var2) {
      this.zzaBS = var1;
      this.zzaBR = var2;
   }

   @MainThread
   public final void run() {
      if (this.zzaBS.mStarted) {
         ConnectionResult var1;
         if ((var1 = this.zzaBR.zzpz()).hasResolution()) {
            this.zzaBS.zzaEG.startActivityForResult(GoogleApiActivity.zza(this.zzaBS.getActivity(), var1.getResolution(), this.zzaBR.zzpy(), false), 1);
         } else if (this.zzaBS.zzaBd.isUserResolvableError(var1.getErrorCode())) {
            this.zzaBS.zzaBd.zza(this.zzaBS.getActivity(), this.zzaBS.zzaEG, var1.getErrorCode(), 2, this.zzaBS);
         } else if (var1.getErrorCode() == 18) {
            Dialog var2 = GoogleApiAvailability.zza((Activity)this.zzaBS.getActivity(), (OnCancelListener)this.zzaBS);
            GoogleApiAvailability.zza((Context)this.zzaBS.getActivity().getApplicationContext(), (zzbdl)(new zzbbd(this, var2)));
         } else {
            this.zzaBS.zza(var1, this.zzaBR.zzpy());
         }
      }
   }
}
