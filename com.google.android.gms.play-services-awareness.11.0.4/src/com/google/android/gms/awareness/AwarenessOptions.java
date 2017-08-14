package com.google.android.gms.awareness;

import android.accounts.Account;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class AwarenessOptions implements Optional {
   public static final int NO_UID = -1;
   private final String zzanK;
   private final int zzanL;
   private final String zzanM;
   private final String zzanN;
   private final int zzanO;
   private final Account zzajb;

   /** @deprecated */
   @Deprecated
   public static AwarenessOptions create1p(String var0) {
      zzbo.zzcF(var0);
      return new AwarenessOptions(var0, 1, (String)null, (String)null, -1, (Account)null);
   }

   public static AwarenessOptions create1p(String var0, Account var1) {
      zzbo.zzcF(var0);
      return new AwarenessOptions(var0, 1, (String)null, (String)null, -1, var1);
   }

   protected AwarenessOptions(String var1, int var2, String var3, String var4, int var5, Account var6) {
      zzbo.zzb(var1, "moduleId must not be null");
      this.zzanK = var1;
      this.zzanL = var2;
      this.zzanM = var3;
      this.zzanN = var4;
      this.zzanO = var5;
      this.zzajb = var6;
   }

   public final String zzmQ() {
      return this.zzanK;
   }

   public final int zzmR() {
      return this.zzanL;
   }

   @Nullable
   public final String zzmS() {
      return this.zzanM;
   }

   @Nullable
   public final String zzmT() {
      return this.zzanN;
   }

   public final int zzmU() {
      return this.zzanO;
   }

   @Nullable
   public final Account getAccount() {
      return this.zzajb;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         AwarenessOptions var2 = (AwarenessOptions)var1;
         return this.zzanL == var2.zzanL && this.zzanO == var2.zzanO && zzbe.equal(this.zzanK, var2.zzanK) && zzbe.equal(this.zzanM, var2.zzanM) && zzbe.equal(this.zzanN, var2.zzanN) && zzbe.equal(this.zzajb, var2.zzajb);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzanK, this.zzanL, this.zzanM, this.zzanN, this.zzanO, this.zzajb});
   }
}
