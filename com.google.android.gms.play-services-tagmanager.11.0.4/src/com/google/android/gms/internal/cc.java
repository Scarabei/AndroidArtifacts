package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public abstract class cc {
   private int zzBM;
   protected final cl zzbKt;
   private ch zzbKu;
   private zze zzvw;
   @Nullable
   protected final zzcuo zzbHO;

   public cc(int var1, cl var2, ch var3, @Nullable zzcuo var4) {
      this(var1, var2, var3, var4, zzi.zzrY());
   }

   private cc(int var1, cl var2, ch var3, @Nullable zzcuo var4, zze var5) {
      this.zzbKt = (cl)zzbo.zzu(var2);
      zzbo.zzu(var2.zzCP());
      this.zzBM = var1;
      this.zzbKu = (ch)zzbo.zzu(var3);
      this.zzvw = (zze)zzbo.zzu(var5);
      this.zzbHO = var4;
   }

   public final void zzv(byte[] var1) {
      cm var2 = this.zzw(var1);
      if (this.zzbHO != null && this.zzBM == 0) {
         this.zzbHO.zzAV();
      }

      cm var3;
      if (var2 != null && var2.getStatus() == Status.zzaBm) {
         db var4 = var2.zzCQ().zzCV();
         cn var5 = new cn(this.zzbKt.zzCP(), var1, var4, this.zzvw.currentTimeMillis());
         dj var6 = var2.zzCR();
         var3 = new cm(Status.zzaBm, this.zzBM, var5, var6);
      } else {
         var3 = new cm(Status.zzaBo, this.zzBM);
      }

      this.zza(var3);
   }

   public final void zzk(int var1, int var2) {
      if (this.zzbHO != null && var2 == 0 && var1 == 3) {
         this.zzbHO.zzAU();
      }

      String var3 = String.valueOf(this.zzbKt.zzCP().getContainerId());
      String var10000;
      switch(var1) {
      case 0:
         var10000 = "Resource not available";
         break;
      case 1:
         var10000 = "IOError";
         break;
      case 2:
         var10000 = "Server error";
         break;
      default:
         var10000 = "Unknown reason";
      }

      String var4 = String.valueOf(var10000);
      zzcvk.v((new StringBuilder(61 + String.valueOf(var3).length() + String.valueOf(var4).length())).append("Failed to fetch the container resource for the container \"").append(var3).append("\": ").append(var4).toString());
      cm var5 = new cm(Status.zzaBo, var2);
      this.zza(var5);
   }

   private final cm zzw(byte[] var1) {
      cm var2 = null;

      try {
         if ((var2 = this.zzbKu.zzx(var1)) == null) {
            zzcvk.zzaS("Parsed resource from is null");
         }
      } catch (ca var3) {
         zzcvk.zzaS("Resource data is corrupted");
      }

      return var2;
   }

   protected abstract void zza(cm var1);
}
