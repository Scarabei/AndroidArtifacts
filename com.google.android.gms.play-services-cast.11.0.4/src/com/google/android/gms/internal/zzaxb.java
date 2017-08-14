package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzaxb extends zzaxe {
   // $FF: synthetic field
   private int zzaxf;
   // $FF: synthetic field
   private String zzaxg;
   // $FF: synthetic field
   private JSONObject zzaxh;
   // $FF: synthetic field
   private zzawy zzaxd;

   zzaxb(zzawy var1, int var2, String var3, JSONObject var4) {
      this.zzaxd = var1;
      this.zzaxf = var2;
      this.zzaxg = var3;
      this.zzaxh = var4;
      super(var1);
   }

   public final void execute() {
      byte var10000;
      switch(this.zzaxf) {
      case 2:
         var10000 = 5;
         break;
      case 3:
         var10000 = 1;
         break;
      case 4:
         var10000 = 2;
         break;
      case 5:
         var10000 = 3;
         break;
      case 6:
         var10000 = 4;
         break;
      default:
         var10000 = 0;
      }

      byte var1 = var10000;
      if (var10000 == 0) {
         super.zzarw.zza(-1L, 2001, (Object)null);
         zzawy.zzow().zzf("sendPlayerRequest for unsupported playerState: %d", this.zzaxf);
      } else {
         zzawy.zza(this.zzaxd, this.zzaxg, var1, this.zzaxh, super.zzarw);
      }
   }
}
