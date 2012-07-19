package tu.coreservice.spellcorrector

import org.xeustechnologies.googleapi.spelling.{SpellRequest, Language, SpellChecker, Configuration}
import tu.coreservice.utilities.Configurator
import tu.model.knowledge.communication.Context


/**
 * @author toschev alex
 *         Date: 28.05.12
 *         Time: 20:52
 */

class SpellCorrectorGoogle extends SpellCorrector {
  /**
   * correct sentence
   * @param inputSentence sentence that should be corrected
   */
  def correctSentence(inputSentence: String): String = {

    var corrected = new StringBuffer(inputSentence)

    var config = new Configuration();

    if (Configurator.proxyAddress().useProxy)
      config.setProxy(Configurator.proxyAddress().proxyHost, Configurator.proxyAddress().proxyPort, Configurator.proxyAddress().proxyType);

    var checker = new SpellChecker(config);
    checker.setOverHttps(true); // Use https (default true from v1.1)
    checker.setLanguage(Language.ENGLISH); // Use English (default)

    var request = new SpellRequest();
    request.setText(inputSentence);
    request.setIgnoreDuplicates(true); // Ignore duplicates

    var spellResponse = checker.check(request);

    var offsetDelta = 0;

    if (spellResponse.getCorrections != null) {

      spellResponse.getCorrections.foreach(c => {
        //perform correction only of confidence 1
        if (c.getConfidence == 1) {

          //extract source word
          var word = corrected.substring(c.getOffset() + offsetDelta, c.getOffset + offsetDelta + c.getLength)

          if (!ifWordIsReserved(word)) {
            // get words seems to be do not working correctly, replace functionality with \t separating
            var toReplace = c.getValue.split("\t").head;

            //clean up wrong word and replace by proper
            corrected.replace(c.getOffset() + offsetDelta, c.getOffset + offsetDelta + c.getLength, toReplace)
            offsetDelta = offsetDelta + toReplace.length() - c.getLength
          }
        }
      })
    }
    return corrected.toString
  }

  def start() = false

  def stop() = false

  /**
   * Way2Think interface.
   * @param inputContext Context of all inbound parameters.
   * @return outputContext
   */
  def apply(inputContext: Context) = null
}
