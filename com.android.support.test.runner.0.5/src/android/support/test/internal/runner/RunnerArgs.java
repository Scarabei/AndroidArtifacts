package android.support.test.internal.runner;

import android.app.Instrumentation;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.test.runner.lifecycle.ApplicationLifecycleCallback;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.runner.notification.RunListener;

public class RunnerArgs {
   private static final String LOG_TAG = "RunnerArgs";
   static final String ARGUMENT_TEST_CLASS = "class";
   static final String ARGUMENT_NOT_TEST_CLASS = "notClass";
   static final String ARGUMENT_TEST_SIZE = "size";
   static final String ARGUMENT_LOG_ONLY = "log";
   static final String ARGUMENT_ANNOTATION = "annotation";
   static final String ARGUMENT_NOT_ANNOTATION = "notAnnotation";
   static final String ARGUMENT_NUM_SHARDS = "numShards";
   static final String ARGUMENT_SHARD_INDEX = "shardIndex";
   static final String ARGUMENT_DELAY_IN_MILLIS = "delay_msec";
   static final String ARGUMENT_COVERAGE = "coverage";
   static final String ARGUMENT_COVERAGE_PATH = "coverageFile";
   static final String ARGUMENT_SUITE_ASSIGNMENT = "suiteAssignment";
   static final String ARGUMENT_DEBUG = "debug";
   static final String ARGUMENT_LISTENER = "listener";
   static final String ARGUMENT_TEST_PACKAGE = "package";
   static final String ARGUMENT_NOT_TEST_PACKAGE = "notPackage";
   static final String ARGUMENT_TIMEOUT = "timeout_msec";
   static final String ARGUMENT_TEST_FILE = "testFile";
   static final String ARGUMENT_DISABLE_ANALYTICS = "disableAnalytics";
   static final String ARGUMENT_APP_LISTENER = "appListener";
   static final String ARGUMENT_IDLE = "idle";
   private static final char CLASS_SEPARATOR = ',';
   private static final char METHOD_SEPARATOR = '#';
   public final boolean debug;
   public final boolean suiteAssignment;
   public final boolean codeCoverage;
   public final String codeCoveragePath;
   public final int delayInMillis;
   public final boolean logOnly;
   public final List testPackages;
   public final List notTestPackages;
   public final String testSize;
   public final String annotation;
   public final List notAnnotations;
   public final long testTimeout;
   public final List listeners;
   public final List tests;
   public final List notTests;
   public final int numShards;
   public final int shardIndex;
   public final boolean disableAnalytics;
   public final List appListeners;
   public final boolean idle;

   private RunnerArgs(RunnerArgs.Builder builder) {
      this.debug = builder.debug;
      this.suiteAssignment = builder.suiteAssignment;
      this.codeCoverage = builder.codeCoverage;
      this.codeCoveragePath = builder.codeCoveragePath;
      this.delayInMillis = builder.delayInMillis;
      this.logOnly = builder.logOnly;
      this.testPackages = builder.testPackages;
      this.notTestPackages = builder.notTestPackages;
      this.testSize = builder.testSize;
      this.annotation = builder.annotation;
      this.notAnnotations = Collections.unmodifiableList(builder.notAnnotations);
      this.testTimeout = builder.testTimeout;
      this.listeners = Collections.unmodifiableList(builder.listeners);
      this.tests = Collections.unmodifiableList(builder.tests);
      this.notTests = Collections.unmodifiableList(builder.notTests);
      this.numShards = builder.numShards;
      this.shardIndex = builder.shardIndex;
      this.disableAnalytics = builder.disableAnalytics;
      this.appListeners = Collections.unmodifiableList(builder.appListeners);
      this.idle = builder.idle;
   }

