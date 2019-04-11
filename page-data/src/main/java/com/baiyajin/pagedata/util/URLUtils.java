package com.baiyajin.pagedata.util;

public class URLUtils {
    protected static char[] c = new char[]{
            '%', '"', '#', '+', '&',
            ',', '/', ']', '$', '?',
            '@', '\\', '|', '=', '<',
            '>', '!', '*', '\'', '(',
            ')', ';', '[', '{', '}',
            '^', '`', '~'
    };

    public static void main(String[] args) {
        /*
            ^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$
    　　　　解释：
    　　　　^  　　　　　　 　　　　　　　　　　与字符串开始的地方匹配
    　　　　(?!_)　　　　　　　　　　　　　　　 不能以_开头
    　　　　(?!.*?_$)　　   　　　　　　　　　　不能以_结尾
    　　　　[a-zA-Z0-9_\u4e00-\u9fa5]+　　  至少一个汉字、数字、字母、下划线
    　　　　$　　　　　　　　　　　　　　　　　与字符串结束的地方匹配
        */
        /*.replace(/[\[mqms2\]]/g,"")*/
        String s = "___.我.sf989{[7&^%Schedule.d[mqm]_and_[mqms2]fer[mqms].mp3__";
        /*把连续重复的两个下划线替换成一个      .replaceAll("_+","_");*/
        /*// ^表示匹配字符串的开头，但是如何在[]里面则表示非，如[^a-Schedule] 不匹配a-Schedule*/
        /*.replaceAll("_+","_")*/
        /*.replaceAll("_.",".")*/
        /*匹配字符串中某字符串最后一次出现   "\\*wer(?!.*\\*wer)"   */
        s = s
                .replace("[mqms2]","")
                .replace("[mqms]","")
                .replaceAll("[^0-9a-zA-Z._\\u4e00-\\u9fa5]", "_")
                .replaceAll("^_+","")
                .replaceAll("_+","_")
                .replaceAll("_$","");
                ;
        /*/and\s*(\S*?)\s*=\s*(\S*)(?!.*and.*)/g*/
        /*[^/]+(?!.*exe)*/
        /*[^/]+ 表示匹配任意长度的字符串,字符串中不包含有字符 / ,就可以把以 / 分割开的字符串全匹配到.
        .*exe 表示任意以exe 结尾的字符串,可以把后在带有exe的字符串匹配到,前面再加上?!,再用圆括号包住表示排除掉(?!.*exe)
整个表达式的意思就是匹配任意长度的不包含/ 的字符串,并把以exe结尾的字符串匹配出来。*/
        String[] split = s.split(s.substring(s.lastIndexOf(".")));

        String fileName = "___.我.sf989{[7&^%Schedule.d[mqm]_and_[mqms2]fer[mqms]_____.mp3__";

        if(fileName.lastIndexOf(".") != -1){
            System.out.println(fileName.substring(fileName.lastIndexOf(".")));
            String[] split2 = fileName.split(fileName.substring(fileName.lastIndexOf(".")));
            if(split2[0].endsWith("_")){
                fileName = split2[0].replaceAll("_+$","") + fileName.substring(fileName.lastIndexOf("."));
                System.out.println(fileName);
            }
        }

//        System.out.println(split[0]);
//        System.out.println(s);
        /*
        .我.sf989_7_f.d_fer.f_..
        .我.sf989_7_f.d_fer.f_._.
        */


    }


}
