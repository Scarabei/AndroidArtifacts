package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface DataApi {
   String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
   int FILTER_LITERAL = 0;
   int FILTER_PREFIX = 1;

   PendingResult putDataItem(GoogleApiClient var1, PutDataRequest var2);

   PendingResult getDataItem(GoogleApiClient var1, Uri var2);

   PendingResult getDataItems(GoogleApiClient var1);

   PendingResult getDataItems(GoogleApiClient var1, Uri var2);

   PendingResult getDataItems(GoogleApiClient var1, Uri var2, int var3);

   PendingResult deleteDataItems(GoogleApiClient var1, Uri var2);

   PendingResult deleteDataItems(GoogleApiClient var1, Uri var2, int var3);

   PendingResult getFdForAsset(GoogleApiClient var1, Asset var2);

   PendingResult getFdForAsset(GoogleApiClient var1, DataItemAsset var2);

   PendingResult addListener(GoogleApiClient var1, DataApi.DataListener var2);

   PendingResult addListener(GoogleApiClient var1, DataApi.DataListener var2, Uri var3, int var4);

   PendingResult removeListener(GoogleApiClient var1, DataApi.DataListener var2);

   public interface DataListener {
      void onDataChanged(DataEventBuffer var1);
   }

   public interface DeleteDataItemsResult extends Result {
      int getNumDeleted();
   }

   public interface GetFdForAssetResult extends Releasable, Result {
      ParcelFileDescriptor getFd();

      InputStream getInputStream();
   }

   public interface DataItemResult extends Result {
      DataItem getDataItem();
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface FilterType {
   }
}