   // $FF: synthetic method
   RunnerArgs(RunnerArgs.Builder x0, RunnerArgs.SyntheticClass_1 x1) {
      this(x0);
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   public static class Builder {
      private boolean debug = false;
      private boolean suiteAssignment = false;
      private boolean codeCoverage = false;
      private String codeCoveragePath = null;
      private int delayInMillis = -1;
      private boolean logOnly = false;
      private List testPackages = new ArrayList();
      private List notTestPackages = new ArrayList();
      private String testSize = null;
      private String annotation = null;
      private List notAnnotations = new ArrayList();
      private long testTimeout = -1L;
      private List listeners = new ArrayList();
      private List tests = new ArrayList();
      private List notTests = new ArrayList();
      private int numShards = 0;
      private int shardIndex = 0;
      private boolean disableAnalytics = false;
      private List appListeners = new ArrayList();
      private boolean idle = false;

      public RunnerArgs.Builder fromBundle(Bundle bundle) {
         this.debug = this.parseBoolean(bundle.getString("debug"));
         this.delayInMillis = this.parseUnsignedInt(bundle.get("delay_msec"), "delay_msec");
         this.tests.addAll(this.parseTestClasses(bundle.getString("class")));
         this.tests.addAll(this.parseTestClassesFromFile(bundle.getString("testFile")));
         this.notTests.addAll(this.parseTestClasses(bundle.getString("notClass")));
         this.listeners.addAll(this.parseAndLoadClasses(bundle.getString("listener"), RunListener.class));
         this.testPackages.addAll(this.parseTestPackages(bundle.getString("package")));
         this.notTestPackages.addAll(this.parseTestPackages(bundle.getString("notPackage")));
         this.testSize = bundle.getString("size");
         this.annotation = bundle.getString("annotation");
         this.notAnnotations.addAll(this.parseStrings(bundle.getString("notAnnotation")));
         this.testTimeout = this.parseUnsignedLong(bundle.getString("timeout_msec"), "timeout_msec");
         this.numShards = this.parseUnsignedInt(bundle.get("numShards"), "numShards");
         this.shardIndex = this.parseUnsignedInt(bundle.get("shardIndex"), "shardIndex");
         this.logOnly = this.parseBoolean(bundle.getString("log"));
         this.disableAnalytics = this.parseBoolean(bundle.getString("disableAnalytics"));
         this.appListeners.addAll(this.parseAndLoadClasses(bundle.getString("appListener"), ApplicationLifecycleCallback.class));
         this.codeCoverage = this.parseBoolean(bundle.getString("coverage"));
         this.codeCoveragePath = bundle.getString("coverageFile");
         this.suiteAssignment = this.parseBoolean(bundle.getString("suiteAssignment"));
         this.idle = this.parseBoolean(bundle.getString("idle"));
         return this;
      }

      public RunnerArgs.Builder fromManifest(Instrumentation instr) {
         PackageManager pm = instr.getContext().getPackageManager();

         try {
            InstrumentationInfo instrInfo = pm.getInstrumentationInfo(instr.getComponentName(), 128);
            Bundle b = instrInfo.metaData;
            return b == null ? this : this.fromBundle(b);
         } catch (NameNotFoundException var5) {
            Log.wtf("RunnerArgs", String.format("Could not find component %s", instr.getComponentName()));
            return this;
         }
      }

      private List parseStrings(String value) {
         return value == null ? Collections.emptyList() : Arrays.asList(value.split(","));
      }

      private boolean parseBoolean(String booleanValue) {
         return booleanValue != null && Boolean.parseBoolean(booleanValue);
      }

      private int parseUnsignedInt(Object value, String name) {
         if (value != null) {
            int intValue = Integer.parseInt(value.toString());
            if (intValue < 0) {
               throw new NumberFormatException(name + " can not be negative");
            } else {
               return intValue;
            }
         } else {
            return -1;
         }
      }

      private long parseUnsignedLong(Object value, String name) {
         if (value != null) {
            long longValue = Long.parseLong(value.toString());
            if (longValue < 0L) {
               throw new NumberFormatException(name + " can not be negative");
            } else {
               return longValue;
            }
         } else {
            return -1L;
         }
      }

      private List parseTestPackages(String packagesArg) {
         List packages = new ArrayList();
         if (packagesArg != null) {
            String[] arr$ = packagesArg.split(String.valueOf(','));
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String packageName = arr$[i$];
               packages.add(packageName);
            }
         }

         return packages;
      }

      private List parseTestClasses(String classesArg) {
         List tests = new ArrayList();
         if (classesArg != null) {
            String[] arr$ = classesArg.split(String.valueOf(','));
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String className = arr$[i$];
               tests.add(this.parseTestClass(className));
            }
         }

         return tests;
      }

      private RunnerArgs.TestArg parseTestClass(String testClassName) {
         int methodSeparatorIndex = testClassName.indexOf(35);
         if (methodSeparatorIndex > 0) {
            String testMethodName = testClassName.substring(methodSeparatorIndex + 1);
            testClassName = testClassName.substring(0, methodSeparatorIndex);
            return new RunnerArgs.TestArg(testClassName, testMethodName);
         } else {
            return new RunnerArgs.TestArg(testClassName);
         }
      }

      private List parseTestClassesFromFile(String filePath) {
         List tests = new ArrayList();
         if (filePath == null) {
            return tests;
         } else {
            BufferedReader br = null;

            try {
               br = new BufferedReader(new FileReader(new File(filePath)));

               String line;
               while((line = br.readLine()) != null) {
                  tests.add(this.parseTestClass(line));
               }
            } catch (FileNotFoundException var14) {
               throw new IllegalArgumentException("testfile not found: " + filePath);
            } catch (IOException var15) {
               throw new IllegalArgumentException("Could not read testfile " + filePath, var15);
            } finally {
               if (br != null) {
                  try {
                     br.close();
                  } catch (IOException var13) {
                     ;
                  }
               }

            }

            return tests;
         }
      }

      private List parseAndLoadClasses(String classString, Class type) {
         List objects = new ArrayList();
         if (classString != null) {
            String[] arr$ = classString.split(",");
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String className = arr$[i$];
               this.addByClassName(objects, className, type);
            }
         }

         return objects;
      }

      private void addByClassName(List objects, String className, Class type) {
         if (className != null && className.length() != 0) {
            try {
               Class klass = Class.forName(className);
               klass.getConstructor().setAccessible(true);
               Object l = klass.newInstance();
               objects.add(l);
            } catch (ClassNotFoundException var6) {
               throw new IllegalArgumentException("Could not find extra class " + className);
            } catch (NoSuchMethodException var7) {
               throw new IllegalArgumentException("Must have no argument constructor for class " + className);
            } catch (ClassCastException var8) {
               throw new IllegalArgumentException(className + " does not extend " + type.getName());
            } catch (InstantiationException var9) {
               throw new IllegalArgumentException("Failed to create: " + className, var9);
            } catch (IllegalAccessException var10) {
               throw new IllegalArgumentException("Failed to create listener: " + className, var10);
            }
         }
      }

      public RunnerArgs build() {
         return new RunnerArgs(this);
      }
   }

   public static class TestArg {
      public final String testClassName;
      public final String methodName;

      TestArg(String className, String methodName) {
         this.testClassName = className;
         this.methodName = methodName;
      }

      TestArg(String className) {
         this(className, (String)null);
      }
   }
}
