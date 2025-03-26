package tests;

import commons.Setup;
import org.testng.annotations.Test;

public class VoucherSearchTest extends Setup {
    @Test(priority = 0)
    public void get_data_from_excel_file(){
        classDecl.excelReader.getVoucherData(
                classDecl.datas.pathVoucherData,
                "Voucher Data",
                "Dunkin' Donuts",
                "About voucher");

    }
}
