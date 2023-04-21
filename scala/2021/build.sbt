scalaVersion := "2.13.4"
libraryDependencies += "io.github.bbstilson" % "aocd_2.13" % "0.1.2"

commands += Command("day") { _ =>
  import complete.DefaultParsers._
  (' ' ~ charClass(_.isDigit, "digit").+.map(_.mkString.toInt)).map(_._2)
} { case (previousState, i: Int) =>
  val formatted = "%02d".format(i)
  Command.process(s"runMain day$formatted", previousState)
}

// https://stackoverflow.com/questions/24996437/how-to-execute-a-bash-script-as-sbt-task/25005

import scala.sys.process._
lazy val distclean = taskKey[Unit]("Clean up temporary files and directories")
distclean := {
  "rm -rf project/target project/project target output spark-warehouse" !
}