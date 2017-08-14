package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzi;
import java.util.ArrayList;
import java.util.List;

public final class zzboc extends zzbor {
   private final int zzaJo = 1;
   private final zzi zzaOH;
   private final zzboe zzaOI;
   private final List zzaOJ = new ArrayList();

   public zzboc(Looper var1, Context var2, int var3, zzi var4) {
      this.zzaOH = var4;
      this.zzaOI = new zzboe(var1, var2, (zzbod)null);
   }

   public final void zzaN(int var1) {
      this.zzaOJ.add(Integer.valueOf(1));
   }

   public final boolean zzaO(int var1) {
      return this.zzaOJ.contains(Integer.valueOf(1));
   }

   public final void zzc(zzbph var1) throws RemoteException {
      DriveEvent var2 = var1.zztj();
      zzbo.zzae(this.zzaJo == var2.getType());
      zzbo.zzae(this.zzaOJ.contains(var2.getType()));
      zzi var4 = this.zzaOH;
      this.zzaOI.sendMessage(this.zzaOI.obtainMessage(1, new Pair(var4, var2)));
   }
}
