package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j
public class UploadController {

    @GetMapping("/uploadForm")
    public void uploadForm(){

        log.info("upload form");
    }

    @PostMapping("/uploadFormAction")
    public void uploadFormPost(MultipartFile[] uploadFile, Model model){

        String uploadFolder = "/Users/kimminsu/upload/temp";

        for(MultipartFile multipartFile : uploadFile){

            log.info("--------------------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());

            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

            try{
                multipartFile.transferTo(saveFile);
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }   // end for
    }

    @GetMapping("uploadAjax")
    public void uploadAjax(){

        log.info("uplaod ajax");
    }

    // ajax success 를 위한 ResponseBody 어노테이션 추가
    @ResponseBody
    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){

        List<AttachFileDTO> list = new ArrayList<>();
        log.info("upload ajax post..........");

        String uploadFolder = "/Users/kimminsu/upload/temp";

        String uploadFolderPath = getFolder();

        // make folder
        File uploadPath = new File(uploadFolder, getFolder());
        log.info("upload path: " + uploadPath);

        if(uploadPath.exists() == false){
            uploadPath.mkdirs();
        }

        // make yyyy/MM/dd folder


        for(MultipartFile multipartFile : uploadFile){

            AttachFileDTO attachDTO = new AttachFileDTO();

            log.info("----------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());

            String uploadFileName = multipartFile.getOriginalFilename();

            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            log.info("Only File Name: " + uploadFileName);
            attachDTO.setFileName(uploadFileName);

            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;

//            File saveFile = new File(uploadFolder, uploadFileName);

            try{
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);

                attachDTO.setUuid(uuid.toString());
                attachDTO.setUploadPath(uploadFolderPath);


                //check image type file
                if(checkImageType(saveFile)){

                    attachDTO.setImage(true);

                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
                    thumbnail.close();
                }

                // add to List
                list.add(attachDTO);

            } catch(Exception e){
                log.error(e.getMessage());
            }
        }   // end for

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName){

        log.info("filename: " + fileName);
        File file = new File("/Users/kimminsu/upload/temp/" + fileName);

        log.info("file: " + file);

        ResponseEntity<byte[]> result = null;

        try{
            HttpHeaders header = new HttpHeaders();

            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch ( Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 다운로드
//    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    @ResponseBody
//    public ResponseEntity<Resource> downloadFile(String fileName){
//
//        log.info("download file: " + fileName);
//
//        Resource resource = new FileSystemResource("/Users/kimminsu/upload/temp/" + fileName);
//
//        log.info("resource: " + resource);
//
//        String resourceName = resource.getFilename();
//
//        HttpHeaders headers = new HttpHeaders();
//        try{
//            headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
//        } catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//
//        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
//    }


    // 다운로드 IE
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){

        log.info("download file: " + fileName);

        Resource resource = new FileSystemResource("/Users/kimminsu/upload/temp/" + fileName);

        if(resource.exists() == false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("resource: " + resource);

        String resourceName = resource.getFilename();

        // remove UUID
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

        HttpHeaders headers = new HttpHeaders();

        try{

            String downloadName = null;

            if(userAgent.contains("Trident")){
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");

            } else if(userAgent.contains("Edge")){
                log.info("edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");

                log.info("Edge name: " + downloadName);
            } else{

                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }

            log.info("downloadName: " + downloadName);

            headers.add("Content-Disposition", "attachment; filename=" + downloadName);
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    // delete Controller
    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type){

        log.info("deleteFile: " + fileName);

        File file;

        try{
            file = new File("/Users/kimminsu/upload/temp/" + URLDecoder.decode(fileName, "UTF-8"));

            file.delete();

            if(type.equals("image")){

                String largeFileName = file.getAbsolutePath().replace("s_", "");

                log.info("largeFileName: " + largeFileName);

                file = new File(largeFileName);

                file.delete();
            }

        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    private String getFolder(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file){
        try{
            String contentType = Files.probeContentType(file.toPath());

            return contentType.startsWith("image");
        } catch ( Exception e){
            e.printStackTrace();
        }

        return false;
    }

}