package com.google.android.gms.awareness.state;

public interface Weather {
   int CONDITION_UNKNOWN = 0;
   int CONDITION_CLEAR = 1;
   int CONDITION_CLOUDY = 2;
   int CONDITION_FOGGY = 3;
   int CONDITION_HAZY = 4;
   int CONDITION_ICY = 5;
   int CONDITION_RAINY = 6;
   int CONDITION_SNOWY = 7;
   int CONDITION_STORMY = 8;
   int CONDITION_WINDY = 9;
   int FAHRENHEIT = 1;
   int CELSIUS = 2;

   float getTemperature(int var1);

   float getFeelsLikeTemperature(int var1);

   float getDewPoint(int var1);

   int getHumidity();

   int[] getConditions();
}
