package fileHandling

class FileHandler:
  val bankDocParser = new BankDocumentParser("textFiles/userBusinesses.txt")
  val transactionsFileName = "textFiles/userTransactions.txt"

  // reads bank file using a private attribute bankDockParser, uses fuction
  // writeTransaction() to do so.
  def loadAndStoreBankFile() = ???


  private def writeTransaction(transaction: String) = ???