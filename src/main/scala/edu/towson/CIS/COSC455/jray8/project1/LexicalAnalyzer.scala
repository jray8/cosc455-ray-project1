package edu.towson.CIS.COSC455.jray8.project1

trait LexicalAnalyzer {
  def start(): Unit
  def addChar() : Unit
  def getChar() : Char
  def getNextToken() : Unit
  def lookup() : Boolean
  def initializeLexemes(): Unit
}
