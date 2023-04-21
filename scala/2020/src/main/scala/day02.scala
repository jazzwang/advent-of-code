import aocd.Problem

object day02 extends Problem(2020, 2) {
    def run(input: List[String]): Unit = {
        val pattern = "([0-9]+)-([0-9]+) ([A-Za-z]): ([A-Za-z]+)".r
        // part-01
        val answer1 = input.filter( x => {
            val pattern(min, max, char, passwd) = x
            val count = passwd.count(_ == char.charAt(0))
            count >= min.toInt && count <= max.toInt
        }).length
        println(s"[1] answer = $answer1")
        // part-02
        val answer2 = input.filter( x => {
            val pattern(min, max, char, passwd) = x
            ( passwd.charAt(min.toInt -1) == char.charAt(0) ) ^
                ( passwd.charAt(max.toInt -1) == char.charAt(0) )
        }).length
        println(s"[2] answer = $answer2")
    }
}