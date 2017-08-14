package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class zzaof {
   private int zzaiu;
   private ByteArrayOutputStream zzaiv;
   // $FF: synthetic field
   private zzaoe zzaiw;

   public zzaof(zzaoe var1) {
      this.zzaiw = var1;
      super();
      this.zzaiv = new ByteArrayOutputStream();
   }

   public final boolean zze(zzanx var1) {
      zzbo.zzu(var1);
      if (this.zzaiu + 1 > zzank.zzlt()) {
         return false;
      } else {
         String var2;
         if ((var2 = this.zzaiw.zza(var1, false)) == null) {
            this.zzaiw.zzkr().zza(var1, "Error formatting hit");
            return true;
         } else {
            byte[] var3;
            int var4;
            if ((var4 = (var3 = var2.getBytes()).length) > zzank.zzlp()) {
               this.zzaiw.zzkr().zza(var1, "Hit size exceeds the maximum size limit");
               return true;
            } else {
               if (this.zzaiv.size() > 0) {
                  ++var4;
               }

               if (this.zzaiv.size() + var4 > ((Integer)zzans.zzahE.get()).intValue()) {
                  return false;
               } else {
                  try {
                     if (this.zzaiv.size() > 0) {
                        this.zzaiv.write(zzaoe.zzlS());
                     }

                     this.zzaiv.write(var3);
                  } catch (IOException var6) {
                     this.zzaiw.zze("Failed to write payload when batching hits", var6);
                     return true;
                  }

                  ++this.zzaiu;
                  return true;
               }
            }
         }
      }
   }

   public final int zzlT() {
      return this.zzaiu;
   }

   public final byte[] getPayload() {
      return this.zzaiv.toByteArray();
   }
}
