package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ef;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.zzbbe;

public final class zzy extends zzbbe {
   private final com.google.android.gms.common.util.zze zzvw;
   private final zzaf zzbDL;
   private final Looper zzrM;
   private final zzek zzbDM;
   private final int zzbDN;
   private final Context mContext;
   private final TagManager zzbDI;
   private final String zzbDw;
   private final zzai zzbDO;
   private zzah zzbDP;
   private ef zzbDQ;
   private volatile zzv zzbDR;
   private volatile boolean zzbDS;
   private com.google.android.gms.internal.zzbq zzbDT;
   private long zzbDB;
   private String zzbDU;
   private zzag zzbDV;
   private zzac zzbDW;

   public zzy(Context var1, TagManager var2, Looper var3, String var4, int var5, zzal var6) {
      this(var1, var2, var3, var4, var5, new zzey(var1, var4), new zzet(var1, var4, var6), new ef(var1), com.google.android.gms.common.util.zzi.zzrY(), new zzdh(1, 5, 900000L, 5000L, "refreshing", com.google.android.gms.common.util.zzi.zzrY()), new zzai(var1, var4));
      this.zzbDQ.zzgc(var6.zzAX());
   }

   private zzy(Context var1, TagManager var2, Looper var3, String var4, int var5, zzah var6, zzag var7, ef var8, com.google.android.gms.common.util.zze var9, zzek var10, zzai var11) {
      super(var3 == null ? Looper.getMainLooper() : var3);
      this.mContext = var1;
      this.zzbDI = var2;
      this.zzrM = var3 == null ? Looper.getMainLooper() : var3;
      this.zzbDw = var4;
      this.zzbDN = var5;
      this.zzbDP = var6;
      this.zzbDV = var7;
      this.zzbDQ = var8;
      this.zzbDL = new zzaf(this, (zzz)null);
      this.zzbDT = new com.google.android.gms.internal.zzbq();
      this.zzvw = var9;
      this.zzbDM = var10;
      this.zzbDO = var11;
      if (this.zzAQ()) {
         this.zzfa(zzei.zzBD().zzBF());
      }

   }

   public final void zzAN() {
      ek var1;
      if ((var1 = this.zzbDP.zzbx(this.zzbDN)) != null) {
         Container var2 = new Container(this.mContext, this.zzbDI.getDataLayer(), this.zzbDw, 0L, var1);
         this.setResult(new zzv(this.zzbDI, this.zzrM, var2, new zzaa(this)));
      } else {
         String var3 = "Default was requested, but no default container was found";
         zzdj.e("Default was requested, but no default container was found");
         this.setResult(this.zzI(new Status(10, var3, (PendingIntent)null)));
      }

      this.zzbDV = null;
      this.zzbDP = null;
   }

   public final void zzAO() {
      this.zzaq(false);
   }

   public final void zzAP() {
      this.zzaq(true);
   }

   private final void zzaq(boolean var1) {
      this.zzbDP.zza((zzdi)(new zzad(this, (zzz)null)));
      this.zzbDV.zza(new zzae(this, (zzz)null));
      ek var2;
      if ((var2 = this.zzbDP.zzbx(this.zzbDN)) != null) {
         this.zzbDR = new zzv(this.zzbDI, this.zzrM, new Container(this.mContext, this.zzbDI.getDataLayer(), this.zzbDw, 0L, var2), this.zzbDL);
      }

      this.zzbDW = new zzab(this, var1);
      if (this.zzAQ()) {
         this.zzbDV.zza(0L, "");
      } else {
         this.zzbDP.zzAR();
      }
   }

   private final synchronized void zza(com.google.android.gms.internal.zzbq var1, long var2, boolean var4) {
      if (var4) {
         boolean var10000 = this.zzbDS;
      }

      if (!this.isReady() || this.zzbDR != null) {
         this.zzbDT = var1;
         this.zzbDB = var2;
         long var5 = this.zzbDO.zzAS();
         this.zzag(Math.max(0L, Math.min(var5, this.zzbDB + var5 - this.zzvw.currentTimeMillis())));
         Container var7 = new Container(this.mContext, this.zzbDI.getDataLayer(), this.zzbDw, var2, var1);
         if (this.zzbDR == null) {
            this.zzbDR = new zzv(this.zzbDI, this.zzrM, var7, this.zzbDL);
         } else {
            this.zzbDR.zza(var7);
         }

         if (!this.isReady() && this.zzbDW.zzb(var7)) {
            this.setResult(this.zzbDR);
         }

      }
   }

   final synchronized void zzfa(String var1) {
      this.zzbDU = var1;
      if (this.zzbDV != null) {
         this.zzbDV.zzfb(var1);
      }

   }

   final synchronized String zzAK() {
      return this.zzbDU;
   }

   private final synchronized void zzag(long var1) {
      if (this.zzbDV == null) {
         zzdj.zzaT("Refresh requested, but no network load scheduler.");
      } else {
         this.zzbDV.zza(var1, this.zzbDT.zzlC);
      }
   }

   protected final ContainerHolder zzI(Status var1) {
      if (this.zzbDR != null) {
         return this.zzbDR;
      } else {
         if (var1 == Status.zzaBp) {
            zzdj.e("timer expired: setting result to failure");
         }

         return new zzv(var1);
      }
   }

   private final boolean zzAQ() {
      zzei var1;
      return ((var1 = zzei.zzBD()).zzBE() == zzei.zza.zzbFK || var1.zzBE() == zzei.zza.zzbFL) && this.zzbDw.equals(var1.getContainerId());
   }

   private final synchronized void zza(com.google.android.gms.internal.zzbq var1) {
      if (this.zzbDP != null) {
         ee var2;
         (var2 = new ee()).zzbLG = this.zzbDB;
         var2.zzlB = new com.google.android.gms.internal.zzbn();
         var2.zzbLH = var1;
         this.zzbDP.zza(var2);
      }

   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return this.zzI(var1);
   }

   // $FF: synthetic method
   static zzek zza(zzy var0) {
      return var0.zzbDM;
   }

   // $FF: synthetic method
   static zzv zzb(zzy var0) {
      return var0.zzbDR;
   }

   // $FF: synthetic method
   static zzai zzc(zzy var0) {
      return var0.zzbDO;
   }

   // $FF: synthetic method
   static com.google.android.gms.common.util.zze zzd(zzy var0) {
      return var0.zzvw;
   }

   // $FF: synthetic method
   static void zza(zzy var0, com.google.android.gms.internal.zzbq var1, long var2, boolean var4) {
      var0.zza(var1, var2, var4);
   }

   // $FF: synthetic method
   static boolean zze(zzy var0) {
      return var0.zzbDS;
   }

   // $FF: synthetic method
   static void zza(zzy var0, long var1) {
      var0.zzag(var1);
   }

   // $FF: synthetic method
   static com.google.android.gms.internal.zzbq zzf(zzy var0) {
      return var0.zzbDT;
   }

   // $FF: synthetic method
   static long zzg(zzy var0) {
      return var0.zzbDB;
   }

   // $FF: synthetic method
   static boolean zzh(zzy var0) {
      return var0.zzAQ();
   }

   // $FF: synthetic method
   static void zza(zzy var0, com.google.android.gms.internal.zzbq var1) {
      var0.zza(var1);
   }
}
