pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokeFriend"
include(":app")
include(":data:remote")
include(":data:local")
include(":data:firebase")
include(":core:core")
include(":feature:pokelist")
include(":core:navigator")
include(":shared:components")
include(":shared:location")
include(":feature:mylocations")
