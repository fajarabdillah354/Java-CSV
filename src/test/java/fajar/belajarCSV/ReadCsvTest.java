package fajar.belajarCSV;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;

import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadCsvTest {

    @Test
    @SneakyThrows
    void testRead() {
        Path path = Path.of("sample.csv");//Path yang ingin dibaca

        Reader reader = Files.newBufferedReader(path);//Read file sample
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);//class CSV untuk membaca file CSV
        for (var getCsv : csvParser){
            System.out.println("firstname : "+getCsv.get(0));
            System.out.println("lastname : "+getCsv.get(1));
            System.out.println("value : "+getCsv.get(2));
            System.out.println("==============================");
        }
    }



    @Test
    @SneakyThrows
    void testReadWithHeader() {
        Path path = Path.of("sample.csv");//Path yang ingin dibaca

        Reader reader = Files.newBufferedReader(path);//Read file sample

        //dengan begini maka java akan secara otomatis menganggap semua line yang ada di baris file csv akan menjadi header

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();//penulisan header pada file csv tidak boleh ada spasi
        CSVParser parser = new CSVParser(reader, format);
        for (var getCsv : parser){
            System.out.println("firstname : "+getCsv.get("firstName"));//kita bisa mendapatkan nama header yang ada di dalam file CSVnya
            System.out.println("lastname : "+getCsv.get("lastName"));
            System.out.println("value : "+getCsv.get("Value"));
            System.out.println("==============================");
        }
    }


    /** CSV Header
     * Seperti yang sempat dibahas di awal, kadang-kadang, saat kita membuat file CSV, biasanya kita menambahkan baris pertama sebagai Header
     * Saat menggunakan Commons CSV, kita harus memberi tahu CSVFormat jika baris pertama adalah kolom, jadi kita bisa menggunakan method setHeader() untuk memberitahu bahwa baris pertama adalah header
     * Keuntungan menggunakan header, kita bisa menggunakan nama header untuk mendapatkan nilai dari tiap kolom di CSV, jadi tidak perlu menggunakan nomor index lagi

     ** Menulis CSV Header
     * CSVFormat juga bisa digunakan untuk menulis CSV dengan Header
     * Kita cukup sebutkan saja nama-nama header dengan method setHeader()
     */


    @Test
    @SneakyThrows
    void testWriteCsv() {

        StringWriter writer = new StringWriter();//kita juga bisa gunakan writer ke file

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader("firstName", "lastName", "Value").build();//ketika run akan menampilkan nama headernya
        CSVPrinter printer = new CSVPrinter(writer, format);//class untuk membuat CSV yaitu CSVPrinter
        printer.printRecord("Cristiano","Ronaldo",100);
        printer.printRecord("Lionel","Messi",100);
        printer.flush();

        System.out.println(writer.getBuffer().toString());

    }

}
