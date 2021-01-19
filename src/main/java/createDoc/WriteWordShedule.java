package createDoc;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

/**
 * пример создания Word документа
 * @author Aleks
 */
public class WriteWordShedule {
    
   public static void main(String[] args)throws Exception {
       WriteWordShedule wws = new WriteWordShedule();
       wws.newDoc();
       System.out.println("Документ создан");
   }

  public static void newDoc() {
      try {
          XWPFDocument document = new XWPFDocument();
          FileOutputStream out = new FileOutputStream(new File("D:\\\\temp\\\\poidemo.docx"));

          XWPFParagraph paragraph = document.createParagraph();
          XWPFRun run = paragraph.createRun();
          run.setText("Hello_Word!");
          document.write(out);
          out.close();
      } catch(Exception e) {
          System.out.println("Документ не создан");
          System.out.println(e);
      }
   }
}
