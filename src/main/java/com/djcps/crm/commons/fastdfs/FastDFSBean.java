package com.djcps.crm.commons.fastdfs;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author 叶千阁
 * @date 2016年8月2日
 */
public class FastDFSBean {
    private String clientName = "config/client.conf";
    private TrackerClient trackerClient = null;
    private StorageServer storageServer = null;
    private TrackerServer trackerServer = null;
    private StorageClient storageClient = null;

    FastDFSBean() throws IOException {
        try {
            ClassPathResource cpr = new ClassPathResource(clientName);
            ClientGlobal.init(cpr.getClassLoader().getResource(clientName).getPath());
            this.trackerClient = new TrackerClient();
            this.trackerServer = trackerClient.getConnection();
            this.storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    void close() throws IOException {
        if (storageServer != null) {
            this.storageServer.close();
        }
        if (trackerServer != null) {

            this.trackerServer.close();
        }

    }

    public TrackerClient getTrackerClient() {
        return trackerClient;
    }

    public StorageServer getStorageServer() {
        return storageServer;
    }

    public TrackerServer getTrackerServer() {
        return trackerServer;
    }

    public StorageClient getStorageClient() {
        return storageClient;
    }

    private static final Logger logger = LoggerFactory.getLogger(FastDFSBean.class);

}
