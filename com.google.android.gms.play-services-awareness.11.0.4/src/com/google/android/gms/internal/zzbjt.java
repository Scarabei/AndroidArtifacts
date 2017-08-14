package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbjt extends zza {
   public static final Creator CREATOR = new zzbju();
   private int type;
   private zzbiw zzaLo;
   public zzbjl zzaLp;
   public final com.google.android.gms.awareness.fence.zza zzaLq;
   private PendingIntent zzaLr;
   private String zzaLf;
   private long zzaLs;
   private long zzaLt;

   public static final zzbjt zza(String var0, long var1, zzbiy var3, PendingIntent var4) {
      return new zzbjt(2, new zzbiw(var0, 0L, var3), (com.google.android.gms.awareness.fence.zza)null, var4, (String)null, -1L, -1L);
   }

   public static final zzbjt zza(PendingIntent var0) {
      return new zzbjt(4, (zzbiw)null, (com.google.android.gms.awareness.fence.zza)null, var0, (String)null, -1L, -1L);
   }

   public static final zzbjt zzcN(String var0) {
      return new zzbjt(5, (zzbiw)null, (com.google.android.gms.awareness.fence.zza)null, (PendingIntent)null, var0, -1L, -1L);
   }

   zzbjt(int var1, zzbiw var2, IBinder var3, PendingIntent var4, String var5, long var6, long var8) {
      this.type = var1;
      this.zzaLo = var2;
      IInterface var11;
      this.zzaLp = (zzbjl)(var3 == null ? null : (var3 == null ? null : ((var11 = var3.queryLocalInterface("com.google.android.gms.contextmanager.fence.internal.IContextFenceListener")) instanceof zzbjl ? (zzbjl)var11 : new zzbjn(var3))));
      this.zzaLq = null;
      this.zzaLr = var4;
      this.zzaLf = var5;
      this.zzaLs = var6;
      this.zzaLt = var8;
   }

   private zzbjt(int var1, zzbiw var2, com.google.android.gms.awareness.fence.zza var3, PendingIntent var4, String var5, long var6, long var8) {
      this.type = var1;
      this.zzaLo = var2;
      this.zzaLp = null;
      this.zzaLq = null;
      this.zzaLr = var4;
      this.zzaLf = var5;
      this.zzaLs = -1L;
      this.zzaLt = -1L;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.type);
      zzd.zza(var1, 3, this.zzaLo, var2, false);
      zzd.zza(var1, 4, this.zzaLp == null ? null : this.zzaLp.asBinder(), false);
      zzd.zza(var1, 5, this.zzaLr, var2, false);
      zzd.zza(var1, 6, this.zzaLf, false);
      zzd.zza(var1, 7, this.zzaLs);
      zzd.zza(var1, 8, this.zzaLt);
      zzd.zzI(var1, var5);
   }
}
