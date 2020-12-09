import aocd.Problem

object day1 extends Problem(2020, 1) {
    def run(input: List[String]): Unit = {
        val data = input.map(_.toInt)
        val out1 = data.flatMap(x =>
            data.filter(y => x + y == 2020)).toList
        val answer1 = out1(0) * out1(1)
        println(s"[1] answer: $answer1")

        val out2 = data.flatMap(x =>
            data.flatMap( y =>
                data.filter( z => x + y + z == 2020))
            ).distinct.toList

        val answer2 = out2(0) * out2(1) * out2(2)
        println(s"[2] answer: $answer2")
    }
}