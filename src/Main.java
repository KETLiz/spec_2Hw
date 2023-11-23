public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.creatMap();
        int turn = game.whoFirst();
        if(turn == 1) {
           while(true) {
               game.humanStep();
               game.printMap();
               if(game.isVictory(game.DOT_HUMAN)) {
                   System.out.print("Вы выиграли! Ура!");
                   break;
               }
               game.aiStep();
               game.printMap();
               if(game.isVictory(game.DOT_AI)) {
                   System.out.print("Выиграл компьютер :(");
                   break;
               }
           }
        }
        if(turn == 0) {
            while(true) {
                game.aiStep();
                game.printMap();
                if(game.isVictory(game.DOT_AI)) {
                    System.out.print("Выиграл компьютер :(");
                    break;
                }
                game.humanStep();
                game.printMap();
                if(game.isVictory(game.DOT_HUMAN)) {
                    System.out.print("Вы выиграли! Ура!");
                    break;
                }
            }
        }


    }
}