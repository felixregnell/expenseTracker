package fileHandling

class TransactionFileHandler:
  private val bankDocParser = new BankDocumentParser("textFiles/userBusinesses.txt")
  private val transactionsFileName = "textFiles/userTransactions.txt"

  // loads/reads bank file using a private attribute bankDockParser, uses fuction
  // storeTransaction() store.
  def loadAndStoreTransactions(fileName: String) = 
    val newTransactions: Map[String, Vector[(String, Double)]] = 
      bankDocParser.parseDocument(fileName)

    val oldTransactions = ??? // Read from file specified by trasactionsFileName

    val combinedTransactions = ??? // new- and oldTransactions concatenated, sorting them if necessary.
                                   // Old transactions can be assumed sorted
                                   
    // Then combinedTransactions is written to file using storeTransactions
    
  private def storeTransactions(): Unit = ???