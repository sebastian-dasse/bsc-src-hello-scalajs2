package hello

import org.scalajs.dom
import org.scalajs.dom.{document, html}
import org.scalajs.dom.raw.MouseEvent

import scala.scalajs.js.JSApp
import scala.util.Random

object HelloApp extends JSApp {
  def main(): Unit = {
    println(hello)
    setupHtml(getContainer)
  }

  def hello: String = "Hello world!"

  def getContainer: html.Div = {
    def grabContainer = Option(document.getElementById("container"))
    def createContainer = {
      val c = document.createElement("div")
      c.id = "container"
      document.body.appendChild(c)
    }
    grabContainer.getOrElse(createContainer)
  }.asInstanceOf[html.Div]

  /// can create only <p> elements
  def appendPar(targetNode: dom.Node, text: String): html.Paragraph = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
    parNode.asInstanceOf[html.Paragraph]
  }

  /// can create any kind of element, thus also non-valid tags
  def appendElement(targetNode: dom.Node, tagName: String, text: String): html.Element = {
    val elemNode = document.createElement(tagName)
    val textNode = document.createTextNode(text)
    elemNode.appendChild(textNode)
    targetNode.appendChild(elemNode)
    elemNode.asInstanceOf[html.Element]
  }

  def createElement(tagName: String, text: String): html.Element = {
    val elemNode = document.createElement(tagName)
    val textNode = document.createTextNode(text)
    elemNode.appendChild(textNode)
    elemNode.asInstanceOf[html.Element]
  }

  def createButton(id: String = s"btn-${Random.nextString(5)}",
                   name: String = "Button",
                   onclick: MouseEvent => Unit = e => dom.console.log(e)): html.Button = {
    val btn = document.createElement("button").asInstanceOf[html.Button]
    btn.id = id
    btn.textContent = name
    btn.onclick = onclick
    btn
  }

  def div(elems: dom.Element*): dom.Node = {
    val div = document.createElement("div").asInstanceOf[html.Div]
    for (elem <- elems)
      div.appendChild(elem)
    div
  }

  val outputId = "output"
  val buttonId = "click-me-button"

  def outputTxt(n: Int): String = n match {
    case 0 => "not clicked yet"
    case _ => s"clicked ($n)"
  }

  def setupHtml(container: dom.Node): Unit = {
    appendPar(container, "Hello world!")
    appendElement(container, "p", "Hello world!")
    appendElement(container, "h1", "Hello world!")
    appendElement(container, "xyz", "Hello world!") // not a valid tag


    val output = createElement("textarea", outputTxt(0))
    output.textContent = outputTxt(0)
    output.id = outputId
    container.appendChild(div(output))

    var clickCount = 0

    def onclick = (e: MouseEvent) => {
      clickCount += 1
      def msg = outputTxt(clickCount)
      println(msg)
      output.textContent = msg
    }

    container.appendChild(
      div(
        createButton(buttonId, "Click me!", onclick),
        createButton()
      )
    )

  }
}