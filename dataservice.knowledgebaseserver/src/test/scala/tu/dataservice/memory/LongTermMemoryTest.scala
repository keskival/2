package tu.dataservice.memory

/**
 * @author max
 *         date 2012-10-20
 *         time: 8:44 PM
 */

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import tu.model.knowledge.domain.Concept
import tu.model.knowledge.KnowledgeURI
import tu.dataservice.knowledgebaseserver.Defaults

@RunWith(classOf[JUnitRunner])
class LongTermMemoryTest extends FunSuite {

  val testURIString = "test"
  val testURI = KnowledgeURI(testURIString)
  val testURI2 = KnowledgeURI("test2")

  test("test Ok") {
    assert(true)
  }

  test("merge concepts") {
    val cp = Concept("parent")
    val cc = Concept.createSubConcept(cp, "child")
    val dm = LongTermMemory.domainModel(KnowledgeURI(testURIString))
    assert(dm.nodes.size > 0)
    val c = dm.nodes(0)
    val stmc = new ShortTermMemoryConceptWrapper(cc, c)
    val resCN = LongTermMemory.merge(stmc, testURI)
    val children = resCN.nodes.filter {
      c: Concept => {
        c.generalisations.frames.filter {
          keyValue: Pair[KnowledgeURI, Concept] => {
            keyValue._2.uri.name == "parentConcept"
          }
        }.size > 0
      }
    }
    assert(children.size > 0)
  }

  test ("load domain model") {
    val dm = LongTermMemory.domainModel(testURI)
    assert(dm.nodes.size > 1)


  }
  test ("save domain model") {
    val dm = LongTermMemory.saveModel(testURI,Defaults.domainModelConceptNetwork)



  }

  test("save short term memory context to long term memory test") {
    val cp = Concept("parent")
    val cc = Concept.createSubConcept(cp, "child")
    val dm = LongTermMemory.domainModel(testURI)
    assert(dm.nodes.size > 0)
    val c = dm.nodes(0)
    val stmc = new ShortTermMemoryConceptWrapper(cc, c)
    var resCN = LongTermMemory.merge(stmc, testURI)
    LongTermMemory.saveModel(testURI2, resCN)
    //reload model and test if context is saved
    resCN = LongTermMemory.domainModel(testURI2)

    val children = resCN.nodes.filter {
      c: Concept => {
        c.generalisations.frames.filter {
          keyValue: Pair[KnowledgeURI, Concept] => {
            keyValue._2.uri.name == "parentConcept"
          }
        }.size > 0
      }
    }
    assert(children.size > 0)
  }



}
