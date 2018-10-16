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
        try (InputStream in = new FileInputStream("C:\\Users\\gsl\\Desktop\\frontend-notebook.pdf")) {
            Ftp.upload("127.0.0.1", 2121, "gsl", "123456", in, "/gsl", "xxx.pdf", "utf-8");
        }
    }


    @Test
    public void download() throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = Ftp.download("127.0.0.1", 2121, "gsl", "123456", "/gsl", "xxx.pdf", "utf-8");
            out = new FileOutputStream("C:\\Users\\gsl\\Desktop\\xxxxxxxxx.pdf");
            ByteStreams.copy(in, out);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    @Test
    public void uploadPassiveMode() throws IOException {
        try (InputStream in = new FileInputStream("C:\\Users\\gsl\\Desktop\\frontend-notebook.pdf")) {
            Ftp.uploadPassiveMode("127.0.0.1", 2121, "gsl", "123456", in, "/gsl/passive", "xxx.pdf", "utf-8");
        }
    }

    @Test
    public void downloadPassiveMode() throws IOException {

        InputStream in = null;
        OutputStream out = null;
        try {
            in = Ftp.downloadPassiveMode("127.0.0.1", 2121, "gsl", "123456", "/gsl", "xxx.pdf", "utf-8");
            out = new FileOutputStream("C:\\Users\\gsl\\Desktop\\xxxxxxxxx.pdf");
            ByteStreams.copy(in, out);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}