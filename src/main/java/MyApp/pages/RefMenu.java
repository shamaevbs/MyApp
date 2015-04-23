package MyApp.pages;

import MyApp.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

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
    private int testB;




    @Property
    private UploadedFile file;

    @Component(id = "file")
    private org.apache.tapestry5.upload.components.Upload fileField;

    @Inject
    private Session session;
    @Property
    private Product product;
    @Environmental
    private ValidationTracker tracker;

    public boolean checkString(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }


    public void onSuccess()
    {
        int numstr= 2, StringType, NumericType;
        try {
            int dotPos = file.getFileName().lastIndexOf(".");
            String ext = file.getFileName().substring(dotPos+1);
            //testB = ext;
            if(ext.compareTo("xlsx")!=0){
                throw new BadLoadFileException();
            }

            File copied = new File("C:\\\\Users\\\\shamaev.bs\\\\Work\\\\MyApp\\\\src\\\\main\\\\java\\\\MyApp\\\\pages\\\\" + file.getFileName());
            file.write(copied);
            File excel = copied;
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();
            // Iterating over Excel file in Java
            if (!itr.hasNext()){
                throw new EmptyFileException();
            }
            itr.next();

            while (itr.hasNext()) {

                Product product1 = new Product();
                Row row = itr.next();

                // Iterating over each column of Excel file
                Iterator<Cell> cellIterator = row.cellIterator();
                if (!cellIterator.hasNext()){
                    throw new BadPriceException();
                }
                Cell cell = cellIterator.next();
                NumericType = cell.getCellType();
                if ( NumericType!=0){
                    throw new BadPriceException();
                }

                if (0 == cell.getNumericCellValue()) {
                    throw new BadPriceException();
                }
                product1.price = new BigDecimal(cell.getNumericCellValue());
                product1.price=product1.price.setScale(2,BigDecimal.ROUND_DOWN);


                if (!cellIterator.hasNext()){
                    throw new BadNameException();
                }
                Cell cell1 = cellIterator.next();
                StringType = cell1.getCellType();
                if ( StringType!=1){
                    throw new BadNameException();
                }
                if ("".equals(cell1.getStringCellValue())){
                    throw new BadNameException();
                }
                product1.name = cell1.getStringCellValue();

                if (!cellIterator.hasNext()){
                    throw new BadConsistException();
                }
                Cell cell2 = cellIterator.next();
                StringType = cell2.getCellType();
                if ( StringType!=1){
                    throw new BadConsistException();
                }
                if("".equals(cell2.getStringCellValue())){
                    throw new BadConsistException();
                }
                product1.consist =cell2.getStringCellValue();

                if (!cellIterator.hasNext()){
                    throw new BadSpecificationException();
                }
                Cell cell3 = cellIterator.next();
                StringType = cell3.getCellType();
                if ( StringType!=1){
                    throw new BadSpecificationException();
                }
                if("".equals(cell3.getStringCellValue())){
                    throw new BadSpecificationException();
                }
                product1.specification = cell3.getStringCellValue();

                product1.basket="В корзину";

                Transaction transaction = session.beginTransaction();
                session.save(product1);
                transaction.commit();

                if ( numstr==2){
                    List<Product> productLst = session.createCriteria(Product.class).list();
                    for(Product product : productLst) {
                        Transaction transaction1 = session.beginTransaction();
                        session.delete(product);
                        transaction1.commit();
                    }
                }

                numstr++;

            }
            if (numstr==2){
                throw new EmptyFileException();
            }

            book.close();
            fis.close();

        } catch (FileNotFoundException fe) {
            String error = "File not found";
            tracker.recordError(error);
            //fe.printStackTrace();
        } catch (IOException ie) {
            String error = "File not found";
            tracker.recordError(error);
            //ie.printStackTrace();
        } catch (BadPriceException e) {
            String error ="In line "+ Integer.toString(numstr)+" invalid price";
            tracker.recordError(error);
        } catch (BadLoadFileException e){
            String error = "Invalid File";
            tracker.recordError(error);
        } catch (BadNameException e ){
            String error ="In line "+ Integer.toString(numstr)+" invalid name";
            tracker.recordError(error);
        } catch (BadConsistException e){
            String error ="In line "+ Integer.toString(numstr)+" invalid consist";
            tracker.recordError(error);
        } catch (BadSpecificationException e){
            String error ="In line "+ Integer.toString(numstr)+" invalid specification";
            tracker.recordError(error);
        } catch (EmptyFileException e){
            String error  = "File is empty";
            tracker.recordError(error);
        }
    }

    private class BadPriceException extends Throwable {
    }

    private class BadLoadFileException extends Throwable {
    }

    private class BadNameException extends Throwable {
    }

    private class BadConsistException extends Throwable {
    }

    private class BadSpecificationException extends Throwable {
    }

    private class NumberPriceException extends Throwable {
    }

    private class EmptyFileException extends Throwable {
    }
}
