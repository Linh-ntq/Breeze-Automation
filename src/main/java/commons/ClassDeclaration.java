package commons;

import datas.Datas;
import features.LandingFeature;
import features.LoginFeature;
import features.VehicleSettingFeature;
import pages.*;

public class ClassDeclaration extends BaseTest {

    public CommonKeyword commonKeyword = new CommonKeyword();
    public Datas datas = new Datas();
    public LandingFeature landingFeature = new LandingFeature(driver);
    public LoginFeature loginFeature = new LoginFeature(driver);
    public VehicleSettingFeature vehicleSettingFeature = new VehicleSettingFeature(driver);
    public CommonPage commonPage = new CommonPage(driver);
    public LandingPage landingPage = new LandingPage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public NameEntryPage nameEntryPage = new NameEntryPage(driver);
    public OnbCardPage onbCardPage = new OnbCardPage(driver);
    public VehicleSettingPage vehicleSettingPage = new VehicleSettingPage(driver);

}
