import aocd.Problem

object day03 extends Problem(2021, 3) {
    def run(input: List[String]): Unit = {
        val sample = Array("00100",
                           "11110",
                           "10110",
                           "10111",
                           "10101",
                           "01111",
                           "00111",
                           "11100",
                           "10000",
                           "11001",
                           "00010",
                           "01010")
        val sa = sample.map(_.toCharArray.map(_.asDigit))
        val oxygen   =   oxygen_rate(sa, sa(0).size - 1)(0).map(_.toString).mkString
        val scrubber = scrubber_rate(sa, sa(0).size - 1)(0).map(_.toString).mkString
        println("==== TEST CASE : Part Two ========")
        println(s"Oxygen = $oxygen")
        println(s"Scrubber = $scrubber")
        println(Integer.parseInt(oxygen,2)*Integer.parseInt(scrubber,2))

        val sa2 = input.toArray.map(_.toCharArray.map(_.asDigit))
        val oxygen2   =   oxygen_rate(sa2, sa2(0).size - 1)(0).map(_.toString).mkString
        val scrubber2 = scrubber_rate(sa2, sa2(0).size - 1)(0).map(_.toString).mkString
        println("============")
        println(s"Oxygen = $oxygen2")
        println(s"Scrubber = $scrubber2")
        println(s"Ans #2 = ${Integer.parseInt(oxygen2,2)*Integer.parseInt(scrubber2,2)}")
    }

    def more(x: Array[Int]): Int = {
        println(s"1: ${x.sum} , 0: ${x.size - x.sum}")
        if ( x.sum >= x.size - x.sum ) 1 else 0
    }

    def fewer(x: Array[Int]): Int = {
        println(s"1: ${x.sum} , 0: ${x.size - x.sum}")
        if ( x.sum < x.size - x.sum ) 1 else 0
    }

    def oxygen_rate(x: Array[Array[Int]], index: Int): Array[Array[Int]] = {
        if (index == 0) {
            println("--------")
            val o = more(x.map(_(0)))
            println(s"[0] more = $o")
            return x.filter(_(0) == o)
        } else {
            val y = oxygen_rate(x, index-1)
            val o = more(y.map(_(index)))
            println(s"[$index] more = $o")
            return y.filter(_(index) == o)
        }
    }

    def scrubber_rate(x: Array[Array[Int]], index: Int): Array[Array[Int]] = {
        if (index == 0) {
            println("--------")
            val o = fewer(x.map(_(0)))
            println(s"[0] fewer = $o")
            return x.filter(_(0) == o)
        } else {
            val y = scrubber_rate(x, index-1)
            if (y.size == 1) return y
            val o = fewer(y.map(_(index)))
            println(s"[$index] fewer = $o")
            return y.filter(_(index) == o)
        }
    }
}