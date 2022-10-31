Feature: partita completa HW4
#simula una partita completa
  @testBuilder
  Scenario: Labirinto completo
  	Given carico un labirinto completo per testare una partita HW4
  	And inserisco il comando "prendi torcia" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "saluta mago" in coda
  	And inserisco il comando "interagisci" in coda
  	And inserisco il comando "prendi ottocsib" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "posa ottocsib" in coda
  	And inserisco il comando "guarda" in coda
  	And inserisco il comando "prendi ottocsib" in coda
  	And inserisco il comando "posa ottocsib" in coda
  	And inserisco il comando "prendi ottocsib" in coda
  	And inserisco il comando "posa ottocsib" in coda
  	And inserisco il comando "prendi biscotto" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "regala biscotto" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "posa torcia" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "interagisci" in coda
  	And inserisco il comando "vai est" in coda
  	And inserisco il comando "posa chiave" in coda
  	And inserisco il comando "vai est" in coda
  	And il gioco viene avviato
   	Then hai vinto la partita