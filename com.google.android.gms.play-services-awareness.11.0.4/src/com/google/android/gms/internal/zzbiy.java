package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class zzbiy extends AwarenessFence {
   public static final Creator CREATOR = new zzbiz();
   private acn zzaKZ;
   private byte[] zzaLa;

   public static zzbiy zze(Collection var0) {
      zzbo.zzu(var0);
      zzbo.zzaf(!var0.isEmpty());
      acn var1;
      (var1 = zzaK(1)).zzcqN = zzg(var0);
      return new zzbiy(var1);
   }

   public static zzbiy zzf(Collection var0) {
      zzbo.zzu(var0);
      zzbo.zzaf(!var0.isEmpty());
      acn var1;
      (var1 = zzaK(2)).zzcqN = zzg(var0);
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbiy var0) {
      zzbo.zzu(var0);
      ArrayList var1;
      (var1 = new ArrayList()).add(var0);
      acn var2;
      (var2 = zzaK(3)).zzcqN = zzg(var1);
      return new zzbiy(var2);
   }

   public static zzbiy zza(zzbjr var0) {
      zzbo.zzu(var0);
      acn var1;
      if (var0.zzsI().zzcrI) {
         (var1 = zzaK(20)).zzcrf = var0.zzsI();
      } else {
         (var1 = zzaK(4)).zzcqO = var0.zzsI();
      }

      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbjo var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(5)).zzcqP = var0.zzsG();
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbin var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(7)).zzcqR = var0.zzsB();
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbio var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(11)).zzcqV = var0.zzsE();
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbis var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(12)).zzcqW = var0.zzsF();
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbjs var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(15)).zzcra = var0.zzsJ();
      return new zzbiy(var1);
   }

   public static zzbiy zza(zzbjq var0) {
      zzbo.zzu(var0);
      acn var1;
      (var1 = zzaK(19)).zzcre = var0.zzsH();
      return new zzbiy(var1);
   }

   private static acn zzaK(int var0) {
      acn var1;
      (var1 = new acn()).type = var0;
      return var1;
   }

   private static acn[] zzg(Collection var0) {
      acn[] var1 = new acn[var0.size()];
      int var2 = 0;

      int var10001;
      zzbiy var4;
      for(Iterator var3 = var0.iterator(); var3.hasNext(); var1[var10001] = var4.zzaKZ) {
         var4 = (zzbiy)var3.next();
         var10001 = var2++;
         var4.zzsz();
      }

      return var1;
   }

   public zzbiy(byte[] var1) {
      this.zzaKZ = null;
      this.zzaLa = var1;
      this.zzsA();
   }

   private zzbiy(acn var1) {
      this.zzaKZ = (acn)zzbo.zzu(var1);
      this.zzaLa = null;
      this.zzsA();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaLa != null ? this.zzaLa : adp.zzc(this.zzaKZ), false);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      this.zzsz();
      return this.zzaKZ.toString();
   }

   private final void zzsz() {
      if (this.zzaKZ == null) {
         try {
            byte[] var2 = this.zzaLa;
            this.zzaKZ = (acn)adp.zza(new acn(), var2);
            this.zzaLa = null;
         } catch (ado var3) {
            zzeq.zza("ContextFenceStub", "Could not deserialize context fence bytes.", (Throwable)var3);
            throw new IllegalStateException(var3);
         }
      }

      this.zzsA();
   }

   private final void zzsA() {
      if (this.zzaKZ != null || this.zzaLa == null) {
         if (this.zzaKZ == null || this.zzaLa != null) {
            if (this.zzaKZ != null && this.zzaLa != null) {
               throw new IllegalStateException("Invalid internal representation - full");
            } else if (this.zzaKZ == null && this.zzaLa == null) {
               throw new IllegalStateException("Invalid internal representation - empty");
            } else {
               throw new IllegalStateException("Impossible");
            }
         }
      }
   }
}
