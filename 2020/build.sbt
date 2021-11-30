scalaVersion := "2.13.4"
libraryDependencies += "io.github.bbstilson" % "aocd_2.13" % "0.1.2"

commands += Command("day") { _ =>
  import complete.DefaultParsers._
  (' ' ~ charClass(_.isDigit, "digit").+.map(_.mkString.toInt)).map(_._2)
} { case (previousState, i: Int) =>
  val formatted = "%02d".format(i)
  Command.process(s"runMain day$formatted", previousState)
}