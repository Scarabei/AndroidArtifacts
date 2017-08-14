package com.google.android.gms.internal;

@zzzn
public abstract class zzzy implements zzahp, zzzw {
   private final zzajp zzSs;
   private final zzzw zzSt;
   private final Object mLock = new Object();

   public zzzy(zzajp var1, zzzw var2) {
      this.zzSs = var1;
      this.zzSt = var2;
   }

   public abstract void zzgA();

   public abstract zzaam zzgB();

   public final void zza(zzaai var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzSt.zza(var1);
         this.zzgA();
      }
   }

   public final void cancel() {
      this.zzgA();
   }

   final boolean zza(zzaam var1, zzaae var2) {
      try {
         var1.zza((zzaae)var2, (zzaap)(new zzaah(this)));
         return true;
      } catch (Throwable var4) {
         zzafr.zzc("Could not fetch ad response from ad request service due to an Exception.", var4);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza(var4, "AdRequestClientTask.getAdResponseFromService");
         this.zzSt.zza(new zzaai(0));
         return false;
      }
   }

   // $FF: synthetic method
   public final Object zzgp() {
      zzaam var2;
      if ((var2 = this.zzgB()) == null) {
         this.zzSt.zza(new zzaai(0));
         this.zzgA();
         return null;
      } else {
         this.zzSs.zza(new zzzz(this, var2), new zzaaa(this));
         return null;
      }
   }
}
