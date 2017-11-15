package edu.towson.CIS.COSC455.jray8.project1

import scala.collection.mutable.ListBuffer

class MyLexicalAnalyzer extends LexicalAnalyzer{

  var currentToken : String = ""
  var nextChar : Char = ' '
  var sourceLine : String = ""
  var position : Int = 0
  val lexeme = new ListBuffer[String]()

  override def start(): Unit = {
    initializeLexemes()

    getChar()
    getNextToken()
  }

  override def addChar(): Unit = {
    currentToken += nextChar
  }

  override def lookup(): Boolean = {
    if (!lexeme.contains(currentToken)) {
      Compiler.Parser.setError()
      println("LEXICAL ERROR - '" + currentToken +  "' is not recognized.")
      false
    }
    else
      true
  }

  override def initializeLexemes(): Unit = {
    lexeme += "\\BEGIN"
    lexeme += "\\END"
    lexeme += "\\TITLE["
    lexeme += "]"
    lexeme += "#"
    lexeme += "\\PARAB"
    lexeme += "\\PARAE"
    lexeme += "*"
    lexeme += "+"
    lexeme += "\\"
    lexeme += "["
    lexeme += "("
    lexeme += ")"
    lexeme += "!["
    lexeme += "\\DEF"
    lexeme += "="
    lexeme += "\\USE"
  }

  override def getNextToken(): Unit = {
    val c  = getChar()

    while (c.equals(' ') || c.equals('\t') || c.equals('\n'))
        getChar()

    c match {
      case '!' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
      case '#' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
      case '[' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
      case '\\' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
      case '+' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
      case '(' =>
        {
          if (lookup().equals(true))
            currentToken += c
          else
            System.exit(1)
        }
    }

    while (c.equals(Char)) {
      if (lookup().equals(true))
        currentToken += c
      else
        System.exit(1)
    }

  }

  override def getChar(): Char = {
    if (position < sourceLine.length) {
      sourceLine.charAt({position+=1; position -1})
    }
    else
      '\n'
  }

}
