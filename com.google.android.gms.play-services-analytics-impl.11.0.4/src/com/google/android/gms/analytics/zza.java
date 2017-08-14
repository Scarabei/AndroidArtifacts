package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzalx;
import com.google.android.gms.internal.zzamj;
import java.util.ListIterator;

public class zza extends zzk {
   private final zzamj zzadj;
   private boolean zzadk;

   public zza(zzamj var1) {
      super(var1.zzkt(), var1.zzkq());
      this.zzadj = var1;
   }

   final zzamj zzji() {
      return this.zzadj;
   }

   public final zzi zzjj() {
      zzi var1;
      (var1 = super.zzaeb.zzjp()).zza((zzj)this.zzadj.zzkB().zzkW());
      var1.zza((zzj)this.zzadj.zzkC().zzlA());
      this.zzd(var1);
      return var1;
   }

   protected final void zza(zzi var1) {
      zzalt var2;
      if (TextUtils.isEmpty((var2 = (zzalt)var1.zzb(zzalt.class)).zzjX())) {
         var2.setClientId(this.zzadj.zzkJ().zzli());
      }

      if (this.zzadk && TextUtils.isEmpty(var2.zzjY())) {
         zzalx var3 = this.zzadj.zzkI();
         var2.zzbk(var3.zzkg());
         var2.zzG(var3.zzjZ());
      }

   }

   public final void enableAdvertisingIdCollection(boolean var1) {
      this.zzadk = var1;
   }

   public final void zzaY(String var1) {
      zzbo.zzcF(var1);
      Uri var4 = zzb.zzaZ(var1);
      ListIterator var5 = super.zzaeb.zzjr().listIterator();

      while(var5.hasNext()) {
         if (var4.equals(((zzo)var5.next()).zzjl())) {
            var5.remove();
         }
      }

      super.zzaeb.zzjr().add(new zzb(this.zzadj, var1));
   }
}
