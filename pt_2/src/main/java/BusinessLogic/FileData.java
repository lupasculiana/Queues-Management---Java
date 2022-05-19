package BusinessLogic;
import Presentation.*;
import Model.*;
import java.io.*;

public class FileData {
    File file1 = new File("text1.txt");
    File file2 = new File("text2.txt");
    File file3 = new File("text3.txt");
    public void createFile() throws IOException {
        file1.createNewFile();
        //file2.createNewFile();
        //file3.createNewFile();
    }

    public void writeInFile(StringBuilder s) throws IOException {
        try {
            FileWriter write = new FileWriter("text1.txt",true);
            BufferedWriter buffWr = new BufferedWriter(write);
            buffWr.append(s);
            buffWr.newLine();
            buffWr.flush();
            buffWr.close();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
