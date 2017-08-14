package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class zzej {
   private static zzej zzrG = new zzej("@@ContextManagerNullAccount@@");
   private static zzek zzrH = null;
   private final String mName;

   public zzej(String var1) {
      this.mName = zzbo.zzcF(var1);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzej)) {
         return false;
      } else {
         zzej var2 = (zzej)var1;
         return TextUtils.equals(this.mName, var2.mName);
      }
   }

   public final String toString() {
      return "#account#";
   }
}
