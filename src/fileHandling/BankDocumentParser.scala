package fileHandling

import scala.io.Source

class BankDocumentParser(private val userBusinessesFile: String):
  private val userBusinesses = loadUserBusinesses(userBusinessesFile)
  private def loadUserBusinesses(businessesFileName: String): collection.mutable.Map[String, String] = ???

  // Returns a map mapping store/business to total costs in specified document.
  // If store/business doesn't already exist in the businessDataBank it asks
  // the user to fill in said business using `saveBusinessAs()`.
  def parseDoc(fileName: String): Map[String, Double] = ???

  //Saves Business in private variable `userBusinesses`
  private def saveBusinessAs(bankBusinessName: String, userDefinedName: String): Unit = ???
