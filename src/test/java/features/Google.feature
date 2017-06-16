# language: nl
@Test
Functionaliteit: Testen zoekmachine

  @RGM
  Scenario: 1 Open google.nl
    Gegeven open browser
    Als de gebruiker google opent en zoekt op "selenium"
    Dan is de eerst hit "selenium"

