import scala.sys.process._
import scalapb.compiler.Version.scalapbVersion

ThisBuild / organization := "com.github.envoy-scala.control-plane"
ThisBuild / version := "0.1.15"
// scalaVersion := "2.11.12"
crossScalaVersions := Seq("2.11.12", "2.12.8")
name := "api"

PB.targets in Compile := Seq(
  scalapb.gen(flatPackage=true) -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  // For finding google/protobuf/descriptor.proto
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-json4s" % "0.7.0"
)

mappings in (Compile, packageSrc) ++= {
  val base  = (sourceManaged  in Compile).value
  val files = (managedSources in Compile).value
  files.map { f => (f, f.relativeTo(base).get.getPath) }
}

lazy val fetchSources = taskKey[Unit]("Downloads the sources for the build")
fetchSources := {
  s"./fetch-sources.sh ${(version in ThisBuild).value}" !
}
