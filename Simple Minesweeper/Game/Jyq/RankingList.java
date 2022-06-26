package Jyq;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class RankingList implements Serializable {
    private static final long serialVersionUID = -6923346268754809418L;
    private ArrayList<Rank> rankArrayList;
    public RankingList() {
        rankArrayList = new ArrayList<>();
    }
    public void addRank(Rank rank) {
        rankArrayList.add(rank);
    }
    public void ShowRank() {
        if (rankArrayList.size() == 0) {
            System.out.println("排行榜目前为空");
            return;
        }
        Comparator<Rank> comparator = (Rank r1, Rank r2) ->{
            return r1.compareTo(r2);
        };
        rankArrayList.sort(comparator);
        for (Rank r: rankArrayList) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
       //LazyUtils.WriteObject(Directories.RankingList, new RankingList());
        RankingList rankingList = LazyUtils.ReadObject(Directories.RankingList, RankingList.class);
        rankingList.addRank(new Rank("127.0.0.1", 114514,"homo", LocalDateTime.now().toString()));
        LazyUtils.WriteObject(Directories.RankingList, rankingList);
    }
}
