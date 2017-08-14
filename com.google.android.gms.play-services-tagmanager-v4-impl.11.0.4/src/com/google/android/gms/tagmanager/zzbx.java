package com.google.android.gms.tagmanager;

import android.text.TextUtils;

final class zzbx {
   private final long zzbEO;
   private final long zzaid;
   private final long zzbEP;
   private String zzbEQ;

   final long zzBm() {
      return this.zzbEO;
   }

   final long zzBn() {
      return this.zzbEP;
   }

   zzbx(long var1, long var3, long var5) {
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
}
