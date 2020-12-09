# Development Notes

[TOC]

## 2020-12-09

- ( 2020-12-09 23:00:56 )
- initial project with `sbt --sbt-create`
```bash
~/git/advent-of-code-2020$ git gi sbt,scala > .gitignore
~/git/advent-of-code-2020$ sbt --sbt-create
[info] Updated file /Users/jazzwang/git/advent-of-code-2020/project/build.properties: set sbt.version to 1.3.8
sbt:advent-of-code-2020> set scalaVersion := "2.13.4"
sbt:advent-of-code-2020> set libraryDependencies += "io.github.bbstilson" % "aocd_2.13" % "0.1.2"
sbt:advent-of-code-2020> update
[success] Total time: 1 s, completed Dec 9, 2020 11:03:27 PM
sbt:advent-of-code-2020> session save
[info] Reapplying settings...
[info] Set current project to advent-of-code-2020 (in build file:/Users/jazzwang/git/advent-of-code-2020/)
sbt:advent-of-code-2020> exit
[info] shutting down sbt server
```
- follow the instructions of <https://github.com/bbstilson/advent-of-code-data>
    - https://search.maven.org/artifact/io.github.bbstilson/aocd_2.13/0.1.2/jar
```bash
~/git/advent-of-code-2020$ mkdir -p ~/.aocd
```
- reference https://github.com/wimglenn/advent-of-code-wim/issues/1
    - found my token with browser
    - store my token in `~/.aocd/token`
```bash
~/git/advent-of-code-2020$ echo "yoursessionstoken" > ~/.aocd/token
```
- ( 2020-12-09 23:08:20 )
```bash
~/git/advent-of-code-2020$ sbt console
Welcome to Scala 2.13.4 (OpenJDK 64-Bit Server VM, Java 1.8.0_275).
Type in expressions for evaluation. Or try :help.

scala>
```
- learn from https://github.com/bbstilson/advent-of-code-data/blob/main/aocd/src/aocd/Problem.scala
    - `os.Path`
    - `os.read.lines`
    - `os.home`

```scala
scala> import aocd.Problem
scala> object day1 extends Problem(2020, 1) {
     |   def run(input: List[String]): Unit = {
     |     println("test")
     |   }
     | }
scala> val args = new Array[String](1)
scala> day1.main(args)
test
scala> val input: os.Path = os.home / ".aocd" / "2020" / "1" / "input.txt"
val input: os.Path = /Users/jazzwang/.aocd/2020/1/input.txt
scala> val in = os.read.lines(input)
val in: IndexedSeq[String] =
... (skip) ...
scala> val data = in.map(_.toInt)
scala> val out = data.flatMap(x => data.filter(y => x + y == 2020)).toList
scala> out(0) * out(1)
scala> val out = data.flatMap(x => data.flatMap( y => data.filter( z => x + y + z == 2020))).distinct.toList
scala> out(0)*out(1)*out(2)
```
- ( 2020-12-09 23:37:18 )
- learn from https://github.com/bbstilson/adventofcode/blob/main/build.sc
    - it will call `runMain( your_class_name )`

## 2020-12-10

- ( 2020-12-10 00:26:20 )
- learn from https://github.com/FlorianCassayre/AdventOfCode-2020/blob/0273e0d33b45a61e91b1b41526501eea46cadb84/build.sbt#L12-L18 about self defined sbt command
```
commands += Command("day") { _ =>
  import complete.DefaultParsers._
  (' ' ~ charClass(_.isDigit, "digit").+.map(_.mkString.toInt)).map(_._2)
} { case (previousState, i: Int) =>
  val formatted = "%02d".format(i)
  Command.process(s"runMain adventofcode.solutions.Day$formatted", previousState)
}
```