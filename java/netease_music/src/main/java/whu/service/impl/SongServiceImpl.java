package whu.service.impl;

import whu.entity.Song;
import whu.mapper.SongMapper;
import whu.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Izumi Sakai
 * @since 2021-03-25
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

}
