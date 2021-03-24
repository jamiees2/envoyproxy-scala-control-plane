import scala.sys.process._
import scalapb.compiler.Version.scalapbVersion

ThisBuild / organization := "io.github.jamiees2"
ThisBuild / version := s"1.15.0"

ThisBuild / description := "ScalaPB generated bindings for Envoy"

crossScalaVersions := Seq("2.12.11")
name := "envoy-scala-control-plane"

PB.targets in Compile := Seq(
  scalapb.gen(flatPackage=true) -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  // For finding google/protobuf/descriptor.proto
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-json4s" % "0.10.3"
)

mappings in (Compile, packageSrc) ++= {
  val base  = (sourceManaged  in Compile).value
  val files = (managedSources in Compile).value
  files.map { f => (f, f.relativeTo(base).get.getPath) }
}

lazy val fetchSources = taskKey[Unit]("Downloads the sources for the build (from the envoyproxy-java-control-plane repo)")
fetchSources := {
  s"./fetch-sources.sh" !
}

lazy val updateApi = taskKey[Unit]("Downloads the API sources (from the envoy repo)")
updateApi := {
  s"./update-api.sh" !
}

publishTo := sonatypePublishToBundle.value
