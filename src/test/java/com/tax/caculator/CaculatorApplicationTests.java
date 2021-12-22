package com.tax.caculator;

import com.tax.caculator.model.Data;
import com.tax.caculator.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CaculatorApplicationTests {
/*


	input  : 2, 25000, 1

	for item_type = 1 we know that tax_rate is 8%
	and we know that tax = principle * tax_rate

	so
	   tax = 25000 * (8/100)
	   tax = 2000 (this is expected value)
	*/

	private Data data;
	@Autowired
	DataService dataService;
	ArrayList<Data> inputData = new ArrayList<>();

	@Before()
	public void setup() {
		data = new Data(2,25000,1,0);
		inputData.add(data);

	}


	 @Test
	public void divideFn_whenCorrectInput_divideResult() {
//		final double numerator = 10;
//		final double denomenator = 5;
		dataService.calculateTax();
		assertEquals(2000, data.getTax() );
	}
}
