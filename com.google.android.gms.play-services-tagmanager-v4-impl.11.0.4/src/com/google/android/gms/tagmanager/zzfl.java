package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzfl extends zzbr {
   private static final String ID;

   public zzfl() {
      super(ID);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      return zzgk.zzI(VERSION.SDK_INT);
   }

   static {
      ID = zzbf.zzdP.toString();
   }
}
