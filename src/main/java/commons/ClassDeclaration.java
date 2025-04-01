package commons;

import datas.Datas;
import datas.ExcelReader;
import features.LandingFeature;
import features.LoginFeature;
import features.VehicleSettingFeature;
import features.VoucherDiscoveryFeature;
import pages.*;

public class ClassDeclaration {

    public CommonKeyword commonKeyword = new CommonKeyword();
    public Datas datas = new Datas();
    public LandingFeature landingFeature = new LandingFeature();
    public LoginFeature loginFeature = new LoginFeature();
    public VehicleSettingFeature vehicleSettingFeature = new VehicleSettingFeature();
    public CommonPage commonPage = new CommonPage();
    public LandingPage landingPage = new LandingPage();
    public LoginPage loginPage = new LoginPage();
    public NameEntryPage nameEntryPage = new NameEntryPage();
    public OnbCardPage onbCardPage = new OnbCardPage();
    public VehicleSettingPage vehicleSettingPage = new VehicleSettingPage();
    public ExcelReader excelReader = new ExcelReader();
    public VoucherDiscoveryFeature voucherDiscoveryFeature = new VoucherDiscoveryFeature();
    public SearchDestinationPage searchDestinationPage = new SearchDestinationPage();

}
