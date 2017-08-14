package android.support.v7.app;

class TwilightCalculator {
   private static TwilightCalculator sInstance;
   public static final int DAY = 0;
   public static final int NIGHT = 1;
   private static final float DEGREES_TO_RADIANS = 0.017453292F;
   private static final float J0 = 9.0E-4F;
   private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976F;
   private static final float C1 = 0.0334196F;
   private static final float C2 = 3.49066E-4F;
   private static final float C3 = 5.236E-6F;
   private static final float OBLIQUITY = 0.4092797F;
   private static final long UTC_2000 = 946728000000L;
   public long sunset;
   public long sunrise;
   public int state;

   static TwilightCalculator getInstance() {
      if (sInstance == null) {
         sInstance = new TwilightCalculator();
      }

      return sInstance;
   }

   public void calculateTwilight(long time, double latitude, double longitude) {
      float daysSince2000 = (float)(time - 946728000000L) / 8.64E7F;
      float meanAnomaly = 6.24006F + daysSince2000 * 0.01720197F;
      double trueAnomaly = (double)meanAnomaly + 0.03341960161924362D * Math.sin((double)meanAnomaly) + 3.4906598739326E-4D * Math.sin((double)(2.0F * meanAnomaly)) + 5.236000106378924E-6D * Math.sin((double)(3.0F * meanAnomaly));
      double solarLng = trueAnomaly + 1.796593063D + 3.141592653589793D;
      double arcLongitude = -longitude / 360.0D;
      float n = (float)Math.round((double)(daysSince2000 - 9.0E-4F) - arcLongitude);
      double solarTransitJ2000 = (double)(n + 9.0E-4F) + arcLongitude + 0.0053D * Math.sin((double)meanAnomaly) + -0.0069D * Math.sin(2.0D * solarLng);
      double solarDec = Math.asin(Math.sin(solarLng) * Math.sin(0.4092797040939331D));
      double latRad = latitude * 0.01745329238474369D;
      double cosHourAngle = (Math.sin(-0.10471975803375244D) - Math.sin(latRad) * Math.sin(solarDec)) / (Math.cos(latRad) * Math.cos(solarDec));
      if (cosHourAngle >= 1.0D) {
         this.state = 1;
         this.sunset = -1L;
         this.sunrise = -1L;
      } else if (cosHourAngle <= -1.0D) {
         this.state = 0;
         this.sunset = -1L;
         this.sunrise = -1L;
      } else {
         float hourAngle = (float)(Math.acos(cosHourAngle) / 6.283185307179586D);
         this.sunset = Math.round((solarTransitJ2000 + (double)hourAngle) * 8.64E7D) + 946728000000L;
         this.sunrise = Math.round((solarTransitJ2000 - (double)hourAngle) * 8.64E7D) + 946728000000L;
         if (this.sunrise < time && this.sunset > time) {
            this.state = 0;
         } else {
            this.state = 1;
         }

      }
   }
}
