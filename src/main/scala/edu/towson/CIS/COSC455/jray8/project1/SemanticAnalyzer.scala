package edu.towson.CIS.COSC455.jray8.project1

import scala.collection.mutable.Stack

class SemanticAnalyzer {

  val BEGINBLOCK : List[String] = List("\BEGIN", "\PARAB", "\TITLEB", "\LINKB", "\LISTITEMB", "\IMAGEB")
  val ENDBLOCK : List[String] = List("\END", "\PARAE", "\BRACKETE", "\ADDRESSE")
  val NONBLOCK : List[String] = List("REQTEXT", "HEADING", "EQSIGN", "BOLD", "NEWLINE")
  var list = Stack[String]()
  var c = Compiler.currentToken

  c match {
   case CONSTANTS.letters => list.push(c)
   case NONBLOCK => list.push(c)
   case BEGINBLOCK => list.push(c)
   case ENDBLOCK => list.pop
   case CONSTANTS.DEFB => list.push(c)
   case CONSTANTS.USEB =>
  }






}
