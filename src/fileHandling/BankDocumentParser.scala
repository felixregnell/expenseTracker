package fileHandling

export scala.collection.mutable.ArrayBuffer as MutableArray
export collection.mutable.Map as MutableMap

class BankDocumentParser(private val userBusinessesFile: String):
  private val userBusinesses = loadUserBusinesses(userBusinessesFile)
  private def loadUserBusinesses(businessesFileName: String): MutableMap[String, String] = ???

  // Returns a map mapping store/business to total costs in specified document.
  // If store/business doesn't already exist in the businessDataBank it asks
  // the user to fill in said business using `getUserDefinedName()` before saving
  // the transaction.
  def parseDocument(fileName: String): 
    Map[String, Vector[(String, Double)]] = 
    val transactions = MutableMap[String, MutableArray[(String, Double)]]() // Company name -> Vector(date, amount)

    val lines: List[String] = scala.io.Source
      .fromFile(fileName)
      .getLines()
      .toList
      .map(line => line.drop(1).dropRight(1)) // Strings sourrounding "" removed

    val transactionsStartingIndex = lines.indexOf("Kontohändelser")
    val bankAccountInformation = lines.take(transactionsStartingIndex - 1).filterNot(_ == "")
    val transactionList = lines
      .drop(transactionsStartingIndex+ 2)
      .dropRight(1)

    transactionList.foreach(transactionString =>
      val columns = transactionString
        .split("     ")
        .toList.filterNot(_ == "")

      val date = columns(0).trim()
      val business = columns(1).trim()
      val balanceAfterTransaction = columns(2).trim() // Currently not used, could be used to ensure uniqeness
      val amount = pareseCurrencyToSEK(
        currency = columns(4).trim(), 
        amount = columns(3).trim()
        )
      saveTransaction(transactions, business, date, amount)
      )
    transactions.map((key, value) => (key, value.toVector)).toMap
 
  private def saveTransaction(
    transactions: MutableMap[String, MutableArray[(String, Double)]],
    business: String,
    date: String,
    amount: Double,
    ): Unit = 
      val userDefinedName = getUserDefinedName(bankBusinessName = business)
      val amountAtDatesOption = transactions.get(userDefinedName)
      amountAtDatesOption match
        case Some(amountAtDates) => amountAtDates.prepend((date, amount))
        case None => transactions.put(userDefinedName, MutableArray((date, amount)))

  private def getUserDefinedName(bankBusinessName: String): String = 
    val userDefinedNameOption = userBusinesses.get(bankBusinessName)
      userDefinedNameOption match
        case Some(userDefinedName) => userDefinedName
        case None => 
          val userDefinedName = 
            scala.io.StdIn.readLine("The business " + '"' + bankBusinessName + 
            '"' + " doesn't exist in the userdefined namespace, define it:" + '\n')
          userBusinesses.put(bankBusinessName, userDefinedName)
          userDefinedName
  
  private def pareseCurrencyToSEK(currency: String, amount: String): Double = 
    val currencies: Map[String, Double] = Map(
      "SEK"-> 1,
      "USD"-> 10.3577,
      "EUR"-> 11.2850,
    )

    val scaler: Double = currencies.getOrElse(currency, 1.0)
    
    val parseResult: scala.util.Try[Double] = scala.util.Try {
      amount.toDouble
    }
    parseResult match
      case scala.util.Failure(exception) => 
        println("The amount-string isn't a valid number, 0 added")
        0
      case scala.util.Success(amount) => amount*scaler
    
    
