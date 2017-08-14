package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.zzac;
import com.google.android.gms.cast.framework.zzi;
import com.google.android.gms.cast.framework.zzk;
import com.google.android.gms.cast.framework.zzm;
import com.google.android.gms.cast.framework.zzs;
import com.google.android.gms.cast.framework.zzu;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.zzd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzaun extends zzed implements zzaum {
   zzaun(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
   }

   public final zzk zza(IObjectWrapper var1, CastOptions var2, zzauo var3, Map var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      var5.writeMap(var4);
      Parcel var6;
      zzk var7 = zzk.zza.zzA((var6 = this.zza(1, var5)).readStrongBinder());
      var6.recycle();
      return var7;
   }

   public final zzu zza(String var1, String var2, zzac var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      zzef.zza(var4, var3);
      Parcel var5;
      zzu var6 = zzu.zza.zzD((var5 = this.zza(2, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final zzm zza(CastOptions var1, IObjectWrapper var2, zzi var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      Parcel var5;
      zzm var6 = zzm.zza.zzB((var5 = this.zza(3, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final zzd zza(IObjectWrapper var1, IObjectWrapper var2, IObjectWrapper var3, CastMediaOptions var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      Parcel var6;
      zzd var7 = zzd.zza.zzE((var6 = this.zza(4, var5)).readStrongBinder());
      var6.recycle();
      return var7;
   }

   public final zzs zza(IObjectWrapper var1, IObjectWrapper var2, IObjectWrapper var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      Parcel var5;
      zzs var6 = zzs.zza.zzC((var5 = this.zza(5, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final zzavj zza(IObjectWrapper var1, zzavl var2, int var3, int var4, boolean var5, long var6, int var8, int var9, int var10) throws RemoteException {
      Parcel var11;
      zzef.zza(var11 = this.zzZ(), var1);
      zzef.zza(var11, var2);
      var11.writeInt(var3);
      var11.writeInt(var4);
      zzef.zza(var11, var5);
      var11.writeLong(var6);
      var11.writeInt(var8);
      var11.writeInt(var9);
      var11.writeInt(var10);
      Parcel var12;
      zzavj var13 = zzavj.zza.zzF((var12 = this.zza(6, var11)).readStrongBinder());
      var12.recycle();
      return var13;
   }
}
