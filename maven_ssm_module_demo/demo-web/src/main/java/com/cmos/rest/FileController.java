package com.cmos.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件上传下载
 * @author HS
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    private static final String filePath = "/testfile/";

    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "file", required = true, dataTypeClass = MultipartFile.class)
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map fileUploadRequest(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "File Upload");
        String result = fileUpload(file);
        resultMap.put("result", result);
        return resultMap;
    }

    @ApiOperation(value = "文件下载")
    @ApiImplicitParam(name = "fileName", value = "文件名", required = true, dataTypeClass = String.class)
    @RequestMapping(value = "/fileDownload/{fileName}", method = RequestMethod.GET)
    public Map fileDownloadRequest(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "File Download");
        String resultStr = fileDownload(fileName, request, response);
        resultMap.put("result", resultStr);
        return resultMap;
    }

    public String fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return "fail";
        }
        String fileName = file.getOriginalFilename();
        File destFile = new File(filePath + fileName);

        if (!destFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("文件所处目录不存在，创建");
            //创建目录
            if (!destFile.getParentFile().mkdirs()) {
                System.out.println("创建文件所处目录失败！");
                return "fail";
            }
        }

        try {
            file.transferTo(destFile);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public String fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(filePath, fileName);
        //如果文件存在
        if (file.exists()) {
            //重置buffer
            response.resetBuffer();
            //设定编码为UTF-8
            response.setCharacterEncoding("UTF-8");
            //设置头部为下载信息
            response.setHeader("Content-type", "application/force-download;charset=UTF-8");
            try {
                // 设置文件名
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //各种流信息
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] buffer = new byte[1024];
                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                outputStream = response.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭各种流
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        return "success";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }//finally结束

        }//if结束
        return "fail";
    }//downloadFile结束

}
