# Development Notes

[TOC]

## 2021-12-01

- ( 2021-12-02 09:30:43 )
```
~/git/advent-of-code/2021$ sbt console
Welcome to Scala 2.13.4 (OpenJDK 64-Bit Server VM, Java 1.8.0_312).
Type in expressions for evaluation. Or try :help.

scala> import aocd.Problem
import aocd.Problem

scala> object day1 extends Problem(2021, 1) {
     |   def run(input: List[String]): Unit = {
     |     println("test")
     |   }
     | }
object day1

scala> val args = new Array[String](1)
val args: Array[String] = Array(null)

scala> day1.main(args)
requests.RequestFailedException: Request to https://adventofcode.com/2021/day/1/input failed with status code 400
Puzzle inputs differ by user.  Please log in to get your puzzle input.

  at requests.Requester$$anon$1.readBytesThrough(Requester.scala:356)
  at geny.Readable.writeBytesTo(Writable.scala:64)
  at geny.Readable.writeBytesTo$(Writable.scala:64)
  at requests.Requester$$anon$1.writeBytesTo(Requester.scala:165)
  at requests.Requester.apply(Requester.scala:114)
  at aocd.Api.getData(Api.scala:7)
  ... 44 elided
```
- ( 2021-12-02 09:34:43 )
- renew my token in `~/.aocd/token`
- reference https://github.com/wimglenn/advent-of-code-wim/issues/1
- ( 2021-12-02 09:40:36 )
```
scala> day1.main(args)
test

scala> val input: os.Path = os.home / ".aocd" / "2021" / "1" / "input.txt"
val input: os.Path = /Users/jazzwang/.aocd/2021/1/input.txt

scala> val in = os.read.lines(input)
val in: IndexedSeq[String] = ArraySeq(...skip

scala> val data = in.map(_.toInt)
val data: IndexedSeq[Int] = ArraySeq(...skip

scala> var count = 0
var count: Int = 0

scala> for ( i <- 1 to (data.size - 1) ) {
     |   if ( data(i) > data(i-1) ) { count = count + 1 }
     | }

scala> count
## this is the answer of Day 1 part 1
```
- ( 2021-12-02 17:11:24 )
```
scala> var count2=0
var count2: Int = 0

scala> for ( i <- 1 to (data.size - 3) ) {
     |   if ( data.slice(i,i+3).sum > data.slice(i-1,i+2).sum ) { count2 = count2 + 1 }
     | }

scala> count2
## this is the answer of Day 1 part 2
```
