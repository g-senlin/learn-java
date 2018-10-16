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
            //切换到指定目录
            changeDirectory(ftpClient, ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

//            默认是　主动模式
            ftpClient.enterLocalActiveMode();
            log.debug("enterLocalActiveMode prot:" + ftpClient.getLocalPort());
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
            changeDirectory(ftpClient, ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            默认是　主动模式
            ftpClient.enterLocalActiveMode();
            log.debug("enterLocalActiveMode prot:" + ftpClient.getLocalPort());
            return ftpClient.retrieveFileStream(ftpFileName);
        } finally {
            ftpClient.disconnect();
        }
    }

    public final static void uploadPassiveMode(String ip, int port, String userName, String password, InputStream sourceStream, String ftpDir, String ftpFileName, String charset) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setConnectTimeout(30000);
            ftpClient.connect(ip, port);
            //登录
            boolean login = ftpClient.login(userName, password);
            //设置上传目录
            changeDirectory(ftpClient, ftpDir);
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
//           有数据的读取才会有服务器端口开放
            log.info("PassivePort：" + ftpClient.getPassivePort());
        } finally {
            ftpClient.disconnect();
        }
    }

    public final static InputStream downloadPassiveMode(String ip, int port, String userName, String password, String ftpDir, String ftpFileName, String charset) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(userName, password);
            //设置上传目录
            changeDirectory(ftpClient, ftpDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding(charset);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //设置被动模式
            ftpClient.enterLocalPassiveMode();
            log.debug("enterLocalPassiveMode prot:" + ftpClient.getPassivePort());
            // 外层调用方关闭流
            InputStream inputStream = ftpClient.retrieveFileStream(ftpFileName);
            log.debug("enterLocalPassiveMode prot:" + ftpClient.getPassivePort());
            return  inputStream;
        } finally {
            ftpClient.disconnect();
        }
    }

    public static boolean changeDirectory(FTPClient client, String path) throws IOException {
        path = path.replaceAll("\\\\", "/");
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (!client.changeWorkingDirectory(path)) {
            String p[] = path.split("/");
            for (int i = 0; i < p.length; i++) {
                //A、B两个线程同时进到这个方法。切换文件夹为archives(第一次这个文件夹是不存在的)，切换失败
                if (!client.changeWorkingDirectory(p[i])) {
                    //假如A线程先执行了makeDirectory方法，创建了archives文件夹，返回true， A继续执行changeWorkingDirectory方法，切换成功
                    //那么B在执行makeDirectory时回返回false，原因550 archives: already exists，B直接return false,不在进行切换
                    //这样就会导致文件在上传的时候并不是我们指定的目录，导致文件获取失败
                    if (client.makeDirectory(p[i])) {
                        log.debug(client.getReplyString());
                    } else {
                        log.warn(client.getReplyString());
                        //为什么要在这加一个切换目录的操作，因为创建目录失败，有可能是因为并发的情况，目录已经被创建好了，所以创建目录失败+切换失败才能证明有异常，，返回false
                        if (client.changeWorkingDirectory(p[i])) {
                            log.warn(client.getReplyString());
                            continue;
                        }
                        return false;
                    }
                    if (client.changeWorkingDirectory(p[i])) {
                        log.debug(client.getReplyString());
                    } else {
                        log.warn(client.getReplyString());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
