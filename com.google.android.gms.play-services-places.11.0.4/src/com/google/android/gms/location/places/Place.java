package com.google.android.gms.location.places;

import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public interface Place extends Freezable {
   int TYPE_OTHER = 0;
   int TYPE_ACCOUNTING = 1;
   int TYPE_AIRPORT = 2;
   int TYPE_AMUSEMENT_PARK = 3;
   int TYPE_AQUARIUM = 4;
   int TYPE_ART_GALLERY = 5;
   int TYPE_ATM = 6;
   int TYPE_BAKERY = 7;
   int TYPE_BANK = 8;
   int TYPE_BAR = 9;
   int TYPE_BEAUTY_SALON = 10;
   int TYPE_BICYCLE_STORE = 11;
   int TYPE_BOOK_STORE = 12;
   int TYPE_BOWLING_ALLEY = 13;
   int TYPE_BUS_STATION = 14;
   int TYPE_CAFE = 15;
   int TYPE_CAMPGROUND = 16;
   int TYPE_CAR_DEALER = 17;
   int TYPE_CAR_RENTAL = 18;
   int TYPE_CAR_REPAIR = 19;
   int TYPE_CAR_WASH = 20;
   int TYPE_CASINO = 21;
   int TYPE_CEMETERY = 22;
   int TYPE_CHURCH = 23;
   int TYPE_CITY_HALL = 24;
   int TYPE_CLOTHING_STORE = 25;
   int TYPE_CONVENIENCE_STORE = 26;
   int TYPE_COURTHOUSE = 27;
   int TYPE_DENTIST = 28;
   int TYPE_DEPARTMENT_STORE = 29;
   int TYPE_DOCTOR = 30;
   int TYPE_ELECTRICIAN = 31;
   int TYPE_ELECTRONICS_STORE = 32;
   int TYPE_EMBASSY = 33;
   int TYPE_ESTABLISHMENT = 34;
   int TYPE_FINANCE = 35;
   int TYPE_FIRE_STATION = 36;
   int TYPE_FLORIST = 37;
   int TYPE_FOOD = 38;
   int TYPE_FUNERAL_HOME = 39;
   int TYPE_FURNITURE_STORE = 40;
   int TYPE_GAS_STATION = 41;
   int TYPE_GENERAL_CONTRACTOR = 42;
   int TYPE_GROCERY_OR_SUPERMARKET = 43;
   int TYPE_GYM = 44;
   int TYPE_HAIR_CARE = 45;
   int TYPE_HARDWARE_STORE = 46;
   int TYPE_HEALTH = 47;
   int TYPE_HINDU_TEMPLE = 48;
   int TYPE_HOME_GOODS_STORE = 49;
   int TYPE_HOSPITAL = 50;
   int TYPE_INSURANCE_AGENCY = 51;
   int TYPE_JEWELRY_STORE = 52;
   int TYPE_LAUNDRY = 53;
   int TYPE_LAWYER = 54;
   int TYPE_LIBRARY = 55;
   int TYPE_LIQUOR_STORE = 56;
   int TYPE_LOCAL_GOVERNMENT_OFFICE = 57;
   int TYPE_LOCKSMITH = 58;
   int TYPE_LODGING = 59;
   int TYPE_MEAL_DELIVERY = 60;
   int TYPE_MEAL_TAKEAWAY = 61;
   int TYPE_MOSQUE = 62;
   int TYPE_MOVIE_RENTAL = 63;
   int TYPE_MOVIE_THEATER = 64;
   int TYPE_MOVING_COMPANY = 65;
   int TYPE_MUSEUM = 66;
   int TYPE_NIGHT_CLUB = 67;
   int TYPE_PAINTER = 68;
   int TYPE_PARK = 69;
   int TYPE_PARKING = 70;
   int TYPE_PET_STORE = 71;
   int TYPE_PHARMACY = 72;
   int TYPE_PHYSIOTHERAPIST = 73;
   int TYPE_PLACE_OF_WORSHIP = 74;
   int TYPE_PLUMBER = 75;
   int TYPE_POLICE = 76;
   int TYPE_POST_OFFICE = 77;
   int TYPE_REAL_ESTATE_AGENCY = 78;
   int TYPE_RESTAURANT = 79;
   int TYPE_ROOFING_CONTRACTOR = 80;
   int TYPE_RV_PARK = 81;
   int TYPE_SCHOOL = 82;
   int TYPE_SHOE_STORE = 83;
   int TYPE_SHOPPING_MALL = 84;
   int TYPE_SPA = 85;
   int TYPE_STADIUM = 86;
   int TYPE_STORAGE = 87;
   int TYPE_STORE = 88;
   int TYPE_SUBWAY_STATION = 89;
   int TYPE_SYNAGOGUE = 90;
   int TYPE_TAXI_STAND = 91;
   int TYPE_TRAIN_STATION = 92;
   int TYPE_TRAVEL_AGENCY = 93;
   int TYPE_UNIVERSITY = 94;
   int TYPE_VETERINARY_CARE = 95;
   int TYPE_ZOO = 96;
   int TYPE_ADMINISTRATIVE_AREA_LEVEL_1 = 1001;
   int TYPE_ADMINISTRATIVE_AREA_LEVEL_2 = 1002;
   int TYPE_ADMINISTRATIVE_AREA_LEVEL_3 = 1003;
   int TYPE_COLLOQUIAL_AREA = 1004;
   int TYPE_COUNTRY = 1005;
   int TYPE_FLOOR = 1006;
   int TYPE_GEOCODE = 1007;
   int TYPE_INTERSECTION = 1008;
   int TYPE_LOCALITY = 1009;
   int TYPE_NATURAL_FEATURE = 1010;
   int TYPE_NEIGHBORHOOD = 1011;
   int TYPE_POLITICAL = 1012;
   int TYPE_POINT_OF_INTEREST = 1013;
   int TYPE_POST_BOX = 1014;
   int TYPE_POSTAL_CODE = 1015;
   int TYPE_POSTAL_CODE_PREFIX = 1016;
   int TYPE_POSTAL_TOWN = 1017;
   int TYPE_PREMISE = 1018;
   int TYPE_ROOM = 1019;
   int TYPE_ROUTE = 1020;
   int TYPE_STREET_ADDRESS = 1021;
   int TYPE_SUBLOCALITY = 1022;
   int TYPE_SUBLOCALITY_LEVEL_1 = 1023;
   int TYPE_SUBLOCALITY_LEVEL_2 = 1024;
   int TYPE_SUBLOCALITY_LEVEL_3 = 1025;
   int TYPE_SUBLOCALITY_LEVEL_4 = 1026;
   int TYPE_SUBLOCALITY_LEVEL_5 = 1027;
   int TYPE_SUBPREMISE = 1028;
   int TYPE_SYNTHETIC_GEOCODE = 1029;
   int TYPE_TRANSIT_STATION = 1030;

   String getId();

   List getPlaceTypes();

   CharSequence getAddress();

   Locale getLocale();

   CharSequence getName();

   LatLng getLatLng();

   LatLngBounds getViewport();

   Uri getWebsiteUri();

   CharSequence getPhoneNumber();

   float getRating();

   int getPriceLevel();

   CharSequence getAttributions();
}
