package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzbi implements DataApi {
   public final PendingResult putDataItem(GoogleApiClient var1, PutDataRequest var2) {
      return var1.zzd(new zzbj(this, var1, var2));
   }

   public final PendingResult getDataItem(GoogleApiClient var1, Uri var2) {
      return var1.zzd(new zzbk(this, var1, var2));
   }

   public final PendingResult getDataItems(GoogleApiClient var1) {
      return var1.zzd(new zzbl(this, var1));
   }

   public final PendingResult getDataItems(GoogleApiClient var1, Uri var2) {
      return this.getDataItems(var1, var2, 0);
   }

   public final PendingResult getDataItems(GoogleApiClient var1, Uri var2, int var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var2 != null, "uri must not be null");
      com.google.android.gms.common.internal.zzbo.zzb(var3 == 0 || var3 == 1, "invalid filter type");
      return var1.zzd(new zzbm(this, var1, var2, var3));
   }

   public final PendingResult deleteDataItems(GoogleApiClient var1, Uri var2) {
      return this.deleteDataItems(var1, var2, 0);
   }

   public final PendingResult deleteDataItems(GoogleApiClient var1, Uri var2, int var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var2 != null, "uri must not be null");
      com.google.android.gms.common.internal.zzbo.zzb(var3 == 0 || var3 == 1, "invalid filter type");
      return var1.zzd(new zzbn(this, var1, var2, var3));
   }

   public final PendingResult getFdForAsset(GoogleApiClient var1, Asset var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("asset is null");
      } else if (var2.getDigest() == null) {
         throw new IllegalArgumentException("invalid asset");
      } else if (var2.getData() != null) {
         throw new IllegalArgumentException("invalid asset");
      } else {
         return var1.zzd(new zzbo(this, var1, var2));
      }
   }

   public final PendingResult getFdForAsset(GoogleApiClient var1, DataItemAsset var2) {
      return var1.zzd(new zzbp(this, var1, var2));
   }

   public final PendingResult addListener(GoogleApiClient var1, DataApi.DataListener var2) {
      IntentFilter[] var3 = new IntentFilter[]{zzez.zzgl("com.google.android.gms.wearable.DATA_CHANGED")};
      return zza(var1, var2, var3);
   }

   public final PendingResult addListener(GoogleApiClient var1, DataApi.DataListener var2, Uri var3, int var4) {
      com.google.android.gms.common.internal.zzbo.zzb(var3 != null, "uri must not be null");
      com.google.android.gms.common.internal.zzbo.zzb(var4 == 0 || var4 == 1, "invalid filter type");
      IntentFilter var5 = zzez.zza("com.google.android.gms.wearable.DATA_CHANGED", var3, var4);
      return zza(var1, var2, new IntentFilter[]{var5});
   }

   private static PendingResult zza(GoogleApiClient var0, DataApi.DataListener var1, IntentFilter[] var2) {
      return zzb.zza(var0, new zzbq(var2), var1);
   }

   public final PendingResult removeListener(GoogleApiClient var1, DataApi.DataListener var2) {
      return var1.zzd(new zzbr(this, var1, var2));
   }
}
