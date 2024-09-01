package fajar.belajarCSV;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

/** Membuat CSV
 * Untuk membuat CSV, kita bisa menggunakan class CSVPrinter yang terdapat di library Commons CSV
 * Saat membuat CSVPrinter, kita perlu tentukan output tujuan dari CSV
 * Di class CSVPrinter, terdapat method printRecord() yang bisa kita gunakan untuk menambah data ke CSV
 * https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVPrinter.html
 */


public class CsvTest {

    @Test
    @SneakyThrows
    void testCsv() {

        StringWriter writer = new StringWriter();//kita juga bisa gunakan writer ke file

        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);//class untuk membuat CSV yaitu CSVPrinter
        printer.printRecord("fajar abdillah","ahmad",100);
        printer.printRecord("saipul","damarkan",20);
        printer.flush();

        System.out.println(writer.getBuffer().toString());

    }


    /** CSV Format
     * Secara default, format file CSV itu dipisahkan menggunakan , (koma)
     * Tapi kadang ada  beberapa format CSV lain yang mungkin menggunakan pemisah dengan karakter tab, titik koma, atau yang lainnya
     * Bahkan beberapa Spreadsheet editor, memiliki aturan tertentu untuk membuat CSV file
     * Untungnya, Commons CSV mendukung beberapa format Spreadsheet editor
     * Kita bisa lihat di class CSVFormat, terdapat banyak sekali format yang didukung, selain DEFAULT
     * https://commons.apache.org/proper/commons-csv/apidocs/index.html
     */

    @Test
    @SneakyThrows
    void testCSVWithTap() {

        StringWriter writer = new StringWriter();//kita juga bisa gunakan writer ke file

        CSVFormat format = CSVFormat.TDF.builder()
                .setHeader("firstName", "lastName", "Value")//membuat line pertama menjadi header
                .build();

        CSVPrinter printer = new CSVPrinter(writer, format);//TDF adalah tap delimiter format
        printer.printRecord("fajar abdillah","ahmad",100);
        printer.printRecord("saipul","damarkan",20);
        printer.flush();

        System.out.println(writer.getBuffer().toString());

    }


    @Test
    @SneakyThrows
    void testCSVWithMYSQLFormat() {

        StringWriter writer = new StringWriter();//kita juga bisa gunakan writer ke file

        CSVFormat format = CSVFormat.MYSQL.builder()//membuat format csv dengan mysql format
                .setHeader("firstName", "lastName", "Value")
                .build();

        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord("fajar abdillah","ahmad",100);
        printer.printRecord("saipul","damarkan",20);
        printer.flush();

        System.out.println(writer.getBuffer().toString());

    }



}
