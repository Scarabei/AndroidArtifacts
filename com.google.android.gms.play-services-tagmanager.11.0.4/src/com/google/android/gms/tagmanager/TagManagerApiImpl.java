package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzcvk;
import com.google.android.gms.internal.zzcvq;
import com.google.android.gms.internal.zzcwn;

@DynamiteApi
public class TagManagerApiImpl extends zzcr {
   private zzcwn zzbGZ;

   public void initialize(IObjectWrapper var1, zzcn var2, zzce var3) throws RemoteException {
      Context var4 = (Context)zzn.zzE(var1);
      this.zzbGZ = zzcwn.zza(var4, var2, var3);
      this.zzbGZ.zze((String[])null);
   }

   /** @deprecated */
   @Deprecated
   public void preview(Intent var1, IObjectWrapper var2) {
      zzcvk.zzaT("Deprecated. Please use previewIntent instead.");
   }

   public void previewIntent(Intent var1, IObjectWrapper var2, IObjectWrapper var3, zzcn var4, zzce var5) {
      Context var6 = (Context)zzn.zzE(var2);
      Context var7 = (Context)zzn.zzE(var3);
      this.zzbGZ = zzcwn.zza(var6, var4, var5);
      (new zzcvq(var1, var6, var7, this.zzbGZ)).zzCv();
   }
}
