package com.google.android.gms.drive;

public interface FileUploadPreferences {
   int PREFERENCE_VALUE_UNKNOWN = 0;
   int NETWORK_TYPE_ANY = 1;
   int NETWORK_TYPE_WIFI_ONLY = 2;
   int BATTERY_USAGE_UNRESTRICTED = 256;
   int BATTERY_USAGE_CHARGING_ONLY = 257;

   int getNetworkTypePreference();

   boolean isRoamingAllowed();

   int getBatteryUsagePreference();

   void setNetworkTypePreference(int var1);

   void setRoamingAllowed(boolean var1);

   void setBatteryUsagePreference(int var1);
}
