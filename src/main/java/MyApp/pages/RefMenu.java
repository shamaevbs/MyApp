package MyApp.pages;

import MyApp.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class RefMenu {
    @Property
    @Persist
    private long testB;

    @Inject
    private Session session;
    @Property
    private Product product;

    @Log
    Object onActionFromRefresh()
    {
    try {
            File excel = new File("C:\\Users\\shamaev.bs\\Work\\MyApp\\src\\main\\java\\MyApp\\pages\\BData.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();
            testB=0;
            // Iterating over Excel file in Java
            while (itr.hasNext()) {
                Product product = new Product();
                Row row = itr.next();

                // Iterating over each column of Excel file
                Iterator<Cell> cellIterator = row.cellIterator();
                /*while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue() + "\t");
                            product.name= cell.getStringCellValue();
                            testB++;
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell.getNumericCellValue() + "\t");;
                            product.price=Double.toString(cell.getNumericCellValue());
                            testB=testB+2;
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //System.out.print(cell.getBooleanCellValue() + "\t");
                            testB++;
                            break;
                        default:

                    }
                } */
                Transaction transaction = session.beginTransaction();
                session.save(product);
                transaction.commit();
                //System.out.println("");
            }

            // writing data into XLSX file



            // open an OutputStream to save written data into Excel file

            //System.out.println("Writing on Excel file Finished ...");

            // Close workbook, OutputStream and Excel file to prevent leak
            book.close();
            fis.close();

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }

}
