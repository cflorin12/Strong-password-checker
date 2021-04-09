import static org.junit.jupiter.api.Assertions.*;

class HowManyCharsTest {

    @org.junit.jupiter.api.Test
    void howManyChars() {
        shortPasswords();
        normalPasswords();
        longPasswords();
    }

    @org.junit.jupiter.api.Test
    void shortPasswords(){
        //Repeating characters, uppercase and digit missing
        //Example for fixing the password: aa1aaR -> adding one uppercase character and one digit
        assert Main.howManyChars("aaaa")==2;

        //Repeating characters, uppercase, lowercase and digit missing
        //Example for fixing the password: ??a??R1 -> adding one uppercase, one lowercase and one digit
        assert Main.howManyChars("????")==3;

        //Non-repeating characters, uppercase, lowercase and digit present
        //Example for fixing the password: aB4abc -> adding any 3 characters, without having 3 repeating chars in consecutive positions
        assert Main.howManyChars("aB4")==3;

        //Repeating characters, uppercase, lowercase and digit present
        //Example for fixing the password: a1BBCB -> adding a random character between the repeating ones
        assert Main.howManyChars("a1BBB")==1;
    }

    @org.junit.jupiter.api.Test
    void normalPasswords(){

        //Repeating characters, uppercase, lowercase and digit missing
        //Example for fixing the password: ??1?aA -> replacing 3 chars with uppercase,lowercase and digit so that no 3 identical chars
        //are consecutive
        assert Main.howManyChars("??????")==3;

        //Repeating characters, uppercase and lowercase missing
        //Example for fixing the password: 11a11B -> replacing 2 chars with uppercase and lowercase so that no 3 identical chars
        //are consecutive
        assert Main.howManyChars("111111")==2;

        //Repeating characters, uppercase, lowercase and digit present
        //Example for fixing the password: AbccF1224e -> replacing 2 chars with random chars so that no 3 identical chars
        //are consecutive
        assert Main.howManyChars("Abccc1222e")==2;
    }

    @org.junit.jupiter.api.Test
    void longPasswords(){
        //Repeating characters, uppercase, lowercase and digit missing
        //Example for fixing the password: ??1??a??A??a??a??a??
        assert Main.howManyChars("??????????????????????")==8;

        //Repeating characters, uppercase, lowercase and digit present
        //Example for fixing the password: Abcc122e12sawe3drew4
        assert Main.howManyChars("Abccc1222e12sawe3drew4")==2;

        //Non-repeating characters, uppercase, lowercase and digit present
        //Example for fixing the password: 1234abcdABCD?)(*eett
        assert Main.howManyChars("1234abcdABCD?)(*eett123")==3;
    }

}