package whu.izumisakaizy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import whu.izumisakaizy.consts.CommonResult;
import whu.izumisakaizy.consts.ResultCode;
import whu.izumisakaizy.entity.Song;
import whu.izumisakaizy.mapper.SongMapper;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-11-26
 */
@RestController
public class SongController {
    @Autowired
    private SongMapper songMapper;

    /**
     * @function 查询所有
     * @state 测试通过
     * */
    @RequestMapping(value = "/songs",method = RequestMethod.GET)
    public CommonResult selectSongs(){
        List<Song> songs =null;
        try {
            songs = songMapper.selectList(null);
            if (null!=songs && !songs.isEmpty())
                return CommonResult.success().add("songs",songs);
            else
                return CommonResult.fail(ResultCode.SELECT_NULL);
        }catch (Exception e){
                return CommonResult.fail(ResultCode.FAIL_TO_SELECT).add("error_message",e);
        }
    }

    /**
     * @function 根据ID查询一个
     * @state 测试通过
     * **/
    //注意@PathVariable 和 @RequestParam区别
    @RequestMapping(value = "/songs/{id}",method = RequestMethod.GET)
    public CommonResult selectSongByName(@PathVariable Integer id){
        if (null==id || id==0)
            return CommonResult.fail(ResultCode.PARA_NULL);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",id);
        Song song =null;
        try {
            song = songMapper.selectOne(wrapper);
            if (null!=song)
                return CommonResult.success().add("song",song);
            else
                return CommonResult.fail(ResultCode.SELECT_NULL);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SELECT).add("error_message",e);
        }
    }

    /**
     * @function 新增一个
     * @state 测试通过
     * **/
    //注意请求的格式要换成json不然会出错
    @RequestMapping(value = "/songs",method = RequestMethod.POST)
    public CommonResult insertSong(@RequestBody Song song){
        if (null==song)
            return CommonResult.fail(ResultCode.PARA_NULL);
        try {
            int i = songMapper.insert(song);
            if (i==1)
                return CommonResult.success();
            else
                return CommonResult.fail(ResultCode.INSERT_RETURN_NOT_EQUAL_ONE);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.INSERT_ERROR).add("error_message",e);
        }
    }

    /**
     * @function 根据id删除
     * @state 测试通过
     * **/
    @RequestMapping(value = "/songs/{id}",method = RequestMethod.DELETE)
    public CommonResult deleteSongById(@PathVariable Integer id){
        if (null==id || id==0)
            return CommonResult.fail(ResultCode.PARA_NULL);
        try {
            int i = songMapper.deleteById(id);
            if (i==1)
                return CommonResult.success();
            else
                return CommonResult.fail(ResultCode.DELETE_RETURN_NOT_EQUAL_ONE);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.DELETE_ERROR).add("error_message",e);
        }
    }

    /**
     * @function 修改
     * @state 测试通过
     * **/
    @RequestMapping(value = "/songs",method = RequestMethod.PUT)
    public CommonResult updateSong(@RequestBody Song song){
        if (null==song)
            return CommonResult.fail(ResultCode.PARA_NULL);
        try {
            int i = songMapper.updateById(song);
            if (i==1)
                return CommonResult.success();
            else
                return CommonResult.fail(ResultCode.UPDATE_RETURN_NOT_EQUAL_ONE);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.UPDATE_ERROR).add("error",e);
        }
    }
}

