package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.api.CommonStatusCodes;

public class NearbyMessagesStatusCodes extends CommonStatusCodes {
   public static final int TOO_MANY_PENDING_INTENTS = 2801;
   public static final int APP_NOT_OPTED_IN = 2802;
   public static final int DISALLOWED_CALLING_CONTEXT = 2803;
   public static final int APP_QUOTA_LIMIT_REACHED = 2804;
   public static final int NOT_AUTHORIZED = 2805;
   public static final int FORBIDDEN = 2806;
   public static final int MISSING_PERMISSIONS = 2807;
   public static final int BLUETOOTH_OFF = 2820;
   public static final int BLE_ADVERTISING_UNSUPPORTED = 2821;
   public static final int BLE_SCANNING_UNSUPPORTED = 2822;

   public static String getStatusCodeString(int var0) {
      switch(var0) {
      case 2801:
         return "TOO_MANY_PENDING_INTENTS";
      case 2802:
         return "APP_NOT_OPTED_IN";
      case 2803:
         return "DISALLOWED_CALLING_CONTEXT";
      case 2804:
      case 2807:
      case 2808:
      case 2809:
      case 2810:
      case 2811:
      case 2812:
      case 2813:
      case 2814:
      case 2815:
      case 2816:
      case 2817:
      case 2818:
      case 2819:
      default:
         return CommonStatusCodes.getStatusCodeString(var0);
      case 2805:
         return "NOT_AUTHORIZED";
      case 2806:
         return "FORBIDDEN";
      case 2820:
         return "BLUETOOTH_OFF";
      case 2821:
         return "BLE_ADVERTISING_UNSUPPORTED";
      case 2822:
         return "BLE_SCANNING_UNSUPPORTED";
      }
   }
}
