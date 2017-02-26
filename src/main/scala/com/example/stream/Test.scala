package com.example.stream

/**
  * Created by vitaliizinchenko on 2/26/17.
  */
object Test {

  val ones: Stream[Int] = Stream.cons(1, ones)

  def main(args: Array[String]): Unit = {
    val s: Stream[Int] = Cons(
      c(1),
      () => {
        println("e_1_2 ")
        Cons(
          c (2),
          () => {
            println("e_2_2 ")
            Empty
          })
      }
    )

//    println(Stream.toList(s))
//    println(Stream.take(s, 1))
//    println(Stream.foldRight(s, 0)((a, b) => a + b))
    println(Stream.exists(s, (item: Int) => item == 2))


  }

  def c(v: Int): () => Int = {
    () => {
      println("evaluating " + v)
      v
    }
  }

}
