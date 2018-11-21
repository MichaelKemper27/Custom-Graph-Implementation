/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        String commaSep = "Hello there,world yup";
        String[] twoWords = commaSep.split(",");
        if (twoWords.length > 1) {
            System.out.println("do a connect function");
        }
        for (String str : twoWords) {
            System.out.println(str);
        }
    }
}
