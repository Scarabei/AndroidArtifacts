package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;

public class LocationSettingsResponse extends Response {
   public LocationSettingsStates getLocationSettingsStates() {
      return ((LocationSettingsResult)this.getResult()).getLocationSettingsStates();
   }
}
