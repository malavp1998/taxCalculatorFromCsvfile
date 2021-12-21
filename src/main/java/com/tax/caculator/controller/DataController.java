package com.tax.caculator.controller;

import com.tax.caculator.model.Data;
import com.tax.caculator.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

   @GetMapping("/")
   public ArrayList<Data> readData()
   {
       dataService.readCustomerData();
       dataService.calculateTax();
       dataService.writeDataOnfile();
       return  dataService.printCustomerData();
   }

    @GetMapping("/printdata")
   public ArrayList<Data> printData()
   {
      return dataService.printCustomerData();
   }
}
