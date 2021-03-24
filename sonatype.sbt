
// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "io.github.jamiees2"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

// Open-source license of your choice
licenses := List(
    ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))
  )

// Where is the source code hosted: GitHub or GitLab?
import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("jamiees2", "envoyproxy-scala-control-plane", "jamiees2@gmail.com"))

// or if you want to set these fields manually
homepage := Some(url("http://github.com/jamiees2/envoyproxy-scala-control-plane"))
scmInfo := Some(
    ScmInfo(
      url("https://github.com/jamiees2/envoyproxy-scala-control-plane"),
      "scm:git@github.com:jamiees2/envoyproxy-scala-control-plane.git"
    )
)
developers := List(
  Developer(id="jamiees2", name="James Elías Sigurðarson", email="jamiees2@gmail.com", url=url("https://github.com/jamiees2"))
)
