Feature: Iniezioni di labirinto
#Da utilizzare a partire dall'homework 3 in poi (Deve esistere la classe LabirintoBuilder)
  @testBuilder
  Scenario: Labirinto bilocale
  	Given carico un bilocale composto da "Stanza Iniziale" collegata a "nord" alla "Stanza Vincente"
  	Given inserisco il comando "guarda" in coda
   	And inserisco il comando "vai nord" in coda
   	And inserisco il comando "guarda" in coda
   	And il gioco viene avviato
   	Then verifico lo spostamento da "Stanza Iniziale" a "Stanza Vincente" verso "nord"
  	And hai vinto la partita
  	And il gioco si chiude