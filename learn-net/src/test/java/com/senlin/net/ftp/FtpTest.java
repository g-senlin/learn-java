package com.senlin.net.ftp;

import com.google.common.io.ByteStreams;
import org.junit.Test;

import java.io.*;

/**
 * @author gsl
 * @date 2018/10/15 7:42.
 */
public class FtpTest {

    @Test
    public void upload() throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\gsl\\Desktop\\frontend-notebook.pdf");
        Ftp.upload("127.0.0.1", 2121, "gsl", "*****", in, "/gsl", "xxx.pdf", "utf-8");
        in.close();
    }

//
//    @Test
//    public void download() throws IOException {
//
//        InputStream in = Ftp.download("127.0.0.1", 2121, "gsl", "xx", "/", "xxx.pdf", "utf-8");
//        FileOutputStream out = new FileOutputStream("C:\\Users\\gsl\\Desktop\\xxxxxxxxx.pdf");
//        ByteStreams.copy(in,out);
//        in.close();
//        out.close();
//    }


}