package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzg extends AbstractDataBuffer {
   private boolean zzaFO = false;
   private ArrayList zzaFP;

   protected zzg(DataHolder var1) {
      super(var1);
   }

   public final Object get(int var1) {
      this.zzqT();
      int var10001 = this.zzaw(var1);
      int var10002;
      if (var1 >= 0 && var1 != this.zzaFP.size()) {
         int var3;
         if (var1 == this.zzaFP.size() - 1) {
            var3 = this.zzaCX.zzaFG - ((Integer)this.zzaFP.get(var1)).intValue();
         } else {
            var3 = ((Integer)this.zzaFP.get(var1 + 1)).intValue() - ((Integer)this.zzaFP.get(var1)).intValue();
         }

         if (var3 == 1) {
            int var4 = this.zzaw(var1);
            this.zzaCX.zzat(var4);
         }

         var10002 = var3;
      } else {
         var10002 = 0;
      }

      return this.zzi(var10001, var10002);
   }

   public int getCount() {
      this.zzqT();
      return this.zzaFP.size();
   }

   protected abstract Object zzi(int var1, int var2);

   protected abstract String zzqS();

   private final void zzqT() {
      synchronized(this) {
         if (!this.zzaFO) {
            int var2 = this.zzaCX.zzaFG;
            this.zzaFP = new ArrayList();
            if (var2 > 0) {
               this.zzaFP.add(Integer.valueOf(0));
               String var3 = this.zzqS();
               int var4 = this.zzaCX.zzat(0);
               String var5 = this.zzaCX.zzd(var3, 0, var4);

               for(int var6 = 1; var6 < var2; ++var6) {
                  var4 = this.zzaCX.zzat(var6);
                  String var7;
                  if ((var7 = this.zzaCX.zzd(var3, var6, var4)) == null) {
                     throw new NullPointerException((new StringBuilder(78 + String.valueOf(var3).length())).append("Missing value for markerColumn: ").append(var3).append(", at row: ").append(var6).append(", for window: ").append(var4).toString());
                  }

                  if (!var7.equals(var5)) {
                     var5 = var7;
                     this.zzaFP.add(var6);
                  }
               }
            }

            this.zzaFO = true;
         }

      }
   }

   private final int zzaw(int var1) {
      if (var1 >= 0 && var1 < this.zzaFP.size()) {
         return ((Integer)this.zzaFP.get(var1)).intValue();
      } else {
         throw new IllegalArgumentException((new StringBuilder(53)).append("Position ").append(var1).append(" is out of bounds for this buffer").toString());
      }
   }
}
