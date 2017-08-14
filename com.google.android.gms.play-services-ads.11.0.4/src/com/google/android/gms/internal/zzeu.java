package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzeu {
   private final zzey zzsh;

   public zzeu(String var1, Context var2, boolean var3) {
      this.zzsh = zzex.zzb(var1, var2, false);
   }

   public final Uri zzb(Uri var1, Context var2) throws zzev, RemoteException {
      IObjectWrapper var3 = zzn.zzw(var1);
      IObjectWrapper var4 = zzn.zzw(var2);
      IObjectWrapper var5;
      if ((var5 = this.zzsh.zza(var3, var4)) == null) {
         throw new zzev();
      } else {
         return (Uri)zzn.zzE(var5);
      }
   }

   public final void zza(MotionEvent var1) throws RemoteException {
      this.zzsh.zzd(zzn.zzw(var1));
   }

   public final Uri zzc(Uri var1, Context var2) throws zzev, RemoteException {
      IObjectWrapper var3 = zzn.zzw(var1);
      IObjectWrapper var4 = zzn.zzw(var2);
      IObjectWrapper var5;
      if ((var5 = this.zzsh.zzb(var3, var4)) == null) {
         throw new zzev();
      } else {
         return (Uri)zzn.zzE(var5);
      }
   }
}
