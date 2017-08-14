package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbp extends zza {
   public static final Creator CREATOR = new zzbq();
   private int zzaku;
   private final Account zzajb;
   private final int zzaIo;
   private final GoogleSignInAccount zzaIp;

   zzbp(int var1, Account var2, int var3, GoogleSignInAccount var4) {
      this.zzaku = var1;
      this.zzajb = var2;
      this.zzaIo = var3;
      this.zzaIp = var4;
   }

   public zzbp(Account var1, int var2, GoogleSignInAccount var3) {
      this(2, var1, var2, var3);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzajb, var2, false);
      zzd.zzc(var1, 3, this.zzaIo);
      zzd.zza(var1, 4, this.zzaIp, var2, false);
      zzd.zzI(var1, var5);
   }
}
