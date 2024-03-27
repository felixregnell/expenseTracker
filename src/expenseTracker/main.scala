import fileHandling.TransactionFileHandler

@main def main() = 
  val fileHandler = TransactionFileHandler() 
  fileHandler.loadAndStoreTransactions("textFiles/exportera.txt")