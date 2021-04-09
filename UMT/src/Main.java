public class Main {

    public static int howManyChars(String password){
        boolean containsLowercase=false, containsUppercase=false, containsDigit=false;
        for(int i = 0; i < password.length(); i++){
            if(Character.isDigit(password.charAt(i))){
                containsDigit = true;
            }
            if(Character.isLowerCase(password.charAt(i))){
                containsLowercase = true;
            }
            if(Character.isUpperCase(password.charAt(i))){
                containsUppercase = true;
            }
        }
        if(password.length()<6)
            return treatShortPassword(password,containsDigit,containsUppercase,containsLowercase);
        else return treatNormalOrLongPassword(password,containsDigit,containsUppercase,containsLowercase);
    }

    //returns number of changes required for a password longer than 6 characters
    //containsDigit - true if the password contains a digit (0-9), false otherwise
    //containsUppercase - true if the password contains an uppercase letter (A-Z), false otherwise
    //containsLowercase -true if the password contains a lowercase letter (a-z), false otherwise
    private static int treatNormalOrLongPassword(String password, boolean containsDigit, boolean containsUppercase, boolean containsLowercase) {
        //numberOfChanges -> the minimum changes required for a password to become strong
        //numberOfRepeatingChars -> number of identical characters in consecutive positions
        //passwordLength -> the number of characters the password has
        int numberOfChanges = 0,numberOfRepeatingChars=1, passwordLength=password.length();
        for(int i = 1; i < password.length(); i++){
            if(password.charAt(i-1) == password.charAt(i)){
                numberOfRepeatingChars++;
                if(numberOfRepeatingChars==3){
                    if(!containsDigit) {
                        containsDigit = true; //the current character should be replace with a digit
                        numberOfRepeatingChars = 1; // by replacing it with a digit, the number of repeating chars will reset
                        i++; // skipping the comparision between the current modified char with the next one
                    }
                    else if(!containsLowercase) {
                        containsLowercase = true;
                        numberOfRepeatingChars = 1;
                        i++;
                    }
                    else if(!containsUppercase) {
                        containsUppercase = true;
                        numberOfRepeatingChars = 1;
                        i++;
                    }
                    else{
                        if(passwordLength>20) {
                            passwordLength--; //the character will be deleted
                            numberOfRepeatingChars--;
                        }
                        else{
                            //if no more character need to be deleted (the length is 20), we will replace the char to not have a
                            //sequence of repeating characters
                            i++;
                            numberOfRepeatingChars=1;
                        }
                    }
                    numberOfChanges++;
                }
            }
            else numberOfRepeatingChars = 1;
        }


        if(!containsDigit)
            numberOfChanges++;
        if(!containsLowercase)
            numberOfChanges++;
        if(!containsUppercase)
            numberOfChanges++;
        if(passwordLength>20)
            numberOfChanges+=passwordLength-20;
        return numberOfChanges;
    }

    //returns number of changes required for a password shorter than 6 characters
    //containsDigit - true if the password contains a digit (0-9), false otherwise
    //containsUppercase - true if the password contains an uppercase letter (A-Z), false otherwise
    //containsLowercase -true if the password contains a lowercase letter (a-z), false otherwise
    private static int treatShortPassword(String password, boolean containsDigit, boolean containsUppercase, boolean containsLowercase) {
        int numberOfUnmetConditions = 0;
        if(!containsDigit)
            numberOfUnmetConditions++;
        if(!containsLowercase)
            numberOfUnmetConditions++;
        if(!containsUppercase)
            numberOfUnmetConditions++;
        return Math.max(numberOfUnmetConditions,6-password.length());
    }

    public static void main(String[] args) {
        System.out.println(howManyChars("aB1sdsdsdsdsdsdsdsdssss"));
    }

}
