package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzasv extends BeaconState.TypeFilter {
   public static final Creator CREATOR = new zzaug();
   private final ack zzanT;

   zzasv(byte[] var1) {
      ack var2 = null;

      try {
         var2 = (ack)adp.zza(new ack(), var1);
      } catch (ado var4) {
         zzeq.zzd("BeaconStateImpl", "Could not deserialize BeaconFence.BeaconTypeFilter");
      }

      this.zzanT = var2;
   }

   public zzasv(String var1, String var2) {
      this.zzanT = new ack();
      this.zzanT.zzbxU = zzbo.zzcF(var1);
      this.zzanT.type = zzbo.zzcF(var2);
   }

   public zzasv(String var1, String var2, byte[] var3) {
      this.zzanT = new ack();
      this.zzanT.zzbxU = zzbo.zzcF(var1);
      this.zzanT.type = zzbo.zzcF(var2);
      this.zzanT.content = (byte[])zzbo.zzu(var3);
   }

   private final String getNamespace() {
      return this.zzanT == null ? null : this.zzanT.zzbxU;
   }

   private final String getType() {
      return this.zzanT == null ? null : this.zzanT.type;
   }

   private final byte[] getContent() {
      return this.zzanT != null && this.zzanT.content != null && this.zzanT.content.length != 0 ? this.zzanT.content : null;
   }

   public final ack zzmV() {
      return this.zzanT;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getNamespace(), this.getType(), this.getContent() == null ? 0 : Arrays.hashCode(this.getContent())});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzasv)) {
         return false;
      } else {
         zzasv var2 = (zzasv)var1;
         return TextUtils.equals(this.getNamespace(), var2.getNamespace()) && TextUtils.equals(this.getType(), var2.getType()) && Arrays.equals(this.getContent(), var2.getContent());
      }
   }

   public final String toString() {
      String var1 = String.valueOf(this.getNamespace());
      String var2 = String.valueOf(this.getType());
      String var3 = this.getContent() == null ? "null" : new String(this.getContent());
      return (new StringBuilder(4 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append("(").append(var1).append(",").append(var2).append(",").append(var3).append(")").toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, adp.zzc(this.zzanT), false);
      zzd.zzI(var1, var5);
   }
}
