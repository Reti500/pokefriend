import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.firebase() {
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
}

fun DependencyHandler.firestore() {
    implementation("com.google.firebase:firebase-firestore-ktx")
}

fun DependencyHandler.hilt() {
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}

fun DependencyHandler.retrofit() {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}

fun DependencyHandler.room() {
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

private fun DependencyHandler.annotationProcessor(depName: String) {
    add("annotationProcessor", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
