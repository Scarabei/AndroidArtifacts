package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzbc extends zzbr {
   private static final String ID;
   private final Context mContext;

   public zzbc(Context var1) {
      super(ID);
      this.mContext = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      String var2;
      return (var2 = Secure.getString(this.mContext.getContentResolver(), "android_id")) == null ? zzgk.zzCh() : zzgk.zzI(var2);
   }

   static {
      ID = zzbf.zzdU.toString();
   }
}
