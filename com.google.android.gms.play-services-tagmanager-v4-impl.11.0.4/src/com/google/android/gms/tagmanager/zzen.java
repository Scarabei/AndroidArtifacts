package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.zzbf;
import java.util.Map;

final class zzen extends zzbr {
   private static final String ID;
   private final Context mContext;

   public zzen(Context var1) {
      super(ID);
      this.mContext = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      DisplayMetrics var2 = new DisplayMetrics();
      ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(var2);
      int var3 = var2.widthPixels;
      int var4 = var2.heightPixels;
      return zzgk.zzI((new StringBuilder(23)).append(var3).append("x").append(var4).toString());
   }

   static {
      ID = zzbf.zzdN.toString();
   }
}
