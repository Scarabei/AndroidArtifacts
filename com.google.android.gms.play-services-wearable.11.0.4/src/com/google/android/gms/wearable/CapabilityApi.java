package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public interface CapabilityApi {
   String ACTION_CAPABILITY_CHANGED = "com.google.android.gms.wearable.CAPABILITY_CHANGED";
   int FILTER_ALL = 0;
   int FILTER_REACHABLE = 1;
   int FILTER_LITERAL = 0;
   int FILTER_PREFIX = 1;

   PendingResult getCapability(GoogleApiClient var1, String var2, int var3);

   PendingResult getAllCapabilities(GoogleApiClient var1, int var2);

   PendingResult addLocalCapability(GoogleApiClient var1, String var2);

   PendingResult removeLocalCapability(GoogleApiClient var1, String var2);

   PendingResult addCapabilityListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, String var3);

   PendingResult removeCapabilityListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, String var3);

   PendingResult addListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2, Uri var3, int var4);

   PendingResult removeListener(GoogleApiClient var1, CapabilityApi.CapabilityListener var2);

   public interface RemoveLocalCapabilityResult extends Result {
   }

   public interface AddLocalCapabilityResult extends Result {
   }

   public interface GetAllCapabilitiesResult extends Result {
      Map getAllCapabilities();
   }

   public interface GetCapabilityResult extends Result {
      CapabilityInfo getCapability();
   }

   public interface CapabilityListener {
      void onCapabilityChanged(CapabilityInfo var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface CapabilityFilterType {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface NodeFilterType {
   }
}
