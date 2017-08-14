package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zznp extends zzot {
   private final Drawable zzHz;
   private final Uri mUri;
   private final double zzHA;

   public zznp(Drawable var1, Uri var2, double var3) {
      this.zzHz = var1;
      this.mUri = var2;
      this.zzHA = var3;
   }

   public final IObjectWrapper zzeg() throws RemoteException {
      return zzn.zzw(this.zzHz);
   }

   public final Uri getUri() throws RemoteException {
      return this.mUri;
   }

   public final double getScale() {
      return this.zzHA;
   }
}
