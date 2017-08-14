package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzag;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zznc extends zznf {
   private final zzag zzHe;
   @Nullable
   private final String zzHf;
   private final String zzHg;

   public zznc(zzag var1, @Nullable String var2, String var3) {
      this.zzHe = var1;
      this.zzHf = var2;
      this.zzHg = var3;
   }

   public final String zzdX() {
      return this.zzHf;
   }

   public final String getContent() {
      return this.zzHg;
   }

   public final void zzi(@Nullable IObjectWrapper var1) {
      if (var1 != null) {
         this.zzHe.zzc((View)zzn.zzE(var1));
      }
   }

   public final void recordClick() {
      this.zzHe.zzaL();
   }

   public final void recordImpression() {
      this.zzHe.zzaM();
   }
}
