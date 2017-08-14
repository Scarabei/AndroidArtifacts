package com.google.android.gms.internal;

import com.google.ads.AdRequest;

// $FF: synthetic class
final class zzwc {
   // $FF: synthetic field
   private static int[] zzNm;
   // $FF: synthetic field
   static final int[] zzNn = new int[AdRequest.ErrorCode.values().length];

   static {
      try {
         zzNn[AdRequest.ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
         ;
      }

      try {
         zzNn[AdRequest.ErrorCode.INVALID_REQUEST.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
         ;
      }

      try {
         zzNn[AdRequest.ErrorCode.NETWORK_ERROR.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
         ;
      }

      try {
         zzNn[AdRequest.ErrorCode.NO_FILL.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
         ;
      }

      zzNm = new int[AdRequest.Gender.values().length];

      try {
         zzNm[AdRequest.Gender.FEMALE.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         zzNm[AdRequest.Gender.MALE.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
         ;
      }

      try {
         zzNm[AdRequest.Gender.UNKNOWN.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
         ;
      }
   }
}
