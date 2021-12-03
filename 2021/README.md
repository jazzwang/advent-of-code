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

## 2021-12-02

- ( 2021-12-02 20:27:40 )
```
~/git/advent-of-code/2021$ sbt console
scala> import aocd.Problem
import aocd.Problem

scala> object day2 extends Problem(2021, 2) {
     |   def run(input: List[String]): Unit = {
     |     println("test")
     |   }
     | }
object day2

scala> day2.main(new Array[String](2))
test

scala> val in = os.read.lines( os.home / ".aocd" / "2021" / "2" / "input.txt" )
val in: IndexedSeq[String] = ArraySeq(forward 5, ...skip

scala> val pos = in.map(x => { x.split(" ") }).map(x => {
     |  x(0) match {
     |     case "forward" => (x(1).toInt,0)
     |     case "down"    => (0, x(1).toInt)
     |     case "up"      => (0, -x(1).toInt)
     |  }
     | }).reduce((x,y) => { (x._1 + y._1, x._2 + y._2) })

scala> pos._1 * pos._2
## this is the answer of Day 2 part 1
```
- ( 2021-12-02 21:03:53 )
```
scala> var hor = 0
var hor: Int = 0

scala> var dep = 0
var dep: Int = 0

scala> var aim = 0
var aim: Int = 0

scala> val part2 = in.map(x => { x.split(" ") })

scala> for ( i <- 0 to part2.size - 1 )
     | {
     |   part2(i)(0) match {
     |     case "forward" => { hor = hor + part2(i)(1).toInt ; dep = dep + aim * part2(i)(1).toInt; }
     |     case "down"    => { aim = aim + part2(i)(1).toInt ; }
     |     case "up"      => { aim = aim - part2(i)(1).toInt ; }
     |   }
     | }

scala> hor * dep
## this is the answer of Day 2 part 2
```

## 2021-12-03
### Part 1
- ( 2021-12-03 13:13:59 )
```
scala> import aocd.Problem
import aocd.Problem

scala> object day3 extends Problem(2021, 3) {
     |   def run(input: List[String]): Unit = {
     |     println("test")
     |   }
     | }
object day3

scala> day3.main(new Array[String](3))
test
```
- ( 2021-12-03 14:44:51 )
- https://stackoverflow.com/a/7197325 - convert binary `"01010101"` String to decimal `85` Int
- https://stackoverflow.com/a/32879121 - `transpose` convert columns to rows
- https://stackoverflow.com/a/16241998 - `'1'.asDigit` convert `Char` to `Int`
- https://alvinalexander.com/scala/scala-convert-array-to-string-mkstring/ - convert Array[String] to String
```
scala> in.size
val res146: Int = 1000

scala> in.map(_.toCharArray.map(_.asDigit)).transpose.map(_.sum).map( x => { if (x > 500) 1 else 0 } )
val res142: IndexedSeq[Int] = ArraySeq(0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0)
```
- ( 2021-12-03 15:04:28 )
-
```
scala> val gamma = in.toArray.map(_.toCharArray.map(_.asDigit)).transpose.map(_.sum).map( x => { if (x > 500) 1 else 0 } ).map(_.toString).mkString
scala> val epsilon = in.toArray.map(_.toCharArray.map(_.asDigit)).transpose.map(_.sum).map( x => { if (x < 500) 1 else 0 } ).map(_.toString).mkString
scala> Integer.parseInt(gamma,2) * Integer.parseInt(epsilon,2)
```
### Part 2

- ( 2021-12-03 15:05:22 )
```
scala> def more(x: Array[Int]): Int = {
     |   if ( x.sum > x.size/2 ) 1 else 0
     | }
def more(x: Array[Int]): Int

scala> def less(x: Array[Int]): Int = {
     |   if ( x.sum < x.size/2 ) 1 else 0
     | }
def less(x: Array[Int]): Int

scala> var oxygen = in.toArray.map(_.toCharArray.map(_.asDigit))
var oxygen: Array[Array[Int]]

scala> val o1 = more(oxygen.map(_(0)))
val o1: Int = 0

scala> oxygen = oxygen.filter(_(0) == o1)
// mutated oxygen

scala> val o2 = more(oxygen.map(_(1)))

scala> oxygen = oxygen.filter(_(0) == o2)
// mutated oxygen

scala> val o3 = more(oxygen.map(_(2)))
```
- ( 2021-12-03 22:28:21 )

