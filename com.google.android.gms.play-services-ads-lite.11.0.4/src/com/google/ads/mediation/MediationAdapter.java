package com.google.ads.mediation;

/** @deprecated */
@Deprecated
public interface MediationAdapter {
   void destroy();

   Class getAdditionalParametersType();

   Class getServerParametersType();
}
