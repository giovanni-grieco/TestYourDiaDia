#scenario di testing che verifica il funzionamento dei comandi "prendi" e "posa"
Feature: test di prendi e posa

	@testPrendiEPosa
	Scenario: monolocale con attrezzo 
		Given inserisco il comando "guarda" in coda
		And inserisco il comando "prendi osso" in coda
		And inserisco il comando "guarda" in coda
		And inserisco il comando "posa osso" in coda
		And inserisco il comando "guarda" in coda
		And il gioco viene avviato 
		Then controllo che "osso" viene preso e posato
