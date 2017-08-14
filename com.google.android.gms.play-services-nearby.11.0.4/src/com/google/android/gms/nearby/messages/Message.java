package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcpj;
import java.util.Arrays;

public class Message extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int MAX_CONTENT_SIZE_BYTES = 102400;
   public static final int MAX_TYPE_LENGTH = 32;
   public static final Creator CREATOR = new zza();
   private static final zzcpj[] zzbxT;
   public static final String MESSAGE_NAMESPACE_RESERVED = "__reserved_namespace";
   public static final String MESSAGE_TYPE_EDDYSTONE_UID = "__eddystone_uid";
   public static final String MESSAGE_TYPE_I_BEACON_ID = "__i_beacon_id";
   public static final String MESSAGE_TYPE_AUDIO_BYTES = "__audio_bytes";
   private int versionCode;
   private final byte[] content;
   private final String type;
   private final String zzbxU;
   /** @deprecated */
   @Deprecated
   private zzcpj[] zzbxV;
   private final long zzbxW;

   Message(int var1, @Nullable byte[] var2, @Nullable String var3, String var4, @Nullable zzcpj[] var5, long var6) {
      this.versionCode = var1;
      this.type = (String)zzbo.zzu(var4);
      this.zzbxU = var3 == null ? "" : var3;
      this.zzbxW = 0L;
      zzbo.zzu(var2);
      zzbo.zzb(var2.length <= 102400, "Content length(%d) must not exceed MAX_CONTENT_SIZE_BYTES(%d)", new Object[]{var2.length, 102400});
      this.content = var2;
      if (var5 == null || var5.length == 0) {
         var5 = zzbxT;
      }

      this.zzbxV = var5;
      zzbo.zzb(var4.length() <= 32, "Type length(%d) must not exceed MAX_TYPE_LENGTH(%d)", new Object[]{var4.length(), Integer.valueOf(32)});
   }

   public final boolean zzeD(String var1) {
      return "__reserved_namespace".equals(this.getNamespace()) && var1.equals(this.getType());
   }

   public Message(byte[] var1) {
      this(var1, "", "");
   }

   public Message(byte[] var1, String var2) {
      this(var1, "", var2);
   }

   public Message(byte[] var1, String var2, String var3) {
      this(var1, var2, var3, zzbxT);
   }

   private Message(byte[] var1, String var2, String var3, zzcpj[] var4) {
      this(var1, var2, var3, var4, 0L);
   }

   private Message(byte[] var1, String var2, String var3, zzcpj[] var4, long var5) {
      this(2, var1, var2, var3, var4, 0L);
   }

   public String getType() {
      return this.type;
   }

   public String getNamespace() {
      return this.zzbxU;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getContent(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getNamespace(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbxV, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, 0L);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxU, this.type, Arrays.hashCode(this.content), 0L});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Message)) {
         return false;
      } else {
         Message var2 = (Message)var1;
         return TextUtils.equals(this.zzbxU, var2.zzbxU) && TextUtils.equals(this.type, var2.type) && Arrays.equals(this.content, var2.content) && 0L == 0L;
      }
   }

   public String toString() {
      String var1 = this.zzbxU;
      String var2 = this.type;
      int var3 = this.content == null ? 0 : this.content.length;
      return (new StringBuilder(59 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Message{namespace='").append(var1).append("', type='").append(var2).append("', content=[").append(var3).append(" bytes]}").toString();
   }

   static {
      zzbxT = new zzcpj[]{zzcpj.zzbyM};
   }
}
