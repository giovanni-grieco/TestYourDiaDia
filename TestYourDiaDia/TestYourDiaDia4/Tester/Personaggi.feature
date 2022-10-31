#Controllo delle funzioni dei personaggi
Feature: Verifica funzionalita personaggi
	
	#Il mago dovrebbe ridare l'attrezzo regalato al giocatore e dimezzarne il peso
	@TestMagoRegalo
	Scenario: Test funzionalita regalo mago
		Given un monolocale con personaggio "Mago" e con un attrezzo "spada" di peso "4"
		And inserisco il comando "guarda" in coda
		And inserisco il comando "prendi spada" in coda
		And inserisco il comando "regala spada" in coda
		And inserisco il comando "guarda" in coda
		And il gioco viene avviato
		Then verifico che il personaggio "Mago" posi nella stanza "spada" di peso "2"
	
	#Dovrebbe regalare un attrezzo specificato
	@TestMagoRegalo
	Scenario: Test funzionalita interazione mago
		Given un monolocale con personaggio "Mago" che posi nella stanza "chiave" di peso "2" dopo averci interagito
		And inserisco il comando "interagisci" in coda
		And inserisco il comando "guarda" in coda
		And il gioco viene avviato
		Then verifico che il personaggio "Mago" posi nella stanza "chiave" di peso "2"
	
	#La strega se non salutata dovrebbe teletrasportarti nella stanza adiacente con meno oggetti all'interno
	@TestStregaInterazioneNonSalutata
	Scenario: Test funzionalita interazione strega non salutata
		Given un trilocale con personaggio Strega con stanze adiacenti con numeri diversi di attrezzi
		And inserisco il comando "guarda" in coda
		And inserisco il comando "interagisci" in coda
		And inserisco il comando "guarda" in coda
		And il gioco viene avviato
		Then verifico lo spostamento da "stanza con strega" a "zero attrezzi" verso "nord"
		
	#La strega se non salutata dovrebbe teletrasportarti nella stanza adiacente con piu' oggetti all'interno
	@TestStregaInterazioneSalutata
	Scenario: Test funzionalita interazione strega salutata
		Given un trilocale con personaggio Strega con stanze adiacenti con numeri diversi di attrezzi
		And inserisco il comando "saluta" in coda
		And inserisco il comando "guarda" in coda
		And inserisco il comando "interagisci" in coda
		And inserisco il comando "guarda" in coda
		And il gioco viene avviato
		Then verifico lo spostamento da "stanza con strega" a "un attrezzo" verso "est"
		
	#se si interagisce con il cane, dovrebbe levarti CFU e portare alla morte del personaggio
	@TestCaneInterazioneAttacco
	Scenario: Test funzionalita di attacco del cane
		Given un monolocale con personaggio "Cane" 
		And inserisco il comando "interagisci" in coda per "21" volte
		And il gioco viene avviato
		Then viene mostrato il messaggio di morte
		And il gioco si chiude
		
	#dando qualcosa al cane, lui in cambio svela un oggetto
	@TestCaneRegalo
	Scenario: Test di funzionalita regalo al cane
		Given un monolocale con personaggio "Cane" a cui piace il "biscotto" e in cambio da una "spada" di peso "2"
		And inserisco il comando "prendi biscotto" in coda
		And inserisco il comando "regala biscotto" in coda
		And il gioco viene avviato 
		Then verifico che il personaggio "Cane" posi nella stanza "spada" di peso "2"