package tu.coreservice.splitter

import _root_.relex.corpus.{DocSplitterFactory, DocSplitter}
import tu.coreservice.action.way2think.Way2Think
import tu.model.knowledge.frame.TransFrame
import tu.model.knowledge.primitive.KnowledgeString
import tu.model.knowledge.communication.{ContextHelper, Context}
import tu.model.knowledge.{KnowledgeURI, Resource}
import tu.coreservice.spellcorrector.SpellCorrectorFactory

/**
 * Created by IntelliJ IDEA.
 * User: toscheva
 * Date: 01.06.12
 * Time: 18:59
 *
 */

/**
 * split text in sentence
 * https://github.com/development-team/2/blob/master/doc/design-specification/splitting-text-to-words.md
 */
class PreliminarySplitter extends Way2Think {

  /**
   * apply way 2 think
   * @param inputContext
   * @param outputContext
   * @return split in sentence text
   */
  def apply(inputContext: Context, outputContext: Context): TransFrame[Resource] = {
    //extract text from input context
    var textFrame = inputContext.frames.filter(p =>

      p._1.name == "inputtext"

    ).head

    //initialize output context
    ContextHelper.initializeContext(outputContext);

    var sentenceURI = new KnowledgeURI("tu-project.com", "sentence", "0.3")


    // split text using relex
    var ds: DocSplitter = DocSplitterFactory.create()
    ds.addText(textFrame._2.asInstanceOf[KnowledgeString].value)

    var sntOrder=1

    var sentence = ds.getNextSentence
    while (sentence != null) {
      //check sentence using autocorrector
      var corrector=SpellCorrectorFactory.construct()
      //append extracted sentence to context and increase counter for sentence
      outputContext.frames += (new KnowledgeURI("tu-project.com",sentenceURI.name+"-"+sntOrder,"0.3")-> new KnowledgeString(corrector.correctSentence(sentence), sentenceURI))
      sntOrder=sntOrder+1
      sentence = ds.getNextSentence
    }


    return null
  }
}
