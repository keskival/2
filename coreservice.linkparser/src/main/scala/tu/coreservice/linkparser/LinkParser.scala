package tu.coreservice.linkparser

import tu.coreservice.action.way2think.Way2Think
import tu.model.knowledge.communication.{ContextHelper, Context}
import tu.model.knowledge.Resource
import tu.model.knowledge.annotator.AnnotatedNarrative
import tu.coreservice.action.way2think.cry4help.Cry4HelpWay2Think
import tu.coreservice.action.UnexpectedException

/**
 * @author max talanov
 *        Date: 7/31/12
 *        Time: 4:18 AM
 */

class LinkParser extends Way2Think{
  def start() = false

  def stop() = false

  /**
   * Way2Think interface.
   * @param inputContext Context of all inbound parameters.
   * @return outputContext
   */
  def apply(inputContext: Context): Context = {

    val narrative = getLastResult(inputContext)
    val sentences = narrative.sentences
    val text = narrative.text

    val outputContext = ContextHelper(List[Resource](), this.getClass.getName)
    outputContext
  }

  def getLastResult(inputContext: Context): AnnotatedNarrative = {
    try {
      val narrative: AnnotatedNarrative = inputContext.lastResult.asInstanceOf[AnnotatedNarrative]
      narrative
    } catch {
      case e: ClassCastException => {
        val cry4Help = Cry4HelpWay2Think("$Context_lastResult_is_not_expectedType " + e.getMessage)
        // throw new UnexpectedException("$Context_lastResult_is_not_expectedType " + e.getMessage)
        ContextHelper(List[Resource](cry4Help), cry4Help, this.getClass.getName + " result")
        throw new UnexpectedException("$Context_lastResult_is_not_expectedType " + e.getMessage)
      }
    }
  }
}
