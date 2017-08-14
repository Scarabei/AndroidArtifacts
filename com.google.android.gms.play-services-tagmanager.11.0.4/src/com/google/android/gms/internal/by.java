package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzf;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class by extends zzcxq {
   private static final String ID;
   private static final List zzbHw;
   private static final Pattern zzbHx;
   private static final Pattern zzbHy;
   private static final Set zzbKl;
   private static final Map zzbKm;
   private static final Map zzbKn;
   private final zzcxj zzbKo;
   private final zzcvy zzbIP;
   private Map zzbKp;

   public by(Context var1, zzcvy var2) {
      this(new zzcxj(var1), var2);
   }

   private by(zzcxj var1, zzcvy var2) {
      this.zzbIP = var2;
      this.zzbKo = var1;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);

      try {
         this.zzbKp = ed.zzC(this.zzbIP.zzCy().zzCl());
         dp var3 = var2[0];
         Object var4 = var2.length > 1 ? var2[1] : new ds(true);
         Object var5 = var2.length > 2 ? var2[2] : new ds(false);
         Object var6 = var2.length > 3 ? var2[3] : dv.zzbLu;
         Object var7 = var2.length > 4 ? var2[4] : dv.zzbLu;
         Object var8 = var2.length > 5 ? var2[5] : new ds(false);
         Object var9 = var2.length > 6 ? var2[6] : new ds(false);
         Object var10 = var2.length > 7 ? var2[7] : dv.zzbLu;
         Object var11 = var2.length > 8 ? var2[8] : new ds(false);
         zzbo.zzaf(var3 instanceof dz);
         zzbo.zzaf(var6 == dv.zzbLu || var6 instanceof dz);
         zzbo.zzaf(var7 == dv.zzbLu || var7 instanceof dz);
         zzbo.zzaf(var10 == dv.zzbLu || var10 instanceof dz);
         Tracker var12;
         (var12 = this.zzbKo.zzfv("_GTM_DEFAULT_TRACKER_")).enableAdvertisingIdCollection(zzcxp.zza((dp)var11));
         if (zzcxp.zza((dp)var8)) {
            ScreenViewBuilder var19 = new ScreenViewBuilder();
            Map var20 = zzi(var3);
            var19.setAll(var20);
            Object var22;
            if (zzcxp.zza((dp)var9)) {
               var22 = this.zzbKp.get("ecommerce");
            } else {
               var22 = ed.zzj(ed.zzk((dp)var10));
            }

            if (var22 instanceof Map) {
               Map var21 = (Map)var22;
               String var23;
               if ((var23 = (String)var20.get("&cu")) == null) {
                  var23 = (String)var21.get("currencyCode");
               }

               if (var23 != null) {
                  var19.set("&cu", var23);
               }

               String var10000;
               String var10001;
               String var10002;
               Object var24;
               if ((var24 = var21.get("impressions")) instanceof List) {
                  Iterator var26 = ((List)var24).iterator();

                  while(var26.hasNext()) {
                     Map var27 = (Map)var26.next();

                     try {
                        Product var28 = zzx(var27);
                        var19.addImpression(var28, (String)var27.get("list"));
                     } catch (RuntimeException var47) {
                        var10001 = String.valueOf(var47.getMessage());
                        if (var10001.length() != 0) {
                           var10000 = "Failed to extract a product from event data. ".concat(var10001);
                        } else {
                           var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("Failed to extract a product from event data. ");
                        }

                        zzcvk.e(var10000);
                     }
                  }
               }

               List var25 = null;
               if (var21.containsKey("promoClick")) {
                  var25 = (List)((Map)var21.get("promoClick")).get("promotions");
               } else if (var21.containsKey("promoView")) {
                  var25 = (List)((Map)var21.get("promoView")).get("promotions");
               }

               boolean var49 = true;
               Iterator var50;
               if (var25 != null) {
                  var50 = var25.iterator();

                  while(var50.hasNext()) {
                     Map var51 = (Map)var50.next();

                     try {
                        Promotion var37 = new Promotion();
                        String var36;
                        if ((var36 = (String)var51.get("id")) != null) {
                           var37.setId(String.valueOf(var36));
                        }

                        if ((var36 = (String)var51.get("name")) != null) {
                           var37.setName(String.valueOf(var36));
                        }

                        if ((var36 = (String)var51.get("creative")) != null) {
                           var37.setCreative(String.valueOf(var36));
                        }

                        if ((var36 = (String)var51.get("position")) != null) {
                           var37.setPosition(String.valueOf(var36));
                        }

                        var19.addPromotion(var37);
                     } catch (RuntimeException var46) {
                        var10001 = String.valueOf(var46.getMessage());
                        if (var10001.length() != 0) {
                           var10000 = "Failed to extract a promotion from event data. ".concat(var10001);
                        } else {
                           var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("Failed to extract a promotion from event data. ");
                        }

                        zzcvk.e(var10000);
                     }
                  }

                  if (var21.containsKey("promoClick")) {
                     var19.set("&promoa", "click");
                     var49 = false;
                  } else {
                     var19.set("&promoa", "view");
                  }
               }

               if (var49) {
                  var50 = zzbHw.iterator();

                  while(var50.hasNext()) {
                     String var52 = (String)var50.next();
                     if (var21.containsKey(var52)) {
                        Map var29;
                        List var30;
                        Map var32;
                        if ((var30 = (List)(var29 = (Map)var21.get(var52)).get("products")) != null) {
                           Iterator var31 = var30.iterator();

                           while(var31.hasNext()) {
                              var32 = (Map)var31.next();

                              try {
                                 Product var33 = zzx(var32);
                                 var19.addProduct(var33);
                              } catch (RuntimeException var45) {
                                 var10001 = String.valueOf(var45.getMessage());
                                 if (var10001.length() != 0) {
                                    var10000 = "Failed to extract a product from event data. ".concat(var10001);
                                 } else {
                                    var10002 = new String;
                                    var10000 = var10002;
                                    var10002.<init>("Failed to extract a product from event data. ");
                                 }

                                 zzcvk.e(var10000);
                              }
                           }
                        }

                        try {
                           ProductAction var53;
                           if (var29.containsKey("actionField")) {
                              var32 = (Map)var29.get("actionField");
                              ProductAction var38 = new ProductAction(var52);
                              Object var54;
                              if ((var54 = var32.get("id")) != null) {
                                 var38.setTransactionId(String.valueOf(var54));
                              }

                              if ((var54 = var32.get("affiliation")) != null) {
                                 var38.setTransactionAffiliation(String.valueOf(var54));
                              }

                              if ((var54 = var32.get("coupon")) != null) {
                                 var38.setTransactionCouponCode(String.valueOf(var54));
                              }

                              if ((var54 = var32.get("list")) != null) {
                                 var38.setProductActionList(String.valueOf(var54));
                              }

                              if ((var54 = var32.get("option")) != null) {
                                 var38.setCheckoutOptions(String.valueOf(var54));
                              }

                              if ((var54 = var32.get("revenue")) != null) {
                                 var38.setTransactionRevenue(zzM(var54).doubleValue());
                              }

                              if ((var54 = var32.get("tax")) != null) {
                                 var38.setTransactionTax(zzM(var54).doubleValue());
                              }

                              if ((var54 = var32.get("shipping")) != null) {
                                 var38.setTransactionShipping(zzM(var54).doubleValue());
                              }

                              if ((var54 = var32.get("step")) != null) {
                                 var38.setCheckoutStep(zzN(var54).intValue());
                              }

                              var53 = var38;
                           } else {
                              var53 = new ProductAction(var52);
                           }

                           var19.setProductAction(var53);
                        } catch (RuntimeException var44) {
                           var10001 = String.valueOf(var44.getMessage());
                           if (var10001.length() != 0) {
                              var10000 = "Failed to extract a product action from event data. ".concat(var10001);
                           } else {
                              var10002 = new String;
                              var10000 = var10002;
                              var10002.<init>("Failed to extract a product action from event data. ");
                           }

                           zzcvk.e(var10000);
                        }
                        break;
                     }
                  }
               }
            }

            var12.send(var19.build());
         } else if (zzcxp.zza((dp)var4)) {
            Map var17 = zzi(var3);
            var12.send(var17);
         } else if (zzcxp.zza((dp)var5)) {
            this.zza(var12, var3, (dp)var6, (dp)var7);
         } else {
            zzcvk.zzaT("Ignoring unknown tag.");
         }
      } finally {
         this.zzbKp = null;
      }

      return dv.zzbLu;
   }

   private final List zzfN(String var1) {
      Object var2;
      if ((var2 = this.zzbKp.get(var1)) == null) {
         return null;
      } else if (!(var2 instanceof List)) {
         throw new IllegalArgumentException("transactionProducts should be of type List.");
      } else {
         List var3;
         Iterator var4 = (var3 = (List)var2).iterator();

         do {
            if (!var4.hasNext()) {
               return var3;
            }
         } while(var4.next() instanceof Map);

         throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
      }
   }

   private final void zza(Tracker var1, dp var2, dp var3, dp var4) {
      String var5;
      if ((var5 = (String)this.zzbKp.get("transactionId")) == null) {
         zzcvk.e("Cannot find transactionId in data layer.");
      } else {
         LinkedList var6 = new LinkedList();

         try {
            Map var7;
            (var7 = zzi(var2)).put("&t", "transaction");
            Iterator var8 = (var3 == dv.zzbLu ? zzbKm : zzh(var3)).entrySet().iterator();

            while(var8.hasNext()) {
               Entry var9 = (Entry)var8.next();
               String var10;
               if ((var10 = (String)this.zzbKp.get(var9.getKey())) != null) {
                  var7.put((String)var9.getValue(), var10);
               }
            }

            var6.add(var7);
            List var17;
            Iterator var18;
            Map var19;
            if ((var17 = this.zzfN("transactionProducts")) != null) {
               var18 = var17.iterator();

               while(var18.hasNext()) {
                  if ((var19 = (Map)var18.next()).get("name") == null) {
                     zzcvk.e("Unable to send transaction item hit due to missing 'name' field.");
                     return;
                  }

                  Map var11;
                  (var11 = zzi(var2)).put("&t", "item");
                  var11.put("&ti", var5);
                  Iterator var12 = (var4 == dv.zzbLu ? zzbKn : zzh(var4)).entrySet().iterator();

                  while(var12.hasNext()) {
                     Entry var13 = (Entry)var12.next();
                     Object var14;
                     if ((var14 = var19.get(var13.getKey())) != null) {
                        var11.put((String)var13.getValue(), var14.toString());
                     }
                  }

                  var6.add(var11);
               }
            }

            var18 = var6.iterator();

            while(var18.hasNext()) {
               var19 = (Map)var18.next();
               var1.send(var19);
            }

         } catch (IllegalArgumentException var16) {
            zzcvk.zzb("Unable to send transaction", var16);
         }
      }
   }

   private static Product zzx(Map var0) {
      Product var2 = new Product();
      Object var1;
      if ((var1 = var0.get("id")) != null) {
         var2.setId(String.valueOf(var1));
      }

      if ((var1 = var0.get("name")) != null) {
         var2.setName(String.valueOf(var1));
      }

      if ((var1 = var0.get("brand")) != null) {
         var2.setBrand(String.valueOf(var1));
      }

      if ((var1 = var0.get("category")) != null) {
         var2.setCategory(String.valueOf(var1));
      }

      if ((var1 = var0.get("variant")) != null) {
         var2.setVariant(String.valueOf(var1));
      }

      if ((var1 = var0.get("coupon")) != null) {
         var2.setCouponCode(String.valueOf(var1));
      }

      if ((var1 = var0.get("position")) != null) {
         var2.setPosition(zzN(var1).intValue());
      }

      if ((var1 = var0.get("price")) != null) {
         var2.setPrice(zzM(var1).doubleValue());
      }

      if ((var1 = var0.get("quantity")) != null) {
         var2.setQuantity(zzN(var1).intValue());
      }

      Iterator var3 = var0.keySet().iterator();

      while(true) {
         String var4;
         int var10;
         label84:
         while(true) {
            while(var3.hasNext()) {
               var4 = (String)var3.next();
               String var10000;
               String var10001;
               String var10002;
               Matcher var5;
               if ((var5 = zzbHx.matcher(var4)).matches()) {
                  try {
                     var10 = Integer.parseInt(var5.group(1));
                     break label84;
                  } catch (NumberFormatException var9) {
                     var10001 = String.valueOf(var4);
                     if (var10001.length() != 0) {
                        var10000 = "illegal number in custom dimension value: ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("illegal number in custom dimension value: ");
                     }

                     zzcvk.zzaT(var10000);
                  }
               } else {
                  Matcher var6;
                  if ((var6 = zzbHy.matcher(var4)).matches()) {
                     int var7;
                     try {
                        var7 = Integer.parseInt(var6.group(1));
                     } catch (NumberFormatException var8) {
                        var10001 = String.valueOf(var4);
                        if (var10001.length() != 0) {
                           var10000 = "illegal number in custom metric value: ".concat(var10001);
                        } else {
                           var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("illegal number in custom metric value: ");
                        }

                        zzcvk.zzaT(var10000);
                        continue;
                     }

                     var2.setCustomMetric(var7, zzN(var0.get(var4)).intValue());
                  }
               }
            }

            return var2;
         }

         var2.setCustomDimension(var10, String.valueOf(var0.get(var4)));
      }
   }

   private static Map zzh(dp var0) {
      zzbo.zzu(var0);
      zzbo.zzaf(var0 instanceof dz);
      LinkedHashMap var1 = new LinkedHashMap();
      Object var2;
      zzbo.zzae((var2 = ed.zzj(ed.zzk(var0))) instanceof Map);
      Iterator var3 = ((Map)var2).entrySet().iterator();

      while(var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         var1.put(var4.getKey().toString(), var4.getValue().toString());
      }

      return var1;
   }

   private static Map zzi(dp var0) {
      Map var1;
      String var2;
      if ((var2 = (String)(var1 = zzh(var0)).get("&aip")) != null && zzbKl.contains(var2.toLowerCase())) {
         var1.remove("&aip");
      }

      return var1;
   }

   private static Double zzM(Object var0) {
      RuntimeException var10000;
      String var10002;
      String var10003;
      String var10004;
      if (var0 instanceof String) {
         try {
            return Double.valueOf((String)var0);
         } catch (NumberFormatException var2) {
            var10000 = new RuntimeException;
            var10003 = String.valueOf(var2.getMessage());
            if (var10003.length() != 0) {
               var10002 = "Cannot convert the object to Double: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Cannot convert the object to Double: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      } else if (var0 instanceof Integer) {
         return ((Integer)var0).doubleValue();
      } else if (var0 instanceof Double) {
         return (Double)var0;
      } else {
         var10000 = new RuntimeException;
         var10003 = String.valueOf(var0.toString());
         if (var10003.length() != 0) {
            var10002 = "Cannot convert the object to Double: ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Cannot convert the object to Double: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   private static Integer zzN(Object var0) {
      RuntimeException var10000;
      String var10002;
      String var10003;
      String var10004;
      if (var0 instanceof String) {
         try {
            return Integer.valueOf((String)var0);
         } catch (NumberFormatException var2) {
            var10000 = new RuntimeException;
            var10003 = String.valueOf(var2.getMessage());
            if (var10003.length() != 0) {
               var10002 = "Cannot convert the object to Integer: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Cannot convert the object to Integer: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      } else if (var0 instanceof Double) {
         return ((Double)var0).intValue();
      } else if (var0 instanceof Integer) {
         return (Integer)var0;
      } else {
         var10000 = new RuntimeException;
         var10003 = String.valueOf(var0.toString());
         if (var10003.length() != 0) {
            var10002 = "Cannot convert the object to Integer: ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Cannot convert the object to Integer: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   static {
      ID = zzbf.zzeV.toString();
      zzbHw = Arrays.asList("detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund");
      zzbHx = Pattern.compile("dimension(\\d+)");
      zzbHy = Pattern.compile("metric(\\d+)");
      zzbKl = zzf.zza("", "0", "false");
      zzbKm = zzf.zza("transactionId", "&ti", "transactionAffiliation", "&ta", "transactionTax", "&tt", "transactionShipping", "&ts", "transactionTotal", "&tr", "transactionCurrency", "&cu");
      zzbKn = zzf.zza("name", "&in", "sku", "&ic", "category", "&iv", "price", "&ip", "quantity", "&iq", "currency", "&cu");
   }
}
