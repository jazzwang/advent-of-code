import aocd.Problem

object day01 extends Problem(2020, 1) {
    def run(input: List[String]): Unit = {
        val data = input.map(_.toInt)
        val answer1 = data.flatMap(x =>
            data.filter(y => x + y == 2020)).toList.product
        println(s"[1] answer: $answer1")

        val answer2 = data.flatMap(x =>
            data.flatMap( y =>
                data.filter( z => x + y + z == 2020))
            ).distinct.toList.product

        println(s"[2] answer: $answer2")
    }
}