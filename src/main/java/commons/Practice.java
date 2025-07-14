package commons;

import java.util.*;

public class Practice extends BaseTest{

    public List<Integer> getCodeLst (String filePath, String sheetName, String rowName) {
        List<String> stringLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
        List<Integer> numLst = new ArrayList<>();
        int num;
        for (String string : stringLst) {
            num = Integer.parseInt(string);
            numLst.add(num);
        }
        return numLst;
    }

    public void getMin (String filePath, String sheetName, String rowName) {
        List<Integer> numLst = getCodeLst(filePath, sheetName, rowName);
        int min = Collections.min(numLst);

        System.out.println("Min of postal code: " + min);

    }

    public void getMax (String filePath, String sheetName, String rowName) {
        List<Integer> numLst = getCodeLst(filePath, sheetName, rowName);
        int max = numLst.get(0);
        for (Integer num : numLst) {
            if (num > max) {
                max = num;
            }
        }

        System.out.println("Max of postal code: " + max);
    }

    public void getSecondToMax (String filePath, String sheetName, String rowName) {
        List<Integer> numLst = getCodeLst(filePath, sheetName, rowName);
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (Integer num : numLst) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num < max && num > secondMax) {
                secondMax = num;
            }
        }

        System.out.println("Second to max of postal code: " + secondMax);
    }

    public void sortListInteger (String filePath, String sheetName, String rowName) {
        List<Integer> numLst = getCodeLst(filePath, sheetName, rowName);
        numLst.sort(Comparator.naturalOrder());
        System.out.println("Smallest to largest of postal code: " + numLst);

        numLst.sort(Comparator.reverseOrder());
        System.out.println("largest to smallest of postal code: " + numLst);

    }

    public void sortListString (String filePath, String sheetName, String rowName) {
        List<String> addressLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "How to use voucher (before claim)");
        addressLst.sort(Comparator.naturalOrder());
        System.out.println("Smallest to largest of address: " + addressLst);

        addressLst.sort(Comparator.reverseOrder());
        System.out.println("largest to smallest of address: " + addressLst);
    }
}
