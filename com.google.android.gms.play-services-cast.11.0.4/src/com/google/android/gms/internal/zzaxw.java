package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.IOException;

public class zzaxw {
   protected final zzayo zzarK;
   private final String zzanR;
   private zzays zzaxK;

   protected zzaxw(String var1, String var2, String var3) {
      zzaye.zzci(var1);
      this.zzanR = var1;
      this.zzarK = new zzayo(var2);
      this.setSessionLabel(var3);
   }

   public final void setSessionLabel(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.zzarK.zzcn(var1);
      }

   }

   public final String getNamespace() {
      return this.zzanR;
   }

   public final void zza(zzays var1) {
      this.zzaxK = var1;
      if (this.zzaxK == null) {
         this.zzoz();
      }

   }

   protected final void zza(String var1, long var2, String var4) throws IOException {
      Object[] var10000 = new Object[]{var1, null};
      this.zzaxK.zza(this.zzanR, var1, var2, (String)null);
   }

   public void zzch(@NonNull String var1) {
   }

   public void zzc(long var1, int var3) {
   }

   protected final long zzoA() {
      return this.zzaxK.zznl();
   }

   public void zzoz() {
   }
}
