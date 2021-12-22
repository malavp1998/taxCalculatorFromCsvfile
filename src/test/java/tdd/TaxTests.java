package tdd;

import com.tax.caculator.model.Data;
import com.tax.caculator.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxTests {

   /* input  : 2, 25000, 1
      for item_type = 1 we know that tax_rate is 8%
      and we know that tax = principle * tax_rate

     so
     tax = 25000 * (8/100)
     tax = 2000 (this is expected value)
   */

    DataService dataService;

    @Before()
    public void setup() {
        dataService = new DataService();
    }


    @Test
    public void  multiplyFn_whenCorrectInput_multipluResult() {
		final int amount = 25000;
		final int  taxRate= 8;
        dataService.calculateTax();
       // System.out.print(d);
        assertEquals(2000, dataService.getTaxFromData(amount, taxRate) );
    }
}
