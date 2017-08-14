package android.support.test.internal.runner;

import dalvik.system.DexFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassPathScanner {
   private Set mApkPaths;

   public ClassPathScanner(String... apkPaths) {
      this((Collection)Arrays.asList(apkPaths));
   }

   public ClassPathScanner(Collection apkPaths) {
      this.mApkPaths = new HashSet();
      this.mApkPaths.addAll(apkPaths);
   }

   private void addEntriesFromApk(Set entryNames, String apkPath, ClassPathScanner.ClassNameFilter filter) throws IOException {
      DexFile dexFile = null;

      try {
         dexFile = new DexFile(apkPath);
         Enumeration apkClassNames = this.getDexEntries(dexFile);

         while(apkClassNames.hasMoreElements()) {
            String apkClassName = (String)apkClassNames.nextElement();
            if (filter.accept(apkClassName)) {
               entryNames.add(apkClassName);
            }
         }
      } finally {
         if (dexFile != null) {
            dexFile.close();
         }

      }

   }

   Enumeration getDexEntries(DexFile dexFile) {
      return dexFile.entries();
   }

   public Set getClassPathEntries(ClassPathScanner.ClassNameFilter filter) throws IOException {
      Set entryNames = new LinkedHashSet();
      Iterator i$ = this.mApkPaths.iterator();

      while(i$.hasNext()) {
         String apkPath = (String)i$.next();
         this.addEntriesFromApk(entryNames, apkPath, filter);
      }

      return entryNames;
   }

   public static class ExcludePackageNameFilter implements ClassPathScanner.ClassNameFilter {
      private final String mPkgName;

      ExcludePackageNameFilter(String pkgName) {
         if (!pkgName.endsWith(".")) {
            this.mPkgName = String.format("%s.", pkgName);
         } else {
            this.mPkgName = pkgName;
         }

      }

      public boolean accept(String pathName) {
         return !pathName.startsWith(this.mPkgName);
      }
   }

   public static class InclusivePackageNameFilter implements ClassPathScanner.ClassNameFilter {
      private final String mPkgName;

      InclusivePackageNameFilter(String pkgName) {
         if (!pkgName.endsWith(".")) {
            this.mPkgName = String.format("%s.", pkgName);
         } else {
            this.mPkgName = pkgName;
         }

      }

      public boolean accept(String pathName) {
         return pathName.startsWith(this.mPkgName);
      }
   }

   public static class ExternalClassNameFilter implements ClassPathScanner.ClassNameFilter {
      public boolean accept(String pathName) {
         return !pathName.contains("$");
      }
   }

   public static class ChainedClassNameFilter implements ClassPathScanner.ClassNameFilter {
      private final List mFilters = new ArrayList();

      public void add(ClassPathScanner.ClassNameFilter filter) {
         this.mFilters.add(filter);
      }

      public void addAll(ClassPathScanner.ClassNameFilter... filters) {
         this.mFilters.addAll(Arrays.asList(filters));
      }

      public boolean accept(String className) {
         Iterator i$ = this.mFilters.iterator();

         ClassPathScanner.ClassNameFilter filter;
         do {
            if (!i$.hasNext()) {
               return true;
            }

            filter = (ClassPathScanner.ClassNameFilter)i$.next();
         } while(filter.accept(className));

         return false;
      }
   }

   public static class AcceptAllFilter implements ClassPathScanner.ClassNameFilter {
      public boolean accept(String className) {
         return true;
      }
   }

   public interface ClassNameFilter {
      boolean accept(String var1);
   }
}
