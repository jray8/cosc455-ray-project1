package edu.towson.CIS.COSC455.jray8.project1

import scala.collection.mutable.ListBuffer

class MySyntaxAnalyzer extends SyntaxAnalyzer {

  var errorFound : Boolean = false
  def setError() = errorFound = true
  def resetError() = errorFound = false
  def getError : Boolean = errorFound

  var list = new ListBuffer[String]()

  override def gittex(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
        list += Compiler.currentToken
        Compiler.Scanner.getNextToken()
        title()
        body()
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
          list += Compiler.currentToken
          Compiler.Scanner.getNextToken()
        }
        else {
          println("SYNTAX ERROR - End of the document was expected when '" + Compiler.currentToken + "' was found.")
          setError()
        }
      }
      else {
        println("SYNTAX ERROR - Variable definition was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("Error")
      setError()
    }
  }

  override def paragraph(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      variableDefine()
      innerText()
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
        list += Compiler.currentToken
      }
      else {
        println("SYNTAX ERROR - End of a paragraph was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - Beginning of a paragraph was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }


  }

  override def innerItem(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      variableUse()
      innerItem()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      bold()
      innerItem()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
      link()
      innerItem()
    }
    else if (Compiler.currentToken.equals(CONSTANTS.letters)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      innerItem()
    }
  }

  override def innerText(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      variableUse()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
      heading()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      bold()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
      listItem()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
      image()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
      link()
      innerText()
    }

    return innerText()

  }

  override def link(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)){
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)) {
        list += Compiler.currentToken
        Compiler.Scanner.getNextToken()
        if (Compiler.currentToken.equals(CONSTANTS.BRACKETE)) {
          list += Compiler.currentToken
          Compiler.Scanner.getNextToken()
          if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB)) {
            list += Compiler.currentToken
            Compiler.Scanner.getNextToken()
            if (Compiler.currentToken.equals(CONSTANTS.letters)) {
              list += Compiler.currentToken
              Compiler.Scanner.getNextToken()
              if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSE)) {
                list += Compiler.currentToken
                Compiler.Scanner.getNextToken()
              }
              else {
                println("SYNTAX ERROR - A parenthesis was expected when '" + Compiler.currentToken + "' was found.")
                setError()
              }
            }
            else {
              println("SYNTAX ERROR - Letters were expected when '" + Compiler.currentToken + "' was found.")
              setError()
            }
          }
          else {
            println("SYNTAX ERROR - A parenthesis was expected when '" + Compiler.currentToken + "' was found.")
            setError()
          }
        }
        else {
          println("SYNTAX ERROR - A bracket was expected when'" + Compiler.currentToken + "' was found.")
        }
      }
      else {
        println("SYNTAX ERROR - Letters were expected when '" + Compiler.currentToken + "' was found.")
      }
    }
    else {
      println("SYNTAX ERROR - A link was expected when '" + Compiler.currentToken + "' was found.")
      System.exit(1)
    }
  }

  override def body(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB))
      paragraph()
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD))
      bold()
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM))
      listItem()
    else if (Compiler.currentToken.equals(CONSTANTS.letters))
      innerText()
  }

  override def bold(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
    }
    else {
      println("SYNTAX ERROR - An asterisk  was expected when '" + Compiler.currentToken + "' was found.")
      System.exit(1)
    }
  }

  override def newline(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
      list += Compiler.currentToken
    }
    else {
      println("SYNTAX ERROR - A new line was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }
  }

  override def title(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)){
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)) {
        list += Compiler.currentToken
        Compiler.Scanner.getNextToken()
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)) {
          list += Compiler.currentToken
          Compiler.Scanner.getNextToken()
        }
        else {
          println("SYNTAX ERROR - A bracket was expected when '" + Compiler.currentToken + "' was found.")
          setError()
        }
      }
      else {
        println("SYNTAX ERROR - Text was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - A title was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }

  }

  override def variableDefine(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)){
      list+= Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)) {
        list+= Compiler.currentToken
        Compiler.Scanner.getNextToken()
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.EQSIGN)) {
          list+= Compiler.currentToken
          Compiler.Scanner.getNextToken()
          if (Compiler.currentToken.equals(CONSTANTS.letters)) {
            list+=Compiler.currentToken
            Compiler.Scanner.getNextToken()
            if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)) {
              list+=Compiler.currentToken
              Compiler.Scanner.getNextToken()
            }
          }
          else {
            println("SYNTAX ERROR - Text was expected when '" + Compiler.currentToken + "' was found.")
            setError()
          }
        }
        else {
          println("SYNTAX ERROR - An equals sign was expected when '" + Compiler.currentToken + "' was found.")
          setError()
        }
      }
      else {
        println("SYNTAX ERROR - Text was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - A variable definition was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }
  }

  override def image(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)){
        list += Compiler.currentToken
        Compiler.Scanner.getNextToken()
        if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)){
          list += Compiler.currentToken
          Compiler.Scanner.getNextToken()
          if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB)) {
            list += Compiler.currentToken
            Compiler.Scanner.getNextToken()
            if (Compiler.currentToken.equals(CONSTANTS.letters)) {
              list += Compiler.currentToken
              Compiler.Scanner.getNextToken()
              if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSE)) {
                list += Compiler.currentToken
                Compiler.Scanner.getNextToken()
              }
              else {
                println("SYNTAX ERROR - A parenthesis was expected when '" + Compiler.currentToken + "' was found.")
                setError()
              }
            }
            else {
              println("SYNTAX ERROR - Letters were expected when '" + Compiler.currentToken + "' was found.")
              setError()
            }
          }
          else
            println("SYNTAX ERROR - A parenthesis was expected when '" + Compiler.currentToken + "' was found.")
            setError()
        }
        else
          println("SYNTAX ERROR - A end bracket was expected when '" + Compiler.currentToken + "' was found.")
          setError()
      }
      else {
        println("SYNTAX ERROR - Letters were expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - A exclamation point was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }
  }

  override def variableUse(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)) {
        list += Compiler.currentToken

      }
      else {
        println("SYNTAX ERROR - Text was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - Use tag was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }
  }

  override def heading(): Unit = {
    if (Compiler.currentToken.equals(CONSTANTS.HEADING)){
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
      if (Compiler.currentToken.equals(CONSTANTS.letters)) {
        list += Compiler.currentToken
      }
      else {
        println("SYNTAX ERROR - Text was expected when '" + Compiler.currentToken + "' was found.")
        setError()
      }
    }
    else {
      println("SYNTAX ERROR - A hashtag was expected when '" + Compiler.currentToken + "' was found.")
      setError()
    }
  }

  override def listItem(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
      list += Compiler.currentToken
      Compiler.Scanner.getNextToken()
    }
    else {
      println("SYNTAX ERROR - A plus sign was expected when ' " + Compiler.currentToken + " ' was found.")
      setError()
    }
  }
}
