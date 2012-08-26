package tu.model.knowledge.primitive

import tu.model.knowledge.{Probability, KnowledgeURI, Resource}


/**
 * @author max
 *         date 2012-05-01
 *         time: 7:46 AM
 */

class Number(_value: Double, _uri: KnowledgeURI, _probability: Probability)
  extends Resource(_uri, _probability) {

  def value: Double = _value

  def this(map: Map[String, String]) = {
    this(map.get("value") match {
      case Some(x) => x.toDouble
      case None => new Double
    },
      new KnowledgeURI(map), new Probability(map))
  }

  override def export: Map[String, String] = {
    Map("value" -> this.value.toString) ++ uri.export ++ probability.export
  }
}
