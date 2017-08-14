package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

final class zzcuw {
   private final long zzbEO;
   private final long zzaid;
   private final long zzbEP;
   private String zzbEQ;
   private String zzbIi;
   private Map zzbIj;
   private String zzbIk;

   final long zzBm() {
      return this.zzbEO;
   }

   final long zzBn() {
      return this.zzbEP;
   }

   zzcuw(long var1, long var3, long var5) {
      this.zzbEO = var1;
      this.zzaid = var3;
      this.zzbEP = var5;
   }

   final String zzBo() {
      return this.zzbEQ;
   }

   final void zzfl(String var1) {
      if (var1 != null && !TextUtils.isEmpty(var1.trim())) {
         this.zzbEQ = var1;
      }
   }

   final void zzfD(String var1) {
      this.zzbIi = var1;
   }

   final void zzu(Map var1) {
      this.zzbIj = var1;
   }

   final void zzfE(String var1) {
      this.zzbIk = var1;
   }

   final String zzCo() {
      return this.zzbIi;
   }

   final Map zzCp() {
      return this.zzbIj;
   }

   final String zzCq() {
      return this.zzbIk;
   }
}
