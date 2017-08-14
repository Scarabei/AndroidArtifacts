package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.TimeZone;

public final class zzazp {
   private int zzazl;
   private String zzazk;
   private String zzazm;
   private String zzazn;
   private int zzazp;
   private final zzazr zzazu;
   private ArrayList zzazv;
   private ArrayList zzazw;
   private ArrayList zzazx;
   private ArrayList zzazy;
   private ArrayList zzazz;
   private boolean zzazA;
   private final aej zzazB;
   private boolean zzazC;
   // $FF: synthetic field
   private zzazn zzazD;

   private zzazp(zzazn var1, byte[] var2) {
      this(var1, var2, (zzazr)null);
   }

   private zzazp(zzazn var1, byte[] var2, zzazr var3) {
      this.zzazD = var1;
      super();
      this.zzazl = com.google.android.gms.internal.zzazn.zza(this.zzazD);
      this.zzazk = com.google.android.gms.internal.zzazn.zzb(this.zzazD);
      this.zzazm = com.google.android.gms.internal.zzazn.zzc(this.zzazD);
      zzazn var6 = this.zzazD;
      this.zzazn = null;
      this.zzazp = 0;
      this.zzazv = null;
      this.zzazw = null;
      this.zzazx = null;
      this.zzazy = null;
      this.zzazz = null;
      this.zzazA = true;
      this.zzazB = new aej();
      this.zzazC = false;
      this.zzazm = com.google.android.gms.internal.zzazn.zzc(var1);
      this.zzazn = null;
      this.zzazB.zzctQ = com.google.android.gms.internal.zzazn.zzd(var1).currentTimeMillis();
      this.zzazB.zzctR = com.google.android.gms.internal.zzazn.zzd(var1).elapsedRealtime();
      aej var10000 = this.zzazB;
      com.google.android.gms.internal.zzazn.zze(var1);
      long var4 = this.zzazB.zzctQ;
      var10000.zzcuc = (long)(TimeZone.getDefault().getOffset(var4) / 1000);
      if (var2 != null) {
         this.zzazB.zzctX = var2;
      }

      this.zzazu = null;
   }

   public final zzazp zzai(int var1) {
      this.zzazB.zzctT = var1;
      return this;
   }

   public final zzazp zzaj(int var1) {
      this.zzazB.zzrB = var1;
      return this;
   }

   public final void zzoS() {
      this.zzoT();
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult zzoT() {
      if (this.zzazC) {
         throw new IllegalStateException("do not reuse LogEventBuilder");
      } else {
         this.zzazC = true;
         zzazu var1;
         zzbak var2 = (var1 = new zzazu(new zzbak(com.google.android.gms.internal.zzazn.zzg(this.zzazD), com.google.android.gms.internal.zzazn.zzh(this.zzazD), this.zzazl, this.zzazk, this.zzazm, this.zzazn, com.google.android.gms.internal.zzazn.zzf(this.zzazD), 0), this.zzazB, (zzazr)null, (zzazr)null, com.google.android.gms.internal.zzazn.zzc((ArrayList)null), (String[])null, com.google.android.gms.internal.zzazn.zzc((ArrayList)null), (byte[][])null, (zzcqn[])null, this.zzazA)).zzazE;
         return com.google.android.gms.internal.zzazn.zzi(this.zzazD).zzg(var2.zzazk, var2.zzazl) ? com.google.android.gms.internal.zzazn.zzj(this.zzazD).zza(var1) : PendingResults.immediatePendingResult(Status.zzaBm);
      }
   }

   // $FF: synthetic method
   zzazp(zzazn var1, byte[] var2, zzazo var3) {
      this(var1, var2);
   }
}
