package com.google.android.gms.awareness.fence;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbiy;
import com.google.android.gms.internal.zzbjj;
import com.google.android.gms.internal.zzbjt;
import java.util.ArrayList;

public interface FenceUpdateRequest {
   public static class Builder {
      private final ArrayList zzanP = new ArrayList();

      public FenceUpdateRequest.Builder addFence(String var1, AwarenessFence var2, PendingIntent var3) {
         zzbo.zzcF(var1);
         zzbo.zzu(var2);
         zzbo.zzu(var3);
         this.zzanP.add(zzbjt.zza(var1, 0L, (zzbiy)var2, var3));
         return this;
      }

      public FenceUpdateRequest.Builder removeFence(PendingIntent var1) {
         zzbo.zzu(var1);
         this.zzanP.add(zzbjt.zza(var1));
         return this;
      }

      public FenceUpdateRequest.Builder removeFence(String var1) {
         zzbo.zzcF(var1);
         this.zzanP.add(zzbjt.zzcN(var1));
         return this;
      }

      public FenceUpdateRequest build() {
         return new zzbjj(this.zzanP);
      }
   }
}
