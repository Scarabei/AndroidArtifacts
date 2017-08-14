package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzgl extends zzgi {
   private static final String ID;
   private static final String zzbHn;
   private static final String zzbHo;
   private static final String zzbHp;
   private static final String zzbHq;
   private static final String zzbHr;
   private static final String zzbHs;
   private static final String zzbHt;
   private static final String zzbHu;
   private static final String zzbHv;
   private static final List zzbHw;
   private static final Pattern zzbHx;
   private static final Pattern zzbHy;
   private static Map zzbHz;
   private static Map zzbHA;
   private final Set zzbHB;
   private final zzgg zzbHC;
   private final DataLayer zzbDx;

   public zzgl(Context var1, DataLayer var2) {
      this(var1, var2, new zzgg(var1));
   }

   private zzgl(Context var1, DataLayer var2, zzgg var3) {
      super(ID);
      this.zzbDx = var2;
      this.zzbHC = var3;
      this.zzbHB = new HashSet();
      this.zzbHB.add("");
      this.zzbHB.add("0");
      this.zzbHB.add("false");
   }

   private static boolean zzg(Map var0, String var1) {
      com.google.android.gms.internal.zzbr var2;
      return (var2 = (com.google.android.gms.internal.zzbr)var0.get(var1)) == null ? false : zzgk.zzf(var2).booleanValue();
   }

   public final void zzq(Map var1) {
      Tracker var2;
      (var2 = this.zzbHC.zzfv("_GTM_DEFAULT_TRACKER_")).enableAdvertisingIdCollection(zzg(var1, "collect_adid"));
      if (zzg(var1, zzbHp)) {
         ScreenViewBuilder var6 = new ScreenViewBuilder();
         Map var7 = this.zzi((com.google.android.gms.internal.zzbr)var1.get(zzbHs));
         var6.setAll(var7);
         Map var8 = null;
         Object var9;
         if (zzg(var1, zzbHq)) {
            if ((var9 = this.zzbDx.get("ecommerce")) instanceof Map) {
               var8 = (Map)var9;
            }
         } else if ((var9 = zzgk.zzg((com.google.android.gms.internal.zzbr)var1.get(zzbHr))) instanceof Map) {
            var8 = (Map)var9;
         }

         if (var8 != null) {
            String var29;
            if ((var29 = (String)var7.get("&cu")) == null) {
               var29 = (String)var8.get("currencyCode");
            }

            if (var29 != null) {
               var6.set("&cu", var29);
            }

            Object var10;
            String var10000;
            String var10001;
            String var10002;
            if ((var10 = var8.get("impressions")) instanceof List) {
               Iterator var12 = ((List)var10).iterator();

               while(var12.hasNext()) {
                  Map var13 = (Map)var12.next();

                  try {
                     Product var14 = zzt(var13);
                     var6.addImpression(var14, (String)var13.get("list"));
                  } catch (RuntimeException var28) {
                     var10001 = String.valueOf(var28.getMessage());
                     if (var10001.length() != 0) {
                        var10000 = "Failed to extract a product from DataLayer. ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Failed to extract a product from DataLayer. ");
                     }

                     zzdj.e(var10000);
                  }
               }
            }

            List var11 = null;
            if (var8.containsKey("promoClick")) {
               var11 = (List)((Map)var8.get("promoClick")).get("promotions");
            } else if (var8.containsKey("promoView")) {
               var11 = (List)((Map)var8.get("promoView")).get("promotions");
            }

            boolean var30 = true;
            Iterator var31;
            if (var11 != null) {
               var31 = var11.iterator();

               while(var31.hasNext()) {
                  Map var32 = (Map)var31.next();

                  try {
                     Promotion var23 = new Promotion();
                     String var22;
                     if ((var22 = (String)var32.get("id")) != null) {
                        var23.setId(String.valueOf(var22));
                     }

                     if ((var22 = (String)var32.get("name")) != null) {
                        var23.setName(String.valueOf(var22));
                     }

                     if ((var22 = (String)var32.get("creative")) != null) {
                        var23.setCreative(String.valueOf(var22));
                     }

                     if ((var22 = (String)var32.get("position")) != null) {
                        var23.setPosition(String.valueOf(var22));
                     }

                     var6.addPromotion(var23);
                  } catch (RuntimeException var27) {
                     var10001 = String.valueOf(var27.getMessage());
                     if (var10001.length() != 0) {
                        var10000 = "Failed to extract a promotion from DataLayer. ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Failed to extract a promotion from DataLayer. ");
                     }

                     zzdj.e(var10000);
                  }
               }

               if (var8.containsKey("promoClick")) {
                  var6.set("&promoa", "click");
                  var30 = false;
               } else {
                  var6.set("&promoa", "view");
               }
            }

            if (var30) {
               var31 = zzbHw.iterator();

               while(var31.hasNext()) {
                  String var33 = (String)var31.next();
                  if (var8.containsKey(var33)) {
                     Map var15;
                     List var16;
                     Map var18;
                     if ((var16 = (List)(var15 = (Map)var8.get(var33)).get("products")) != null) {
                        Iterator var17 = var16.iterator();

                        while(var17.hasNext()) {
                           var18 = (Map)var17.next();

                           try {
                              Product var19 = zzt(var18);
                              var6.addProduct(var19);
                           } catch (RuntimeException var26) {
                              var10001 = String.valueOf(var26.getMessage());
                              if (var10001.length() != 0) {
                                 var10000 = "Failed to extract a product from DataLayer. ".concat(var10001);
                              } else {
                                 var10002 = new String;
                                 var10000 = var10002;
                                 var10002.<init>("Failed to extract a product from DataLayer. ");
                              }

                              zzdj.e(var10000);
                           }
                        }
                     }

                     try {
                        ProductAction var34;
                        if (var15.containsKey("actionField")) {
                           var18 = (Map)var15.get("actionField");
                           ProductAction var24 = new ProductAction(var33);
                           Object var35;
                           if ((var35 = var18.get("id")) != null) {
                              var24.setTransactionId(String.valueOf(var35));
                           }

                           if ((var35 = var18.get("affiliation")) != null) {
                              var24.setTransactionAffiliation(String.valueOf(var35));
                           }

                           if ((var35 = var18.get("coupon")) != null) {
                              var24.setTransactionCouponCode(String.valueOf(var35));
                           }

                           if ((var35 = var18.get("list")) != null) {
                              var24.setProductActionList(String.valueOf(var35));
                           }

                           if ((var35 = var18.get("option")) != null) {
                              var24.setCheckoutOptions(String.valueOf(var35));
                           }

                           if ((var35 = var18.get("revenue")) != null) {
                              var24.setTransactionRevenue(zzM(var35).doubleValue());
                           }

                           if ((var35 = var18.get("tax")) != null) {
                              var24.setTransactionTax(zzM(var35).doubleValue());
                           }

                           if ((var35 = var18.get("shipping")) != null) {
                              var24.setTransactionShipping(zzM(var35).doubleValue());
                           }

                           if ((var35 = var18.get("step")) != null) {
                              var24.setCheckoutStep(zzN(var35).intValue());
                           }

                           var34 = var24;
                        } else {
                           var34 = new ProductAction(var33);
                        }

                        var6.setProductAction(var34);
                     } catch (RuntimeException var25) {
                        var10001 = String.valueOf(var25.getMessage());
                        if (var10001.length() != 0) {
                           var10000 = "Failed to extract a product action from DataLayer. ".concat(var10001);
                        } else {
                           var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("Failed to extract a product action from DataLayer. ");
                        }

                        zzdj.e(var10000);
                     }
                     break;
                  }
               }
            }
         }

         var2.send(var6.build());
      } else if (zzg(var1, zzbHo)) {
         var2.send(this.zzi((com.google.android.gms.internal.zzbr)var1.get(zzbHs)));
      } else if (zzg(var1, zzbHt)) {
         this.zza(var2, var1);
      } else {
         zzdj.zzaT("Ignoring unknown tag.");
      }
   }

   private final String zzfA(String var1) {
      Object var2;
      return (var2 = this.zzbDx.get(var1)) == null ? null : var2.toString();
   }

   private final List zzfB(String var1) {
      Object var2;
      if ((var2 = this.zzbDx.get(var1)) == null) {
         return null;
      } else if (!(var2 instanceof List)) {
         throw new IllegalArgumentException("transactionProducts should be of type List.");
      } else {
         Iterator var3 = ((List)var2).iterator();

         do {
            if (!var3.hasNext()) {
               return (List)var2;
            }
         } while(var3.next() instanceof Map);

         throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
      }
   }

   private static void zzd(Map var0, String var1, String var2) {
      if (var2 != null) {
         var0.put(var1, var2);
      }

   }

   private final void zza(Tracker var1, Map var2) {
      String var3;
      if ((var3 = this.zzfA("transactionId")) == null) {
         zzdj.e("Cannot find transactionId in data layer.");
      } else {
         LinkedList var4 = new LinkedList();

         try {
            Map var5;
            (var5 = this.zzi((com.google.android.gms.internal.zzbr)var2.get(zzbHs))).put("&t", "transaction");
            com.google.android.gms.internal.zzbr var13;
            HashMap var14;
            Map var10000;
            if ((var13 = (com.google.android.gms.internal.zzbr)var2.get(zzbHu)) != null) {
               var10000 = zzh(var13);
            } else {
               if (zzbHz == null) {
                  (var14 = new HashMap()).put("transactionId", "&ti");
                  var14.put("transactionAffiliation", "&ta");
                  var14.put("transactionTax", "&tt");
                  var14.put("transactionShipping", "&ts");
                  var14.put("transactionTotal", "&tr");
                  var14.put("transactionCurrency", "&cu");
                  zzbHz = var14;
               }

               var10000 = zzbHz;
            }

            Iterator var6 = var10000.entrySet().iterator();

            while(var6.hasNext()) {
               Entry var7 = (Entry)var6.next();
               zzd(var5, (String)var7.getValue(), this.zzfA((String)var7.getKey()));
            }

            var4.add(var5);
            Map var8;
            List var16;
            Iterator var17;
            if ((var16 = this.zzfB("transactionProducts")) != null) {
               var17 = var16.iterator();

               while(var17.hasNext()) {
                  if ((var8 = (Map)var17.next()).get("name") == null) {
                     zzdj.e("Unable to send transaction item hit due to missing 'name' field.");
                     return;
                  }

                  Map var9;
                  (var9 = this.zzi((com.google.android.gms.internal.zzbr)var2.get(zzbHs))).put("&t", "item");
                  var9.put("&ti", var3);
                  if ((var13 = (com.google.android.gms.internal.zzbr)var2.get(zzbHv)) != null) {
                     var10000 = zzh(var13);
                  } else {
                     if (zzbHA == null) {
                        (var14 = new HashMap()).put("name", "&in");
                        var14.put("sku", "&ic");
                        var14.put("category", "&iv");
                        var14.put("price", "&ip");
                        var14.put("quantity", "&iq");
                        var14.put("currency", "&cu");
                        zzbHA = var14;
                     }

                     var10000 = zzbHA;
                  }

                  Iterator var10 = var10000.entrySet().iterator();

                  while(var10.hasNext()) {
                     Entry var11 = (Entry)var10.next();
                     zzd(var9, (String)var11.getValue(), (String)var8.get(var11.getKey()));
                  }

                  var4.add(var9);
               }
            }

            var17 = var4.iterator();

            while(var17.hasNext()) {
               var8 = (Map)var17.next();
               var1.send(var8);
            }

         } catch (IllegalArgumentException var15) {
            zzdj.zzb("Unable to send transaction", var15);
         }
      }
   }

   private static Product zzt(Map var0) {
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

                     zzdj.zzaT(var10000);
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

                        zzdj.zzaT(var10000);
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

   private static Map zzh(com.google.android.gms.internal.zzbr var0) {
      Object var1;
      if (!((var1 = zzgk.zzg(var0)) instanceof Map)) {
         return null;
      } else {
         Map var2 = (Map)var1;
         LinkedHashMap var3 = new LinkedHashMap();
         Iterator var4 = var2.entrySet().iterator();

         while(var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            var3.put(var5.getKey().toString(), var5.getValue().toString());
         }

         return var3;
      }
   }

   private final Map zzi(com.google.android.gms.internal.zzbr var1) {
      if (var1 == null) {
         return new HashMap();
      } else {
         Map var2;
         if ((var2 = zzh(var1)) == null) {
            return new HashMap();
         } else {
            String var3;
            if ((var3 = (String)var2.get("&aip")) != null && this.zzbHB.contains(var3.toLowerCase())) {
               var2.remove("&aip");
            }

            return var2;
         }
      }
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
      zzbHn = zzbg.zzfB.toString();
      zzbHo = zzbg.zzfM.toString();
      zzbHp = zzbg.zzhm.toString();
      zzbHq = zzbg.zzhf.toString();
      zzbHr = zzbg.zzhe.toString();
      zzbHs = zzbg.zzfL.toString();
      zzbHt = zzbg.zzjU.toString();
      zzbHu = zzbg.zzjX.toString();
      zzbHv = zzbg.zzjZ.toString();
      zzbHw = Arrays.asList("detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund");
      zzbHx = Pattern.compile("dimension(\\d+)");
      zzbHy = Pattern.compile("metric(\\d+)");
   }
}
