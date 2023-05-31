import scala.sys.process._
import scalapb.compiler.Version.scalapbVersion

ThisBuild / organization := "io.github.jamiees2"
ThisBuild / version := s"1.23.0-dev-f04150-1"

ThisBuild / description := "ScalaPB generated bindings for Envoy"

crossScalaVersions := Seq("2.12.16", "2.13.8")
name := "envoy-scala-control-plane"

PB.protocVersion := "3.15.0"
Compile / PB.protocOptions := Seq("--experimental_allow_proto3_optional")

Compile / PB.targets := Seq(
  scalapb.gen(flatPackage=true) -> (Compile / sourceManaged).value
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  // For finding google/protobuf/descriptor.proto
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-json4s" % "0.11.1"
)

Compile / packageSrc / mappings ++= {
  val base  = (Compile / sourceManaged).value
  val files = (Compile / managedSources).value
  files.map { f => (f, f.relativeTo(base).get.getPath) }
}

lazy val updateApi = taskKey[Unit]("Downloads the API sources (from the envoy repo)")
updateApi := {
  s"./update-api.sh" !
}

publishTo := sonatypePublishToBundle.value
