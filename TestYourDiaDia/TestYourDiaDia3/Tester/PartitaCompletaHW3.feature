Feature: partita completa HW3
#simula una partita completa
  @testBuilder
  Scenario: Labirinto bilocale
  	Given carico un labirinto completo per testare una partita
  	Given inserisco il comando "guarda" in coda
  	And inserisco il comando "prendi aicrot" in coda
   	And inserisco il comando "vai est" in coda
   	And inserisco il comando "guarda" in coda
   	And inserisco il comando "vai ovest" in coda
   	And inserisco il comando "vai nord" in coda
   	And inserisco il comando "guarda" in coda
   	And inserisco il comando "vai sud" in coda
   	And inserisco il comando "vai sud" in coda
   	And inserisco il comando "posa aicrot" in coda
   	And inserisco il comando "prendi aicrot" in coda
   	And inserisco il comando "posa aicrot" in coda
   	And inserisco il comando "prendi aicrot" in coda
   	And inserisco il comando "posa aicrot" in coda
   	And inserisco il comando "prendi torcia" in coda
   	And inserisco il comando "vai nord" in coda
   	And inserisco il comando "vai est" in coda
   	And inserisco il comando "posa torcia" in coda
   	And inserisco il comando "prendi chiave" in coda
   	And inserisco il comando "vai ovest" in coda
   	And inserisco il comando "vai nord" in coda
   	And inserisco il comando "posa chiave" in coda
   	And inserisco il comando "vai ovest" in coda
   	And il gioco viene avviato
   	Then hai vinto la partita