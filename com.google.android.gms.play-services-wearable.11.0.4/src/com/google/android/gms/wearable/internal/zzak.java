package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;

public final class zzak extends com.google.android.gms.common.internal.safeparcel.zza implements Channel {
   public static final Creator CREATOR = new zzau();
   private final String zzakv;
   private final String zzbRe;
   private final String mPath;

   public zzak(String var1, String var2, String var3) {
      this.zzakv = (String)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbRe = (String)com.google.android.gms.common.internal.zzbo.zzu(var2);
      this.mPath = (String)com.google.android.gms.common.internal.zzbo.zzu(var3);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getNodeId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getPath(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = this.zzakv;
      String var2 = this.zzbRe;
      String var3 = this.mPath;
      return (new StringBuilder(43 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append("ChannelImpl{, token='").append(var1).append("', nodeId='").append(var2).append("', path='").append(var3).append("'}").toString();
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzak)) {
         return false;
      } else {
         zzak var2 = (zzak)var1;
         return this.zzakv.equals(var2.zzakv) && com.google.android.gms.common.internal.zzbe.equal(var2.zzbRe, this.zzbRe) && com.google.android.gms.common.internal.zzbe.equal(var2.mPath, this.mPath);
      }
   }

   public final int hashCode() {
      return this.zzakv.hashCode();
   }

   public final String getNodeId() {
      return this.zzbRe;
   }

   public final String getPath() {
      return this.mPath;
   }

   public final PendingResult close(GoogleApiClient var1) {
      return var1.zzd(new zzal(this, var1));
   }

   public final PendingResult close(GoogleApiClient var1, int var2) {
      return var1.zzd(new zzam(this, var1, var2));
   }

   public final PendingResult getInputStream(GoogleApiClient var1) {
      return var1.zzd(new zzan(this, var1));
   }

   public final PendingResult getOutputStream(GoogleApiClient var1) {
      return var1.zzd(new zzao(this, var1));
   }

   public final PendingResult receiveFile(GoogleApiClient var1, Uri var2, boolean var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "uri is null");
      return var1.zzd(new zzap(this, var1, var2, var3));
   }

   public final PendingResult sendFile(GoogleApiClient var1, Uri var2) {
      return this.sendFile(var1, var2, 0L, -1L);
   }

   public final PendingResult sendFile(GoogleApiClient var1, Uri var2, long var3, long var5) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(this.zzakv, "token is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "uri is null");
      com.google.android.gms.common.internal.zzbo.zzb(var3 >= 0L, "startOffset is negative: %s", new Object[]{var3});
      com.google.android.gms.common.internal.zzbo.zzb(var5 >= 0L || var5 == -1L, "invalid length: %s", new Object[]{var5});
      return var1.zzd(new zzaq(this, var1, var2, var3, var5));
   }

   public final PendingResult addListener(GoogleApiClient var1, ChannelApi.ChannelListener var2) {
      IntentFilter[] var3 = new IntentFilter[]{zzez.zzgl("com.google.android.gms.wearable.CHANNEL_EVENT")};
      String var4 = this.zzakv;
      return zzb.zza(var1, new zzar(var4, var3), var2);
   }

   public final PendingResult removeListener(GoogleApiClient var1, ChannelApi.ChannelListener var2) {
      com.google.android.gms.common.internal.zzbo.zzb(var1, "client is null");
      com.google.android.gms.common.internal.zzbo.zzb(var2, "listener is null");
      return var1.zzd(new zzag(var1, var2, this.zzakv));
   }

   // $FF: synthetic method
   static String zza(zzak var0) {
      return var0.zzakv;
   }
}
