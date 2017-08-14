package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;

public final class zzcvz {
   private final Context mContext;
   private final String zzbDw;
   private final zzcn zzbHN;
   private final zzce zzbHW;

   public zzcvz(Context var1, zzcn var2, zzce var3, String var4) {
      this.mContext = var1.getApplicationContext();
      this.zzbHN = var2;
      this.zzbHW = var3;
      this.zzbDw = var4;
   }

   public final zzcvu zza(db var1, dj var2) {
      return new zzcvu(this.mContext, this.zzbDw, var1, var2, this.zzbHN, this.zzbHW);
   }
}
