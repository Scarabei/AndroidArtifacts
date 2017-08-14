package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzzn
public final class zzmk {
   private final Collection zzBP = new ArrayList();
   private final Collection zzBQ = new ArrayList();
   private final Collection zzBR = new ArrayList();

   public final void zza(zzme var1) {
      this.zzBP.add(var1);
   }

   public final void zzb(zzme var1) {
      this.zzBQ.add(var1);
   }

   public final void zzc(zzme var1) {
      this.zzBR.add(var1);
   }

   public final void zza(Editor var1, int var2, JSONObject var3) {
      Iterator var4 = this.zzBP.iterator();

      while(var4.hasNext()) {
         zzme var5;
         if ((var5 = (zzme)var4.next()).getSource() == 1) {
            var5.zza(var1, var5.zzb(var3));
         }
      }

   }

   public final List zzdJ() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.zzBQ.iterator();

      while(var2.hasNext()) {
         zzme var4 = (zzme)var2.next();
         String var3;
         if ((var3 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)) != null) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public final List zzdK() {
      List var1 = this.zzdJ();
      Iterator var2 = this.zzBR.iterator();

      while(var2.hasNext()) {
         zzme var4 = (zzme)var2.next();
         String var3;
         if ((var3 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)) != null) {
            var1.add(var3);
         }
      }

      return var1;
   }
}
