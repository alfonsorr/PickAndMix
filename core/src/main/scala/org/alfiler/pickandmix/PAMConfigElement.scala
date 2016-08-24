package org.alfiler.pickandmix

import com.typesafe.config.Config

/**
  * Created by Alfonso on 24/08/2016.
  */

trait PAMConfigElementConstructor[T] {
  val CONFIG_TAG:String = "type"
  val ELEMENT_TYPE_TAG:String
  val ELEMENT_TYPE_TAG_IN_LOWERCASE:String = ELEMENT_TYPE_TAG.toLowerCase
  val apply: PartialFunction[Config, T] = {case c:Config if c.getString(CONFIG_TAG) == ELEMENT_TYPE_TAG_IN_LOWERCASE =>factoryFromConfiguration(c)}
  def factoryFromConfiguration(config:Config):T
  def configExample:String
}
