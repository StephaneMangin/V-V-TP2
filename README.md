# V-V-TP2

## Question 1 : Graphe de dépendances

Situé dans src/main/resources

## Question 2 : Plan de test

Au vu du graphe de dépendances, nous allons simuler le comportement de `Pawn` dans les tests de `Board`. C'est la seule dépendance cyclique trouvée.

## Question 3 : 

Les classes à mockées sont respectivement, `Board` pour le test des use case d'un `Game` dans l'état game-over et `Pawn` pour le test de la méthode maxGold de la classe `Board`.
La raison est le besoin de limitation des dépendances entre classes, ne connaissant pas l'ordre d'exécution des tests.

