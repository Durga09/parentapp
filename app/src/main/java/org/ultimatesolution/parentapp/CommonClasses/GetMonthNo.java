package org.ultimatesolution.parentapp.CommonClasses;

public class GetMonthNo {
    static public String GetMonth(String monthText){

        switch (monthText.toUpperCase()) {
            case "JAN":
                return "01";
            case "FEB":
                return "02";
            case "MAR":
                return "03";
            case "APR":
                return "04";
            case "MAY":
                return "05";
            case "JUN":
                return "06";
            case "JUL":
                return "07";
            case "AUG":
                return "08";
            case "SEP":
                return "09";
            case "OCT":
                return "10";
            case "NOV":
                return "11";
            case "DEC":
                return "12";
        }
        return monthText;
    }

    static public String GetMonthName(String monthText){

        switch (monthText.toUpperCase()) {
            case "01":
            case "1":
                return "Jan";
            case "02":
            case "2":
                return "Feb";
            case "03":
            case "3":
                return "Mar";
            case "04":
            case "4":
                return "Apr";
            case "05":
            case "5":
                return "May";
            case "06":
            case "6":
                return "Jun";
            case "07":
            case "7":
                return "Jul";
            case "08":
            case "8":
                return "Aug";
            case "09":
            case "9":
                return "Sep";
            case "10":
                return "Oct";
            case "11":
                return "Nov";
            case "12":
                return "Dec";
        }
        return monthText;
    }

    static public String GetMonthName(int monthText){

        switch (monthText) {

            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return String.valueOf(monthText);
    }

    static public String GetDay(int day){

        switch (day) {

            case 1:
                return "01";

            case 2:
                return "02";

            case 3:
                return "03";

            case 4:
                return "04";

            case 5:
                return "05";

            case 6:
                return "06";

            case 7:
                return "07";

            case 8:
                return "08";

            case 9:
                return "09";
        }
        return String.valueOf(day);
    }
}
