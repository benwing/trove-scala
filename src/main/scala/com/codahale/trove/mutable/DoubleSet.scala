package com.codahale.trove.mutable

import gnu.trove.set.TDoubleSet
import gnu.trove.set.hash.TDoubleHashSet
import scala.collection.mutable.{GrowingBuilder, Builder}
import scala.collection.generic.{CanBuildFrom}
import com.codahale.trove.generic.{DoubleSetFactory, DoubleIterator}

class DoubleSet(private[this] val underlying: TDoubleSet)
  extends scala.collection.mutable.Set[Double]
          with com.codahale.trove.collection.DoubleSet
          with com.codahale.trove.collection.DoubleSetLike[DoubleSet]
          with scala.collection.mutable.SetLike[Double, DoubleSet]
          with Serializable {

  override def empty = DoubleSet.empty

  def -=(elem: Double) = {
    underlying.remove(elem)
    this
  }

  def +=(elem: Double) = {
    underlying.add(elem)
    this
  }

  def contains(elem: Double) = underlying.contains(elem)

  def iterator = new DoubleIterator(underlying.iterator)
}


object DoubleSet extends DoubleSetFactory[DoubleSet] {
  def empty: DoubleSet = new DoubleSet(new TDoubleHashSet())
  def newBuilder: Builder[Double, DoubleSet] = new GrowingBuilder[Double, DoubleSet](empty)
  implicit def canBuildFrom: CanBuildFrom[DoubleSet, Double, DoubleSet] = doublesetCanBuildFrom
}
