import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazyUtils<T> {
    public static void WriteObject(File file , Serializable obj) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(obj);
            fileOutputStream.close();
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static<T extends Serializable> T  ReadObject(File file, Class<T> Class) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T result = Class.cast(objectInputStream.readObject());
            fileInputStream.close();
            objectInputStream.close();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> FilesIn(File file) {
        if (file.isDirectory()) {
            String[] f = file.list();
            assert f != null;
            if (f.length > 0) {
                ArrayList<String> list = new ArrayList<>(f.length);
                list.addAll(Arrays.asList(f));
                return list;
            }
        }
        return null;
    }
}
