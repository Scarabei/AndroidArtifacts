package android.support.test.internal.runner;

import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import junit.framework.Test;
import junit.framework.TestCase;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

public final class TestLoader {
   private static final String LOG_TAG = "TestLoader";
   private Map mLoadedClassesMap = new LinkedHashMap();
   private Map mLoadFailuresMap = new LinkedHashMap();
   private ClassLoader mClassLoader;

   public void setClassLoader(ClassLoader loader) {
      this.mClassLoader = loader;
   }

   public Class loadClass(String className) {
      Class loadedClass = this.doLoadClass(className);
      if (loadedClass != null) {
         this.mLoadedClassesMap.put(className, loadedClass);
      }

      return loadedClass;
   }

   protected ClassLoader getClassLoader() {
      return this.mClassLoader != null ? this.mClassLoader : this.getClass().getClassLoader();
   }

   private Class doLoadClass(String className) {
      if (this.mLoadFailuresMap.containsKey(className)) {
         return null;
      } else if (this.mLoadedClassesMap.containsKey(className)) {
         return (Class)this.mLoadedClassesMap.get(className);
      } else {
         try {
            ClassLoader myClassLoader = this.getClassLoader();
            return Class.forName(className, false, myClassLoader);
         } catch (ClassNotFoundException var6) {
            String errMsg = String.format("Could not find class: %s", className);
            Log.e("TestLoader", errMsg);
            Description description = Description.createSuiteDescription(className, new Annotation[0]);
            Failure failure = new Failure(description, var6);
            this.mLoadFailuresMap.put(className, failure);
            return null;
         }
      }
   }

   public Class loadIfTest(String className) {
      Class loadedClass = this.doLoadClass(className);
      if (loadedClass != null && this.isTestClass(loadedClass)) {
         this.mLoadedClassesMap.put(className, loadedClass);
         return loadedClass;
      } else {
         return null;
      }
   }

   public boolean isEmpty() {
      return this.mLoadedClassesMap.isEmpty() && this.mLoadFailuresMap.isEmpty();
   }

   public Collection getLoadedClasses() {
      return this.mLoadedClassesMap.values();
   }

   public Collection getLoadFailures() {
      return this.mLoadFailuresMap.values();
   }

   private boolean isTestClass(Class loadedClass) {
      try {
         if (Modifier.isAbstract(loadedClass.getModifiers())) {
            this.logDebug(String.format("Skipping abstract class %s: not a test", loadedClass.getName()));
            return false;
         } else if (Test.class.isAssignableFrom(loadedClass)) {
            return TestCase.class.isAssignableFrom(loadedClass) ? this.hasJUnit3TestMethod(loadedClass) : true;
         } else if (loadedClass.isAnnotationPresent(RunWith.class)) {
            return true;
         } else {
            Method[] arr$ = loadedClass.getMethods();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               Method testMethod = arr$[i$];
               if (testMethod.isAnnotationPresent(org.junit.Test.class)) {
                  return true;
               }
            }

            this.logDebug(String.format("Skipping class %s: not a test", loadedClass.getName()));
            return false;
         }
      } catch (Exception var6) {
         Log.w("TestLoader", String.format("%s in isTestClass for %s", var6.toString(), loadedClass.getName()));
         return false;
      } catch (Error var7) {
         Log.w("TestLoader", String.format("%s in isTestClass for %s", var7.toString(), loadedClass.getName()));
         return false;
      }
   }

   private boolean hasJUnit3TestMethod(Class loadedClass) {
      Method[] arr$ = loadedClass.getMethods();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Method testMethod = arr$[i$];
         if (this.isPublicTestMethod(testMethod)) {
            return true;
         }
      }

      return false;
   }

   private boolean isPublicTestMethod(Method m) {
      return this.isTestMethod(m) && Modifier.isPublic(m.getModifiers());
   }

   private boolean isTestMethod(Method m) {
      return m.getParameterTypes().length == 0 && m.getName().startsWith("test") && m.getReturnType().equals(Void.TYPE);
   }

   private void logDebug(String msg) {
      if (Log.isLoggable("TestLoader", 3)) {
         Log.d("TestLoader", msg);
      }

   }
}
