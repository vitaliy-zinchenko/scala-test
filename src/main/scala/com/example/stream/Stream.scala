package com.example.stream

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader

/**
  * Created by vitaliizinchenko on 2/26/17.
  */
trait Stream[+A]

case object Empty extends Stream[Nothing]

case class Cons[+A](head: () => A, tail: () => Stream[A]) extends Stream[A]

object Stream {

  def cons[A](head: => A, tail: => Stream[A]): Stream[A] = {
    Cons(() => head, () => tail)
  }

  def empty[A](): Stream[A] = Empty

  def apply[A](items: A*): Stream[A] = if (items.isEmpty) empty() else cons(items.head, apply(items.tail: _*))

  def toList[A](stream: Stream[A]): List[A] = stream match {
    case Empty => Nil
    case Cons(head, tail) => head() :: toList(tail())
  }

  def take[A](stream: Stream[A], n: Int): List[A] = {
    def doTake(stream: Stream[A], i: Int): List[A] = stream match {
      case s if n == i => Nil
      case Empty => Nil
      case Cons(head, tail) => head() :: doTake(tail(), i + 1)
    }
    doTake(stream, 0)
  }

  def foldRight[A, B](stream: Stream[A], z: => B)(f: (A, => B) => B): B = stream match {
    case Empty => z
    case Cons(head, tail) => f(head(), foldRight(tail(), z)(f))
  }

  def exists[A](stream: Stream[A], p: A => Boolean): Boolean = {
    foldRight(stream, false)((currentItem, buffer) => {
      p(currentItem) || buffer
    })
  }

}