package tu.model.knowledge.domain

import tu.model.knowledge._
import tu.model.knowledge.annotator.AnnotatedPhrase
import tu.exception.UnexpectedException
import org.slf4j.LoggerFactory

/**
 * @author max
 *         Date: 8/30/12
 *         Time: 7:54 AM
 */
class ConceptTag(val _content: TypedKLine[Concept],
                 var __links: List[ConceptLink],
                 val _uri: KnowledgeURI,
                 val _probability: Probability = new Probability())
  extends Resource(_uri, _probability) {

  def links = __links

  def this(map: Map[String, String]) = {
    this(
      TypedKLine[Concept](Constant.CONTENT),
      List[ConceptLink](),
      new KnowledgeURI(map),
      new Probability(map)
    )
  }

}

object ConceptTag {

  val log = LoggerFactory.getLogger(this.getClass)

  def load(kb: KB, parentId: KBNodeId, key: String, linkType: String): ConceptTag = {
    val selfMap = kb.loadChild(parentId, key, linkType)
    if (selfMap.isEmpty) {
      log.error("Concept not loaded for link {}/{} for {}", List(key, linkType, parentId.toString))
      throw new UnexpectedException("Concept not loaded for link " + key + "/" + linkType + " for " + parentId.toString)
    }

    val ID = new KBNodeId(selfMap)

    def oneList(items: Map[String, Map[String, String]]): Map[KnowledgeURI, Concept] = {
      items.keys.foldLeft(Map[KnowledgeURI, Concept]()) {
        (acc, uri) => acc + Pair(KnowledgeURI(uri,true), new Concept(items(uri)))
      }
    }

    def oneListPhrases(items: Map[String, Map[String, String]]): Map[KnowledgeURI, AnnotatedPhrase] = {
      items.keys.foldLeft(Map[KnowledgeURI, AnnotatedPhrase]()) {
        (acc, uri) => acc + Pair(KnowledgeURI(uri,true), AnnotatedPhrase.load(kb, ID, uri, Constant.SENTENCES_LINK_NAME))
      }
    }

    val content =
      TypedKLine[Concept](
        Constant.CONTENT,
        oneList(kb.loadChildrenMap(ID, Constant.GENERALISATION_LINK_NAME))
      )

    val linksSourceMap = kb.loadChildrenMap(ID, Constant.CONCEPT_LINK_SOURCE_NAME)
    val linksDestinationMap = kb.loadChildrenMap(ID, Constant.CONCEPT_LINK_SOURCE_NAME)
    val conceptLinkList: List[ConceptLink] =
      linksSourceMap.keys.foldLeft(List[ConceptLink]()) {
        (acc, uri) => ConceptLink(new Concept(linksSourceMap(uri)), new Concept(linksDestinationMap(uri)), uri) :: acc
      }

    new ConceptTag(content,
      conceptLinkList,
      new KnowledgeURI(selfMap),
      new Probability(selfMap)
    )
  }
}
