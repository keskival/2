package tu.coreservice.utilities

import java.net.URI

/**
 * Created by IntelliJ IDEA.
 * User: toscheva
 * Date: 06.06.12
 * Time: 10:50
 *
 */

/**
 * Contain helpers to work with URI
 */
object URIHelper {

  /**
   * Check if URI contain specific tag
   * @param tag tagName
   * @param uri target URI
   * @return true if uri contains tag
   */
  def isContainTag(tag: String, uri: URI): Boolean = {
    return uri.toString.contains(tag)
  }

  /**
   * generate empty uri
   * @return  empty uri
   */
  def emptyURI() = "http://tu-project.com/knowledge/empty"
}