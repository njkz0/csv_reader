package redactor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRedactor {

    public static String removeQuotesFromString(String in){
        Pattern pattern = Pattern.compile("”(\\w+;)+\\w+”");
        Matcher matcher = pattern.matcher(in);
        while (matcher.find()) {
            String whatReplace = matcher.group();
            String replaceTo = whatReplace.replaceAll(";", ",");
            replaceTo = replaceTo.replaceAll("”","");
            in = in.replace(whatReplace, replaceTo);
        }
        return in;
    }

    public static String addQuoteTiString(String in){
        Pattern pattern = Pattern.compile(";(\\w+,)+\\w+");
        Matcher matcher = pattern.matcher(in);
        while (matcher.find()) {
            String whatReplace = matcher.group();
            StringBuilder stringBuilder = new StringBuilder(whatReplace);
            stringBuilder.insert(1, "”");
            stringBuilder.insert(stringBuilder.length()  , "”");
            String replaceTo = stringBuilder.toString();
            replaceTo = replaceTo.replaceAll(",",";");
            in = in.replace(whatReplace, replaceTo);
        }
        return in;
    }


}
