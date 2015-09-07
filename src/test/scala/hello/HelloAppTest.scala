package hello

import utest._

import org.scalajs.dom.{html, document}
import org.scalajs.jquery.jQuery

object HelloAppTest extends TestSuite {

  import HelloApp._

  def setup() = {
    val container = getContainer
    container.innerHTML = ""
    setupHtml(container)
  }

  override def tests = TestSuite{
    "hello says hello" - {
      assert(hello == "Hello world!")
    }
    "IDs are correct" - {
      assert(outputId == "output")
      assert(buttonId == "click-me-button")
    }
    "output exists" - {
      setup()
      assert(document.getElementById(outputId) != null)
    }
    "outputTxt works" - {
      assert(outputTxt(0) == "not clicked yet")
      for (c <- 1 to 5) {
        assert(outputTxt(c) == s"clicked ($c)")
      }
    }
    "button exists" - {
      setup()
      assert(document.getElementById(buttonId) != null)
    }
    "button works (without jQuery)" - {
      setup()
      def clickCountIs(n: Int): Boolean =
        document.getElementById(outputId).textContent == outputTxt(n)

      val button = document.getElementById(buttonId).asInstanceOf[html.Button]
      assert(button != null)
      assert(clickCountIs(0))

      for (c <- 1 to 5) {
        button.click()
        assert(clickCountIs(c))
      }
    }
    "button works (with jQuery)" - {
      setup()
      def clickCountIs(n: Int): Boolean =
        jQuery(s"textarea#${outputId}").text() == outputTxt(n)

      val button = jQuery(s":button#${buttonId}")
      assert(button.length == 1)
      assert(clickCountIs(0))

      for (c <- 1 to 5) {
        button.click()
        assert(clickCountIs(c))
      }
    }
  }
}