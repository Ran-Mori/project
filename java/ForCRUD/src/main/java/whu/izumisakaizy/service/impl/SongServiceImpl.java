package whu.izumisakaizy.service.impl;

import whu.izumisakaizy.entity.Song;
import whu.izumisakaizy.mapper.SongMapper;
import whu.izumisakaizy.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-11-26
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

}
