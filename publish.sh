set -o allexport

echo "Publishing..."

./gradlew :android-pdf-viewer:publishToMavenLocal
