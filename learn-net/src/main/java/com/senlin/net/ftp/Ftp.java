package com.senlin.net.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gsl
 * @date 2018/10/15 7:23.
 */
@Slf4j
public class Ftp {


    public final static void upload(String ip, int port, String userName, String password, InputStream sourceStream, String ftpDir, String ftpFileName, String charset) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(userName, password);
            //设置上传目录
            ftpClient.changeWorkingDirectory(ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(ftpFileName, sourceStream);
        } finally {
            ftpClient.disconnect();
        }
    }

    public final static InputStream download(String ip, int port, String userName, String password, String ftpDir, String ftpFileName, String charset) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(userName, password);
            //设置上传目录
            ftpClient.changeWorkingDirectory(ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            return ftpClient.retrieveFileStream(ftpFileName);
        } finally {
            ftpClient.disconnect();
        }
    }

    public final static void uploadPassiveMode(String ip, int port, String userName, String password, InputStream sourceStream, String ftpDir, String ftpFileName, String charset) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            //登录
            boolean login = ftpClient.login(userName, password);
            //设置上传目录
            ftpClient.changeWorkingDirectory(ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置被动模式
            ftpClient.enterLocalPassiveMode();
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (ftpClient.storeFile(ftpFileName, sourceStream)) {
                log.info("上传成功...");
            } else {
                log.info("上传失败...");
            }
        } finally {
            ftpClient.disconnect();
        }
    }
}
