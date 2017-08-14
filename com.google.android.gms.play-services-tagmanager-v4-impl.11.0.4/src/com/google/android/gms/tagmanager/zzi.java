package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzi extends zzbr {
   private static final String ID;
   private final Context mContext;

   public zzi(Context var1) {
      super(ID);
      this.mContext = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return zzgk.zzI(this.mContext.getPackageName());
   }

   static {
      ID = zzbf.zzdo.toString();
   }
}
