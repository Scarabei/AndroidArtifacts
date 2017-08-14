package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@zzzn
public final class zzafh {
   private final zzafk zzvt;
   private final LinkedList zzXZ;
   private final Object mLock;
   private final String zzYa;
   private final String zzYb;
   private long zzYc;
   private long zzYd;
   private boolean zzVa;
   private long zzYe;
   private long zzYf;
   private long zzYg;
   private long zzYh;

   public zzafh(String var1, String var2) {
      this(com.google.android.gms.ads.internal.zzbs.zzbD(), var1, var2);
   }

   private zzafh(zzafk var1, String var2, String var3) {
      this.mLock = new Object();
      this.zzYc = -1L;
      this.zzYd = -1L;
      this.zzVa = false;
      this.zzYe = -1L;
      this.zzYf = 0L;
      this.zzYg = -1L;
      this.zzYh = -1L;
      this.zzvt = var1;
      this.zzYa = var2;
      this.zzYb = var3;
      this.zzXZ = new LinkedList();
   }

   public final void zzo(zzir var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYg = SystemClock.elapsedRealtime();
         this.zzvt.zzhq().zzb(var1, this.zzYg);
      }
   }

   public final void zzh(long var1) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzYh = var1;
         if (this.zzYh != -1L) {
            this.zzvt.zza(this);
         }

      }
   }

   public final void zzi(long var1) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzYh != -1L) {
            this.zzYc = var1;
            this.zzvt.zza(this);
         }

      }
   }

   public final void zzhc() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzYh != -1L && this.zzYd == -1L) {
            this.zzYd = SystemClock.elapsedRealtime();
            this.zzvt.zza(this);
         }

         this.zzvt.zzhq().zzhc();
      }
   }

   public final void zzhd() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzYh != -1L) {
            zzafi var2;
            (var2 = new zzafi()).zzhh();
            this.zzXZ.add(var2);
            ++this.zzYf;
            this.zzvt.zzhq().zzhd();
            this.zzvt.zza(this);
         }

      }
   }

   public final void zzhe() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzafi var2;
         if (this.zzYh != -1L && !this.zzXZ.isEmpty() && (var2 = (zzafi)this.zzXZ.getLast()).zzhf() == -1L) {
            var2.zzhg();
            this.zzvt.zza(this);
         }

      }
   }

   public final void zzv(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzYh != -1L) {
            this.zzYe = SystemClock.elapsedRealtime();
            if (!var1) {
               this.zzYd = this.zzYe;
               this.zzvt.zza(this);
            }
         }

      }
   }

   public final void zzw(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzYh != -1L) {
            this.zzVa = var1;
            this.zzvt.zza(this);
         }

      }
   }

   public final Bundle toBundle() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         Bundle var2;
         (var2 = new Bundle()).putString("seq_num", this.zzYa);
         var2.putString("slotid", this.zzYb);
         var2.putBoolean("ismediation", this.zzVa);
         var2.putLong("treq", this.zzYg);
         var2.putLong("tresponse", this.zzYh);
         var2.putLong("timp", this.zzYd);
         var2.putLong("tload", this.zzYe);
         var2.putLong("pcc", this.zzYf);
         var2.putLong("tfetch", this.zzYc);
         ArrayList var3 = new ArrayList();
         Iterator var4 = this.zzXZ.iterator();

         while(var4.hasNext()) {
            zzafi var5 = (zzafi)var4.next();
            var3.add(var5.toBundle());
         }

         var2.putParcelableArrayList("tclick", var3);
         return var2;
      }
   }
}
