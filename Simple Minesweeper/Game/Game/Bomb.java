package Game;

import java.util.Random;

public class Bomb {
    //这里设置行数, 列数, 密度
    static int rows=20;//10~50
    static int lines=10;//10~25
    static int density=15;//0~40

    int allBombs=rows*lines*density/100;
    int flags=rows*lines*density/100;
    int toVictory=rows*lines-allBombs;
    int totalTime=999;
    int startTime=0;

    public static void setRows(int rows) {
        Bomb.rows = rows;
    }

    public static void setLines(int lines) {
        Bomb.lines = lines;
    }

    public static void setDensity(int density) {
        Bomb.density = density;
    }

    int[][] map=new int[rows][lines];
    int[][] mapState=new int[rows][lines];

    public Bomb() {
        generateMap();
        while(isClosed()){
            generateMap();
        }
    }
    public static void SetLevel(int rows, int lines, int density) {
        Bomb.rows = rows;
        Bomb.lines = lines;
        Bomb.density = density;
    }
    public void generateMap(){
        Random rd=new Random();
        int bombs=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lines; j++) {
                map[i][j]=0;
            }
        }
        while(bombs<allBombs){
            int temp=rd.nextInt()%(rows*lines);
            temp=temp<0?(-1*temp):temp;
            int tempLines=temp%lines;
            int tempRows=temp/lines;
            if(map[tempRows][tempLines]==0){
                map[tempRows][tempLines]=-1;
                bombs++;
            }
        }
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < lines; j++) {
                if (map[i][j] == -1) {
                    int[] tempLine = {j - 1, j, j + 1, j - 1, j + 1, j - 1, j, j + 1};
                    int[] tempRow = {i - 1, i - 1, i - 1, i, i, i + 1, i + 1, i + 1};
                    for (int k = 0; k < 8; k++) {
                        if (tempRow[k] < rows && tempRow[k] > -1 && tempLine[k] < lines && tempLine[k] > -1) {
                            if (map[tempRow[k]][tempLine[k]] != -1)
                                map[tempRow[k]][tempLine[k]]++;
                        }
                    }
                }
            }
        }
    }

    public boolean isClosed(){
        if(map[0][0]==3||map[0][lines-1]==3||map[rows-1][0]==3||map[rows-1][lines-1]==3)
            return true;
        for (int i = 0; i < lines; i++) {
            if(map[0][i]==5||map[rows-1][i]==5)
                return true;
        }
        for (int i = 0; i < rows; i++) {
            if(map[i][0]==5||map[i][lines-1]==5)
                return true;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < lines; j++) {
                if(map[i][j]==8)
                    return true;
            }
        }
        return false;
    }

    public void clickRight(int i,int j){
        if(! (i< rows && i> -1 && j < lines && j > -1))
            return;
        if(mapState[i][j]==0&&flags>0) {
            mapState[i][j] = 1;
            flags--;
            return;
        }
        if(mapState[i][j]==1){
            mapState[i][j] =0;
            flags++;
        }
    }
    //true无炸弹，false有炸弹
    public boolean clickLeft(int i,int j){
        if(! (i< rows && i> -1 && j < lines && j > -1))
            return true;
        if(mapState[i][j]==1||mapState[i][j]==-1);
        else if(map[i][j]==0){
            mapState[i][j]=-1;
            toVictory--;
            int[] tempLine = {j - 1, j, j + 1, j - 1, j + 1, j - 1, j, j + 1};
            int[] tempRow = {i - 1, i - 1, i - 1, i, i, i + 1, i + 1, i + 1};
            for(int k=0;k<8;k++){
                if (tempRow[k] < rows && tempRow[k] > -1 && tempLine[k] < lines && tempLine[k] > -1)
                    clickLeft(tempRow[k],tempLine[k]);
            }
        }
        else if(map[i][j]==-1){
            map[i][j] = -2;
            return false;
        }else {
            toVictory--;
            mapState[i][j]=-1;
        }
        return true;
    }

}
