# language: nl
@Test @RGM
Functionaliteit: Testen zoekmachine


  Scenario: 1 Open google.nl
    Gegeven open browser
    Als de gebruiker google opent en zoekt op "selenium"
    Dan is de eerste hit "selenium"


  Scenario: 2 Open google.nl
    Gegeven open browser
    Als de gebruiker google opent en zoekt op "selenium"
    Dan is de eerste hit "selenium"
