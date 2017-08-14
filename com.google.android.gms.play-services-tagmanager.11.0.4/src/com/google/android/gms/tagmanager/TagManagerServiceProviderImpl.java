package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzcvg;
import com.google.android.gms.internal.zzcxa;

@DynamiteApi
public class TagManagerServiceProviderImpl extends zzcu {
   private static volatile zzcxa zzbHa;

   public zzcvg getService(IObjectWrapper var1, zzcn var2, zzce var3) throws RemoteException {
      zzcxa var4 = zzbHa;
      if (zzbHa == null) {
         Class var5 = TagManagerServiceProviderImpl.class;
         synchronized(TagManagerServiceProviderImpl.class) {
            var4 = zzbHa;
            if (zzbHa == null) {
               Context var6 = (Context)zzn.zzE(var1);
               zzbHa = var4 = new zzcxa(var6, var2, var3);
            }
         }
      }

      return var4;
   }
}
