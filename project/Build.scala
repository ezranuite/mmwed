import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "mmwed"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "postgresql" % "postgresql" % "9.1-901.jdbc4",
      jdbc,
      anorm
    )

    val main = play.Project(appName, appVersion, appDependencies)

}
