import org.junit._
import Assert._
import tu.coreservice.spellcorrector.{SpellCorrectorGoogle, SpellCorrector}


@Test
class CorrectorTest {

  @Test
  def testCompoundCorrector() = {
    //Hello world test
    var corrector = SpellCorrector.apply()
    var test = corrector.correctSentence("The wrld are red")
    assertTrue(test == "The world is red")

  }

  @Test
  def testGoogle()={
    var corrector = new SpellCorrectorGoogle()
    var test = corrector.correctSentence("The wrld are red")
    assertTrue(test == "The world is red")
  }
  /*@Test
  def testWorkingSentence() = {
    //Seems to CatiaV5 aren?t installed correcly

    var corrector = SpellCorrector.apply()
    var test = corrector.correctSentence("Seems to CatiaV5 aren?t installed correcly")
    assertTrue(test == "Seems to CatiaV5 aren't installed correctly")

    test = corrector.correctSentence("user is not able to use the wirelsess function on his laptop")
    assertTrue(test == "user is not able to use the wireless function on his laptop")

  }

  @Test
  def testAdditional() = {
    //It?s a Vista-PC
    var corrector = SpellCorrector.apply()
    var test = corrector.correctSentence("It?s a Vista-PC")
    assertTrue(test == "It?s a Vista-PC")

  }     */

  //    @Test
  //    def testKO() = assertTrue(false)

}


