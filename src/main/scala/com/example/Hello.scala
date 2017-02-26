package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println(&&({
      println("")
      true
    })(false))
  }

  def &&(left: => Boolean)(right: => Boolean): Boolean = {
    if(left) {
      if(right) true
    }
    false
  }
}




// if() {
//
// }
