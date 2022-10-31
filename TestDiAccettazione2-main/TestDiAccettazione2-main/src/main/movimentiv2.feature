#Controllo del corretto spostamento tra stanze (comando Vai)
Feature: Verifica funzionalità dello spostamento

	@TestVaiNord
  Scenario: verifica che in un labirito formato da due stanze nord/sud ci si possa spostare 
   Given inserisco il comando "guarda" in coda
   And inserisco il comando "vai nord" in coda
   And inserisco il comando "guarda" in coda
   And il gioco viene avviato
   Then verifico lo spostamento da "Atrio" a "Biblioteca" verso "nord"
   And hai vinto la partita
   
  @TestVaiEst
  Scenario: verifica che in un labirito formato da due stanze nord/sud ci si possa spostare 
   Given inserisco il comando "guarda" in coda
   And inserisco il comando "vai est" in coda
   And inserisco il comando "guarda" in coda
   And il gioco viene avviato
   Then verifico lo spostamento da "Atrio" a "Aula N11" verso "est"