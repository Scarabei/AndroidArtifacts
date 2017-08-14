package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public final class RealTimeMessage implements Parcelable {
   private final String zzbdD;
   private final byte[] zzbdE;
   private final int zzbdF;
   public static final int UNRELIABLE = 0;
   public static final int RELIABLE = 1;
   public static final Creator CREATOR = new zza();

   private RealTimeMessage(String var1, byte[] var2, int var3) {
      this.zzbdD = (String)zzbo.zzu(var1);
      this.zzbdE = (byte[])((byte[])zzbo.zzu(var2)).clone();
      this.zzbdF = var3;
   }

   public final String getSenderParticipantId() {
      return this.zzbdD;
   }

   public final byte[] getMessageData() {
      return this.zzbdE;
   }

   public final boolean isReliable() {
      return this.zzbdF == 1;
   }

   private RealTimeMessage(Parcel var1) {
      this(var1.readString(), var1.createByteArray(), var1.readInt());
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.zzbdD);
      var1.writeByteArray(this.zzbdE);
      var1.writeInt(this.zzbdF);
   }

   // $FF: synthetic method
   RealTimeMessage(Parcel var1, zza var2) {
      this(var1);
   }
}
