package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class Barcode extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   public static final int ALL_FORMATS = 0;
   public static final int CODE_128 = 1;
   public static final int CODE_39 = 2;
   public static final int CODE_93 = 4;
   public static final int CODABAR = 8;
   public static final int DATA_MATRIX = 16;
   public static final int EAN_13 = 32;
   public static final int EAN_8 = 64;
   public static final int ITF = 128;
   public static final int QR_CODE = 256;
   public static final int UPC_A = 512;
   public static final int UPC_E = 1024;
   public static final int PDF417 = 2048;
   public static final int AZTEC = 4096;
   public static final int CONTACT_INFO = 1;
   public static final int EMAIL = 2;
   public static final int ISBN = 3;
   public static final int PHONE = 4;
   public static final int PRODUCT = 5;
   public static final int SMS = 6;
   public static final int TEXT = 7;
   public static final int URL = 8;
   public static final int WIFI = 9;
   public static final int GEO = 10;
   public static final int CALENDAR_EVENT = 11;
   public static final int DRIVER_LICENSE = 12;
   public int format;
   public String rawValue;
   public String displayValue;
   public int valueFormat;
   public Point[] cornerPoints;
   public Barcode.Email email;
   public Barcode.Phone phone;
   public Barcode.Sms sms;
   public Barcode.WiFi wifi;
   public Barcode.UrlBookmark url;
   public Barcode.GeoPoint geoPoint;
   public Barcode.CalendarEvent calendarEvent;
   public Barcode.ContactInfo contactInfo;
   public Barcode.DriverLicense driverLicense;

   public Barcode() {
   }

   public Barcode(int var1, String var2, String var3, int var4, Point[] var5, Barcode.Email var6, Barcode.Phone var7, Barcode.Sms var8, Barcode.WiFi var9, Barcode.UrlBookmark var10, Barcode.GeoPoint var11, Barcode.CalendarEvent var12, Barcode.ContactInfo var13, Barcode.DriverLicense var14) {
      this.format = var1;
      this.rawValue = var2;
      this.displayValue = var3;
      this.valueFormat = var4;
      this.cornerPoints = var5;
      this.email = var6;
      this.phone = var7;
      this.sms = var8;
      this.wifi = var9;
      this.url = var10;
      this.geoPoint = var11;
      this.calendarEvent = var12;
      this.contactInfo = var13;
      this.driverLicense = var14;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.format);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.rawValue, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.displayValue, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.valueFormat);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.cornerPoints, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.email, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.phone, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.sms, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.wifi, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.url, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.geoPoint, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.calendarEvent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.contactInfo, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.driverLicense, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public Rect getBoundingBox() {
      int var1 = Integer.MAX_VALUE;
      int var2 = Integer.MIN_VALUE;
      int var3 = Integer.MAX_VALUE;
      int var4 = Integer.MIN_VALUE;

      for(int var5 = 0; var5 < this.cornerPoints.length; ++var5) {
         Point var6 = this.cornerPoints[var5];
         var1 = Math.min(var1, var6.x);
         var2 = Math.max(var2, var6.x);
         var3 = Math.min(var3, var6.y);
         var4 = Math.max(var4, var6.y);
      }

      return new Rect(var1, var3, var2, var4);
   }

   public static class DriverLicense extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzg();
      public String documentType;
      public String firstName;
      public String middleName;
      public String lastName;
      public String gender;
      public String addressStreet;
      public String addressCity;
      public String addressState;
      public String addressZip;
      public String licenseNumber;
      public String issueDate;
      public String expiryDate;
      public String birthDate;
      public String issuingCountry;

      public DriverLicense() {
      }

      public DriverLicense(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14) {
         this.documentType = var1;
         this.firstName = var2;
         this.middleName = var3;
         this.lastName = var4;
         this.gender = var5;
         this.addressStreet = var6;
         this.addressCity = var7;
         this.addressState = var8;
         this.addressZip = var9;
         this.licenseNumber = var10;
         this.issueDate = var11;
         this.expiryDate = var12;
         this.birthDate = var13;
         this.issuingCountry = var14;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.documentType, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.firstName, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.middleName, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.lastName, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.gender, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.addressStreet, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.addressCity, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.addressState, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.addressZip, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.licenseNumber, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.issueDate, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.expiryDate, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.birthDate, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.issuingCountry, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class ContactInfo extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzf();
      public Barcode.PersonName name;
      public String organization;
      public String title;
      public Barcode.Phone[] phones;
      public Barcode.Email[] emails;
      public String[] urls;
      public Barcode.Address[] addresses;

      public ContactInfo() {
      }

      public ContactInfo(Barcode.PersonName var1, String var2, String var3, Barcode.Phone[] var4, Barcode.Email[] var5, String[] var6, Barcode.Address[] var7) {
         this.name = var1;
         this.organization = var2;
         this.title = var3;
         this.phones = var4;
         this.emails = var5;
         this.urls = var6;
         this.addresses = var7;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.name, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.organization, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.title, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.phones, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.emails, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.urls, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.addresses, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class Address extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zza();
      public static final int UNKNOWN = 0;
      public static final int WORK = 1;
      public static final int HOME = 2;
      public int type;
      public String[] addressLines;

      public Address() {
      }

      public Address(int var1, String[] var2) {
         this.type = var1;
         this.addressLines = var2;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.type);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.addressLines, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class PersonName extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzj();
      public String formattedName;
      public String pronunciation;
      public String prefix;
      public String first;
      public String middle;
      public String last;
      public String suffix;

      public PersonName() {
      }

      public PersonName(String var1, String var2, String var3, String var4, String var5, String var6, String var7) {
         this.formattedName = var1;
         this.pronunciation = var2;
         this.prefix = var3;
         this.first = var4;
         this.middle = var5;
         this.last = var6;
         this.suffix = var7;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.formattedName, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.pronunciation, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.prefix, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.first, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.middle, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.last, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.suffix, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class CalendarEvent extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zze();
      public String summary;
      public String description;
      public String location;
      public String organizer;
      public String status;
      public Barcode.CalendarDateTime start;
      public Barcode.CalendarDateTime end;

      public CalendarEvent() {
      }

      public CalendarEvent(String var1, String var2, String var3, String var4, String var5, Barcode.CalendarDateTime var6, Barcode.CalendarDateTime var7) {
         this.summary = var1;
         this.description = var2;
         this.location = var3;
         this.organizer = var4;
         this.status = var5;
         this.start = var6;
         this.end = var7;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.summary, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.description, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.location, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.organizer, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.status, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.start, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.end, var2, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class CalendarDateTime extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzd();
      public int year;
      public int month;
      public int day;
      public int hours;
      public int minutes;
      public int seconds;
      public boolean isUtc;
      public String rawValue;

      public CalendarDateTime() {
      }

      public CalendarDateTime(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7, String var8) {
         this.year = var1;
         this.month = var2;
         this.day = var3;
         this.hours = var4;
         this.minutes = var5;
         this.seconds = var6;
         this.isUtc = var7;
         this.rawValue = var8;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.year);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.month);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.day);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.hours);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.minutes);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.seconds);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.isUtc);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.rawValue, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class GeoPoint extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzi();
      public double lat;
      public double lng;

      public GeoPoint() {
      }

      public GeoPoint(double var1, double var3) {
         this.lat = var1;
         this.lng = var3;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.lat);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.lng);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class UrlBookmark extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzm();
      public String title;
      public String url;

      public UrlBookmark() {
      }

      public UrlBookmark(String var1, String var2) {
         this.title = var1;
         this.url = var2;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.title, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.url, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class WiFi extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzn();
      public static final int OPEN = 1;
      public static final int WPA = 2;
      public static final int WEP = 3;
      public String ssid;
      public String password;
      public int encryptionType;

      public WiFi() {
      }

      public WiFi(String var1, String var2, int var3) {
         this.ssid = var1;
         this.password = var2;
         this.encryptionType = var3;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.ssid, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.password, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.encryptionType);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class Sms extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzl();
      public String message;
      public String phoneNumber;

      public Sms() {
      }

      public Sms(String var1, String var2) {
         this.message = var1;
         this.phoneNumber = var2;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.message, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.phoneNumber, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class Phone extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzk();
      public static final int UNKNOWN = 0;
      public static final int WORK = 1;
      public static final int HOME = 2;
      public static final int FAX = 3;
      public static final int MOBILE = 4;
      public int type;
      public String number;

      public Phone() {
      }

      public Phone(int var1, String var2) {
         this.type = var1;
         this.number = var2;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.type);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.number, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }

   public static class Email extends com.google.android.gms.common.internal.safeparcel.zza {
      public static final Creator CREATOR = new zzh();
      public static final int UNKNOWN = 0;
      public static final int WORK = 1;
      public static final int HOME = 2;
      public int type;
      public String address;
      public String subject;
      public String body;

      public Email() {
      }

      public Email(int var1, String var2, String var3, String var4) {
         this.type = var1;
         this.address = var2;
         this.subject = var3;
         this.body = var4;
      }

      public void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.type);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.address, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.subject, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.body, false);
         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }
   }
}
