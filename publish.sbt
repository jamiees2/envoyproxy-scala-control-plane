licenses := List(
    ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))
  )
homepage := Some(url("http://github.com/jamiees2/envoyproxy-scala-control-plane"))

publishMavenStyle := true
// Resolver.url("raisercostin ivy resolver", url("http://dl.bintray.com/raisercostin/maven"))(Resolver.ivyStylePatterns)

// Not publish sources and/or Javadoc
//packagedArtifacts in publish ~= { m =>
//  val classifiersToExclude = Set(
//    Artifact.SourceClassifier,
//    Artifact.DocClassifier
//  )
//  m.filter { case (art, _) =>
//    art.classifier.forall(c => !classifiersToExclude.contains(c))
//  }
//}

// The plain URL for browsder:
// https://bintray.com/odenzoorg/odenzooss/rippled-wsmodels


//publishArtifact in Test := true // to add the tests JAR
publishArtifact in Test := false
bintrayOrganization := None
bintrayRepository := "maven"
bintrayPackage := "envoyproxy-scala-control-plane"
bintrayReleaseOnPublish in ThisBuild := true
