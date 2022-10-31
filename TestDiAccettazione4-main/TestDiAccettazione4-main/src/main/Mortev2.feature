
Feature: Il giocatore muore quando finisce i cfu

#Non abbiamo modo di testare accuratamente la feature di morte per come impostati i requisiti attualmente

  @TestMorte
  Scenario: Eseguito un grande numero di spostamenti il giocatore muore e la partita termina
    Given inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And inserisco il comando "vai est" in coda
    And inserisco il comando "vai ovest" in coda
    And il gioco viene avviato 
    Then viene mostrato il messaggio di morte
    And il gioco si chiude