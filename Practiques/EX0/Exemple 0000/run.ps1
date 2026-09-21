param(
  [string]$MainClass = "com.project.Main"
)

# --- Setup (Windows) ---
$ErrorActionPreference = "Stop"

# --- Ensure deps (download to ~/.m2) ---
mvn -q -DskipTests dependency:resolve | Out-Null

# --- Try to build module-path via dependency plugin ---
$mpFile = "target\javafx.modulepath.txt"
mvn -q org.apache.maven.plugins:maven-dependency-plugin:3.6.1:build-classpath `
  -DincludeScope=runtime `
  -Dmdep.includeGroupIds=org.openjfx `
  -Dmdep.outputFile="$mpFile" `
  -Dmdep.pathSeparator=";" | Out-Null

$modulePath = ""
if (Test-Path $mpFile) {
  $modulePath = (Get-Content $mpFile -Raw).Trim()
}

# --- Fallback: scan ~/.m2 for OpenJFX jars (Windows classifiers) ---
if ([string]::IsNullOrWhiteSpace($modulePath)) {
  # Note: adjust patterns if you use aarch64 or different version/classifier
  #$home = $env:USERPROFILE
  $fxRoot = Join-Path $home ".m2\repository\org\openjfx"
  if (Test-Path $fxRoot) {
    $jars = Get-ChildItem -Path $fxRoot -Recurse -Include `
      "javafx-base*-win*.jar","javafx-graphics*-win*.jar","javafx-controls*-win*.jar","javafx-fxml*-win*.jar" `
      | Sort-Object FullName -Unique
    if ($jars.Count -gt 0) {
      $modulePath = ($jars | ForEach-Object { $_.FullName }) -join ";"
    }
  }
}

if ([string]::IsNullOrWhiteSpace($modulePath)) {
  Write-Error "No s'ha pogut construir el module-path de JavaFX. Assegura dependències 'org.openjfx' al pom i torna-ho a provar."
  exit 1
}

# --- Compile (classes ready) ---
mvn -q -DskipTests compile | Out-Null

# --- Run with JVM module flags (NOT program args) ---
# Note: exec-maven-plugin accepts -Dexec.jvmArgs for JVM flags.

Write-Host "Main Class: $MainClass"
Write-Host "Module Path (JavaFX): $modulePath"
Write-Host "Running..."

# Definimos el argumento de JVM de forma segura sin comillas escapadas que confundan a la terminal
$jvmArgs = "--module-path $modulePath --add-modules javafx.controls,javafx.fxml"

# Ejecutamos pasando los parámetros de forma directa y limpia
mvn exec:java -PrunMain "-Dexec.mainClass=$MainClass" "-Dexec.jvmArgs=$jvmArgs"

