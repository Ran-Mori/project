package whu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import whu.constant.CommonResult;
import whu.constant.MyURLEncoder;
import whu.constant.ResultCode;
import whu.entity.Song;
import whu.mapper.SongMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Izumi Sakai
 * @since 2021-03-25
 */
@RestController
public class SongController {
    final String basePath = "/home/music/";

    @Autowired
    private SongMapper songMapper;

    //歌曲上传
    @PostMapping("/song/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取歌手名
        String singer = originalFilename.substring(0,originalFilename.indexOf('-') - 1);
        //获取歌曲名
        String songName = originalFilename.substring(originalFilename.indexOf("-") + 2, originalFilename.indexOf('.'));

        File localFile = new File(basePath + originalFilename);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.fail(ResultCode.UPLOAD_ERROR);
        }

        Song song = new Song();
        song.setName(songName);
        song.setSinger(singer);

        try {
            int insertNumber = songMapper.insert(song);
            if(insertNumber !=1)
                return CommonResult.fail(ResultCode.INSERT_RETURN_NOT_EQUAL_ONE);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.INSERT_ERROR);
        }
        if (localFile.exists()) {
            return CommonResult.success();
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return CommonResult.fail(ResultCode.UPLOAD_ERROR);
        }
    }

    //获取下载
    @GetMapping("/song/download")
    public CommonResult downloadFile(@RequestParam("songname") String songName,
                             @RequestParam("singer") String singer,
                             HttpServletResponse response) {
        if (songName == null || singer == null)
            return CommonResult.fail(ResultCode.PARA_NULL);

        //获取服务器上歌曲路径
        String filePath = basePath+singer+" - "+songName+".mp3";

        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                response.setContentType("application/force-download");
                response.setHeader("cache-control","max-age=31536000");
                //保证下载时文件名为指定文件名，避免中文文件名乱码
                response.addHeader("Content-Disposition", "attachment;fileName="
                        + MyURLEncoder.encode(file.getName(), "utf-8"));
                String fileName = file.getName();
                OutputStream os = response.getOutputStream();
                StreamUtils.copy(bis, os);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                e.printStackTrace();
            }
        }
        return CommonResult.success();
    }

    @GetMapping("/song/{id}")
    public CommonResult getSongById(@PathVariable("id") Integer id){
        Song song = null;
        try {
            song = songMapper.selectById(id);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SELECT);
        }
        if (song == null)
            return CommonResult.fail(ResultCode.SELECT_NULL);
        else
            return CommonResult.success().add("song",song);
    }
    //查询所有
    @GetMapping("/songs")
    public CommonResult getAllSongs(){
        List<Song> songs = null;
        try {
            songs = songMapper.selectList(null);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SELECT);
        }
        if (songs != null)
            return CommonResult.success().add("songs",songs);
        else
            return CommonResult.fail(ResultCode.SELECT_NULL);
    }

    //批量文件上传
    @PostMapping(path = "/song/uploads")
    public CommonResult uploadFiles(@RequestParam("file") MultipartFile[] files, HttpServletResponse response) {
        boolean result = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (MultipartFile file : files) {
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            File localFile = new File(basePath + originalFilename);
            try {
                //直接将文件拷贝到指定位置
                file.transferTo(localFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean exists = localFile.exists();
            if (!exists) {
                result = false;
                stringBuilder.append(file.getOriginalFilename()).append(" ");
            }
        }
        if (result) {
            return CommonResult.success();
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return CommonResult.fail(ResultCode.UPLOAD_ERROR);
    }
}

