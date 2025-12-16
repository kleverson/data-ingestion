# Regras ProGuard para otimização do RunnableApi

# Manter classes principais
-keep class MainKt { *; }
-keep class com.kholanda.runnableapi.** { *; }

# Manter classes do Compose
-keep class androidx.compose.** { *; }
-keep class org.jetbrains.compose.** { *; }
-keep class org.jetbrains.skiko.** { *; }

# Manter classes do Ktor
-keep class io.ktor.** { *; }
-keepclassmembers class io.ktor.** { *; }

# Manter classes de serialização
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class kotlinx.serialization.** { *; }
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# Manter classes anotadas com @Serializable
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
-keepclassmembers @kotlinx.serialization.Serializable class * {
    *** Companion;
}
-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}

# Otimizações gerais
-optimizationpasses 5
-dontpreverify
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# Remover logs em produção
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    public static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
    public static void checkExpressionValueIsNotNull(java.lang.Object, java.lang.String);
}

# Manter nomes de métodos para reflection
-keepattributes Signature,InnerClasses,EnclosingMethod

# Warnings ignoráveis
-dontwarn org.slf4j.**
-dontwarn org.jetbrains.skiko.**
