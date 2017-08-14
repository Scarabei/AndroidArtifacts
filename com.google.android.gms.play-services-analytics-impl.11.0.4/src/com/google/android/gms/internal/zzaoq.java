package com.google.android.gms.internal;

final class zzaoq extends zzamg implements zzanj {
   private final zzaor zzaiH = new zzaor();

   public zzaoq(zzamj var1) {
      super(var1);
   }

   public final void zzl(String var1, String var2) {
      this.zzaiH.zzaiN.put(var1, var2);
   }

   public final void zzm(String var1, String var2) {
      if ("ga_trackingId".equals(var1)) {
         this.zzaiH.zzado = var2;
      } else if ("ga_sampleFrequency".equals(var1)) {
         try {
            this.zzaiH.zzaiI = Double.parseDouble(var2);
         } catch (NumberFormatException var4) {
            this.zzc("Error parsing ga_sampleFrequency value", var2, var4);
         }
      } else {
         this.zzd("string configuration name not recognized", var1);
      }
   }

   public final void zze(String var1, boolean var2) {
      if ("ga_autoActivityTracking".equals(var1)) {
         this.zzaiH.zzaiK = var2 ? 1 : 0;
      } else if ("ga_anonymizeIp".equals(var1)) {
         this.zzaiH.zzaiL = var2 ? 1 : 0;
      } else if ("ga_reportUncaughtExceptions".equals(var1)) {
         this.zzaiH.zzaiM = var2 ? 1 : 0;
      } else {
         this.zzd("bool configuration name not recognized", var1);
      }
   }

   public final void zzd(String var1, int var2) {
      if ("ga_sessionTimeout".equals(var1)) {
         this.zzaiH.zzaiJ = var2;
      } else {
         this.zzd("int configuration name not recognized", var1);
      }
   }

   // $FF: synthetic method
   public final zzanh zzlm() {
      return this.zzaiH;
   }
}
