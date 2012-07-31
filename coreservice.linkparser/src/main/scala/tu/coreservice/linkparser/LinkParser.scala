package tu.coreservice.linkparser

import tu.coreservice.action.way2think.Way2Think
import tu.model.knowledge.communication.Context

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
  def apply(inputContext: Context) = null
}
