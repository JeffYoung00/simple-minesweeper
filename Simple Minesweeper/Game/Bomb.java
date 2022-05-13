package Game;

import java.util.Random;

public class Bomb {
    //这里设置行数, 列数, 密度
    static final int rows=20;
    static final int lines=10;
    static final int density=2;

    final int allBombs=rows*lines*density/100;
    int flags=rows*lines*density/100*2;
    int toVictory=rows*lines-allBombs;
    int totalTime=999;
    int startTime=0;

    int[][] map=new int[rows][lines];
    int[][] mapState=new int[rows][lines];

    public Bomb() {
        generateMap();
    }

    public void generateMap(){
        Random rd=new Random();
        int bombs=0;
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
