import java.util.Random;
import java.util.Scanner;
public class Game {
    final char DOT_HUMAN = 'X';
    final char DOT_AI = 'O';
    final char DOT_EMPTY = '*';
    final int sizeX = 5;
    final int sizeY = 5;
    final char[][] map= new char[sizeX][sizeY];
    final int WIN_COUNT = 4;
    Random r = new Random();
    Scanner in = new Scanner(System.in);

    /**
     * Начальная инициализация карты
     */
    public void creatMap() {
        for(int x = 0; x < sizeX; x++) {
            for(int y = 0; y < sizeY; y++) {
                map[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать карты в консоль
     */
    public void printMap() {
        for(int x = 0; x < sizeX*4 + 1; x++) {
            System.out.print("_");
        }
        System.out.println();
        for(int x = 0; x < sizeX; x++) {
            System.out.print("|");
            for(int y = 0; y < sizeY; y++) {
                if(y == 0) {
                    System.out.print("_" + map[x][y] + "_|_");
                }
                if(y != 0 && y != sizeY -1) {
                    System.out.print(map[x][y] + "_|_");
                }
                if(y == sizeY - 1) {
                    System.out.print(map[x][y] + "_|");
                }
            }
            System.out.println();
        }
        for(int x = 0; x < sizeX*4 + 1; x++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Метод, который определяет, кто первый начинает
     * (1 - человек, 0 - компьютер)
     * @return
     */
    public int whoFirst() {
        int turn = r.nextInt(2);
        return turn;
    }

    /**
     * Ход человека
     */
    public void humanStep() {
        int x;
        int y;
        do {
            System.out.print("Введите координаты x, y от 1 до 5 через пробел: ");
            x = in.nextInt() - 1;
            y = in.nextInt() - 1;
        }
        while(!isValid(x, y) || !isEmpty(x, y));
        map[x][y] = DOT_HUMAN;
    }

    /**
     * Ход компьютера
     */
    public void aiStep()  {
        int x;
        int y;
        do {
            x = r.nextInt(sizeX);
            y = r.nextInt(sizeY);
        }
        while(!isEmpty(x, y));

        map[x][y] = DOT_AI;
    }

    /**
     * Проверка на валидность координаты: входят ли x, y в размерность поля
     * @param x
     * @param y
     * @return
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }

    /**
     * Проверка пустая ли ячейка
     * @param x
     * @param y
     * @return
     */
    private boolean isEmpty(int x, int y) {
        return map[x][y] == DOT_EMPTY;
    }

    public boolean isVictory(char dot) {
        int x = 0;
        int y = 0;
        while(x < sizeX) {
            y = 0;
            while(y < sizeY) {
                if(y + 3 < sizeY) {
                    if(map[x][y]==dot && map[x][y+1]==dot && map[x][y+2]==dot && map[x][y+3]==dot) return true;
                }
                if(x + 3 < sizeX && y + 3 < sizeY) {
                    if(map[x][y]==dot && map[x+1][y+1]==dot && map[x+2][y+2]==dot && map[x+3][y+3]==dot) return true;
                }
                if(x + 3 < sizeX) {
                    if(map[x][y]==dot && map[x+1][y]==dot && map[x+2][y]==dot && map[x+3][y]==dot) return true;
                }
                if(y - 3 >= 0 && x + 3 < sizeX) {
                    if(map[x][y]==dot && map[x+1][y-1]==dot && map[x+2][y-2]==dot && map[x+3][y-3]==dot) return true;
                }
                y++;
            }
            x++;
        }
        return false;
    }

    public boolean nobodyVictory() {
        for(int x = 0; x < sizeX; x++) {
            for(int y = 0; y < sizeY; y++) {
                if(isEmpty(x, y)) {
                    return true;
                }
            }
        }
        System.out.println("Ничья");
        return false;
    }
}
