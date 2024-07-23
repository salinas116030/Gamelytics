# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
# -keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
# }

# Uncomment this to preserve the line number information for
# debugging stack traces.
# -keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
# -renamesourcefileattribute SourceFile

-keep class com.blongho.** {
    *;
}

-keep interface com.blongho.** {
    *;
}

# If you keep the line number information, uncomment this to
# hide the original source file name.
# -renamesourcefileattribute SourceFile

-keeppackagenames com.blongho.country_data

-keepclassmembers class com.blongho.country_data.* {
    public *;
}

-keep class com.blongho.country_data.R$* {
    *;
}

# Preserve okhttp3 classes
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Preserve javax.annotation.Nullable
-keep class javax.annotation.** { *; }
-dontwarn javax.annotation.**

# Preserve org.conscrypt classes
-keep class org.conscrypt.** { *; }
-dontwarn org.conscrypt.**

# Preserve classes in Conscrypt
-keep class java.security.Provider { *; }
-dontwarn java.security.Provider
