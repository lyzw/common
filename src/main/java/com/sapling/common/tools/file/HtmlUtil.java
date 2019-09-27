package com.sapling.common.tools.file;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/9/27
 * @since 1.0
 */
public class HtmlUtil {

    public static class Html2Text extends HTMLEditorKit.ParserCallback {
        StringBuffer s;

        public Html2Text() {
        }

        public void parse(Reader in) throws IOException {
            s = new StringBuffer();
            ParserDelegator delegator = new ParserDelegator();
            // the third parameter is TRUE to ignore charset directive
            delegator.parse(in, this, Boolean.TRUE);
        }

        @Override
        public void handleText(char[] text, int pos) {
            s.append(text);
        }

        public String getText() {
            return s.toString();
        }



    }

    public static String getContent(String htmlContent) throws IOException {
        Reader reader = new StringReader(htmlContent);
        Html2Text html2Text = new Html2Text();
        html2Text.parse(reader);
        return html2Text.getText();
    }

    public static void main(String[] args) throws IOException {
        String content = FileUtil.getFileContent(new File("C:\\Users\\zhouwei\\Desktop\\Java实现从Html文本中提取纯文本 - weixin_39816740的博客 - CSDN博客.html"),"UTF-8");
        System.out.println();
        System.out.println(getContent(content));
    }
}
