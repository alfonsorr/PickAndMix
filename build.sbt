name := "PickAndMix"

version := "1.0"

scalaVersion := "2.11.8"

lazy val core = project
lazy val spark = project.dependsOn(core)


