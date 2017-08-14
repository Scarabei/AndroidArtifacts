package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class do {
   private final Integer zzbLj;
   private final Object mValue;
   private final List zzbLh = new ArrayList();
   private boolean zzbLk = false;

   public do(int var1, Object var2) {
      this.zzbLj = var1;
      this.mValue = var2;
   }

   public final do zzbF(int var1) {
      this.zzbLh.add(var1);
      return this;
   }

   public final do zzau(boolean var1) {
      this.zzbLk = true;
      return this;
   }

   public final dm zzDj() {
      zzbo.zzu(this.zzbLj);
      zzbo.zzu(this.mValue);
      return new dm(this.zzbLj, this.mValue, this.zzbLh, this.zzbLk, (dn)null);
   }
}
