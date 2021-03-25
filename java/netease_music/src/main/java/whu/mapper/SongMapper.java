package whu.mapper;

import org.apache.ibatis.annotations.Mapper;
import whu.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Izumi Sakai
 * @since 2021-03-25
 */
@Mapper
public interface SongMapper extends BaseMapper<Song> {

}
