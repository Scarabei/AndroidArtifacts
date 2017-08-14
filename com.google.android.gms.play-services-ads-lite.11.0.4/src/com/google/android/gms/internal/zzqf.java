package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzzn
public final class zzqf extends zzpu {
   private final NativeCustomTemplateAd.OnCustomClickListener zzIN;

   public zzqf(NativeCustomTemplateAd.OnCustomClickListener var1) {
      this.zzIN = var1;
   }

   public final void zzb(zzpj var1, String var2) {
      this.zzIN.onCustomClick(new zzpm(var1), var2);
   }
}
