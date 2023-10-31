/*Author: Tianle Chen Chenyan Fan*/
package demo.ide;

import demo.data.Song;
import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class input {

    public static List<Song> songList = new ArrayList<>();
//    public static List<String> showList = new ArrayList<>();


    public input(){}



    static {
        try {
//            String root = System.getProperty("user.dir");
//            String FileName="save.txt";
//            String filePath = root+File.separator+"src/demo/ide"+File.separator+FileName;
            String filePath = "src/demo/ide/save.txt";
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line="";
            String[] arrs=null;
            while ((line=br.readLine())!=null) {
                arrs=line.split(";");
                Song temp = new Song(arrs[0],arrs[1]);
                if(arrs.length == 3){
                    temp.setAlbum(arrs[2]);
                    temp.setYear("");
                }else if(arrs.length == 4){
                    temp.setAlbum(arrs[2]);
                    temp.setYear(arrs[3]);
                }else {
                    temp.setAlbum("");
                    temp.setYear("");
                }
                songList.add(temp);
//                String s = arrs[0] + ";" + arrs[1];
//                showList.add(s);
            }
            br.close();
            fr.close();
        }catch (Exception ignore) {
            ignore.printStackTrace();
        }
        sortList();
    }

    public static List<Song> getSongList() {
        return songList;
    }
//    public static List<String> getShowList(){
//        return showList;
//    }
    public static void sortList() {
        songList.sort((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return Collator.getInstance().compare(o1.getArtist(), o2.getArtist());
            }
            return Collator.getInstance().compare(o1.getName(), o2.getName());
        });
    }



}
